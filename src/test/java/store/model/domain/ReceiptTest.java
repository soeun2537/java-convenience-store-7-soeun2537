package store.model.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.TestPathConstant;
import store.model.PromotionManager;
import store.model.StockManager;
import store.service.inventory.InventoryService;

class ReceiptTest {

    private Receipt receipt;

    @BeforeEach
    void beforeEach() {
        StockManager stockManager = StockManager.getInstance();
        PromotionManager promotionManager = PromotionManager.getInstance();
        stockManager.clearStocks();

        InventoryService inventoryService = new InventoryService(promotionManager, stockManager);
        inventoryService.setupPromotions(TestPathConstant.PROMOTION_FILE_PATH.getPath());
        inventoryService.setupStocks(TestPathConstant.PRODUCT_FILE_PATH.getPath());

        receipt = Receipt.createAndInitialize();
    }

    @Test
    @DisplayName("영수증 생성 및 초기화 확인")
    void createAndInitialize() {
        // when
        Receipt newReceipt = Receipt.createAndInitialize();

        // then
        assertThat(newReceipt.getPurchasedStocks()).isEmpty();
        assertThat(newReceipt.getGiftStocks()).isEmpty();
        assertThat(newReceipt.calculateMembershipDiscount()).isEqualTo(0);
    }

    @Test
    @DisplayName("구매한 상품을 추가")
    void addPurchasedStock() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 3;

        // when
        receipt.addPurchasedStock(product, quantity);

        // then
        Stock purchasedStock = receipt.getPurchasedStocks().getFirst();
        assertThat(purchasedStock.getProductName()).isEqualTo("콜라");
        assertThat(purchasedStock.getQuantity()).isEqualTo(quantity);
    }
}
