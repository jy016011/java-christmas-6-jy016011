package christmas.views;

import christmas.constants.Badge;
import christmas.domain.Dish;
import java.util.Map;

public class OutputView {
    private static final int MONTH = 12;
    private static final int NOTHING = 0;
    private static final String NONE = "없음";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private OutputView() {
    }

    public static void printGreetings() {
        System.out.println(LINE_SEPARATOR + "안녕하세요! 우테코 식당 " + MONTH + "월 이벤트 플래너입니다.");
    }

    public static void printPrefaceOfResultOf(int day) {
        System.out.println(MONTH + "월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printOrderedMenu(Map<Dish, Integer> orderedMenu) {
        StringBuilder readableFormatOfMenu = new StringBuilder(LINE_SEPARATOR + "<주문 메뉴>" + LINE_SEPARATOR);
        for (Dish dish : orderedMenu.keySet()) {
            readableFormatOfMenu.append(dish.getName()).append(" ")
                    .append(orderedMenu.get(dish)).append("개")
                    .append(LINE_SEPARATOR);
        }
        System.out.println(readableFormatOfMenu);
    }

    public static void printBeforeDiscount(int totalPriceBeforeDiscount) {
        System.out.println("<할인 전 총주문 금액>" + LINE_SEPARATOR
                + String.format("%,d원", totalPriceBeforeDiscount));
    }

    public static void printPresent(String gift) {
        System.out.println(LINE_SEPARATOR + "<증정 메뉴>" + LINE_SEPARATOR + gift);
    }

    public static void printBenefitsDetails(Map<String, Integer> benefitsDetails) {
        StringBuilder readableFormatOfBenefits = new StringBuilder(LINE_SEPARATOR + "<혜택 내역>" + LINE_SEPARATOR);
        if (benefitsDetails.isEmpty()) {
            System.out.println(readableFormatOfBenefits + NONE + LINE_SEPARATOR);
            return;
        }
        for (String event : benefitsDetails.keySet()) {
            int benefit = benefitsDetails.get(event);
            readableFormatOfBenefits.append(event).append(": ")
                    .append("-").append(String.format("%,d원", benefit)).append(LINE_SEPARATOR);
        }
        System.out.println(readableFormatOfBenefits);
    }

    public static void printTotalBenefits(int totalBenefits) {
        if (totalBenefits == NOTHING) {
            System.out.println("<총혜택 금액>" + LINE_SEPARATOR + String.format("%,d원", NOTHING));
            return;
        }
        System.out.println("<총혜택 금액>" + LINE_SEPARATOR + String.format("-%,d원", totalBenefits));
    }

    public static void printExpectedPriceToPay(int expectedPriceToPay) {
        System.out.println(
                LINE_SEPARATOR + "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + String.format("%,d원", expectedPriceToPay)
        );
    }

    public static void printBadge(Badge badge) {
        System.out.println(LINE_SEPARATOR + "<" + MONTH + "월 이벤트 배지>" + LINE_SEPARATOR + badge.getName());
    }
}
