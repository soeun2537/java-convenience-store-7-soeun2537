package store.service.inventory;

import java.time.LocalDate;
import java.util.List;
import store.dto.request.file.PromotionRequest;
import store.model.PromotionManager;
import store.model.StockManager;
import store.model.domain.Promotion;
import store.util.MarkdownFileParser;
import store.util.MarkdownFileReader;

public class InventoryService {

    private final PromotionManager promotionManager;
    private final StockManager stockManager;

    public InventoryService(PromotionManager promotionManager, StockManager stockManager) {
        this.promotionManager = promotionManager;
        this.stockManager = stockManager;
    }

    public void setupPromotions(String resourcePath) {
        List<String> promotionLines = MarkdownFileReader.readFile(resourcePath);
        for (String promotionLine : promotionLines) {
            PromotionRequest promotionRequest = MarkdownFileParser.readPromotionFile(promotionLine);
            Promotion promotion = createPromotion(promotionRequest);
            promotionManager.addPromotion(promotion);
        }
    }

    private Promotion createPromotion(PromotionRequest promotionRequest) {
        String name = promotionRequest.getName();
        Integer purchaseConditionCount = promotionRequest.getRequiredCount();
        Integer giftCount = promotionRequest.getGiftCount();
        LocalDate startDate = promotionRequest.getStartDate();
        LocalDate endDate = promotionRequest.getEndDate();

        return Promotion.of(name, purchaseConditionCount, giftCount, startDate, endDate);
    }
}
