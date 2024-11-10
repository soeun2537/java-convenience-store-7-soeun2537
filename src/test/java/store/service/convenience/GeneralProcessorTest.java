package store.service.convenience;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.TestPathConstant;
import store.dto.server.StatusDto;
import store.model.PromotionManager;
import store.model.ReceiptManager;
import store.model.Status;
import store.model.StockManager;
import store.model.domain.Receipt;
import store.model.domain.Stock;
import store.service.inventory.InventoryService;

class GeneralProcessorTest {

    private GeneralProcessor generalProcessor;
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
        generalProcessor = new GeneralProcessor(transactionHandler);

        stockManager.clearStocks();
        receiptManager.createReceipt();
        InventoryService inventoryService = new InventoryService(promotionManager, stockManager);
        inventoryService.setupPromotions(TestPathConstant.PROMOTION_FILE_PATH.getPath());
        inventoryService.setupStocks(TestPathConstant.PRODUCT_FILE_PATH.getPath());
    }

    @Test
    @DisplayName("일반 상품 구매")
    void handleGeneral() {
        // given
        Stock generalStock = stockManager.findPromotionAndGeneralStocks("에너지바").getFirst();
        Integer quantity = 3;

        // when
        StatusDto statusDto = generalProcessor.handleGeneral(generalStock, quantity);

        // then
        Receipt receipt = receiptManager.get();

        assertThat(generalStock.getQuantity()).isEqualTo(2);
        assertThat(receipt.getPurchasedStocks().size()).isEqualTo(1);
        assertThat(receipt.getPurchasedStocks().getFirst().getProductName()).isEqualTo(generalStock.getProductName());
        assertThat(receipt.getPurchasedStocks().getFirst().getQuantity()).isEqualTo(3);
        assertThat(receipt.getGiftStocks()).isEmpty();
        assertThat(statusDto.getStatus()).isEqualTo(Status.NO_ACTION_REQUIRED);
        assertThat(statusDto.getProduct()).isNull();
        assertThat(statusDto.getQuantity()).isEqualTo(0);
    }
}
