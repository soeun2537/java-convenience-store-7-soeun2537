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

    @Test
    @DisplayName("입력 문자열 유효성 검사 Yes No: true - 성공 테스트")
    void validateYesOrNo_true() {
        // given
        String upperInput = "Y";
        String lowerInput = "y";

        // when & then
        assertThatCode(() -> CommonValidator.validateYesOrNo(upperInput))
                .doesNotThrowAnyException();
        assertThatCode(() -> CommonValidator.validateYesOrNo(lowerInput))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Yes No: false - 성공 테스트")
    void validateYesOrNo_false() {
        // given
        String upperInput = "N";
        String lowerInput = "n";

        // when & then
        assertThatCode(() -> CommonValidator.validateYesOrNo(upperInput))
                .doesNotThrowAnyException();
        assertThatCode(() -> CommonValidator.validateYesOrNo(lowerInput))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Yes No: 다른 문자 - 예외 테스트")
    void validateYesOrNo_anotherCharacter() {
        // given
        String upperInput = "A";
        String lowerInput = "a";

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateYesOrNo(upperInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThatThrownBy(() -> CommonValidator.validateYesOrNo(lowerInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Yes No: 중복 문자 - 예외 테스트")
    void validateYesOrNo_duplicate() {
        // given
        String input = "Yy";

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateYesOrNo(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Date - 성공 테스트")
    void validateDate() {
        // given
        String input = "2024-11-09";

        // when & then
        assertThatCode(() -> CommonValidator.validateDate(input))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Date: 다른 문자 - 예외 테스트")
    void validateDate_anotherCharacter() {
        // given
        String input = "aaa";

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("입력 문자열 유효성 검사 Date: 잘못된 형식 - 예외 테스트")
    void validateDate_invalidFormat() {
        // given
        String input = "2024/11/09";

        // when & then
        assertThatThrownBy(() -> CommonValidator.validateDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
