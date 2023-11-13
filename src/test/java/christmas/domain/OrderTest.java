package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        List<Integer> dishCounts = new ArrayList<>(List.of(1, 1)); // 55,000원, 25,000원
        Order order = new Order(dishNames, dishCounts);
        assertThat(order.getTotalPrice()).isEqualTo(80_000);
    }

    @DisplayName("초코케이크 2개를 시키면 주문 내용을 초코케이크 : 2개와 같이 저장할 것이다.")
    @Test
    void checkOrderDetails() {
        List<String> dishNames = new ArrayList<>(List.of("초코케이크"));
        List<Integer> dishCounts = new ArrayList<>(List.of(2));
        Order order = new Order(dishNames, dishCounts);
        assertThat(order.getOrderedDishes().get(new Dish("초코케이크"))).isEqualTo(2);
    }

    @DisplayName("할인 전 총 주문 금액이 만원 미만이면 이벤트 대상이 아닐 것이다.")
    @Test
    void orderUnderMinTotalPrice() {
        List<String> dishNames = new ArrayList<>(List.of("제로콜라", "아이스크림"));
        List<Integer> dishCounts = new ArrayList<>(List.of(1, 1)); // 8000원
        Order order = new Order(dishNames, dishCounts);
        assertThat(order.getTotalPrice()).isEqualTo(8_000);
        assertThat(order.isEventTarget()).isEqualTo(false);
    }

    @DisplayName("할인 전 총 주문 금액이 만원 이상이면 이벤트 대상일 것이다.")
    @Test
    void orderMinTotalPrice() {
        List<String> dishNames = new ArrayList<>(List.of("아이스크림"));
        List<Integer> dishCounts = new ArrayList<>(List.of(2));
        Order order = new Order(dishNames, dishCounts);
        assertThat(order.getTotalPrice()).isEqualTo(10_000);
        assertThat(order.isEventTarget()).isEqualTo(true);
    }

    @DisplayName("할인 전 총 주문 금액이 12만원 미만이면, 증정 대상이 아닐 것이다.")
    @Test
    void orderUnderEventTotalPrice() {
        List<String> dishNames = new ArrayList<>(List.of("티본스테이크"));
        List<Integer> dishCounts = new ArrayList<>(List.of(2)); // 11만원
        Order order = new Order(dishNames, dishCounts);
        assertThat(order.getTotalPrice()).isEqualTo(110_000);
        assertThat(order.canGetGift()).isEqualTo(false);
    }

    @DisplayName("할인 전 총 주문 금액이 12만원 이상이면, 증정 대상일 것이다.")
    @Test
    void orderEventTotalPrice() {
        List<String> dishNames = new ArrayList<>(List.of("티본스테이크", "아이스크림"));
        List<Integer> dishCounts = new ArrayList<>(List.of(2, 2));
        Order order = new Order(dishNames, dishCounts);
        assertThat(order.getTotalPrice()).isEqualTo(120_000);
        assertThat(order.canGetGift()).isEqualTo(true);
    }
}
