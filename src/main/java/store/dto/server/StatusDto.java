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
