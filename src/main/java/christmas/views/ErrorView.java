package christmas.views;

import christmas.constants.Error;

public class ErrorView {
    private static final Error INVALID_DAY = Error.INVALID_DAY;
    private static final Error INVALID_ORDER = Error.INVALID_ORDER;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private ErrorView() {
    }

    public static void printInvalidDayInput() {
        System.out.println(LINE_SEPARATOR + INVALID_DAY.getErrorMessage() + LINE_SEPARATOR);
    }

    public static void printInvalidOrderInput() {
        System.out.println(LINE_SEPARATOR + INVALID_ORDER.getErrorMessage() + LINE_SEPARATOR);
    }
}
