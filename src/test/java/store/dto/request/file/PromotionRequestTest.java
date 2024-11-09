package store.dto.request.file;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionRequestTest {

    @Test
    @DisplayName("프로모션 Request DTO 생성 - 성공 테스트")
    void from() {
        // given
        String line = "탄산2+1,2,1,2024-01-01,2024-12-31";

        // when
        PromotionRequest request = PromotionRequest.from(line);

        // then
        assertThat(request.getName()).isEqualTo("탄산2+1");
        assertThat(request.getRequiredCount()).isEqualTo(2);
        assertThat(request.getGiftCount()).isEqualTo(1);
        assertThat(request.getStartDate()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(request.getEndDate()).isEqualTo(LocalDate.of(2024, 12, 31));
    }
}
