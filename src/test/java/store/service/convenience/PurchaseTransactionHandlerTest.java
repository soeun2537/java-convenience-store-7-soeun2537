package store.service.convenience;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.TestPathConstant;
import store.model.PromotionManager;
import store.model.ReceiptManager;
import store.model.StockManager;
import store.model.domain.Product;
import store.model.domain.Receipt;
import store.model.domain.Stock;
import store.service.inventory.InventoryService;

class PurchaseTransactionHandlerTest {

    private StockManager stockManager;
    private PromotionManager promotionManager;
    private ReceiptManager receiptManager;
    private PurchaseTransactionHandler transactionHandler;

    @BeforeEach
    void beforeEach() {
        stockManager = StockManager.getInstance();
        promotionManager = PromotionManager.getInstance();
        receiptManager = new ReceiptManager();
        transactionHandler = new PurchaseTransactionHandler(stockManager, promotionManager, receiptManager);

        InventoryService inventoryService = new InventoryService(promotionManager, stockManager);
        receiptManager.createReceipt();
        inventoryService.setupPromotions(TestPathConstant.PROMOTION_FILE_PATH.getPath());
        inventoryService.setupStocks(TestPathConstant.PRODUCT_FILE_PATH.getPath());
    }

    @Test
    @DisplayName("프로모션 포함 상품 구매: 증정 계산 true")
    void processWithGift_calculateGift_true() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 4;

        // when
        transactionHandler.processWithGift(product, quantity, true);

        // then
        Receipt receipt = receiptManager.get();
        Stock promotionStock = stockManager.findPromotionAndGeneralStocks(product.getName()).getFirst();

        assertThat(promotionStock.getQuantity()).isEqualTo(6);
        assertThat(receipt.getPurchasedStocks().size()).isEqualTo(1);
        assertThat(receipt.getPurchasedStocks().getFirst().getProductName()).isEqualTo("콜라");
        assertThat(receipt.getPurchasedStocks().getFirst().getQuantity()).isEqualTo(4);
        assertThat(receipt.getGiftStocks().size()).isEqualTo(1);
        assertThat(receipt.getGiftStocks().getFirst().getProductName()).isEqualTo("콜라");
        assertThat(receipt.getGiftStocks().getFirst().getQuantity()).isEqualTo(1);
    }

    @Test
    @DisplayName("프로모션 포함 상품 구매: 증정 계산 false")
    void processWithGift_calculateGift_false() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 4;

        // when
        transactionHandler.processWithGift(product, quantity, false);

        // then
        Receipt receipt = receiptManager.get();
        Stock promotionStock = stockManager.findPromotionAndGeneralStocks(product.getName()).getFirst();

        assertThat(promotionStock.getQuantity()).isEqualTo(6);
        assertThat(receipt.getPurchasedStocks().size()).isEqualTo(1);
        assertThat(receipt.getPurchasedStocks().getFirst().getProductName()).isEqualTo("콜라");
        assertThat(receipt.getPurchasedStocks().getFirst().getQuantity()).isEqualTo(4);
        assertThat(receipt.getGiftStocks().size()).isEqualTo(1);
        assertThat(receipt.getGiftStocks().getFirst().getProductName()).isEqualTo("콜라");
        assertThat(receipt.getGiftStocks().getFirst().getQuantity()).isEqualTo(4);
    }

    @Test
    @DisplayName("프로모션 제외 상품 구매")
    void processWithoutGift() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 4;

        // when
        transactionHandler.processWithoutGift(product, quantity);

        // then
        Receipt receipt = receiptManager.get();
        Stock promotionStock = stockManager.findPromotionAndGeneralStocks(product.getName()).getFirst();

        assertThat(promotionStock.getQuantity()).isEqualTo(6);
        assertThat(receipt.getPurchasedStocks().size()).isEqualTo(1);
        assertThat(receipt.getPurchasedStocks().getFirst().getProductName()).isEqualTo("콜라");
        assertThat(receipt.getPurchasedStocks().getFirst().getQuantity()).isEqualTo(4);
        assertThat(receipt.getGiftStocks()).isEmpty();
    }
}
