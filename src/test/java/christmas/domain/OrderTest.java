package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {
    private static final String ERROR = "[ERROR]";

    @DisplayName("메뉴가 중복되거나,"
            + "음료만 주문하거나,"
            + "각 메뉴의 개수가 하나라도 1개 미만이거나,"
            + "메뉴의 총 개수가 20개면 예외가 발생할 것이다.")
    @ValueSource(strings = {
            "양송이수프-1,양송이수프-2",
            "제로콜라-2,샴페인-1,레드와인-3",
            "제로콜라-0,양송이수프-2,바비큐립-2",
            "제로콜라-7,양송이수프-8,바비큐립-6"
    })
    @ParameterizedTest
    void createOrderByDuplicatedMenu(String userInput) {
        assertThatThrownBy(() -> new Order(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR);
    }

    @DisplayName("티본스테이크와 샴페인을 하나씩 시키면 할인전 총금액은 80,000원일 것이다.")
    @Test
    void orderTBoneSteakAndChampagne() {
        Order order = new Order("티본스테이크-1,샴페인-1");
        assertThat(order.getTotalPrice()).isEqualTo(80_000);
    }

    @DisplayName("초코케이크 2개를 시키면 주문 내용을 초코케이크 = 2개와 같이 저장할 것이다.")
    @Test
    void checkOrderDetails() {
        Order order = new Order("초코케이크-2");
        assertThat(order.getOrderedDishes().get(new Dish("초코케이크"))).isEqualTo(2);
    }

    @DisplayName("할인 전 총 주문 금액이 만원 미만이면 이벤트 대상이 아닐 것이다.")
    @Test
    void orderUnderMinTotalPrice() {
        Order order = new Order("제로콜라-1,아이스크림-1");
        assertThat(order.getTotalPrice()).isEqualTo(8_000);
        assertThat(order.isEventTarget()).isEqualTo(false);
    }

    @DisplayName("할인 전 총 주문 금액이 만원 이상이면 이벤트 대상일 것이다.")
    @Test
    void orderMinTotalPrice() {
        Order order = new Order("아이스크림-2");
        assertThat(order.getTotalPrice()).isEqualTo(10_000);
        assertThat(order.isEventTarget()).isEqualTo(true);
    }

    @DisplayName("할인 전 총 주문 금액이 12만원 미만이면, 증정 대상이 아닐 것이다.")
    @Test
    void orderUnderEventTotalPrice() {
        Order order = new Order("티본스테이크-2");
        assertThat(order.getTotalPrice()).isEqualTo(110_000);
        assertThat(order.canGetGift()).isEqualTo(false);
    }

    @DisplayName("할인 전 총 주문 금액이 12만원 이상이면, 증정 대상일 것이다.")
    @Test
    void orderEventTotalPrice() {
        Order order = new Order("티본스테이크-2,아이스크림-2");
        assertThat(order.getTotalPrice()).isEqualTo(120_000);
        assertThat(order.canGetGift()).isEqualTo(true);
    }
}
