package store.dto.response;

import static store.constant.ConvenienceConstant.*;
import static store.constant.message.OutputMessage.*;

import java.util.ArrayList;
import java.util.List;
import store.model.domain.Stock;

public class StocksResponse {

    private final List<InnerStockResponse> stocksResponse;

    private StocksResponse(List<InnerStockResponse> stocksResponse) {
        this.stocksResponse = stocksResponse;
    }

    public static StocksResponse from(List<Stock> stocks) {
        List<InnerStockResponse> newStocksResponse = new ArrayList<>();
        for (Stock stock : stocks) {
            InnerStockResponse newStockResponse = InnerStockResponse.from(stock);
            newStocksResponse.add(newStockResponse);
        }
        return new StocksResponse(newStocksResponse);
    }

    public List<InnerStockResponse> getStocksResponse() {
        return stocksResponse;
    }

    public static class InnerStockResponse {

        private final String name;
        private final Integer price;
        private final String promotionName;
        private final String quantity;

        private InnerStockResponse(String name, Integer price, String promotionName, String quantity) {
            this.name = name;
            this.price = price;
            this.promotionName = promotionName;
            this.quantity = quantity;
        }

        private static InnerStockResponse from(Stock stock) {
            return new InnerStockResponse(
                    stock.getProductName(),
                    stock.getProductPrice(),
                    formatPromotionName(stock.getPromotionName()),
                    formatQuantity(stock.getQuantity()));
        }

        private static String formatPromotionName(String promotionName) {
            if (promotionName.equals(NO_PROMOTION)) {
                return EMPTY_STRING;
            }
            return promotionName;
        }

        private static String formatQuantity(Integer quantity) {
            if (quantity <= 0) {
                return NOT_IN_STOCK;
            }
            return String.format(STOCKS_COUNT.getMessage(), quantity);
        }

        public String getName() {
            return name;
        }

        public Integer getPrice() {
            return price;
        }

        public String getPromotionName() {
            return promotionName;
        }

        public String getQuantity() {
            return quantity;
        }
    }
}