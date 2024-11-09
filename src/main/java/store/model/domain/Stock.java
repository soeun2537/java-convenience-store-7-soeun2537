package store.model.domain;

import store.util.CommonValidator;

public class Stock {

    private final Product product;
    private Integer quantity;

    private Stock(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static Stock of(String name, Integer price, Integer quantity, String promotionName) {
        Product product = Product.of(name, price, promotionName);
        return new Stock(product, quantity);
    }

    // TODO: 사용 파악
    public static Stock of(Product product, Integer quantity) {
        return new Stock(product, quantity);
    }

    public void addQuantity(Integer addedQuantity) {
        CommonValidator.validateNonNegative(addedQuantity);
        this.quantity += addedQuantity;
    }

    public void reduceQuantity(Integer reducedQuantity) {
        CommonValidator.validateNonNegative(reducedQuantity);
        this.quantity -= reducedQuantity;
        CommonValidator.validateNonNegative(this.quantity);
    }

    public Product getProduct() {
        return product;
    }

    public String getProductName() {
        return product.getName();
    }

    public Integer getProductPrice() {
        return product.getPrice();
    }

    public String getPromotionName() {
        return product.getPromotionName();
    }

    public Integer getQuantity() {
        return quantity;
    }
}
