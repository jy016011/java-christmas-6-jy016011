package christmas.views;

import christmas.domain.Dish;
import java.util.Map;

public class OutputView {
    private static final int MONTH = 12;
    private static final int NOTHING = 0;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private OutputView() {
    }

    public static void printGreetings() {
        System.out.println("안녕하세요! 우테코 식당 " + MONTH + "월 이벤트 플래너입니다.");
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
        System.out.println(LINE_SEPARATOR + "<할인 전 총주문 금액>" + LINE_SEPARATOR
                + String.format("%,d원", totalPriceBeforeDiscount));
    }

    public static void printPresent(String gift) {
        System.out.println(LINE_SEPARATOR + "<증정 메뉴>" + LINE_SEPARATOR + gift);
    }

    public static void printBenefitsDetails(Map<String, Integer> benefitsDetails) {
        StringBuilder readableFormatOfBenefits = new StringBuilder(LINE_SEPARATOR + "<혜택 내역>" + LINE_SEPARATOR);
        for (String event : benefitsDetails.keySet()) {
            int benefit = benefitsDetails.get(event);
            if (benefit == NOTHING) {
                continue;
            }
            readableFormatOfBenefits.append(event).append(": ")
                    .append("-").append(String.format("%,d원", benefit)).append(LINE_SEPARATOR);
        }
        System.out.println(readableFormatOfBenefits);
    }

    public static void printTotalBenefits(int totalBenefit) {
        System.out.println(LINE_SEPARATOR + "<총혜택 금액>" + LINE_SEPARATOR + String.format("-%,d원", totalBenefit));
    }
}
