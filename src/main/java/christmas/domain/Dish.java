package christmas.domain;

import christmas.constants.Error;
import christmas.constants.MenuBoard;
import christmas.constants.menu.Appetizer;
import christmas.constants.menu.Dessert;
import christmas.constants.menu.Drink;
import christmas.constants.menu.MainDish;
import christmas.constants.menu.Menu;
import java.util.Objects;

public class Dish {
    private static final Error ERROR_HEADER = Error.ERROR_HEADER;
    private final String name;

    public Dish(String name) {
        validate(name);
        this.name = name;
    }

    public Class<? extends Menu> getType() {
        return MenuBoard.getBy(name).getClass();
    }

    public int getPrice() {
        if (getType() == Appetizer.class) {
            return Appetizer.getPriceBy(name);
        }
        if (getType() == MainDish.class) {
            return MainDish.getPriceBy(name);
        }
        if (getType() == Dessert.class) {
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
        boolean isNotInMenu = MenuBoard.getAllMenu().stream()
                .noneMatch(menu -> menu.getName().equals(name));
        if (isNotInMenu) {
            throw new IllegalArgumentException(ERROR_HEADER.getErrorMessage() + " 메뉴판에 있는 메뉴만 주문하세요.");
        }
    }
}
