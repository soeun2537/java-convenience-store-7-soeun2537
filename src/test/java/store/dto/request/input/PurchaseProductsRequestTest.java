package store.dto.request.input;

import static org.assertj.core.api.Assertions.*;
import static store.constant.message.ErrorMessage.*;
import static store.constant.message.ErrorMessage.NOT_FOUND_PRODUCT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.StockManager;
import store.model.domain.Stock;

class PurchaseProductsRequestTest {

    @Test
    @DisplayName("구매할 상품 Request DTO 생성: 상품 한개 - 성공 테스트")
    void from_oneProduct() {
        // given
        String input = "[콜라-2]]";

        // when
        PurchaseProductsRequest request = PurchaseProductsRequest.from(input);

        // then
        assertThat(request.getPurchaseProductsRequests()).hasSize(1);
        assertThat(request.getPurchaseProductsRequests().get(0).getProductName()).isEqualTo("콜라");
        assertThat(request.getPurchaseProductsRequests().get(0).getProductQuantity()).isEqualTo(2);
    }

    @Test
    @DisplayName("구매할 상품 Request DTO 생성: 상품 두개 - 성공 테스트")
    void from_twoProduct() {
        // given
        String input = "[콜라-2],[사이다-3]";

        // when
        PurchaseProductsRequest request = PurchaseProductsRequest.from(input);

        // then
        assertThat(request.getPurchaseProductsRequests()).hasSize(2);
        assertThat(request.getPurchaseProductsRequests().get(0).getProductName()).isEqualTo("콜라");
        assertThat(request.getPurchaseProductsRequests().get(0).getProductQuantity()).isEqualTo(2);
        assertThat(request.getPurchaseProductsRequests().get(1).getProductName()).isEqualTo("사이다");
        assertThat(request.getPurchaseProductsRequests().get(1).getProductQuantity()).isEqualTo(3);
    }

    @Test
    @DisplayName("구매할 상품 Request DTO 생성: 빈 문자열- 예외 테스트")
    void from_emptyString() {
        // given
        String input = "";

        // when & then
        assertThatThrownBy(() -> PurchaseProductsRequest.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("구매할 상품 Request DTO 생성: 공백 - 예외 테스트")
    void from_whileSpace() {
        // given
        String input = " ";

        // when & then
        assertThatThrownBy(() -> PurchaseProductsRequest.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("구매할 상품 Request DTO 생성: 숫자 아닌 값 - 예외 테스트")
    void from_invalidNumber() {
        // given
        String input = "[콜라-abc]";

        // when & then
        assertThatThrownBy(() -> PurchaseProductsRequest.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("구매할 상품 Request DTO 생성: 존재하지 않는 상품 - 예외 테스트")
    void from_validateNonExistentProduct() {
        // given
        String input = "[존재하지않는상품-3]";

        // when & then
        assertThatThrownBy(() -> PurchaseProductsRequest.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_FOUND_PRODUCT.getMessage());
    }

    @Test
    @DisplayName("구매할 상품 Request DTO 생성: 재고 초과 - 예외 테스트")
    void from_validateSufficientStocksQuantity() {
        // given
        StockManager stockManager = StockManager.getInstance();
        Stock stock = Stock.of("바나나", 1000, 4, "null");
        stockManager.addStock(stock);
        String input = "[바나나-5]";

        // when & then
        assertThatThrownBy(() -> PurchaseProductsRequest.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INSUFFICIENT_STOCK.getMessage());
    }
}