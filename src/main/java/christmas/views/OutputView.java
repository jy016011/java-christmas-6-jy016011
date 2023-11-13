package christmas.views;

public class OutputView {
    private static final int MONTH = 12;

    private OutputView() {
    }

    public static void printGreetings() {
        System.out.println("안녕하세요! 우테코 식당 " + MONTH + "월 이벤트 플래너입니다.");
    }
}
