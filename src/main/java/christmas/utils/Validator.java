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
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " 숫자가 아닙니다.");
        }
    }

    public static void validateNotLessThan(int checkingNumber, int standardNumber) {
        if (checkingNumber < standardNumber) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + standardNumber + " 이상의 수가 아닙니다.");
        }
    }

    public static void validateNotGreaterThan(int checkingNumber, int standardNumber) {
        if (checkingNumber > standardNumber) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + standardNumber + " 이하의 수가 아닙니다.");
        }
    }

    public static void validateIsEqual(int checkingNumber, int standardNumber) {
        boolean notEqual = checkingNumber != standardNumber;
        if (notEqual) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + standardNumber + "와 같은 수가 아닙니다.");
        }
    }

    public static void validateIsUnique(List<String> inputs) {
        boolean isDuplicated = (
                inputs.stream().distinct().count() != inputs.size()
        );
        if (isDuplicated) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " 중복된 입력이 있습니다.");
        }
    }

    public static void raiseIllegalArgumentException(String errorMessage) {
        throw new IllegalArgumentException(errorMessage);
    }
}
