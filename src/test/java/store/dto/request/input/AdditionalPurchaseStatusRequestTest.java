package store.dto.request.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdditionalPurchaseStatusRequestTest {

    @Test
    @DisplayName("추가 구매 여부 Request DTO 생성: Y 입력으로 true 변환 - 성공 테스트")
    void from_upperTrue() {
        // given
        String input = "Y";

        // then
        AdditionalPurchaseStatusRequest request = AdditionalPurchaseStatusRequest.from(input);

        // when
        assertThat(request.isAdditionalPurchaseStatus()).isTrue();
    }

    @Test
    @DisplayName("추가 구매 여부 Request DTO 생성: y 입력으로 true 변환 - 성공 테스트")
    void from_lowerTrue() {
        // given
        String input = "y";

        // then
        AdditionalPurchaseStatusRequest request = AdditionalPurchaseStatusRequest.from(input);

        // when
        assertThat(request.isAdditionalPurchaseStatus()).isTrue();
    }

    @Test
    @DisplayName("추가 구매 여부 Request DTO 생성: N 입력으로 false 변환 - 성공 테스트")
    void from_upperFalse() {
        // given
        String input = "N";

        // then
        AdditionalPurchaseStatusRequest request = AdditionalPurchaseStatusRequest.from(input);

        // when
        assertThat(request.isAdditionalPurchaseStatus()).isFalse();
    }

    @Test
    @DisplayName("추가 구매 여부 Request DTO 생성: n 입력으로 false 변환 - 성공 테스트")
    void from_lowerFalse() {
        // given
        String input = "n";

        // then
        AdditionalPurchaseStatusRequest request = AdditionalPurchaseStatusRequest.from(input);

        // when
        assertThat(request.isAdditionalPurchaseStatus()).isFalse();
    }

    @Test
    @DisplayName("추가 구매 여부 Request DTO 생성: 잘못된 입력 - 예외 테스트")
    void from_anotherCharacter() {
        // given
        String input = "A";

        // then & when
        assertThatThrownBy(() -> AdditionalPurchaseStatusRequest.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
