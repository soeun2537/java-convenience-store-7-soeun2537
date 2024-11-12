package store.dto.request.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AddingQuantityStatusRequestTest {

    @Test
    @DisplayName("추가 수량 여부 Request DTO 생성: Y 입력으로 true 변환 - 성공 테스트")
    void from_upperTrue() {
        // given
        String input = "Y";

        // then
        AddingQuantityStatusRequest request = AddingQuantityStatusRequest.from(input);

        // when
        assertThat(request.isAddingQuantityStatus()).isTrue();
    }

    @Test
    @DisplayName("추가 수량 여부 Request DTO 생성: y 입력으로 true 변환 - 성공 테스트")
    void from_lowerTrue() {
        // given
        String input = "y";

        // then
        AddingQuantityStatusRequest request = AddingQuantityStatusRequest.from(input);

        // when
        assertThat(request.isAddingQuantityStatus()).isTrue();
    }

    @Test
    @DisplayName("추가 수량 여부 Request DTO 생성: N 입력으로 false 변환 - 성공 테스트")
    void from_upperFalse() {
        // given
        String input = "N";

        // then
        AddingQuantityStatusRequest request = AddingQuantityStatusRequest.from(input);

        // when
        assertThat(request.isAddingQuantityStatus()).isFalse();
    }

    @Test
    @DisplayName("추가 수량 여부 Request DTO 생성: n 입력으로 false 변환 - 성공 테스트")
    void from_lowerFalse() {
        // given
        String input = "n";

        // then
        AddingQuantityStatusRequest request = AddingQuantityStatusRequest.from(input);

        // when
        assertThat(request.isAddingQuantityStatus()).isFalse();
    }

    @Test
    @DisplayName("추가 수량 여부 Request DTO 생성: 잘못된 입력 - 예외 테스트")
    void from_anotherCharacter() {
        // given
        String input = "A";

        // then & when
        assertThatThrownBy(() -> AddingQuantityStatusRequest.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
