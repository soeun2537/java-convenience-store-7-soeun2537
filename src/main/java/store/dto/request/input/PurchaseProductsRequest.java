package store.dto.request.input;

import static store.constant.InputConstant.*;
import static store.constant.message.ErrorMessage.INSUFFICIENT_STOCK;
import static store.constant.message.ErrorMessage.NOT_FOUND_PRODUCT;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import store.model.StockManager;
import store.util.CommonParser;
import store.util.CommonValidator;

public class PurchaseProductsRequest {

    private final List<InnerPurchaseProductRequest> purchaseProductsRequests;

    private PurchaseProductsRequest(List<InnerPurchaseProductRequest> purchaseProductsRequests) {
        this.purchaseProductsRequests = purchaseProductsRequests;
    }

    public static PurchaseProductsRequest from(String input) {
        CommonValidator.validateNotNull(input);

        List<InnerPurchaseProductRequest> newPurchaseProductRequests = new ArrayList<>();
        List<String> separatedProducts = CommonParser.separateBySeparator(input, PRODUCT_SEPARATOR.getContent());
        for (String separatedProduct : separatedProducts) {
            String replacedProduct = CommonParser.replaceSquareBrackets(separatedProduct);
            InnerPurchaseProductRequest newPurchaseProductRequest = InnerPurchaseProductRequest.from(replacedProduct);
            newPurchaseProductRequests.add(newPurchaseProductRequest);
        }
        return new PurchaseProductsRequest(newPurchaseProductRequests);
    }

    public List<InnerPurchaseProductRequest> getPurchaseProductsRequests() {
        return purchaseProductsRequests;
    }

    public static class InnerPurchaseProductRequest {

        private final String productName;
        private final Integer productQuantity;

        private InnerPurchaseProductRequest(String productName, Integer productQuantity) {
            this.productName = productName;
            this.productQuantity = productQuantity;
        }

        private static InnerPurchaseProductRequest from(String separatedProduct) {
            List<String> productEntry = CommonParser.separateBySeparator(separatedProduct, "-");
            String productName = parseProductName(productEntry);
            Integer productQuantity = parseProductQuantity(productEntry);
            validateNonExistentProduct(productName);
            return new InnerPurchaseProductRequest(productName, productQuantity);
        }

        private static String parseProductName(List<String> productEntry) {
            return productEntry.getFirst();
        }

        private static Integer parseProductQuantity(List<String> productEntry) {
            String productQuantity = productEntry.getLast();
            CommonValidator.validateNumeric(productQuantity);
            return CommonParser.convertStringToInteger(productQuantity);
        }

        private static void validateNonExistentProduct(String productName) {
            StockManager stockManager = StockManager.getInstance();
            Set<String> existingProductNames = stockManager.getProductNames();
            if (!existingProductNames.contains(productName)) {
                throw new IllegalArgumentException(NOT_FOUND_PRODUCT.getMessage());
            }
        }

        public String getProductName() {
            return productName;
        }

        public Integer getProductQuantity() {
            return productQuantity;
        }
    }
}
