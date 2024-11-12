package store.service.convenience;

import static store.constant.message.ErrorMessage.NOT_FOUND_PROMOTION;

import store.model.PromotionManager;
import store.model.StockManager;
import store.model.domain.Product;
import store.model.domain.Promotion;
import store.model.domain.Receipt;
import store.model.ReceiptManager;

public class PurchaseTransactionHandler {

    private final StockManager stockManager;
    private final PromotionManager promotionManager;
    private final ReceiptManager receiptManager;

    public PurchaseTransactionHandler(StockManager stockManager,
                                      PromotionManager promotionManager,
                                      ReceiptManager receiptManager
    ) {
        this.stockManager = stockManager;
        this.promotionManager = promotionManager;
        this.receiptManager = receiptManager;
    }

    public void processWithGift(Product product, Integer quantity, boolean calculateGift) {
        Receipt receipt = receiptManager.get();
        Promotion promotion = promotionManager.findPromotion(product.getPromotionName())
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_PROMOTION.getMessage()));
        stockManager.reduceStockQuantity(product, quantity);
        receipt.addPurchasedStock(product, quantity, promotion);
        if (calculateGift) {
            receipt.addGiftStock(product, (quantity / (promotion.getRequiredPlusGiftCount())), promotion);
            return;
        }
        receipt.addGiftStock(product, quantity, promotion);
    }

    public void processWithoutGift(Product product, Integer quantity) {
        Receipt receipt = receiptManager.get();
        stockManager.reduceStockQuantity(product, quantity);
        receipt.addPurchasedStock(product, quantity, null);
    }
}
