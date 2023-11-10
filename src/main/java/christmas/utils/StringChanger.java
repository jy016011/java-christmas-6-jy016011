package christmas.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringChanger {
    private StringChanger() {
    }
    
    public static List<String> toTrimmedStringList(String input, String separator) {
        return Arrays.stream(
                        input.split(separator))
                .map(String::trim).collect(Collectors.toList()
                );
    }

    public static int toInteger(String input) {
        return Integer.parseInt(input);
    }
}
