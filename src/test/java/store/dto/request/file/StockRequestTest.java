package store.dto.request.file;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockRequestTest {

    @Test
    @DisplayName("재고 Request DTO 생성 - 성공 테스트")
    void from() {
        // given
        String line = "콜라,1000,10,탄산2+1";

        // when
        StockRequest request = StockRequest.from(line);

        // then
        assertThat(request.getName()).isEqualTo("콜라");
        assertThat(request.getPrice()).isEqualTo(1000);
        assertThat(request.getQuantity()).isEqualTo(10);
        assertThat(request.getPromotionName()).isEqualTo("탄산2+1");
    }
}
