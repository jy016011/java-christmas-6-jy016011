package christmas.domain;

import christmas.constants.Error;
import christmas.constants.Menu;
import christmas.constants.dishes.Appetizer;
import christmas.constants.dishes.Dessert;
import christmas.constants.dishes.Drink;
import christmas.constants.dishes.MainDish;
import java.util.Arrays;
import java.util.Objects;

public class Dish {
    private static final Error ERROR_HEADER = Error.ERROR_HEADER;
    private final String name;

    public Dish(String name) {
        validate(name);
        this.name = name;
    }

    public String getType() {
        return Menu.getBy(name).getType();
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

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dish dish = (Dish) o;
        return this.name.equals(dish.name);
    }

    private void validate(String name) {
        boolean isNotInMenu = Arrays.stream(Menu.values())
                .noneMatch(menu -> menu.getNames().contains(name));
        if (isNotInMenu) {
            throw new IllegalArgumentException(ERROR_HEADER.getErrorMessage() + " 메뉴판에 있는 메뉴만 주문하세요.");
        }
    }
}
