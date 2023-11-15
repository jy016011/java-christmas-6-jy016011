package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitingDateTest {
    @DisplayName("입력한 날이 1미만 혹은 31초과면 예외가 발생할 것이다.")
    @ValueSource(strings = {"0", "32"})
    @ParameterizedTest
    void crateVisitingDateByOverRange(String userInput) {
        assertThatThrownBy(() -> new VisitingDate(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1일부터 25일까지는 크리스마스 디데이이다.")
    @ValueSource(strings = {
            "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12", "13", "14", "15", "16",
            "17", "18", "19", "20", "21", "22", "23", "24", "25"
    })
    @ParameterizedTest
    void visitChristmasDDays(String userInput) {
        VisitingDate visitingDate = new VisitingDate(userInput);
        assertThat(visitingDate.isChristmasDDay()).isEqualTo(true);
    }

    @DisplayName("26일부터는 크리스마스 디데이가 아니다.")
    @ValueSource(strings = {"26", "27", "28", "29", "30", "31"})
    @ParameterizedTest
    void visitNotChristmasDDays(String userInput) {
        VisitingDate visitingDate = new VisitingDate(userInput);
        assertThat(visitingDate.isChristmasDDay()).isEqualTo(false);
    }

    @DisplayName("일요일부터 목요일까지는 평일이다.")
    @ValueSource(strings = {
            "3", "4", "5", "6", "7",
            "10", "11", "12", "13", "14",
            "17", "18", "19", "20", "21",
            "24", "25", "26", "27", "28", "31"
    })
    @ParameterizedTest
    void visitWeekDays(String userInput) {
        VisitingDate visitingDate = new VisitingDate(userInput);
        assertThat(visitingDate.isWeekday()).isEqualTo(true);
        assertThat(visitingDate.isWeekend()).isEqualTo(false);
    }

    @DisplayName("금요일부터 토요일까지는 주말이다.")
    @ValueSource(strings = {
            "1", "2", "8", "9", "15",
            "16", "22", "23", "29", "30",
    })
    @ParameterizedTest
    void visitWeekend(String userInput) {
        VisitingDate visitingDate = new VisitingDate(userInput);
        assertThat(visitingDate.isWeekend()).isEqualTo(true);
        assertThat(visitingDate.isWeekday()).isEqualTo(false);
    }

    @DisplayName("3, 10, 17, 24, 25, 31일은 이벤트 달력에 별이있는(특별한) 날이다.")
    @ValueSource(strings = {"3", "10", "17", "24", "25", "31"})
    @ParameterizedTest
    void visitSpecialDays(String userInput) {
        VisitingDate visitingDate = new VisitingDate(userInput);
        assertThat(visitingDate.isSpecialDay()).isEqualTo(true);
    }

    @DisplayName("3, 10, 17, 24, 25, 31일 외의 날은 이벤트 달력에 별이있는(특별한) 날이 아니다.")
    @ValueSource(strings = {
            "1", "2", "4", "5", "6", "7",
            "8", "9", "11", "12", "13", "14",
            "15", "16", "18", "19", "20", "21",
            "22", "23", "26", "27", "28", "29", "30"
    })
    @ParameterizedTest
    void visitNotSpecialDays(String userInput) {
        VisitingDate visitingDate = new VisitingDate(userInput);
        assertThat(visitingDate.isSpecialDay()).isEqualTo(false);
    }

    @DisplayName("12월 3일은 크리스마스 디데이이자 특별 할인이 있는 날이고, 평일이다.")
    @Test
    void createVisitingDateByThirdOfMonth() {
        VisitingDate visitingDate = new VisitingDate("3");
        assertThat(visitingDate.isChristmasDDay()).isEqualTo(true);
        assertThat(visitingDate.isSpecialDay()).isEqualTo(true);
        assertThat(visitingDate.isWeekday()).isEqualTo(true);
        assertThat(visitingDate.isWeekend()).isEqualTo(false);
    }

    @DisplayName("12월 30일은 크리스마스 디데이가 아니고, 특별 할인이 없는 날이고, 주말이다.")
    @Test
    void createVisitingDateByTwentyThirdOfMonth() {
        VisitingDate visitingDate = new VisitingDate("30");
        assertThat(visitingDate.isChristmasDDay()).isEqualTo(false);
        assertThat(visitingDate.isSpecialDay()).isEqualTo(false);
        assertThat(visitingDate.isWeekday()).isEqualTo(false);
        assertThat(visitingDate.isWeekend()).isEqualTo(true);
    }
}
