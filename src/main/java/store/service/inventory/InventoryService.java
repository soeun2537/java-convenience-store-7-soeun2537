package store.service.inventory;

import static store.constant.ConvenienceConstant.NO_PROMOTION;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import store.dto.request.file.PromotionRequest;
import store.dto.request.file.StockRequest;
import store.model.PromotionManager;
import store.model.StockManager;
import store.model.domain.Promotion;
import store.model.domain.Stock;
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

    public void setupStocks(String resourcePath) {
        List<String> productLines = MarkdownFileReader.readFile(resourcePath);
        for (String productLine : productLines) {
            StockRequest stockRequest = MarkdownFileParser.readStockFile(productLine);
            Stock stock = createStock(stockRequest);
            stockManager.addStock(stock);
        }
        addMissingGeneralStock();
    }

    private Promotion createPromotion(PromotionRequest promotionRequest) {
        String name = promotionRequest.getName();
        Integer purchaseConditionCount = promotionRequest.getRequiredCount();
        Integer giftCount = promotionRequest.getGiftCount();
        LocalDate startDate = promotionRequest.getStartDate();
        LocalDate endDate = promotionRequest.getEndDate();

        return Promotion.of(name, purchaseConditionCount, giftCount, startDate, endDate);
    }

    private Stock createStock(StockRequest stockRequest) {
        String name = stockRequest.getName();
        Integer price = stockRequest.getPrice();
        Integer quantity = stockRequest.getQuantity();
        String promotionName = stockRequest.getPromotionName();

        return Stock.of(name, price, quantity, promotionName);
    }

    private void addMissingGeneralStock() {
        List<Stock> addToStocks = new ArrayList<>();
        for (Stock stock : stockManager.getStocks()) {
            List<Stock> findStocks = stockManager.findPromotionAndGeneralStocks(stock.getProductName());
            if (stockManager.existsPromotionStock(stock.getProductName()) && findStocks.size() == 1) {
                Stock promotionStock = findStocks.getFirst();
                Stock generalStock = Stock.of(
                        promotionStock.getProductName(), promotionStock.getProductPrice(), 0, NO_PROMOTION);
                addToStocks.add(generalStock);
            }
        }
        for (Stock stock : addToStocks) {
            stockManager.addStock(stock);
        }
    }
}
