package store.util;

import static store.constant.message.ErrorMessage.*;
import static store.constant.InputConstant.*;

public class CommonValidator {

    private CommonValidator() {
    }

    public static void validateNotNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(GENERAL_INVALID_INPUT.getMessage());
        }
    }

    public static void validateNumeric(String input) {
        if (!input.matches(NUMERIC_PATTERN.getContent())) {
            throw new IllegalArgumentException(GENERAL_INVALID_INPUT.getMessage());
        }
    }

    public static void validateYesOrNo(String input) {
        if (!input.matches(YES_NO_PATTERN.getContent())) {
            throw new IllegalArgumentException(GENERAL_INVALID_INPUT.getMessage());
        }
    }

    public static void validateDate(String input) {
        if (!input.matches(DATE_PATTERN.getContent())) {
            throw new IllegalArgumentException(GENERAL_INVALID_INPUT.getMessage());
        }
    }

    public static void validateNonNegative(Integer input) {
        if (input < 0) {
            throw new IllegalArgumentException(GENERAL_INVALID_INPUT.getMessage());
        }
    }
}
