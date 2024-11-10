package store.model;

import store.model.domain.Receipt;

public class ReceiptManager {

    private Receipt receipt;

    public void createReceipt() {
        receipt = Receipt.createAndInitialize();
    }
}
