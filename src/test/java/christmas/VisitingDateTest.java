package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.VisitingDate;
import christmas.utils.StringChanger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitingDateTest {
    @DisplayName("입력한 날이 1미만 혹은 31초과면 예외가 발생할 것이다.")
    @ValueSource(strings = {"0", "32"})
    @ParameterizedTest
    void crateVisitingDateByOverRange(String userInput) {
        int day = StringChanger.toInteger(userInput);
        assertThatThrownBy(() -> new VisitingDate(day))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
