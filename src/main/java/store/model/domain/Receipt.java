package store.model.domain;

import java.util.ArrayList;
import java.util.List;

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
}
