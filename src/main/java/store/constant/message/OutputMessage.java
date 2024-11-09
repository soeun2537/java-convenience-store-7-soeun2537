package store.constant.message;

public enum OutputMessage {
    START_GUIDANCE("안녕하세요. W편의점입니다."),
    EXISTING_STOCKS_GUIDANCE("현재 보유하고 있는 상품입니다."),
    EXISTING_STOCKS("- %s %,d원 %d개 %s"),
    PURCHASE_PRODUCTS_GUIDANCE("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    ADDING_QUANTITY_STATUS_GUIDANCE("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N))"),
    REGULAR_PRICE_PAYMENT_STATUS_GUIDANCE("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    MEMBERSHIP_APPLICATION_STATUS_GUIDANCE("멤버십 할인을 받으시겠습니까? (Y/N)"),
    ADDITIONAL_PURCHASE_STATUS_GUIDANCE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),

    RECEIPT_TITLE_HEADER("===========W 편의점============="),
    RECEIPT_PURCHASE_PRODUCTS_HEADER("상품명\t\t\t수량\t\t금액"),
    RECEIPT_GIFTS_HEADER("===========증\t정============="),
    RECEIPT_DIVISION_HEADER("=============================="),
    RECEIPT_TOTAL_PURCHASE_AMOUNT("총구매액\t\t\t%d\t%,10d"),
    RECEIPT_PROMOTION_DISCOUNT("행사할인\t%,22d"),
    RECEIPT_MEMBERSHIP_DISCOUNT("멤버십할인\t%,22d"),
    RECEIPT_FINAL_AMOUNT("내실돈\t%,22d"),
    RECEIPT_PURCHASE_PRODUCT_LINE("%-11s\t%d\t%,10d"),
    RECEIPT_GIFT_LINE("%-11s\t%d"),

    NEW_LINE("%n"),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
