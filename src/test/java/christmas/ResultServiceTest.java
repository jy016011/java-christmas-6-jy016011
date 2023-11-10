package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.service.ResultService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("12월 3일에 다음과 같이 주문하면, 할인 전 총금액은 142,000원 일 것이다.")
    @Test
    void getTotalPriceByThisOrder() {
        ResultService resultService = new ResultService();
        resultService.setDate("3");
        resultService.setOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertThat(resultService.getTotalPrice()).isEqualTo(142_000);
    }

    @DisplayName("12월 3일에 다음과 같이 주문하면, 샴페인을 증정받아 25,000원 이득을 볼 것이다.")
    @Test
    void getGiftBenefitByThisOrder() {
        ResultService resultService = new ResultService();
        resultService.setDate("3");
        resultService.setOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertThat(resultService.getGiftBenefit()).isEqualTo(25_000);
    }

    @DisplayName("12월 3일에 다음과 같이 주문하면, 할인 금액이 6,246원일 것이다.")
    @Test
    void getTotalDiscountByThisOrder() {
        ResultService resultService = new ResultService();
        resultService.setDate("3");
        resultService.setOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertThat(resultService.getTotalDiscount()).isEqualTo(6_246);
    }

    @DisplayName("12월 3일에 다음과 같이 주문하면, 총 혜택 금액이 31,246원일 것이다.")
    @Test
    void getTotalBenefitByThisOrder() {
        ResultService resultService = new ResultService();
        resultService.setDate("3");
        resultService.setOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertThat(resultService.getTotalBenefit()).isEqualTo(31_246);
    }


    @DisplayName("12월 3일에 다음과 같이 주문하면, 할인된 총금액은 135,754원일 것이다.")
    @Test
    void getTotalByThisOrder() {
        ResultService resultService = new ResultService();
        resultService.setDate("3");
        resultService.setOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertThat(resultService.getTotalDiscountedPrice()).isEqualTo(135_754);
    }

    @DisplayName("만원이하로 주문하면, 어떤 이벤트도 받지 못한다.")
    @Test
    void getNoEventsByUnderMinPrice() {
        ResultService resultService = new ResultService();
        resultService.setDate("3");
        resultService.setOrder("타파스-1,제로콜라-1");
        assertThat(resultService.getTotalBenefit()).isEqualTo(0);
        assertThat(resultService.getTotalDiscountedPrice())
                .isEqualTo(resultService.getTotalPrice());
    }

}
