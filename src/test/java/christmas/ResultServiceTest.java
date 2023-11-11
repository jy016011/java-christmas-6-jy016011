package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constants.Error;
import christmas.service.ResultService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ResultServiceTest {
    private static final Error ERROR_HEADER = Error.ERROR_HEADER;

    @DisplayName("입력한 메뉴가 형식과 안맞으면 예외가 발생할 것이다.")
    @ValueSource(strings = {
            "양송이수프 : 1, 바비큐립 : 2, 샴페인 : 3",
            "양송이수프 : 1 바비큐립 : 2 샴페인 : 3",
            "양송이수프-1 바비큐립-2 샴페인-3",
            "양송이수프-1-바비큐립-2-샴페인-3",
            "-바베큐-1,아이스크림-2,",
            "초코케이크,--"
    })
    @ParameterizedTest
    void createOrderByInvalidFormInput(String userInput) {
        ResultService resultService = new ResultService();
        assertThatThrownBy(() -> resultService.setOrder(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER.getErrorMessage());
    }

    @DisplayName("입력한 날짜가 0이상의 정수가 아니면 예외가 발생할 것이다.")
    @ValueSource(strings = {"a", "1.2", "-1"})
    @ParameterizedTest
    void createDateByInvalidInput(String userInput) {
        ResultService resultService = new ResultService();
        assertThatThrownBy(() -> resultService.setDate(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("12월 3일에 실행 에시와 같이 주문하면, "
            + "할인 전 총금액은 142,000원 일 것이고,"
            + "샴페인 1개를 증정받아 25,000원을 혜택받을 것이며,"
            + "총 할인 금액은 6,246원일 것이라,"
            + "총 혜택 금액은 31,246원일 것이고,"
            + "따라서 할인된 총금액은 135,754원 일 것이다.")
    @Test
    void getTotalPriceByThisOrder() {
        ResultService resultService = new ResultService();
        resultService.setDate("3");
        resultService.setOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertThat(resultService.getTotalPrice()).isEqualTo(142_000);
        assertThat(resultService.getGiftBenefit()).isEqualTo(25_000);
        assertThat(resultService.getTotalDiscount()).isEqualTo(6_246);
        assertThat(resultService.getTotalBenefit()).isEqualTo(31_246);
        assertThat(resultService.getTotalDiscountedPrice()).isEqualTo(135_754);
    }

    @DisplayName("만원 미만으로로 주문하면, 어떤 이벤트도 받지 못한다.")
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
