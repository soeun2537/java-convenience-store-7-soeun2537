package store.service.convenience;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.TestPathConstant;
import store.dto.request.input.PurchaseProductsRequest;
import store.dto.response.StocksResponse;
import store.dto.server.StatusDto;
import store.model.PromotionManager;
import store.model.ReceiptManager;
import store.model.Status;
import store.model.StockManager;
import store.model.domain.Receipt;
import store.service.inventory.InventoryService;

class ConvenienceServiceTest {

    private ConvenienceService convenienceService;
    private ReceiptManager receiptManager;

    @BeforeEach
    void beforeEach() {
        StockManager stockManager = StockManager.getInstance();
        PromotionManager promotionManager = PromotionManager.getInstance();
        receiptManager = new ReceiptManager();
        convenienceService = new ConvenienceService(promotionManager, stockManager, receiptManager);

        receiptManager.createReceipt();
        stockManager.clearStocks();
        InventoryService inventoryService = new InventoryService(promotionManager, stockManager);
        inventoryService.setupPromotions(TestPathConstant.PROMOTION_FILE_PATH.getPath());
        inventoryService.setupStocks(TestPathConstant.PRODUCT_FILE_PATH.getPath());
    }

    @Test
    @DisplayName("재고 응답을 생성")
    void createStocksResponse() {
        // when
        StocksResponse stocksResponse = convenienceService.createStocksResponse();

        // then
        assertThat(stocksResponse.getStocksResponse()).isNotEmpty();
        assertThat(stocksResponse.getStocksResponse().getFirst().getName()).isEqualTo("콜라");
    }

    @Test
    @DisplayName("영수증을 생성")
    void createReceipt() {
        // when
        convenienceService.createReceipt();

        // then
        Receipt receipt = receiptManager.get();
        assertThat(receipt.getPurchasedStocks()).isEmpty();
        assertThat(receipt.getGiftStocks()).isEmpty();
    }

    @Test
    @DisplayName("상품 구매: NO_ACTION_REQUIRED")
    void purchaseProducts_noActionRequired() {
        // given
        String input = "[콜라-3]";
        PurchaseProductsRequest request = PurchaseProductsRequest.from(input);

        // when
        StatusDto statusDto = convenienceService.purchaseProducts(request).getFirst();

        // then
        assertThat(statusDto.getStatus()).isEqualTo(Status.NO_ACTION_REQUIRED);
        assertThat(statusDto.getProduct()).isNull();
        assertThat(statusDto.getQuantity()).isEqualTo(0);
    }

    @Test
    @DisplayName("상품 구매: ADDING_QUANTITY")
    void purchaseProducts_addingQuantity() {
        // given
        String input = "[콜라-2]";
        PurchaseProductsRequest request = PurchaseProductsRequest.from(input);

        // when
        StatusDto statusDto = convenienceService.purchaseProducts(request).getFirst();

        // then
        assertThat(statusDto.getStatus()).isEqualTo(Status.ADDING_QUANTITY);
        assertThat(statusDto.getProductName()).isEqualTo("콜라");
        assertThat(statusDto.getQuantity()).isEqualTo(1);
    }
}
