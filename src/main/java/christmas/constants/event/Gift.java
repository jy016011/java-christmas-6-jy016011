package christmas.constants.event;

import christmas.constants.menu.Drink;
import java.util.Arrays;
import java.util.List;

public enum Gift implements Event {
    NONE(0, "없음", ""),
    CHAMPAGNE(Drink.CHAMPAGNE.getPrice(), Drink.CHAMPAGNE.getName(), "증정 이벤트");

    private final int price;
    private final String menu;
    private final String eventName;

    Gift(int price, String menu, String eventName) {
        this.price = price;
        this.menu = menu;
        this.eventName = eventName;
    }

    public static List<Gift> getGifts() {
        return Arrays.stream(Gift.values()).filter(gift -> gift != NONE).toList();
    }

    public String getItemBy(int count) {
        return menu + String.format(" %d개", count);
    }

    public String getMenu() {
        return menu;
    }

    @Override
    public int getBenefit(int count) {
        return price * count;
    }

    @Override
    public String getEventName() {
        return eventName;
    }
}
