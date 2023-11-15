package christmas.constants;

import christmas.constants.menu.Appetizer;
import christmas.constants.menu.Dessert;
import christmas.constants.menu.Drink;
import christmas.constants.menu.MainDish;
import christmas.constants.menu.Menu;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuBoard {

    public static List<Menu> getAll() {
        return Stream.of(Appetizer.getAll(), Dessert.getAll(), Drink.getAll(), MainDish.getAll())
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static Menu getBy(String name) {
        return getAll().stream().filter(menu -> menu.getName().equals(name)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
