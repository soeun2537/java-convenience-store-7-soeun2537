package store.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.domain.Product;
import store.model.domain.Receipt;

class ReceiptManagerTest {

    private ReceiptManager receiptManager;

    @BeforeEach
    void beforeEach() {
        receiptManager = new ReceiptManager();
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
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer purchaseQuantity = 3;
        Integer giftQuantity = 1;
        receipt.addPurchasedStock(product, purchaseQuantity);
        receipt.addGiftStock(product, giftQuantity);

        // when
        Receipt newReceipt = receiptManager.get();

        // then
        assertThat(newReceipt).isSameAs(receipt);
    }
}
