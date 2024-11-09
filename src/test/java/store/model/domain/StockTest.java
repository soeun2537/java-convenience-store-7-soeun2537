package store.model.domain;

import static org.assertj.core.api.Assertions.*;

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
}
