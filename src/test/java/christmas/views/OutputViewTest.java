package christmas.views;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.Badge;
import christmas.constants.events.Discount;
import christmas.constants.events.Gift;
import christmas.domain.Dish;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputViewTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String NONE = "없음";
    private static final int GIFT_COUNT = 1;

    private PrintStream standardOut;
    private ByteArrayOutputStream captor;

    @BeforeEach
    public void init() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @AfterEach
    public void reset() {
        System.setOut(standardOut);
        captor.reset();
    }

    private String output() {
        return captor.toString().trim();
    }

    @DisplayName("플래너 인삿말 출력")
    @Test
    void testPrintingGreetings() {
        OutputView.printGreetings();
        assertThat(output()).isEqualTo("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @DisplayName("결과 출력 전의 머리말 출력")
    @Test
    void testPrintingPrefaceOfResult() {
        int userVisitingDay = 3;
        OutputView.printPrefaceOfResultWith(userVisitingDay);
        assertThat(output()).isEqualTo("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    @DisplayName("주문 메뉴 출력")
    @Test
    void testPrintingOrderedMenu() {
        Map<Dish, Integer> userOrderedMenu = new LinkedHashMap<>();
        userOrderedMenu.put(new Dish("타파스"), 1);
        userOrderedMenu.put(new Dish("제로콜라"), 1);
        OutputView.printEachOrderBy(userOrderedMenu);
        assertThat(output()).isEqualTo("<주문 메뉴>" + LINE_SEPARATOR + "타파스 1개" + LINE_SEPARATOR + "제로콜라 1개");
    }

    @DisplayName("할인 전 총주문 금액 출력")
    @Test
    void testPrintingTotalPriceBeforeDiscount() {
        int totalPrice = 8_500;
        OutputView.printBeforeDiscount(totalPrice);
        assertThat(output()).isEqualTo("<할인 전 총주문 금액>" + LINE_SEPARATOR + "8,500원");
    }

    @DisplayName("증정 메뉴 출력")
    @Test
    void testPrintingPresentEventWithGift() {
        OutputView.printPresent(Gift.CHAMPAGNE.getItemBy(GIFT_COUNT));
        assertThat(output()).isEqualTo("<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개");
    }

    @Test
    void testPrintingPresentEventWithOutGift() {
        OutputView.printPresent(NONE);
        assertThat(output()).isEqualTo("<증정 메뉴>" + LINE_SEPARATOR + "없음");
    }

    @DisplayName("혜택 내역 출력")
    @Test
    void testPrintingBenefitsDetails() {
        Map<String, Integer> benefitDetails = new LinkedHashMap<>();
        benefitDetails.put(Discount.CHRISTMAS_D_DAY.getEventName(), 1_200);
        benefitDetails.put(Discount.WEEKDAY.getEventName(), 4_046);
        benefitDetails.put(Discount.SPECIAL.getEventName(), 1_000);
        benefitDetails.put(Gift.CHAMPAGNE.getEventName(), 25_000);
        OutputView.printDetailsOf(benefitDetails);
        assertThat(output()).isEqualTo(
                "<혜택 내역>" + LINE_SEPARATOR +
                        "크리스마스 디데이 할인: -1,200원" + LINE_SEPARATOR +
                        "평일 할인: -4,046원" + LINE_SEPARATOR +
                        "특별 할인: -1,000원" + LINE_SEPARATOR +
                        "증정 이벤트: -25,000원"
        );
    }

    @DisplayName("받은 혜택이 없을 경우의 혜택 내역 출력")
    @Test
    void testPrintingBenefitsNone() {
        Map<String, Integer> benefitDetails = new LinkedHashMap<>();
        OutputView.printDetailsOf(benefitDetails);
        assertThat(output()).isEqualTo(
                "<혜택 내역>" + LINE_SEPARATOR +
                        "없음"
        );
    }

    @DisplayName("총혜택 금액 출력")
    @Test
    void testPrintingTotalBenefits() {
        int totalBenefits = 31_246;
        OutputView.printTheSumOf(totalBenefits);
        assertThat(output()).isEqualTo("<총혜택 금액>" + LINE_SEPARATOR + "-31,246원");
    }

    @DisplayName("예상 결제 금액 출력")
    @Test
    void testPrintingExpectedPriceToPay() {
        int priceToPay = 135_754;
        OutputView.printExpected(priceToPay);
        assertThat(output()).isEqualTo("<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "135,754원");
    }

    @DisplayName("12월 이벤트 배지 출력")
    @Test
    void testPrintingSantaBadge() {
        Badge badge = Badge.SANTA;
        OutputView.printLastMessageWith(badge);
        assertThat(output()).isEqualTo("<12월 이벤트 배지>" + LINE_SEPARATOR + "산타");
    }

    @DisplayName("받은 배지가 없을 경우의 12월 이벤트 배지 출력")
    @Test
    void testPrintingNoneBadge() {
        Badge badge = Badge.NONE;
        OutputView.printLastMessageWith(badge);
        assertThat(output()).isEqualTo("<12월 이벤트 배지>" + LINE_SEPARATOR + "없음");
    }
}
