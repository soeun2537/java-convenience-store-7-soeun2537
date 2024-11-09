package store.model;

import static org.assertj.core.api.Assertions.*;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.domain.Promotion;

class PromotionManagerTest {

    @Test
    @DisplayName("PromotionManager 싱글톤 인스턴스 생성")
    void getInstance() {
        // when
        PromotionManager instance1 = PromotionManager.getInstance();
        PromotionManager instance2 = PromotionManager.getInstance();

        // then
        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("Promotion 추가 및 조회")
    void addAndFindPromotion_succes() {
        // given
        PromotionManager manager = PromotionManager.getInstance();
        Promotion promotion = Promotion.of("탄산2+1", 2, 1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));

        // when
        manager.addPromotion(promotion);
        Optional<Promotion> findPromotion = manager.findPromotion("탄산2+1");

        // then
        assertThat(findPromotion).isPresent();
        assertThat(findPromotion.get().getName()).isEqualTo("탄산2+1");
        assertThat(findPromotion.get().getRequiredCount()).isEqualTo(2);
        assertThat(findPromotion.get().getGiftCount()).isEqualTo(1);
        assertThat(findPromotion.get().getStartDate()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(findPromotion.get().getEndDate()).isEqualTo(LocalDate.of(2024, 12, 31));
    }

    @Test
    @DisplayName("Promotion 추가 및 조회: 없는 프로모션")
    void addAndFindPromotion_fail() {
        // given
        PromotionManager manager = PromotionManager.getInstance();

        // when
        Optional<Promotion> findPromotion = manager.findPromotion("없는 프로모션");

        // then
        assertThat(findPromotion).isNotPresent();
    }

    @Test
    @DisplayName("Promotion 조회: 불변성")
    void getStocks_immutable() {
        // given
        PromotionManager manager = PromotionManager.getInstance();
        Promotion promotion = Promotion.of("탄산2+1", 2, 1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));

        // when
        List<Promotion> promotions = manager.getPromotions();

        // then
        assertThatThrownBy(() -> promotions.add(promotion))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("프로모션 기간 내 여부를 확인: true")
    void validateWithinPeriod_true() {
        // given
        PromotionManager promotionManager = PromotionManager.getInstance();
        Promotion promotion = Promotion.of("프로모션", 2, 1,
                LocalDate.of(2024, 1, 1),
                DateTimes.now().toLocalDate().plusDays(1));

        // when
        boolean result = promotionManager.validateWithinPeriod(promotion);

        // then
        assertThat(result).isTrue();
    }
}
