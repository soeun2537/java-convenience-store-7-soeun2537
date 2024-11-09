package store.dto.request.input;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}