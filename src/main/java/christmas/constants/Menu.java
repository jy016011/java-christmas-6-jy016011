package christmas.constants;

import christmas.constants.menu.Appetizer;
import christmas.constants.menu.Dessert;
import christmas.constants.menu.Drink;
import christmas.constants.menu.MainDish;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Menu {
    APPETIZER(
            "에피타이저", Appetizer.getAppetizers()
            .stream().map(Appetizer::getName).toList()
    ),
    MAIN_DISH(
            "메인", MainDish.getMainDishes()
            .stream().map(MainDish::getName).toList()
    ),
    DESSERT(
            "디저트", Dessert.getDesserts()
            .stream().map(Dessert::getName).toList()
    ),
    DRINK(
            "음료", Drink.getDrinks()
            .stream().map(Drink::getName).toList()
    );

    private final String type;
    private final List<String> names;

    Menu(String type, List<String> names) {
        this.type = type;
        this.names = names;
    }

    public static Menu getBy(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getNames().contains(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getType() {
        return type;
    }

    public List<String> getNames() {
        return new ArrayList<>(names);
    }
}
