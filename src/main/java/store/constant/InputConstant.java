package store.constant;

public enum InputConstant {

    SQUARE_BRACKETS_PATTERN("[\\[\\]]"),
    NUMERIC_PATTERN("\\d+"),
    YES_NO_PATTERN("[YyNn]"),
    DATE_PATTERN("^\\d{4}-\\d{2}-\\d{2}$"),
    PRODUCT_SEPARATOR(","),
    DATE_SEPARATOR("-"),
    TRUE_STRING("Y"),
    EMPTY_STRING(""),
    ;

    private final String content;

    InputConstant(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
