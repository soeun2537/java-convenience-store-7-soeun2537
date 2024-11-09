package store.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockManagerTest {

    @Test
    @DisplayName("StockManager 싱글톤 인스턴스 생성")
    void getInstance() {
        // when
        StockManager instance1 = StockManager.getInstance();
        StockManager instance2 = StockManager.getInstance();

        // then
        assertThat(instance1).isSameAs(instance2);
    }
}