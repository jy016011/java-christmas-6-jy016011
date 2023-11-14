package christmas.views;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final int MONTH = 12;

    private InputView() {
    }

    public static String getVisitingDayInput() {
        System.out.println(MONTH + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)");
        return Console.readLine();
    }

    public static String getOrderInput() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Console.readLine();
    }
}
