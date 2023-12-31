package christmas.constants.event;

import java.util.Arrays;
import java.util.List;

public enum Discount implements Event {
    CHRISTMAS_D_DAY(1_000, 100, "크리스마스 디데이 할인"),
    WEEKDAY(0, 2_023, "평일 할인"),
    WEEKEND(0, 2_023, "주말 할인"),
    SPECIAL(1_000, 0, "특별 할인");

    private final int baseDiscount;
    private final int unitOfChange;
    private final String contentName;

    Discount(int baseDiscount, int unitOfChange, String contentName) {
        this.baseDiscount = baseDiscount;
        this.unitOfChange = unitOfChange;
        this.contentName = contentName;
    }

    public static List<Discount> getAll() {
        return Arrays.stream(Discount.values()).toList();
    }

    @Override
    public int getBenefit(int additionalCount) {
        return baseDiscount + unitOfChange * additionalCount;
    }

    @Override
    public String getContentName() {
        return contentName;
    }
}
