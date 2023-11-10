package christmas.domain;

import christmas.constants.Error;
import christmas.constants.Menu;
import christmas.utils.Validator;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Order {
    private final static int MIN_COUNT = 1;
    private final static int MAX_COUNT = 20;
    private final static int NOTHING = 0;
    private final static int COUNT_ONE = MIN_COUNT;
    private final static int MIN_TOTAL = 10_000;
    private final static int EVENT_TOTAL = 120_000;
    private final static Error ERROR_HEADER = Error.ERROR_HEADER;

    private final HashMap<Dish, Integer> dishes = new HashMap<>();
    private final int totalPrice;

    public Order(List<String> dishNames, List<Integer> dishCounts) {
        validate(dishNames, dishCounts);
        setDishes(dishNames, dishCounts);
        totalPrice = setTotalPrice();
    }

    public Map<Dish, Integer> getDishes() {
        return Collections.unmodifiableMap(dishes);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isEventTarget() {
        return totalPrice > MIN_TOTAL;
    }

    public boolean canGetGift() {
        return totalPrice >= EVENT_TOTAL;
    }

    private void changeFormat(Map<String, Integer> order, String dishName) {
        if (order.containsKey(dishName)) {
            order.put(dishName, order.get(dishName) + COUNT_ONE);
            return;
        }
        order.put(dishName, COUNT_ONE);
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
            dishes.put(new Dish(dishNames.get(i)), dishCounts.get(i));
        }
    }

    private int setTotalPrice() {
        int totalPrice = NOTHING;
        for (Dish dish : dishes.keySet()) {
            totalPrice += dish.getPrice();
        }
        return totalPrice;
    }
}
