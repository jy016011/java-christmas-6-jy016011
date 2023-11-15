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

public class Dishes {
    private final static int NOTHING = 0;
    private final static int MIN_COUNT = 1;
    private final static int MAX_COUNT = 20;
    private final static int FIRST = 0;
    private final static Error ERROR_HEADER = Error.ERROR_HEADER;

    private final HashMap<Dish, Integer> orderDetails = new LinkedHashMap<>();

    public Dishes(List<String> dishNames, List<Integer> dishCounts) {
        setOrderDetails(dishNames, dishCounts);
    }

    public Map<Dish, Integer> getOrderedDishes() {
        return Collections.unmodifiableMap(orderDetails);
    }

    public int getTotalPrice() {
        int totalPrice = NOTHING;
        for (Dish dish : orderDetails.keySet()) {
            int dishCount = orderDetails.get(dish);
            totalPrice += dish.getPrice() * dishCount;
        }
        return totalPrice;
    }

    public int getMainDishCount() {
        int count = NOTHING;
        for (Dish dish : orderDetails.keySet()) {
            if (dish.getType() == MainDish.class) {
                count += orderDetails.get(dish);
            }
        }
        return count;
    }

    public int getDessertCount() {
        int count = NOTHING;
        for (Dish dish : orderDetails.keySet()) {
            if (dish.getType() == Dessert.class) {
                count += orderDetails.get(dish);
            }
        }
        return count;
    }

    private void setOrderDetails(List<String> dishNames, List<Integer> dishCounts) {
        validateNameAndCount(dishNames, dishCounts);
        for (int each = FIRST; each < dishNames.size(); each++) {
            orderDetails.put(new Dish(dishNames.get(each)), dishCounts.get(each));
        }
    }

    private void validateNameAndCount(List<String> dishNames, List<Integer> dishCounts) {
        validateNames(dishNames);
        validateCounts(dishCounts);
    }

    private void validateNames(List<String> dishNames) {
        ArgumentValidator.isUnique(dishNames);
        validateNotAllDrinks(dishNames);
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

    private void validateNotAllDrinks(List<String> dishNames) {
        boolean isAllDrink = new HashSet<>(Drink.getDrinks().stream()
                .map(Drink::getName).toList())
                .containsAll(dishNames);
        if (isAllDrink) {
            throw new IllegalArgumentException(ERROR_HEADER.getErrorMessage() + " 읍료만 주문할 수 없습니다.");
        }
    }
}
