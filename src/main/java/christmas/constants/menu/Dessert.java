package christmas.constants.menu;

import java.util.Arrays;
import java.util.List;

public enum Dessert {
    CHOCO_CAKE(15_000, "초코케이크"),
    ICE_CREAM(5_000, "아이스크림");

    private final int price;
    private final String name;

    Dessert(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public static int getPriceBy(String name) {
        return Arrays.stream(Dessert.values())
                .filter(dessert -> dessert.getName().equals(name))
                .map(Dessert::getPrice)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public static List<Dessert> getDesserts() {
        return Arrays.stream(Dessert.values()).toList();
    }
}
