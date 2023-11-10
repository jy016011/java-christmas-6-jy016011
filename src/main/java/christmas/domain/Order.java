package christmas.domain;

import christmas.constants.Error;
import christmas.constants.Menu;
import christmas.utils.Validator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Order {
    private final static int MIN_COUNT = 1;
    private final static int MAX_COUNT = 20;
    private final static int NOTHING = 0;
    private final static int COUNT_ONE = MIN_COUNT;
    private final static Error ERROR_HEADER = Error.ERROR_HEADER;

    private final List<Dish> dishes = new ArrayList<>();

    public Order(List<String> dishNames, List<Integer> dishCounts) {
        validate(dishNames, dishCounts);
        setDishes(dishNames, dishCounts);
    }

    public HashMap<String, Integer> getOrderInMapFormat() {
        HashMap<String, Integer> order = new HashMap<>();
        for (Dish dish : dishes) {
            String dishName = dish.getName();
            changeFormat(order, dishName);
        }
        return order;
    }

    private void changeFormat(Map<String, Integer> order, String dishName) {
        if (order.containsKey(dishName)) {
            order.put(dishName, order.get(dishName) + COUNT_ONE);
            return;
        }
        order.put(dishName, COUNT_ONE);
    }

    public int getTotalPrice() {
        int totalPrice = NOTHING;
        for (Dish dish : dishes) {
            totalPrice += dish.getPrice();
        }
        return totalPrice;
    }

    private void validate(List<String> dishNames, List<Integer> dishCounts) {
        validateNames(dishNames);
        validateCounts(dishCounts);
    }

    private void validateNames(List<String> dishNames) {
        Validator.validateUniqueNames(dishNames);
        validateNotAllDrinks(dishNames);
    }

    private void validateNotAllDrinks(List<String> dishNames) {
        boolean isAllDrink = new HashSet<>(Menu.DRINK.getNames()).containsAll(dishNames);
        if (isAllDrink) {
            throw new IllegalArgumentException(ERROR_HEADER.getErrorMessage());
        }
    }

    private void validateCounts(List<Integer> dishCounts) {
        int totalCounts = NOTHING;
        for (int dishCount : dishCounts) {
            Validator.validateNotLessThan(dishCount, MIN_COUNT);
            Validator.validateNotGreaterThan(dishCount, MAX_COUNT);
            totalCounts += dishCount;
        }
        Validator.validateNotGreaterThan(totalCounts, MAX_COUNT);
    }

    private void setDishes(List<String> dishNames, List<Integer> dishCounts) {
        for (int i = NOTHING; i < dishNames.size(); i++) {
            setDish(dishNames.get(i), dishCounts.get(i));
        }
    }

    private void setDish(String dishName, int dishCount) {
        for (int i = NOTHING; i < dishCount; i++) {
            dishes.add(new Dish(dishName));
        }
    }
}
