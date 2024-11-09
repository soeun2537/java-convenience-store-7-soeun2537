package store.util;

import store.dto.request.file.StockRequest;
import store.dto.request.file.PromotionRequest;

public class MarkdownFileParser {

    private MarkdownFileParser() {
    }

    public static PromotionRequest readPromotionFile(String promotionLine) {
        return PromotionRequest.from(promotionLine);
    }

    public static StockRequest readStockFile(String stockLine) {
        return StockRequest.from(stockLine);
    }
}
