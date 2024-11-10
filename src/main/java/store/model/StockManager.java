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
}
