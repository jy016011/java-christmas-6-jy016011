package christmas.constants.menu;

import java.util.Arrays;
import java.util.List;

public enum MainDish implements Menu {
    T_BONE_STEAK(55_000, "티본스테이크"),
    BARBECUE_RIBS(54_000, "바비큐립"),
    SEAFOOD_PASTA(35_000, "해산물파스타"),
    CHRISTMAS_PASTA(25_000, "크리스마스파스타");

    private final int price;
    private final String name;

    MainDish(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public static List<MainDish> getMainDishes() {
        return Arrays.stream(MainDish.values()).toList();
    }

    public static int getPriceBy(String name) {
        return Arrays.stream(MainDish.values())
                .filter(mainDish -> mainDish.getName().equals(name))
                .map(MainDish::getPrice)
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
