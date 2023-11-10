package christmas.domain;

import christmas.constants.Error;
import christmas.constants.Menu;
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
