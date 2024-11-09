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

    @Test
    @DisplayName("입력 문자열 유효성 검사 Not Null: 공백 - 예외 테스트")
    void validateNotNull_whiteSpace() {
        // given
        String input = " ";

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateNotNull(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Not Null: 빈 문자열 - 예외 테스트")
    void validateNotNull_emptyString() {
        // given
        String input = "";

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateNotNull(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Not Null: null - 예외 테스트")
    void validateNotNull_null() {
        // given
        String input = null;

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateNotNull(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Numeric - 성공 테스트")
    void validateNumeric_success() {
        // given
        String input = "12";

        // when & then
        assertThatCode(() -> CommonValidator.validateNumeric(input))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Numeric: 문자 - 예외 테스트")
    void validateNumeric_string() {
        // given
        String input = "a";

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateNumeric(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Numeric: 특수 문자 - 예외 테스트")
    void validateNumeric_specialCharacter() {
        // given
        String input = "$";

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateNumeric(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Numeric: 개행 문자 - 예외 테스트")
    void validateNumeric_newLine() {
        // given
        String input = "\n";

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateNumeric(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Numeric: 빈 문자열 - 예외 테스트")
    void validateNumeric_emptyString() {
        // given
        String input = "";

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateNumeric(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}