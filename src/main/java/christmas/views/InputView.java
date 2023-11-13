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
}
