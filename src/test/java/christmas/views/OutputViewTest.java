package christmas.views;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputViewTest {
    private static final int MONTH = 12;

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
        assertThat(output()).isEqualTo("안녕하세요! 우테코 식당 " + MONTH + "월 이벤트 플래너입니다.");
    }
}
