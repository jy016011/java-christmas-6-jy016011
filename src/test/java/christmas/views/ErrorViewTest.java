package christmas.views;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ErrorViewTest {
    private PrintStream standardOut;
    private ByteArrayOutputStream captor;

    @BeforeEach
    public void init() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @AfterEach
    public void reset() {
        System.setOut(standardOut);
        captor.reset();
    }

    private String output() {
        return captor.toString().trim();
    }

    @DisplayName("올바른 날짜 입력이 아닐 경우 예외 출력")
    @Test
    void testPrintInvalidDayInputMessage() {
        ErrorView.printInvalidDayInput();
        assertThat(output()).isEqualTo("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("올바른 주문 입력이 아닐 경우 예외 출력")
    @Test
    void testPrintInvalidOrderInputMessage() {
        ErrorView.printInvalidOrderInput();
        assertThat(output()).isEqualTo("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
