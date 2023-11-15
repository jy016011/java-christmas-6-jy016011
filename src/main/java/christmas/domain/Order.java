package christmas.domain;

import christmas.utils.ArgumentValidator;
import christmas.utils.StringChanger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    private final static String DISH_SEPARATOR = ",";
    private final static String DISH_NAME_AND_COUNT_SEPARATOR = "-";

    private final static int VALID_DIFFERENCE = 1;
    private final static int VALID_SIZE = 2;

    private final static int DISH_NAME = 0;
    private final static int DISH_COUNT = 1;
    private final static int MIN_TOTAL = 10_000;
    private final static int EVENT_TOTAL = 120_000;

    private final Dishes dishes;
    private final int totalPrice;

    public Order(String userInput) {
        validateFormat(userInput);
        dishes = setDishes(userInput);
        totalPrice = setTotalPrice();
    }

    public Map<Dish, Integer> getOrderedDishes() {
        return dishes.getOrderedDishes();
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
        return dishes.getMainDishCount();
    }

    public int getDessertCount() {
        return dishes.getDessertCount();
    }

    private Dishes setDishes(String userInput) {
        List<String> dishNames = new ArrayList<>();
        List<Integer> dishCounts = new ArrayList<>();
        changeFormat(userInput, dishNames, dishCounts);
        return new Dishes(dishNames, dishCounts);
    }

    private int setTotalPrice() {
        return dishes.getTotalPrice();
    }


    private void changeFormat(String userInput, List<String> dishNames, List<Integer> dishCounts) {
        List<String> orderInput = StringChanger.toTrimmedStringList(userInput, DISH_SEPARATOR);
        separateNameAndCount(orderInput, dishNames, dishCounts);
    }

    private void separateNameAndCount(List<String> orderInput, List<String> dishNames, List<Integer> dishCounts) {
        for (String eachOrder : orderInput) {
            List<String> dishNameAndCount = StringChanger.toTrimmedStringList(eachOrder, DISH_NAME_AND_COUNT_SEPARATOR);
            validateIsSeparated(dishNameAndCount);
            dishNames.add(dishNameAndCount.get(DISH_NAME));
            dishCounts.add(StringChanger.toInteger(dishNameAndCount.get(DISH_COUNT)));
        }
    }

    private void validateIsSeparated(List<String> dishNameAndCount) {
        ArgumentValidator.isEqual(dishNameAndCount.size(), VALID_SIZE);
    }

    private void validateFormat(String userInput) {
        int dishSeparatorCount = (int) userInput.chars()
                .filter(c -> c == StringChanger.toChar(DISH_SEPARATOR)).count();
        int dishNameAndCountSeparatorCount = (int) userInput.chars()
                .filter(c -> c == StringChanger.toChar(DISH_NAME_AND_COUNT_SEPARATOR)).count();
        ArgumentValidator.isEqual(
                dishSeparatorCount,
                dishNameAndCountSeparatorCount - VALID_DIFFERENCE
        );
    }
}
