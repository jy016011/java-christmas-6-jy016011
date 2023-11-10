package christmas.constants.dishes;

import java.util.Arrays;
import java.util.List;

public enum MainDish {
    T_BONE_STEAK(55_000, "55,000원", "티본스테이크"),
    BARBECUE_RIBS(54_000, "54,000원", "바비큐립"),
    SEAFOOD_PASTA(35_000, "35,000원", "해산물파스타"),
    CHRISTMAS_PASTA(25_000, "25,000원", "크리스마스파스타");

    private final int price;
    private final String priceToWon;
    private final String name;

    MainDish(int price, String priceToWon, String name) {
        this.price = price;
        this.priceToWon = priceToWon;
        this.name = name;
    }

    public static int getPriceBy(String name) {
        return Arrays.stream(MainDish.values())
                .filter(appetizer -> appetizer.getName().equals(name))
                .map(MainDish::getPrice)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public int getPrice() {
        return price;
    }

    public String getPriceToWon() {
        return priceToWon;
    }

    public String getName() {
        return name;
    }

    public static List<MainDish> getMainDishes() {
        return Arrays.stream(MainDish.values()).toList();
    }
}
