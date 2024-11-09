package store.constant;

public enum PathConstant {

    PROMOTION_FILE_PATH("/promotions.md"),
    PRODUCT_FILE_PATH("/products.md"),
    ;

    private final String path;

    PathConstant(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
