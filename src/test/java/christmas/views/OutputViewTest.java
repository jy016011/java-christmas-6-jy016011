package christmas.views;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputViewTest {
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

    @DisplayName("플래너 인삿말 출력잘하는지 확인")
    @Test
    void testPrintingGreetings() {
        OutputView.printGreetings();
        assertThat(output()).isEqualTo("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @DisplayName("결과 출력 전의 머리말 출력확인")
    @Test
    void testPrintingPrefaceOfResult() {
        int userVisitingDay = 3;
        OutputView.printPrefaceOfResult(userVisitingDay);
        assertThat(output()).isEqualTo("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }
}
