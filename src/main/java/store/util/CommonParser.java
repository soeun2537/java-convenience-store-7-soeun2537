package store.util;

import static store.constant.InputConstant.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CommonParser {

    private CommonParser() {
    }

    public static String replaceSquareBrackets(String input) {
        return input.replaceAll(SQUARE_BRACKETS_PATTERN.getContent(), EMPTY_STRING.getContent());
    }

    public static List<String> separateBySeparator(String input, String separator) {
        return Arrays.asList(input.split(separator));
    }

    public static Integer convertStringToInteger(String input) {
        return Integer.parseInt(input);
    }

    public static boolean parseBoolean(String input) {
        return TRUE_STRING.getContent().equalsIgnoreCase(input);
    }

    public static LocalDate parseDate(String input) {
        List<String> separatedDate = separateBySeparator(input, DATE_SEPARATOR.getContent());
        return LocalDate.of(
                convertStringToInteger(separatedDate.get(0)),
                convertStringToInteger(separatedDate.get(1)),
                convertStringToInteger(separatedDate.get(2))
        );
    }
}
