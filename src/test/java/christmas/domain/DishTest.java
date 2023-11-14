package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DishTest {
    private static final String ERROR = "[ERROR]";

    @DisplayName("없는 메뉴일 경우 예외가 발생한다.")
    @Test
    void createMenuByNotInMenus() {
        assertThatThrownBy(() -> new Dish("게살버거"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR);
    }

    @DisplayName("에피타이저 메뉴들이면, 음식 유형이 에피타이저일 것이다.")
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드"})
    @ParameterizedTest
    void createAppetizer(String input) {
        Dish dish = new Dish(input);
        assertThat(dish.getType()).isEqualTo("에피타이저");
    }

    @DisplayName("메인 메뉴들이면, 음식 유형이 메인일 것이다.")
    @ValueSource(strings = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"})
    @ParameterizedTest
    void createMainDish(String input) {
        Dish dish = new Dish(input);
        assertThat(dish.getType()).isEqualTo("메인");
    }

    @DisplayName("디저트 메뉴들이면, 음식 유형이 디저트일 것이다.")
    @ValueSource(strings = {"초코케이크", "아이스크림"})
    @ParameterizedTest
    void createDessert(String input) {
        Dish dish = new Dish(input);
        assertThat(dish.getType()).isEqualTo("디저트");
    }

    @DisplayName("음료면, 음식 유형이 음료일 것이다.")
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    @ParameterizedTest
    void createDrink(String input) {
        Dish dish = new Dish(input);
        assertThat(dish.getType()).isEqualTo("음료");
    }

}
