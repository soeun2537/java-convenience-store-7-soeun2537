package store.model.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionTest {

    @Test
    @DisplayName("Promotion 생성 - 성공 테스트")
    void of() {
        // given
        String name = "탄산2+1";
        Integer requiredCount = 2;
        Integer giftCount = 1;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        // when
        Promotion promotion = Promotion.of(name, requiredCount, giftCount, startDate, endDate);

        // then
        assertThat(promotion.getName()).isEqualTo("탄산2+1");
        assertThat(promotion.getRequiredCount()).isEqualTo(2);
        assertThat(promotion.getGiftCount()).isEqualTo(1);
        assertThat(promotion.getStartDate()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(promotion.getEndDate()).isEqualTo(LocalDate.of(2024, 12, 31));
    }
}
