package store.model.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockTest {

    @Test
    @DisplayName("Stock 생성: 모든 속성 - 성공 테스트")
    void of_allAttribute() {
        // given
        String productName = "콜라";
        Integer productPrice = 1000;
        Integer productQuantity = 10;
        String promotionName = "탄산2+1";

        // when
        Stock stock = Stock.of(productName, productPrice, productQuantity, promotionName);

        // then
        assertThat(stock.getProductName()).isEqualTo("콜라");
        assertThat(stock.getProductPrice()).isEqualTo(1000);
        assertThat(stock.getQuantity()).isEqualTo(10);
        assertThat(stock.getPromotionName()).isEqualTo("탄산2+1");
    }

    @Test
    @DisplayName("Stock 재고 추가 - 성공 테스트")
    void addQuantity() {
        // given
        Stock stock = Stock.of("콜라", 1000, 10, "탄산2+1");
        Integer addQuantity = 3;

        // when
        stock.addQuantity(addQuantity);

        // then
        assertThat(stock.getQuantity()).isEqualTo(13);
    }

    @Test
    @DisplayName("재고 추가: 0 - 성공 테스트")
    void addQuantity_zero() {
        // given
        Stock stock = Stock.of("콜라", 1000, 10, "탄산2+1");
        Integer addQuantity = 0;

        // when
        stock.addQuantity(addQuantity);

        // then
        assertThat(stock.getQuantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("재고 추가: 음수 - 예외 테스트")
    void addQuantity_negative() {
        // given
        Stock stock = Stock.of("콜라", 1000, 10, "탄산2+1");
        Integer addQuantity = -5;

        // when & then
        assertThatThrownBy(() -> stock.addQuantity(addQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("재고 차감 - 성공 테스트")
    void reduceQuantity() {
        // given
        Stock stock = Stock.of("콜라", 1000, 10, "탄산2+1");
        Integer addQuantity = 3;

        // when
        stock.reduceQuantity(addQuantity);

        // then
        assertThat(stock.getQuantity()).isEqualTo(7);
    }

    @Test
    @DisplayName("재고 차감: 0 - 성공 테스트")
    void reduceQuantity_zero() {
        // given
        Stock stock = Stock.of("콜라", 1000, 10, "탄산2+1");
        Integer addQuantity = 0;

        // when
        stock.reduceQuantity(addQuantity);

        // then
        assertThat(stock.getQuantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("재고 차감: 음수 - 예외 테스트")
    void reduceQuantity_negative() {
        // given
        Stock stock = Stock.of("콜라", 1000, 10, "탄산2+1");
        Integer addQuantity = -5;

        // when & then
        assertThatThrownBy(() -> stock.reduceQuantity(addQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("재고 차감: 초과 - 예외 테스트")
    void reduceQuantity_excessiveQuantity() {
        // given
        Stock stock = Stock.of("콜라", 1000, 10, "탄산2+1");
        Integer addQuantity = -11;

        // when & then
        assertThatThrownBy(() -> stock.reduceQuantity(addQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
