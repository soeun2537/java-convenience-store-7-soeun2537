package store.model;

import static store.constant.ConvenienceConstant.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import store.model.domain.Product;
import store.model.domain.Stock;

public class StockManager {

    private List<Stock> stocks;

    private StockManager() {
        this.stocks = new ArrayList<>();
    }

    private static class ProductManagerHolder {
        private static final StockManager INSTANCE = new StockManager();
    }

    public static StockManager getInstance() {
        return ProductManagerHolder.INSTANCE;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public List<Stock> getStocks() {
        return Collections.unmodifiableList(stocks);
    }

    public void clearStocks() {
        stocks.clear();
    }

    public Set<String> getProductNames() {
        Set<String> productNames = new HashSet<>();
        for (Stock stock : stocks) {
            productNames.add(stock.getProductName());
        }
        return productNames;
    }

    public void reduceStockQuantity(Product product, Integer quantity) {
        List<Stock> stocks = findPromotionAndGeneralStocks(product.getName());

        if (existsPromotionStock(product.getName()) && stocks.size() > 1) {
            reducePromotionAndGeneralStock(stocks, quantity);
            return;
        }
        reduceSingleStock(stocks, quantity);
    }

    private void reducePromotionAndGeneralStock(List<Stock> stocks, Integer quantity) {
        Stock promotionStock = stocks.get(0);
        Stock generalStock = stocks.get(1);
        Integer promotionQuantity = promotionStock.getQuantity();

        if (promotionQuantity >= quantity) {
            promotionStock.reduceQuantity(quantity);
            return;
        }
        promotionStock.reduceQuantity(promotionQuantity);
        generalStock.reduceQuantity(quantity - promotionQuantity);
    }

    private void reduceSingleStock(List<Stock> stocks, Integer quantity) {
        stocks.get(0).reduceQuantity(quantity);
    }

    public boolean existsPromotionStock(String productName) {
        for (Stock stock : findPromotionAndGeneralStocks(productName)) {
            if (!stock.getPromotionName().equals(NO_PROMOTION)) {
                return true;
            }
        }
        return false;
    }

    public List<Stock> findPromotionAndGeneralStocks(String productName) {
        List<Stock> findStocks = new ArrayList<>();
        for (Stock stock : stocks) {
            if (stock.getProductName().equals(productName)) {
                findStocks.add(stock);
            }
        }
        return findStocks;
    }
}
