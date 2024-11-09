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

    @Test
    @DisplayName("Stock 생성: Product, quantity 속성 - 성공 테스트")
    void of_ProductAndQuantityAttribute() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 10;

        // when
        Stock stock = Stock.of(product, quantity);

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
}
