package christmas.domain;

import christmas.utils.Validator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VisitingDate {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;
    private static final int CHRISTMAS = 25;
    private static final List<String> weekends = new ArrayList<>(List.of("금", "토"));
    private static final List<String> weekdays = new ArrayList<>(List.of("일", "월", "화", "수", "목"));
    private static final List<Integer> specialDays = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));

    private final LocalDate localDate;

    public VisitingDate(int day) {
        validate(day);
        localDate = LocalDate.of(YEAR, MONTH, day);
    }

    public int getDifferenceFromFirstDay() {
        return localDate.getDayOfMonth() - FIRST_DAY;
    }

    public boolean isChristmasDDay() {
        return localDate.getDayOfMonth() <= CHRISTMAS;
    }

    public boolean isWeekday() {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        String weekday = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        return weekdays.contains(weekday);
    }

    public boolean isSpecialDay() {
        return specialDays.contains(localDate.getDayOfMonth());
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        String weekend = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        return weekends.contains(weekend);
    }

    private void validate(int day) {
        Validator.validateNotLessThan(day, FIRST_DAY);
        Validator.validateNotGreaterThan(day, LAST_DAY);
    }
}
