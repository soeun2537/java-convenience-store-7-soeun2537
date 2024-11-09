package store.model;

import java.util.ArrayList;
import java.util.List;
import store.model.domain.Promotion;

public class PromotionManager {

    private List<Promotion> promotions;

    private PromotionManager() {
        this.promotions = new ArrayList<>();
    }

    private static class PromotionManagerHolder {
        private static final PromotionManager INSTANCE = new PromotionManager();
    }

    public static PromotionManager getInstance() {
        return PromotionManagerHolder.INSTANCE;
    }

    public void addPromotion(Promotion promotion) {
        promotions.add(promotion);
    }
}
