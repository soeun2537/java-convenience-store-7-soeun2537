package store.model.domain;

public class Product {

    private String name;
    private Integer price;
    private String promotionName;

    private Product(String name, Integer price, String promotionName) {
        this.name = name;
        this.price = price;
        this.promotionName = promotionName;
    }

    public static Product of(String name, Integer price, String promotionName) {
        return new Product(name, price, promotionName);
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getPromotionName() {
        return promotionName;
    }
}
