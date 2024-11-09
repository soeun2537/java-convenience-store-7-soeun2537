package store.constant;

public enum TestPathConstant {

    PROMOTION_FILE_PATH("/test-promotions.md"),
    PRODUCT_FILE_PATH("/test-products.md"),
    ;

    private final String path;

    TestPathConstant(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
