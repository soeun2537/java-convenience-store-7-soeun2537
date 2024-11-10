package store.model.domain;

import static store.constant.ConvenienceConstant.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import store.util.CommonValidator;

public class Receipt {

    private final List<InnerReceipt> purchasedStocks;
    private final List<InnerReceipt> giftStocks;
    private boolean membership;

    private Receipt(List<InnerReceipt> purchasedStocks, List<InnerReceipt> giftStocks, boolean membership) {
        this.purchasedStocks = purchasedStocks;
        this.giftStocks = giftStocks;
        this.membership = membership;
    }

    public static Receipt createAndInitialize() {
        return new Receipt(new ArrayList<>(), new ArrayList<>(), false);
    }

    public void addPurchasedStock(Product product, Integer quantity, Promotion promotion) {
        addStock(purchasedStocks, product, quantity, promotion);
    }

    public void addGiftStock(Product product, Integer quantity, Promotion promotion) {
        addStock(giftStocks, product, quantity, promotion);
    }

    private void addStock(List<InnerReceipt> stocks, Product product, Integer quantity, Promotion promotion) {
        Optional<InnerReceipt> existingStock = findStockByProduct(stocks, product);

        if (existingStock.isPresent()) {
            existingStock.get().addQuantity(quantity);
            return;
        }

        InnerReceipt innerReceipt = InnerReceipt.of(product, quantity, promotion);
        stocks.add(innerReceipt);
    }

    public void applyMembership() {
        membership = true;
    }

    public Integer calculateTotalPurchaseQuantity() {
        Integer totalPurchaseQuantity = 0;
        for (InnerReceipt purchasedStock : purchasedStocks) {
            totalPurchaseQuantity += purchasedStock.getQuantity();
        }
        return totalPurchaseQuantity;
    }

    public Integer calculateTotalPurchaseAmount() {
        Integer purchaseAmount = 0;
        for (InnerReceipt purchasedStock : purchasedStocks) {
            purchaseAmount += purchasedStock.getProductPrice() * purchasedStock.getQuantity();
        }
        return purchaseAmount;
    }

    public Integer calculatePromotionDiscount() {
        Integer promotionDiscount = 0;
        for (InnerReceipt giftStock : giftStocks) {
            promotionDiscount += giftStock.getProductPrice() * giftStock.getQuantity();
        }
        return promotionDiscount;
    }

    public Integer calculateMembershipDiscount() {
        if (!membership) {
            return 0;
        }

        int membershipDiscount = 0;

        for (InnerReceipt giftStock : giftStocks) {
            membershipDiscount +=
                    giftStock.getProductPrice() * giftStock.getRequiredPlusGiftCount() * giftStock.getQuantity();
        }
        int totalDiscount = (int) ((calculateTotalPurchaseAmount() - membershipDiscount) * MEMBERSHIP_DISCOUNT_RATE);
        return Math.min(totalDiscount, MAX_MEMBERSHIP_DISCOUNT);
    }

    public Integer calculateFinalAmount() {
        return calculateTotalPurchaseAmount() - calculatePromotionDiscount() - calculateMembershipDiscount();
    }

    private Optional<InnerReceipt> findStockByProduct(List<InnerReceipt> stocks, Product product) {
        for (InnerReceipt stock : stocks) {
            if (stock.getProductName().equals(product.getName())) {
                return Optional.of(stock);
            }
        }
        return Optional.empty();
    }

    public List<InnerReceipt> getPurchasedStocks() {
        return Collections.unmodifiableList(purchasedStocks);
    }

    public List<InnerReceipt> getGiftStocks() {
        return Collections.unmodifiableList(giftStocks);
    }

    public static class InnerReceipt {

        private Product product;
        private Integer quantity;
        private Promotion promotion;

        private InnerReceipt(Product product, Integer quantity, Promotion promotion) {
            this.product = product;
            this.quantity = quantity;
            this.promotion = promotion;
        }

        public static InnerReceipt of(Product product, Integer quantity, Promotion promotion) {
            return new InnerReceipt(product, quantity, promotion);
        }

        public void addQuantity(Integer addQuantity) {
            CommonValidator.validateNonNegative(addQuantity);
            this.quantity += addQuantity;
        }

        public Product getProduct() {
            return product;
        }

        public String  getProductName() {
            return product.getName();
        }

        public Integer getProductPrice() {
            return product.getPrice();
        }

        public Integer getQuantity() {
            return quantity;
        }

        public Promotion getPromotion() {
            return promotion;
        }

        public String getPromotionName() {
            return promotion.getName();
        }

        public Integer getRequiredPlusGiftCount() {
            return promotion.getRequiredPlusGiftCount();
        }
    }
}
