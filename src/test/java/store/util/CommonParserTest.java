package store.util;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
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

    @Test
    @DisplayName("문자열을 정수로 변환하는지 확인")
    void convertStringToInteger() {
        // given
        String input = "2024";

        // when
        Integer convertedInput = CommonParser.convertStringToInteger(input);

        // then
        assertThat(convertedInput).isEqualTo(2024);
    }

    @Test
    @DisplayName("입력 문자열을 boolean으로 변환하는지 확인: true")
    void parseBoolean_true() {
        // given
        String upperInput = "Y";
        String lowerInput = "y";

        // when
        boolean parseUpperBoolean = CommonParser.parseBoolean(upperInput);
        boolean parseLowerBoolean = CommonParser.parseBoolean(lowerInput);

        // then
        assertThat(parseUpperBoolean).isTrue();
        assertThat(parseLowerBoolean).isTrue();
    }

    @Test
    @DisplayName("입력 문자열을 boolean으로 변환하는지 확인: false")
    void parseBoolean_false() {
        // given
        String upperInput = "N";
        String lowerInput = "n";

        // when
        boolean parseUpperBoolean = CommonParser.parseBoolean(upperInput);
        boolean parseLowerBoolean = CommonParser.parseBoolean(lowerInput);

        // then
        assertThat(parseUpperBoolean).isFalse();
        assertThat(parseLowerBoolean).isFalse();
    }

    @Test
    @DisplayName("입력 문자열을 날짜로 변환하는지 확인")
    void parseDate() {
        // given
        String input = "2024-11-09";
        LocalDate expectedDate = LocalDate.of(2024, 11, 9);

        // when
        LocalDate parseDate = CommonParser.parseDate(input);

        // then
        assertThat(parseDate).isEqualTo(expectedDate);
    }
}
