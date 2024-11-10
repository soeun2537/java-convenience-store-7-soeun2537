package store.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
}
