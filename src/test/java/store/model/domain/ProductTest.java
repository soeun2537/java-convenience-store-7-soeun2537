package store.model.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    @DisplayName("Product 생성 - 성공 테스트")
    void of() {
        // given
        String name = "콜라";
        Integer price = 1000;
        String promotionName = "탄산2+1";

        // when
        Product product = Product.of(name, price, promotionName);

        // then
        assertThat(product.getName()).isEqualTo("콜라");
        assertThat(product.getPrice()).isEqualTo(1000);
        assertThat(product.getPromotionName()).isEqualTo("탄산2+1");
    }
}
