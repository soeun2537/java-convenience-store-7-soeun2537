package store.dto.response;

import static store.constant.ConvenienceConstant.*;

import java.util.ArrayList;
import java.util.List;
import store.model.domain.Product;
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
        private final Integer quantity;

        private InnerStockResponse(String name, Integer price, String promotionName, Integer quantity) {
            this.name = name;
            this.price = price;
            this.promotionName = promotionName;
            this.quantity = quantity;
        }

        private static InnerStockResponse from(Stock stock) {
            Product product = stock.getProduct();
            String formattedPromotionName = formatPromotionName(product.getPromotionName());

            return new InnerStockResponse(
                    product.getName(),
                    product.getPrice(),
                    formattedPromotionName,
                    stock.getQuantity());
        }

        private static String formatPromotionName(String promotionName) {
            if (promotionName.equals(NO_PROMOTION.getContent())) {
                return EMPTY_STRING.getContent();
            }
            return promotionName;
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

        public Integer getQuantity() {
            return quantity;
        }
    }
}