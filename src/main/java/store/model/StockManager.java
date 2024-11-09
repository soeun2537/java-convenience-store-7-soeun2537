package store.model;

import java.util.ArrayList;
import java.util.List;
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
}
