package store.dto.response;

import java.util.ArrayList;
import java.util.List;
import store.model.domain.Stock;
import store.model.domain.Receipt;

public class ReceiptResponse {

    private final List<InnerReceiptStockResponse> purchaseStocks;
    private final List<InnerReceiptStockResponse> giftStocks;
    private final Integer totalPurchaseQuantity;
    private final Integer totalPurchaseAmount;
    private final Integer promotionDiscount;
    private final Integer membershipDiscount;
    private final Integer finalAmount;

    private ReceiptResponse(List<InnerReceiptStockResponse> purchaseStocks,
                            List<InnerReceiptStockResponse> giftStocks,
                            Integer totalPurchaseQuantity,
                            Integer totalPurchaseAmount,
                            Integer promotionDiscount,
                            Integer membershipDiscount,
                            Integer finalAmount
    ) {
        this.purchaseStocks = purchaseStocks;
        this.giftStocks = giftStocks;
        this.totalPurchaseQuantity = totalPurchaseQuantity;
        this.totalPurchaseAmount = totalPurchaseAmount;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
        this.finalAmount = finalAmount;
    }

    public static ReceiptResponse from(Receipt receipt) {
        List<InnerReceiptStockResponse> purchasedStocks = convertToInnerReceiptStocks(receipt.getPurchasedStocks());
        List<InnerReceiptStockResponse> giftStocks = convertToInnerReceiptStocks(receipt.getGiftStocks());

        return new ReceiptResponse(
                purchasedStocks,
                giftStocks,
                receipt.calculateTotalPurchaseQuantity(),
                receipt.calculateTotalPurchaseAmount(),
                receipt.calculatePromotionDiscount(),
                receipt.calculateMembershipDiscount(),
                receipt.calculateFinalAmount()
        );
    }

    private static List<InnerReceiptStockResponse> convertToInnerReceiptStocks(List<Stock> stocks) {
        List<InnerReceiptStockResponse> convertToInnerReceiptStocks = new ArrayList<>();
        for (Stock stock : stocks) {
            convertToInnerReceiptStocks.add(InnerReceiptStockResponse.from(stock));
        }
        return convertToInnerReceiptStocks;
    }

    public List<InnerReceiptStockResponse> getPurchaseStocks() {
        return purchaseStocks;
    }

    public List<InnerReceiptStockResponse> getGiftStocks() {
        return giftStocks;
    }

    public Integer getTotalPurchaseQuantity() {
        return totalPurchaseQuantity;
    }

    public Integer getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public Integer getPromotionDiscount() {
        return promotionDiscount;
    }

    public Integer getMembershipDiscount() {
        return membershipDiscount;
    }

    public Integer getFinalAmount() {
        return finalAmount;
    }

    public static class InnerReceiptStockResponse {

        private final String productName;
        private final Integer quantity;
        private final Integer totalPrice;

        private InnerReceiptStockResponse(String productName, Integer quantity, Integer totalPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
        }

        public static InnerReceiptStockResponse from(Stock stock) {
            return new InnerReceiptStockResponse(
                    stock.getProductName(),
                    stock.getQuantity(),
                    stock.getProductPrice() * stock.getQuantity()
            );
        }

        public String getProductName() {
            return productName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public Integer getTotalPrice() {
            return totalPrice;
        }
    }
}
