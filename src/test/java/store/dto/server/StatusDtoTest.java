package store.dto.server;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.Status;
import store.model.domain.Product;

class StatusDtoTest {

    @Test
    @DisplayName("ADDING_QUANTITY 상태 설정 확인")
    void setAddingQuantityStatus() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 2;

        // when
        StatusDto statusDto = StatusDto.setAddingQuantityStatus(product, quantity);

        // then
        assertThat(statusDto.getStatus()).isEqualTo(Status.ADDING_QUANTITY);
        assertThat(statusDto.getProductName()).isEqualTo("콜라");
        assertThat(statusDto.getQuantity()).isEqualTo(2);
    }

    @Test
    @DisplayName("REGULAR_PRICE_PAYMENT 상태 설정 확인")
    void setRegularStatusPaymentStatus() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 11;

        // when
        StatusDto statusDto = StatusDto.setRegularStatusPaymentStatus(product, quantity);

        // then
        assertThat(statusDto.getStatus()).isEqualTo(Status.REGULAR_PRICE_PAYMENT);
        assertThat(statusDto.getProductName()).isEqualTo("콜라");
        assertThat(statusDto.getQuantity()).isEqualTo(11);
    }

    @Test
    @DisplayName("NO_ACTION_REQUIRED 상태 설정 확인")
    void setNoActionRequiredStatus() {
        // when
        StatusDto statusDto = StatusDto.setNoActionRequiredStatus();

        // then
        assertThat(statusDto.getStatus()).isEqualTo(Status.NO_ACTION_REQUIRED);
        assertThat(statusDto.getProduct()).isNull();
        assertThat(statusDto.getQuantity()).isEqualTo(0);
    }
}
