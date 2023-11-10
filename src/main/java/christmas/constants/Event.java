package christmas.constants;

public enum Event {
    CHRISTMAS_D_DAY(1_000, 100),
    WEEKDAY(0, 2_023),
    WEEKEND(0, 2_023),
    SPECIAL(1_000, 0),
    GIFT(25_000, 0);

    private final int baseDiscount;
    private final int unitOfChange;

    Event(int baseDiscount, int unitOfChange) {
        this.baseDiscount = baseDiscount;
        this.unitOfChange = unitOfChange;
    }

    public int getBaseDiscount() {
        return baseDiscount;
    }

    public int getUnitOfChange() {
        return unitOfChange;
    }
}
