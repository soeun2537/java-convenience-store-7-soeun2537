package store.service.convenience;

import store.dto.server.StatusDto;
import store.model.domain.Stock;

public class GeneralProcessor {

    private final PurchaseTransactionHandler transactionHandler;

    public GeneralProcessor(PurchaseTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    public StatusDto handleGeneral(Stock generalStock, Integer requestQuantity) {
        transactionHandler.processWithoutGift(generalStock.getProduct(), requestQuantity);
        return StatusDto.setNoActionRequiredStatus();
    }
}
