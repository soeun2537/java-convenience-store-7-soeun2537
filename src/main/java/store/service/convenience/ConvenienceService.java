package store.service.convenience;

import store.dto.response.StocksResponse;
import store.model.PromotionManager;
import store.model.StockManager;
import store.model.ReceiptManager;

public class ConvenienceService {

    private final StockManager stockManager;
    private final ReceiptManager receiptManager;
    private final PromotionProcessor promotionProcessor;
    private final GeneralProcessor generalProcessor;

    public ConvenienceService(PromotionManager promotionManager,
                              StockManager stockManager,
                              ReceiptManager receiptManager
    ) {
        this.stockManager = stockManager;
        this.receiptManager = receiptManager;
        PurchaseTransactionHandler transactionHandler =
                new PurchaseTransactionHandler(stockManager, promotionManager, receiptManager);
        this.promotionProcessor = new PromotionProcessor(transactionHandler, promotionManager);
        this.generalProcessor = new GeneralProcessor(transactionHandler);
    }

    public StocksResponse createStocksResponse() {
        return StocksResponse.from(stockManager.getStocks());
    }

    public void createReceipt() {
        receiptManager.createReceipt();
    }
}
