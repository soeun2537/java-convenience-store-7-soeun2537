package store.model.domain;

import static store.constant.ConvenienceConstant.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import store.model.PromotionManager;

public class Receipt {

    private final List<Stock> purchasedStocks;
    private final List<Stock> giftStocks;
    private boolean membership;

    private Receipt(List<Stock> purchasedStocks, List<Stock> giftStocks, boolean membership) {
        this.purchasedStocks = purchasedStocks;
        this.giftStocks = giftStocks;
        this.membership = membership;
    }

    public static Receipt createAndInitialize() {
        return new Receipt(new ArrayList<>(), new ArrayList<>(), false);
    }

    public void addPurchasedStock(Product product, Integer quantity) {
        addStock(purchasedStocks, product, quantity);
    }

    public void addGiftStock(Product product, Integer quantity) {
        addStock(giftStocks, product, quantity);
    }

    private void addStock(List<Stock> stocks, Product product, Integer quantity) {
        Optional<Stock> existingStock = findStockByProduct(stocks, product);

        if (existingStock.isPresent()) {
            existingStock.get().addQuantity(quantity);
            return;
        }
        Stock stock = Stock.of(product.getName(), product.getPrice(), quantity, product.getPromotionName());
        stocks.add(stock);
    }

    public void applyMembership() {
        membership = true;
    }

    public Integer calculateTotalPurchaseQuantity() {
        Integer totalPurchaseQuantity = 0;
        for (Stock purchasedStock : purchasedStocks) {
            totalPurchaseQuantity += purchasedStock.getQuantity();
        }
        return totalPurchaseQuantity;
    }

    public Integer calculateTotalPurchaseAmount() {
        Integer purchaseAmount = 0;
        for (Stock purchasedStock : purchasedStocks) {
            purchaseAmount += purchasedStock.getProductPrice() * purchasedStock.getQuantity();
        }
        return purchaseAmount;
    }

    public Integer calculatePromotionDiscount() {
        Integer promotionDiscount = 0;
        for (Stock giftStock : giftStocks) {
            promotionDiscount += giftStock.getProductPrice() * giftStock.getQuantity();
        }
        return promotionDiscount;
    }

    public Integer calculateMembershipDiscount() {
        if (!membership) {
            return 0;
        }

        int membershipDiscount = 0;
        PromotionManager promotionManager = PromotionManager.getInstance();

        for (Stock giftStock : giftStocks) {
            Promotion promotion = promotionManager.findPromotion(giftStock.getPromotionName()).get();
            membershipDiscount +=
                    giftStock.getProductPrice() * promotion.getRequiredPlusGiftCount() * giftStock.getQuantity();
        }
        int totalDiscount = (int) ((calculateTotalPurchaseAmount() - membershipDiscount) * MEMBERSHIP_DISCOUNT_RATE);
        return Math.min(totalDiscount, MAX_MEMBERSHIP_DISCOUNT);
    }

    public Integer calculateFinalAmount() {
        return calculateTotalPurchaseAmount() - calculatePromotionDiscount() - calculateMembershipDiscount();
    }

    private Optional<Stock> findStockByProduct(List<Stock> stocks, Product product) {
        for (Stock stock : stocks) {
            if (stock.getProductName().equals(product.getName())) {
                return Optional.of(stock);
            }
        }
        return Optional.empty();
    }

    public List<Stock> getPurchasedStocks() {
        return Collections.unmodifiableList(purchasedStocks);
    }

    public List<Stock> getGiftStocks() {
        return Collections.unmodifiableList(giftStocks);
    }
}
