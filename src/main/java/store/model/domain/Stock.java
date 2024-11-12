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

    public void addQuantity(Integer addQuantity) {
        CommonValidator.validateNonNegative(addQuantity);
        this.quantity += addQuantity;
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
