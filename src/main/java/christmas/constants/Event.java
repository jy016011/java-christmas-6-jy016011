package christmas.constants;

public enum Event {
    CHRISTMAS_D_DAY(1_000, 100, "크리스마스 디데이 할인"),
    WEEKDAY(0, 2_023, "평일 할인"),
    WEEKEND(0, 2_023, "주말 할인"),
    SPECIAL(1_000, 0, "특별 할인"),
    GIFT(25_000, 0, "증정 이벤트");

    private final int baseDiscount;
    private final int unitOfChange;
    private final String name;

    Event(int baseDiscount, int unitOfChange, String name) {
        this.baseDiscount = baseDiscount;
        this.unitOfChange = unitOfChange;
        this.name = name;
    }

    public int getBaseDiscount() {
        return baseDiscount;
    }

    public int getUnitOfChange() {
        return unitOfChange;
    }

    public String getName() {
        return name;
    }
}
