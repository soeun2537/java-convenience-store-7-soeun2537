package store.dto.server;

import store.model.Status;
import store.model.domain.Product;

public class StatusDto {

    private final Status status;
    private final Product product;
    private final Integer quantity;

    private StatusDto(Status status, Product product, Integer quantity) {
        this.status = status;
        this.product = product;
        this.quantity = quantity;
    }

    public static StatusDto setAddingQuantityStatus(Product product, Integer quantity) {
        return new StatusDto(Status.ADDING_QUANTITY, product, quantity);
    }

    public static StatusDto setRegularStatusPaymentStatus(Product product, Integer quantity) {
        return new StatusDto(Status.REGULAR_PRICE_PAYMENT, product, quantity);
    }

    public static StatusDto setNoActionRequiredStatus() {
        return new StatusDto(Status.NO_ACTION_REQUIRED, null, 0);
    }

    public Status getStatus() {
        return status;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return product.getName();
    }
}
