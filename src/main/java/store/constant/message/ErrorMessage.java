package store.constant.message;

public enum ErrorMessage {

    INVALID_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    NOT_FOUND_PRODUCT("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    INSUFFICIENT_STOCK("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    GENERAL_INVALID_INPUT("잘못된 입력입니다. 다시 입력해 주세요."),
    NOT_FOUND_FILE("파일이 존재하지 않습니다."),
    INVALID_FILE_VALUE("파일의 값이 유효하지 않습니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
