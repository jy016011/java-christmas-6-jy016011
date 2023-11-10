package christmas.domain;

import christmas.constants.Error;
import christmas.constants.Menu;
import christmas.constants.dishes.Appetizer;
import christmas.constants.dishes.Dessert;
import christmas.constants.dishes.Drink;
import christmas.constants.dishes.MainDish;
import java.util.Arrays;

public class Dish {
    private static final Error ERROR_HEADER = Error.ERROR_HEADER;
    private final String name;

    public Dish(String name) {
        validate(name);
        this.name = name;
    }

    public String getType() {
        return Menu.getMenuBy(name).getType();
    }

    public int getPrice() {
        if (getType().equals(Menu.APPETIZER.getType())) {
            return Appetizer.getPriceBy(name);
        }
        if (getType().equals(Menu.MAIN_DISH.getType())) {
            return MainDish.getPriceBy(name);
        }
        if (getType().equals(Menu.DESSERT.getType())) {
            return Dessert.getPriceBy(name);
        }
        return Drink.getPriceBy(name);
    }

    public String getName() {
        return name;
    }

    private void validate(String name) {
        boolean isNotInMenu = Arrays.stream(Menu.values())
                .noneMatch(menu -> menu.getNames().contains(name));
        if (isNotInMenu) {
            throw new IllegalArgumentException(ERROR_HEADER.getErrorMessage());
        }
    }
}
