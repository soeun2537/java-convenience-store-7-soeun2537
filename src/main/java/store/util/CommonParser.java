package store.util;

import static store.constant.InputConstant.*;

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
}
