package christmas.constants.menu;

import java.util.Arrays;
import java.util.List;

public enum Appetizer implements Menu {
    WHITE_MUSHROOM_SOUP(6_000, "양송이수프"),
    TAPAS(5_500, "타파스"),
    CAESAR_SALAD(8_000, "시저샐러드");

    private final int price;
    private final String name;

    Appetizer(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public static List<Appetizer> getAppetizers() {
        return Arrays.stream(Appetizer.values()).toList();
    }

    public static int getPriceBy(String name) {
        return Arrays.stream(Appetizer.values())
                .filter(appetizer -> appetizer.getName().equals(name))
                .map(Appetizer::getPrice)
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
