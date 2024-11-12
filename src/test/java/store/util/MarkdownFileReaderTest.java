package store.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.TestPathConstant;

class MarkdownFileReaderTest {

    @Test
    @DisplayName("MarkDown 파일 읽어오기 - 성공 테스트")
    void readFile_success() {
        // given
        String resourcePath = TestPathConstant.PRODUCT_FILE_PATH.getPath();
        List<String> expectedLines = List.of(
                "콜라,1000,10,탄산2+1",
                "콜라,1000,10,null",
                "감자칩,1500,5,반짝할인",
                "감자칩,1500,5,null",
                "초코바,1200,5,MD추천상품",
                "초코바,1200,5,null",
                "에너지바,2000,5,null");

        // when
        List<String> lines = MarkdownFileReader.readFile(resourcePath);

        // then
        assertThat(lines).containsAnyElementsOf(expectedLines);
    }

    @Test
    @DisplayName("MarkDown 파일 읽어오기: 없는 파일 - 예외 테스트")
    void readFile_invalidFile() {
        // given
        String resourcePath = "/invalid.md";

        // when & then
        assertThatThrownBy(() -> MarkdownFileReader.readFile(resourcePath))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR]");
    }
}
