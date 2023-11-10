package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.service.ResultService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ResultServiceTest {
    @DisplayName("입력한 메뉴가 형식과 안맞으면 예외가 발생할 것이다.")
    @ValueSource(strings = {
            "양송이수프 : 1, 바비큐립 : 2, 샴페인 : 3",
            "양송이수프-1 바비큐립-2 샴페인-3",
            "양송이수프 : 1 바비큐립 : 2 샴페인 : 3"
    })
    @ParameterizedTest
    void createOrderByInvalidFormInput(String userInput) {
        ResultService resultService = new ResultService();
        assertThatThrownBy(() -> resultService.setOrder(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력한 날짜가 0이상의 정수가 아니면 예외가 발생할 것이다.")
    @ValueSource(strings = {"a", "1.2", "-1"})
    @ParameterizedTest
    void createDateByInvalidInput(String userInput) {
        ResultService resultService = new ResultService();
        assertThatThrownBy(() -> resultService.setDate(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
