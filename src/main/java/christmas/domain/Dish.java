package christmas.domain;

import christmas.constants.Error;
import christmas.constants.MenuBoard;
import christmas.constants.menu.Menu;
import java.util.Objects;

public class Dish {
    private static final Error ERROR_HEADER = Error.ERROR_HEADER;
    private final Menu menu;

    public Dish(String name) {
        validate(name);
        this.menu = MenuBoard.getBy(name);
    }

    public Class<? extends Menu> getType() {
        return menu.getClass();
    }

    public int getPrice() {
        return menu.getPrice();
    }

    public String getName() {
        return menu.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
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
        return this.menu == dish.menu;
    }

    private void validate(String name) {
        boolean isNotInMenu = MenuBoard.getAllMenu().stream()
                .noneMatch(menu -> menu.getName().equals(name));
        if (isNotInMenu) {
            throw new IllegalArgumentException(ERROR_HEADER.getErrorMessage() + " 메뉴판에 있는 메뉴만 주문하세요.");
        }
    }
}
