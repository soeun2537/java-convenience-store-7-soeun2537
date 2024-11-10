package store.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.domain.Product;
import store.model.domain.Promotion;
import store.model.domain.Receipt;

class ReceiptManagerTest {

    private ReceiptManager receiptManager;
    private PromotionManager promotionManager;

    @BeforeEach
    void beforeEach() {
        receiptManager = new ReceiptManager();
        promotionManager = PromotionManager.getInstance();
    }

    @Test
    @DisplayName("영수증 생성")
    void createReceipt() {
        // when
        receiptManager.createReceipt();

        // then
        Receipt receipt = receiptManager.get();
        assertThat(receipt.getPurchasedStocks()).isEmpty();
        assertThat(receipt.getGiftStocks()).isEmpty();
    }

    @Test
    @DisplayName("영수증 가져오기")
    void get() {
        // given
        receiptManager.createReceipt();
        Receipt receipt = receiptManager.get();
        Promotion promotion = promotionManager.findPromotion("탄산2+1").get();
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer purchaseQuantity = 3;
        Integer giftQuantity = 1;
        receipt.addPurchasedStock(product, purchaseQuantity, promotion);
        receipt.addGiftStock(product, giftQuantity, promotion);

        // when
        Receipt newReceipt = receiptManager.get();

        // then
        assertThat(newReceipt).isSameAs(receipt);
    }
}
