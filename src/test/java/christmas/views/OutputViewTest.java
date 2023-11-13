package christmas.views;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Dish;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputViewTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private PrintStream standardOut;
    private ByteArrayOutputStream captor;

    @BeforeEach
    public void init() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @AfterEach
    public void printOutput() {
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
        OutputView.printPrefaceOfResultOf(userVisitingDay);
        assertThat(output()).isEqualTo("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    @DisplayName("주문 메뉴 출력")
    @Test
    void testPrintingOrderedMenu() {
        Map<Dish, Integer> userOrderedMenu = new HashMap<>();
        userOrderedMenu.put(new Dish("타파스"), 1);
        userOrderedMenu.put(new Dish("제로콜라"), 1);
        OutputView.printOrderedMenu(userOrderedMenu);
        assertThat(output()).contains("<주문 메뉴>", "타파스 1개", "제로콜라 1개");
    }

    @DisplayName("할인 전 총주문 금액 출력")
    @Test
    void testPrintingTotalPriceBeforeDiscount() {
        int totalPrice = 8_500;
        OutputView.printBeforeDiscount(totalPrice);
        assertThat(output()).isEqualTo("<할인 전 총주문 금액>" + LINE_SEPARATOR + "8,500원");
    }
}
