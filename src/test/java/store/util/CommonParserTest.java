package store.util;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
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

    @Test
    @DisplayName("문자열을 분리하는지 확인")
    void separateBySeparator() {
        // given
        String input = "콜라-3";
        String separator = "-";

        // when
        List<String> separatedInput = CommonParser.separateBySeparator(input, separator);

        // then
        assertThat(separatedInput).hasSize(2);
        assertThat(separatedInput.get(0)).isEqualTo("콜라");
        assertThat(separatedInput.get(1)).isEqualTo("3");
    }
}
