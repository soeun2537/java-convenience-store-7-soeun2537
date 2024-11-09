package store.util;

import static store.constant.InputConstant.*;

public class CommonParser {

    private CommonParser() {
    }

    public static String replaceSquareBrackets(String input) {
        return input.replaceAll(SQUARE_BRACKETS_PATTERN.getContent(), EMPTY_STRING.getContent());
    }
}
