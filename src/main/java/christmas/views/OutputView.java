package christmas.views;

import christmas.domain.Dish;
import java.util.Map;

public class OutputView {
    private static final int MONTH = 12;
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
}
