package christmas.constants.menu;

import java.util.Arrays;
import java.util.List;

public enum Drink implements Menu {
    ZERO_COKE(3_000, "제로콜라"),
    RED_WINE(60_000, "레드와인"),
    CHAMPAGNE(25_000, "샴페인");

    private final int price;
    private final String name;

    Drink(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public static List<Drink> getDrinks() {
        return Arrays.stream(Drink.values()).toList();
    }

    public static int getPriceBy(String name) {
        return Arrays.stream(Drink.values())
                .filter(drink -> drink.getName().equals(name))
                .map(Drink::getPrice)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }
}
