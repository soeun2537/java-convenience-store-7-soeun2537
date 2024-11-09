package store.dto.request.input;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MembershipApplicationStatusRequestTest {

    @Test
    @DisplayName("멤버십 적용 여부 Request DTO 생성: Y 입력으로 true 변환 - 성공 테스트")
    void from_upperTrue() {
        // given
        String input = "Y";

        // then
        MembershipApplicationStatusRequest request = MembershipApplicationStatusRequest.from(input);

        // when
        assertThat(request.isMembershipApplicationStatus()).isTrue();
    }

    @Test
    @DisplayName("멤버십 적용 여부 Request DTO 생성: y 입력으로 true 변환 - 성공 테스트")
    void from_lowerTrue() {
        // given
        String input = "y";

        // then
        MembershipApplicationStatusRequest request = MembershipApplicationStatusRequest.from(input);

        // when
        assertThat(request.isMembershipApplicationStatus()).isTrue();
    }

    @Test
    @DisplayName("멤버십 적용 여부 Request DTO 생성: N 입력으로 false 변환 - 성공 테스트")
    void from_upperFalse() {
        // given
        String input = "N";

        // then
        MembershipApplicationStatusRequest request = MembershipApplicationStatusRequest.from(input);

        // when
        assertThat(request.isMembershipApplicationStatus()).isFalse();
    }

    @Test
    @DisplayName("멤버십 적용 여부 Request DTO 생성: n 입력으로 false 변환 - 성공 테스트")
    void from_lowerFalse() {
        // given
        String input = "n";

        // then
        MembershipApplicationStatusRequest request = MembershipApplicationStatusRequest.from(input);

        // when
        assertThat(request.isMembershipApplicationStatus()).isFalse();
    }

    @Test
    @DisplayName("멤버십 적용 여부 Request DTO 생성: 잘못된 입력 - 예외 테스트")
    void from_anotherCharacter() {
        // given
        String input = "A";

        // then & when
        assertThatThrownBy(() -> MembershipApplicationStatusRequest.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
