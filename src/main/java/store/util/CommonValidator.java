package store.util;

import static store.constant.ErrorMessage.*;

public class CommonValidator {

    private CommonValidator() {
    }

    public static void validateNotNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(GENERAL_INVALID_INPUT.getMessage());
        }
    }
}
