package store.model;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    public Optional<Promotion> findPromotion(String promotionName) {
        for (Promotion promotion : promotions) {
            if (promotion.getName().equals(promotionName)) {
                return Optional.of(promotion);
            }
        }
        return Optional.empty();
    }

    public List<Promotion> getPromotions() {
        return Collections.unmodifiableList(promotions);
    }

    public boolean validateWithinPeriod(Promotion promotion) {
        LocalDate today = DateTimes.now().toLocalDate();
        LocalDate startDate = promotion.getStartDate();
        LocalDate endDate = promotion.getEndDate();

        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }
}
