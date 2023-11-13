package christmas.constants;

import christmas.constants.dishes.Drink;

public enum Event {
    CHRISTMAS_D_DAY(1_000, 100, "크리스마스 디데이 할인", ""),
    WEEKDAY(0, 2_023, "평일 할인", ""),
    WEEKEND(0, 2_023, "주말 할인", ""),
    SPECIAL(1_000, 0, "특별 할인", ""),
    PRESENT(Drink.CHAMPAGNE.getPrice(), 0, "증정 이벤트", Drink.CHAMPAGNE.getName() + " 1개");

    private final int baseDiscount;
    private final int unitOfChange;
    private final String name;
    private final String gift;

    Event(int baseDiscount, int unitOfChange, String name, String gift) {
        this.baseDiscount = baseDiscount;
        this.unitOfChange = unitOfChange;
        this.name = name;
        this.gift = gift;
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

    public String getGift() {
        return gift;
    }
}
