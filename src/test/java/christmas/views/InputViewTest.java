package christmas.views;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputViewTest {
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

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    @DisplayName("밤문 날짜 입력 확인")
    @Test
    void testGettingVisitingDayInput() {
        String userInput = "3";
        command(userInput);
        String systemReceived = InputView.getVisitingDayInput();
        assertThat(output()).isEqualTo("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)");
        assertThat(systemReceived).isEqualTo(userInput);
    }
}
