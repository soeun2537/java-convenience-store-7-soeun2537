package store.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.domain.Stock;

class StockManagerTest {

    private StockManager stockManager;

    @BeforeEach
    void beforeEach() {
        stockManager = StockManager.getInstance();
        stockManager.clearStocks();
    }

    @Test
    @DisplayName("StockManager 싱글톤 인스턴스 생성")
    void getInstance() {
        // when
        StockManager instance1 = StockManager.getInstance();
        StockManager instance2 = StockManager.getInstance();

        // then
        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("Stock 추가 및 조회")
    void addAndGetStock() {
        // given
        Stock stock1 = Stock.of("콜라", 1000, 10, "탄산2+1");
        Stock stock2 = Stock.of("사이다", 1200, 5, "null");

        // when
        stockManager.addStock(stock1);
        stockManager.addStock(stock2);
        List<Stock> stocks = stockManager.getStocks();

        // then
        assertThat(stocks).hasSize(2);
        assertThat(stocks).containsExactly(stock1, stock2);
    }

    @Test
    @DisplayName("Stock 조회: 불변성")
    void getStocks_immutable() {
        // given
        Stock stock = Stock.of("콜라", 1000, 10, "탄산2+1");

        // when
        List<Stock> stocks = stockManager.getStocks();

        // then
        assertThatThrownBy(() -> stocks.add(stock))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("StockManager 초기화")
    void clearStocks() {
        // given
        Stock stock1 = Stock.of("콜라", 1000, 10, "탄산2+1");
        Stock stock2 = Stock.of("사이다", 1200, 5, "null");
        stockManager.addStock(stock1);
        stockManager.addStock(stock2);

        // when
        stockManager.clearStocks();

        // then
        assertThat(stockManager.getStocks()).isEmpty();
    }

    @Test
    @DisplayName("저장되어 있는 중복되지 않는 상품 이름 가져오기")
    void getProductNames() {
        // given
        Stock stock1 = Stock.of("콜라", 1000, 10, "탄산2+1");
        Stock stock2 = Stock.of("콜라", 1000, 5, "탄산2+1");
        Stock stock3 = Stock.of("사이다", 1200, 5, "null");
        stockManager.addStock(stock1);
        stockManager.addStock(stock2);
        stockManager.addStock(stock3);

        // when
        Set<String> productNames = stockManager.getProductNames();

        // then
        assertThat(productNames).hasSize(2);
        assertThat(productNames).containsOnly(stock1.getProductName(), stock3.getProductName());
    }

    @Test
    @DisplayName("재고 차감: 프로모션 재고 우선 차감")
    void reduceStockQuantity() {
        Stock stock1 = Stock.of("콜라", 1000, 3, "탄산2+1");
        Stock stock2 = Stock.of("콜라", 1000, 5, "null");
        stockManager.addStock(stock1);
        stockManager.addStock(stock2);

        // when
        stockManager.reduceStockQuantity(stock1.getProduct(), 7);

        // then
        List<Stock> stocks = stockManager.findPromotionAndGeneralStocks(stock1.getProductName());
        assertThat(stocks.getFirst().getQuantity()).isEqualTo(0);
        assertThat(stocks.getLast().getQuantity()).isEqualTo(1);
    }
}