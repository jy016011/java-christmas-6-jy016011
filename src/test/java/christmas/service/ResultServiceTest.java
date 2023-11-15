package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.Badge;
import christmas.constants.event.Discount;
import christmas.constants.event.Gift;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultServiceTest {
    private ResultService resultService;

    @BeforeEach
    void setResultService() {
        resultService = new ResultService();
        resultService.setDate("3");
        resultService.setOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        resultService.setBenefits();
    }

    @DisplayName("할인 전 총금액은 142,000원 일 것이다.")
    @Test
    void getTotalPrice() {
        assertThat(resultService.getTotalPrice()).isEqualTo(142_000);
    }

    @DisplayName("총 혜택 금액은 31,246원일 것이다.")
    @Test
    void getTotalBenefit() {
        assertThat(resultService.getTotalBenefit()).isEqualTo(31_246);
    }

    @DisplayName("할인된 총금액은 135,754원 일 것이다.")
    @Test
    void getTotalDiscountedPrice() {
        assertThat(resultService.getTotalDiscountedPrice()).isEqualTo(135_754);
    }

    @DisplayName("혜택 내역은"
            + "크리스마스 디데이 할인 : -1,200원"
            + "평일 할인: -4,046원"
            + "특별 할인: -1,000원"
            + "증정 이벤트: -25,000원"
            + "이고, 주말 할인은 없다.")
    @Test
    void getSynthesizedAllBenefits() {
        Map<String, Integer> allBenefits = resultService.getSynthesizedAllBenefits();
        assertThat(allBenefits.get(Discount.CHRISTMAS_D_DAY.getEventName())).isEqualTo(1_200);
        assertThat(allBenefits.get(Discount.WEEKDAY.getEventName())).isEqualTo(4_046);
        assertThat(allBenefits.get(Discount.SPECIAL.getEventName())).isEqualTo(1_000);
        assertThat(allBenefits.get(Gift.CHAMPAGNE.getEventName())).isEqualTo(25_000);
        assertThat(allBenefits.containsKey("주말 할인")).isEqualTo(false);
    }

    @DisplayName("배지는 산타일 것이다.")
    @Test
    void getBadge() {
        assertThat(resultService.getBadge()).isEqualTo(Badge.SANTA);
    }
}
