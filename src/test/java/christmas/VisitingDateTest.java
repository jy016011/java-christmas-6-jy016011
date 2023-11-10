package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.VisitingDate;
import christmas.utils.StringChanger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("12월 3일은 크리스마스 디데이이자 특별 할인이 있는 날이고, 평일이다.")
    @Test
    void createVisitingDateByThirdOfMonth() {
        VisitingDate visitingDate = new VisitingDate(3);
        assertThat(visitingDate.isChristmasDDay()).isEqualTo(true);
        assertThat(visitingDate.isSpecialDay()).isEqualTo(true);
        assertThat(visitingDate.isWeekday()).isEqualTo(true);
        assertThat(visitingDate.isWeekend()).isEqualTo(false);
    }
}
