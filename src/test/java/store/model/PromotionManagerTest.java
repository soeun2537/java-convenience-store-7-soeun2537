package store.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
