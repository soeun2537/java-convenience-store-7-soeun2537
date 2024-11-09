package store.service.inventory;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.TestPathConstant;
import store.model.PromotionManager;
import store.model.StockManager;
import store.model.domain.Promotion;
import store.model.domain.Stock;

class InventoryServiceTest {

    private PromotionManager promotionManager;
    private StockManager stockManager;
    private InventoryService inventoryService;

    @BeforeEach
    void beforeEach() {
        promotionManager = PromotionManager.getInstance();
        stockManager = StockManager.getInstance();
        inventoryService = new InventoryService(promotionManager, stockManager);
    }

    @Test
    @DisplayName("파일을 통해 프로모션 설정")
    void setupPromotions() {
        // when
        inventoryService.setupPromotions(TestPathConstant.PROMOTION_FILE_PATH.getPath());

        // then
        List<Promotion> promotions = promotionManager.getPromotions();
        assertThat(promotions).isNotEmpty();

        Promotion promotion = promotions.get(0);
        assertThat(promotion.getName()).isEqualTo("탄산2+1");
        assertThat(promotion.getRequiredCount()).isEqualTo(2);
        assertThat(promotion.getGiftCount()).isEqualTo(1);
        assertThat(promotion.getStartDate()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(promotion.getEndDate()).isEqualTo(LocalDate.of(2024, 12, 31));
    }

    @Test
    @DisplayName("파일을 통해 재고 설정")
    void setupStocks() {
        // when
        inventoryService.setupStocks(TestPathConstant.PRODUCT_FILE_PATH.getPath());

        // then
        List<Stock> stocks = stockManager.getStocks();
        assertThat(stocks).isNotEmpty();

        Stock stock = stocks.get(0);
        assertThat(stock.getProductName()).isEqualTo("콜라");
        assertThat(stock.getProductPrice()).isEqualTo(1000);
        assertThat(stock.getQuantity()).isEqualTo(10);
        assertThat(stock.getPromotionName()).isEqualTo("탄산2+1");
    }
}
