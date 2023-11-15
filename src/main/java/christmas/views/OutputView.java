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

    public static void printPrefaceOfResultWith(int day) {
        System.out.println(MONTH + "월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printEachOrderBy(Map<Dish, Integer> orderedMenu) {
        StringBuilder readableFormatOfMenu = new StringBuilder(LINE_SEPARATOR + "<주문 메뉴>" + LINE_SEPARATOR);
        for (Dish dish : orderedMenu.keySet()) {
            readableFormatOfMenu.append(dish.getName()).append(" ")
                    .append(orderedMenu.get(dish)).append("개")
                    .append(LINE_SEPARATOR);
        }
        System.out.println(readableFormatOfMenu);
    }

    public static void printBeforeDiscount(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>" + LINE_SEPARATOR
                + String.format("%,d원", totalPrice));
    }

    public static void printGiftItem(String gift) {
        System.out.println(LINE_SEPARATOR + "<증정 메뉴>" + LINE_SEPARATOR + gift);
    }

    public static void printDetailsOf(Map<String, Integer> allBenefits) {
        StringBuilder readableFormatOfBenefits = new StringBuilder(LINE_SEPARATOR + "<혜택 내역>" + LINE_SEPARATOR);
        if (allBenefits.isEmpty()) {
            readableFormatOfBenefits.append(NONE).append(LINE_SEPARATOR);
        }
        for (String event : allBenefits.keySet()) {
            int benefit = allBenefits.get(event);
            readableFormatOfBenefits.append(event).append(String.format(": -%,d원", benefit))
                    .append(LINE_SEPARATOR);
        }
        System.out.println(readableFormatOfBenefits);
    }

    public static void printTheSumOf(int totalBenefits) {
        StringBuilder outputOfTotalBenefits = new StringBuilder("<총혜택 금액>" + LINE_SEPARATOR);
        if (totalBenefits > NOTHING) {
            outputOfTotalBenefits.append("-");
        }
        outputOfTotalBenefits.append(String.format("%,d원", totalBenefits));
        System.out.println(outputOfTotalBenefits);
    }

    public static void printExpectedPrice(int priceToPay) {
        System.out.println(
                LINE_SEPARATOR + "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + String.format("%,d원", priceToPay)
        );
    }

    public static void printLastMessageWith(Badge badge) {
        System.out.println(LINE_SEPARATOR + "<" + MONTH + "월 이벤트 배지>" + LINE_SEPARATOR + badge.getName());
    }
}
