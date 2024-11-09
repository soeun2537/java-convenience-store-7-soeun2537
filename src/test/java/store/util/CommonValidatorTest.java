package store.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommonValidatorTest {

    @Test
    @DisplayName("입력 문자열 유효성 검사 Not Null - 성공 테스트")
    void validateNotNull_success() {
        // given
        String input = "[콜라-2]";

        // when & then
        assertThatCode(() -> CommonValidator.validateNotNull(input))
                .doesNotThrowAnyException();
    }
}