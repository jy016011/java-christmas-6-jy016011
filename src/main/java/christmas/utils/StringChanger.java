package christmas.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringChanger {
    private static final int INCLUDING_LAST_BLANK = -1;
    private static final int ITSELF = 0;

    private StringChanger() {
    }

    public static List<String> toTrimmedStringList(String input, String separator) {
        return Arrays.stream(
                        input.split(separator, INCLUDING_LAST_BLANK))
                .map(String::trim).collect(Collectors.toList()
                );
    }

    public static int toInteger(String input) {
        return Integer.parseInt(input);
    }

    public static char toChar(String input) {
        return input.charAt(ITSELF);
    }
}
