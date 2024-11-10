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

class PromotionProcessorTest {

    private PromotionProcessor promotionProcessor;
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
        promotionProcessor = new PromotionProcessor(transactionHandler, promotionManager);

        stockManager.clearStocks();
        receiptManager.createReceipt();
        InventoryService inventoryService = new InventoryService(promotionManager, stockManager);
        inventoryService.setupPromotions(TestPathConstant.PROMOTION_FILE_PATH.getPath());
        inventoryService.setupStocks(TestPathConstant.PRODUCT_FILE_PATH.getPath());
    }

    @Test
    @DisplayName("프로모션 재고 > 요청 수량: 추가 수량 확인 필요 없음")
    void handlePromotion_checkAdditionalQuantity() {
        // given
        Stock promotionStock = stockManager.findPromotionAndGeneralStocks("콜라").getFirst();
        Integer quantity = 6;

        // when
        StatusDto statusDto = promotionProcessor.handlePromotion(promotionStock, quantity);

        // then
        Receipt receipt = receiptManager.get();

        assertThat(promotionStock.getQuantity()).isEqualTo(4);
        assertThat(receipt.getPurchasedStocks().size()).isEqualTo(1);
        assertThat(receipt.getPurchasedStocks().getFirst().getProductName()).isEqualTo(promotionStock.getProductName());
        assertThat(receipt.getPurchasedStocks().getFirst().getQuantity()).isEqualTo(6);
        assertThat(receipt.getGiftStocks().size()).isEqualTo(1);
        assertThat(receipt.getGiftStocks().getFirst().getProductName()).isEqualTo(promotionStock.getProductName());
        assertThat(receipt.getGiftStocks().getFirst().getQuantity()).isEqualTo(2);
        assertThat(statusDto.getStatus()).isEqualTo(Status.NO_ACTION_REQUIRED);
        assertThat(statusDto.getProduct()).isNull();
        assertThat(statusDto.getQuantity()).isEqualTo(0);
    }

    @Test
    @DisplayName("프로모션 재고 > 요청 수량: 추가 수량 확인 필요")
    void handlePromotion_dontCheckAdditionalQuantity() {
        // given
        Stock promotionStock = stockManager.findPromotionAndGeneralStocks("콜라").getFirst();
        Integer quantity = 8;

        // when
        StatusDto statusDto = promotionProcessor.handlePromotion(promotionStock, quantity);

        // then
        Receipt receipt = receiptManager.get();

        assertThat(promotionStock.getQuantity()).isEqualTo(2);
        assertThat(receipt.getPurchasedStocks().size()).isEqualTo(1);
        assertThat(receipt.getPurchasedStocks().getFirst().getProductName()).isEqualTo(promotionStock.getProductName());
        assertThat(receipt.getPurchasedStocks().getFirst().getQuantity()).isEqualTo(8);
        assertThat(receipt.getGiftStocks().size()).isEqualTo(1);
        assertThat(receipt.getGiftStocks().getFirst().getProductName()).isEqualTo(promotionStock.getProductName());
        assertThat(receipt.getGiftStocks().getFirst().getQuantity()).isEqualTo(2);
        assertThat(statusDto.getStatus()).isEqualTo(Status.ADDING_QUANTITY);
        assertThat(statusDto.getProductName()).isEqualTo(promotionStock.getProductName());
        assertThat(statusDto.getQuantity()).isEqualTo(1);

    }

    @Test
    @DisplayName("프로모션 재고 <= 요청 수량: 정가 결제 필요")
    void handlePromotion_insufficientPromotionStock() {
        // given
        Stock promotionStock = stockManager.findPromotionAndGeneralStocks("콜라").getFirst();
        Integer quantity = 10;

        // when
        StatusDto statusDto = promotionProcessor.handlePromotion(promotionStock, quantity);

        // then
        Receipt receipt = receiptManager.get();

        assertThat(promotionStock.getQuantity()).isEqualTo(1);
        assertThat(receipt.getPurchasedStocks().size()).isEqualTo(1);
        assertThat(receipt.getPurchasedStocks().getFirst().getProductName()).isEqualTo(promotionStock.getProductName());
        assertThat(receipt.getPurchasedStocks().getFirst().getQuantity()).isEqualTo(9);
        assertThat(receipt.getGiftStocks().size()).isEqualTo(1);
        assertThat(receipt.getGiftStocks().getFirst().getProductName()).isEqualTo(promotionStock.getProductName());
        assertThat(receipt.getGiftStocks().getFirst().getQuantity()).isEqualTo(3);
        assertThat(statusDto.getStatus()).isEqualTo(Status.REGULAR_PRICE_PAYMENT);
        assertThat(statusDto.getProductName()).isEqualTo(promotionStock.getProductName());
        assertThat(statusDto.getQuantity()).isEqualTo(1);
    }

    @Test
    @DisplayName("프로모션 날짜가 지난 경우 일반 결제")
    void handlePromotion_ExpiredPromotion() {
        // given
        Stock promotionStock = stockManager.findPromotionAndGeneralStocks("프로모션기간이지난상품").getFirst();
        Integer quantity = 5;

        // when
        StatusDto statusDto = promotionProcessor.handlePromotion(promotionStock, quantity);

        // then
        Receipt receipt = receiptManager.get();

        assertThat(promotionStock.getQuantity()).isEqualTo(3);
        assertThat(receipt.getPurchasedStocks().size()).isEqualTo(1);
        assertThat(receipt.getPurchasedStocks().getFirst().getProductName()).isEqualTo(promotionStock.getProductName());
        assertThat(receipt.getPurchasedStocks().getFirst().getQuantity()).isEqualTo(5);
        assertThat(receipt.getGiftStocks()).isEmpty();
        assertThat(statusDto.getStatus()).isEqualTo(Status.NO_ACTION_REQUIRED);
        assertThat(statusDto.getProduct()).isNull();
        assertThat(statusDto.getQuantity()).isEqualTo(0);
    }
}
