package christmas.domain;

import christmas.utils.Validator;
import java.time.LocalDate;

public class VisitingDate {
    private static final int YEAR = 2023;
    private static final int DECEMBER = 12;
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;

    private final LocalDate localDate;

    public VisitingDate(int day) {
        validate(day);
        localDate = LocalDate.of(YEAR, DECEMBER, day);
    }

    private void validate(int day) {
        Validator.validateNotLessThan(day, FIRST_DAY);
        Validator.validateNotGreaterThan(day, LAST_DAY);
    }
}
