package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.event.Gift;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BenefitsTest {
    private static final int NOTHING = 0;
    private static final int GIFT_COUNT = 1;

    @DisplayName("12만원 미만이면, 증정 메뉴를 받을 수 없다.")
    @Test
    void getGiftWithOrderUnderEventTotalPrice() {
        Order order = new Order("티본스테이크-2");
        Benefits benefits = new Benefits();
        assertThat(benefits.getGift(order)).isEqualTo(Gift.NONE);
        assertThat(benefits.getGiftPrice(order)).isEqualTo(NOTHING);
    }

    @DisplayName("12만원 이상이면, 증정 메뉴를 받을 수 있다.")
    @Test
    void getGiftWithOrderOverEventTotalPrice() {
        Order order = new Order("티본스테이크-2,아이스크림-2");
        Benefits benefits = new Benefits();
        assertThat(benefits.getGift(order)).isEqualTo(Gift.CHAMPAGNE);
        assertThat(benefits.getGiftPrice(order)).isEqualTo(Gift.CHAMPAGNE.getBenefit(GIFT_COUNT));
    }

    @DisplayName("12월 3일에 실행 에시와 같이 주문하면,"
            + "크리스마스 할인은 1,200원이고,"
            + "평일 할인은 4,046원이고,"
            + "주말 할인은 없고,"
            + "특별 할인은 1,000원이고,"
            + "증정 혜택은 샴페인 1개 가격이다.")
    @Test
    void getBenefitsWithExampleOrder() {
        VisitingDate visitingDate = new VisitingDate("3");
        Order order = new Order("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Benefits benefits = new Benefits();
        assertThat(benefits.getChristmasDDayDiscount(order, visitingDate)).isEqualTo(1_200);
        assertThat(benefits.getWeekdayDiscount(order, visitingDate)).isEqualTo(4_046);
        assertThat(benefits.getWeekendDiscount(order, visitingDate)).isEqualTo(NOTHING);
        assertThat(benefits.getSpecialDiscount(order, visitingDate)).isEqualTo(1_000);
        assertThat(benefits.getGiftPrice(order)).isEqualTo(Gift.CHAMPAGNE.getBenefit(GIFT_COUNT));
    }
}
