package store.service.convenience;

import static store.constant.message.ErrorMessage.NOT_FOUND_PROMOTION;

import store.dto.server.StatusDto;
import store.model.PromotionManager;
import store.model.domain.Product;
import store.model.domain.Promotion;
import store.model.domain.Stock;

public class PromotionProcessor {

    private final PurchaseTransactionHandler transactionHandler;
    private final PromotionManager promotionManager;

    public PromotionProcessor(PurchaseTransactionHandler transactionHandler, PromotionManager promotionManager) {
        this.transactionHandler = transactionHandler;
        this.promotionManager = promotionManager;
    }

    public StatusDto handlePromotion(Stock stock, Integer quantity) {
        Promotion promotion = promotionManager.findPromotion(stock.getPromotionName())
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_PROMOTION.getMessage()));

        if (!promotionManager.validateWithinPeriod(promotion)) {
            return handleExpiredPromotion(stock.getProduct(), quantity);
        }
        if (stock.getQuantity() > quantity) {
            return processSufficientPromotion(stock, promotion, quantity);
        }
        return processInsufficientPromotion(stock, promotion, quantity);
    }

    private StatusDto handleExpiredPromotion(Product product, Integer quantity) {
        transactionHandler.processWithoutGift(product, quantity);
        return StatusDto.setNoActionRequiredStatus();
    }

    private StatusDto processSufficientPromotion(Stock stock, Promotion promotion, Integer quantity) {
        transactionHandler.processWithGift(stock.getProduct(), quantity, true);
        if (quantity % (promotion.getRequiredPlusGiftCount()) == promotion.getRequiredCount()) {
            return StatusDto.setAddingQuantityStatus(stock.getProduct(), promotion.getGiftCount());
        }
        return StatusDto.setNoActionRequiredStatus();
    }

    private StatusDto processInsufficientPromotion(Stock stock, Promotion promotion, Integer quantity) {
        Integer notApplyProductQuantity =
                quantity - (stock.getQuantity() / (promotion.getRequiredPlusGiftCount()))
                        * (promotion.getRequiredPlusGiftCount());
        transactionHandler.processWithGift(stock.getProduct(), quantity - notApplyProductQuantity, true);
        return StatusDto.setRegularStatusPaymentStatus(stock.getProduct(), notApplyProductQuantity);
    }

    public void processAddingQuantity(StatusDto statusDto) {
        transactionHandler.processWithGift(statusDto.getProduct(), statusDto.getQuantity(), false);
    }

    public void processRegularPricePayment(StatusDto statusDto) {
        transactionHandler.processWithoutGift(statusDto.getProduct(), statusDto.getQuantity());
    }
}
