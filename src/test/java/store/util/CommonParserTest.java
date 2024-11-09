package store.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommonParserTest {

    @Test
    @DisplayName("문자열의 대괄호를 제거하는지 확인")
    void replaceSquareBrackets() {
        // given
        String input = "[콜라-3]";

        // when
        String replacedInput = CommonParser.replaceSquareBrackets(input);

        // then
        assertThat(replacedInput).isEqualTo("콜라-3");
    }
}