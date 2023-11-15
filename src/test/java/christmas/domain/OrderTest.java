package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constants.Error;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {
    private static final Error ERROR_HEADER = Error.ERROR_HEADER;

    @DisplayName("입력한 메뉴가 형식과 안맞으면 예외가 발생할 것이다.")
    @ValueSource(strings = {
            "양송이수프 : 1, 바비큐립 : 2, 샴페인 : 3",
            "양송이수프 : 1 바비큐립 : 2 샴페인 : 3",
            "양송이수프-1 바비큐립-2 샴페인-3",
            "양송이수프-1-바비큐립-2-샴페인-3",
            "-바비큐립-1,초코케이크-2,",
            "초코케이크,--"

    })
    @ParameterizedTest
    void createOrderByInvalidFormInput(String userInput) {
        assertThatThrownBy(() -> new Order(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER.getMessage());
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
