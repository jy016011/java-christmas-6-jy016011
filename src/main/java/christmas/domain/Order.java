package christmas.domain;

import christmas.constants.Error;
import christmas.constants.menu.Dessert;
import christmas.constants.menu.Drink;
import christmas.constants.menu.MainDish;
import christmas.utils.ArgumentValidator;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private final static int MIN_COUNT = 1;
    private final static int MAX_COUNT = 20;
    private final static int NOTHING = 0;
    private final static int MIN_TOTAL = 10_000;
    private final static int EVENT_TOTAL = 120_000;
    private final static Error ERROR_HEADER = Error.ERROR_HEADER;

    private final HashMap<Dish, Integer> dishes = new LinkedHashMap<>();
    private final int totalPrice;

    public Order(List<String> dishNames, List<Integer> dishCounts) {
        validate(dishNames, dishCounts);
        setDishes(dishNames, dishCounts);
        totalPrice = setTotalPrice();
    }

    public Map<Dish, Integer> getOrderedDishes() {
        return Collections.unmodifiableMap(dishes);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isEventTarget() {
        return totalPrice >= MIN_TOTAL;
    }

    public boolean canGetGift() {
        return totalPrice >= EVENT_TOTAL;
    }

    public int getMainDishCount() {
        int count = NOTHING;
        for (Dish dish : dishes.keySet()) {
            if (dish.getType() == MainDish.class) {
                count += dishes.get(dish);
            }
        }
        return count;
    }

    public int getDessertCount() {
        int count = NOTHING;
        for (Dish dish : dishes.keySet()) {
            if (dish.getType() == Dessert.class) {
                count += dishes.get(dish);
            }
        }
        return count;
    }
    
    private void setDishes(List<String> dishNames, List<Integer> dishCounts) {
        for (int i = NOTHING; i < dishNames.size(); i++) {
            dishes.put(new Dish(dishNames.get(i)), dishCounts.get(i));
        }
    }

    private int setTotalPrice() {
        int totalPrice = NOTHING;
        for (Dish dish : dishes.keySet()) {
            int dishCount = dishes.get(dish);
            totalPrice += dish.getPrice() * dishCount;
        }
        return totalPrice;
    }

    private void validate(List<String> dishNames, List<Integer> dishCounts) {
        validateNames(dishNames);
        validateCounts(dishCounts);
    }

    private void validateNames(List<String> dishNames) {
        ArgumentValidator.isUnique(dishNames);
        validateNotAllDrinks(dishNames);
    }

    private void validateNotAllDrinks(List<String> dishNames) {
        boolean isAllDrink = new HashSet<>(Drink.getDrinks().stream()
                .map(Drink::getName).toList())
                .containsAll(dishNames);
        if (isAllDrink) {
            throw new IllegalArgumentException(ERROR_HEADER.getErrorMessage() + " 읍료만 주문할 수 없습니다.");
        }
    }

    private void validateCounts(List<Integer> dishCounts) {
        int totalCounts = NOTHING;
        for (int dishCount : dishCounts) {
            ArgumentValidator.isNotLessThan(dishCount, MIN_COUNT);
            ArgumentValidator.isNotGreaterThan(dishCount, MAX_COUNT);
            totalCounts += dishCount;
        }
        ArgumentValidator.isNotGreaterThan(totalCounts, MAX_COUNT);
    }
}
