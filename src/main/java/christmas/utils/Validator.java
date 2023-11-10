package christmas.utils;

import christmas.constants.Error;
import java.util.List;

public class Validator {
    private static final String ERROR_MESSAGE_HEADER = Error.ERROR_HEADER.getErrorMessage();

    private Validator() {
    }

    public static void validateIsNumber(String input) {
        boolean isNotNumber = !input.chars().allMatch(Character::isDigit);
        if (isNotNumber) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " 숫자만 입력해주세요.");
        }
    }

    public static void validateNotLessThan(int checkingNumber, int standardNumber) {
        if (checkingNumber < standardNumber) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + standardNumber + " 이상의 수를 입력하세요.");
        }
    }

    public static void validateNotGreaterThan(int checkingNumber, int standardNumber) {
        if (checkingNumber > standardNumber) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + standardNumber + " 이하의 수를 입력하세요.");
        }
    }

    public static void validateSize(int size, int limit) {
        boolean notMatchSize = size != limit;
        if (notMatchSize) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + limit + "개만큼 입력하세요.");
        }
    }

    public static void validateUniqueNames(List<String> names) {
        boolean isDuplicated = (
                names.stream().distinct().count() != names.size()
        );
        if (isDuplicated) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " 중복되지 않는 이름만 입력하세요.");
        }
    }

    public static void raiseIllegalArgumentException(String errorMessage) {
        throw new IllegalArgumentException(errorMessage);
    }
}
