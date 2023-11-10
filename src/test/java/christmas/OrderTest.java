package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Order;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private static final String ERROR = "[ERROR]";

    @DisplayName("입력한 메뉴가 중복되면 예외가 발생할 것이다.")
    @Test
    void createOrderByDuplicatedMenu() {
        List<String> dishNames = new ArrayList<>(List.of("양송이수프", "양송이수프"));
        List<Integer> dishCounts = new ArrayList<>(List.of(1, 2));
        assertThatThrownBy(() -> new Order(dishNames, dishCounts))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR);
    }

    @DisplayName("음료만 주문하면 예외가 발생할 것이다.")
    @Test
    void createOrderByOnlyDrinks() {
        List<String> dishNames = new ArrayList<>(List.of("제로콜라", "샴페인", " 레드와인"));
        List<Integer> dishCounts = new ArrayList<>(List.of(2, 1, 3));
        assertThatThrownBy(() -> new Order(dishNames, dishCounts))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR);
    }

    @DisplayName("각 메뉴의 개수중 하나라도 1개미만이면 예외가 발생할 것이다.")
    @Test
    void createOrderByUnderCountOne() {
        List<String> dishNames = new ArrayList<>(List.of("제로콜라", "양송이수프", "바비큐립"));
        List<Integer> dishCounts = new ArrayList<>(List.of(0, 2, 2));
        assertThatThrownBy(() -> new Order(dishNames, dishCounts))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR);
    }

    @DisplayName("메뉴의 총 개수가 20초과면 예외가 발생할 것이다.")
    @Test
    void createOrderByOverTwenties() {
        List<String> dishNames = new ArrayList<>(List.of("제로콜라", "양송이수프", "바비큐립"));
        List<Integer> dishCounts = new ArrayList<>(List.of(7, 8, 6));
        assertThatThrownBy(() -> new Order(dishNames, dishCounts))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR);
    }

    @DisplayName("티본스테이크와 샴페인을 하나씩 시키면 할인전 총금액은 80,000원일 것이다.")
    @Test
    void orderTBoneSteakAndChampagne() {
        List<String> dishNames = new ArrayList<>(List.of("티본스테이크", "샴페인"));
        List<Integer> dishCounts = new ArrayList<>(List.of(1, 1));
        Order order = new Order(dishNames, dishCounts);
        assertThat(order.getTotalPrice()).isEqualTo(80000);
    }
}
