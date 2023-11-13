package christmas.constants;

public enum Error {
    ERROR_HEADER("[ERROR]"),
    INVALID_DAY(ERROR_HEADER.errorMessage + " 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER(ERROR_HEADER.errorMessage + " 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String errorMessage;

    Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
