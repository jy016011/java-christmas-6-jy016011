package christmas.service;

import christmas.constants.Error;
import christmas.constants.Event;
import christmas.domain.Dish;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.utils.StringChanger;
import christmas.utils.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultService {
    private final static Error ERROR_HEADER = Error.ERROR_HEADER;
    private final static String DISH_SEPARATOR = ",";
    private final static String SEPARATOR_DISH_NAME_AND_COUNT = "-";
    private final static String NONE = "없음";
    private final static int DISH_NAME = 0;
    private final static int DISH_COUNT = 1;
    private final static int VALID_DIFFERENCE = 1;
    private final static int NOTHING = 0;

    private Order order;
    private VisitingDate visitingDate;

    public void setDate(String userInput) {
        int day = toNumber(userInput);
        visitingDate = new VisitingDate(day);
    }

    public void setOrder(String userInput) {
        List<String> dishNames = new ArrayList<>();
        List<Integer> dishCounts = new ArrayList<>();
        validateFormat(userInput);
        List<String> orderInput = StringChanger.toTrimmedStringList(userInput, DISH_SEPARATOR);
        separateNameAndCount(orderInput, dishNames, dishCounts);
        order = new Order(dishNames, dishCounts);
    }

    public Map<Dish, Integer> getOrderedMenu() {
        return order.getOrderedDishes();
    }

    public int getTotalPrice() {
        return order.getTotalPrice();
    }

    public String getGift() {
        if (order.isEventTarget() && order.canGetGift()) {
            return Event.PRESENT.getGift();
        }
        return NONE;
    }

    public int getChristmasDDayDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isChristmasDDay()) {
            int additionalCount = visitingDate.getDifferenceFromFirstDay();
            discount = Event.CHRISTMAS_D_DAY.getBaseDiscount() +
                    Event.CHRISTMAS_D_DAY.getUnitOfChange() * additionalCount;
        }
        return discount;
    }

    public int getWeekdayDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isWeekday()) {
            int additionalCount = order.getDessertCount();
            discount = Event.WEEKDAY.getUnitOfChange() * additionalCount;
        }
        return discount;
    }

    public int getWeekendDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isWeekend()) {
            int additionalCount = order.getMainDishCount();
            discount = Event.WEEKEND.getUnitOfChange() * additionalCount;
        }
        return discount;
    }

    public int getSpecialDiscount() {
        int dicount = NOTHING;
        if (order.isEventTarget() && visitingDate.isSpecialDay()) {
            dicount = Event.SPECIAL.getBaseDiscount();
        }
        return dicount;
    }

    public int getGiftBenefit() {
        if (order.isEventTarget() && order.canGetGift()) {
            return Event.PRESENT.getBaseDiscount();
        }
        return NOTHING;
    }

    public int getTotalDiscount() {
        int totalDiscount = NOTHING;
        totalDiscount += getChristmasDDayDiscount();
        totalDiscount += getWeekdayDiscount();
        totalDiscount += getWeekendDiscount();
        totalDiscount += getSpecialDiscount();
        return totalDiscount;
    }

    public int getTotalDiscountedPrice() {
        int totalPriceDiscounted = order.getTotalPrice();
        totalPriceDiscounted -= getTotalDiscount();
        return totalPriceDiscounted;
    }

    public int getTotalBenefit() {
        return getTotalDiscount() + getGiftBenefit();
    }

    private void validateFormat(String userInput) {
        int dishSeparatorCount = (int) userInput.chars()
                .filter(c -> c == StringChanger.toChar(DISH_SEPARATOR)).count();
        int dishNameAndCountSeparatorCount = (int) userInput.chars()
                .filter(c -> c == StringChanger.toChar(SEPARATOR_DISH_NAME_AND_COUNT)).count();
        Validator.validateIsEqual(
                dishSeparatorCount,
                dishNameAndCountSeparatorCount - VALID_DIFFERENCE
        );
    }

    private void separateNameAndCount(List<String> orderInput, List<String> dishNames, List<Integer> dishCounts) {
        for (String eachOrder : orderInput) {
            List<String> dishNameAndCount = StringChanger.toTrimmedStringList(eachOrder, SEPARATOR_DISH_NAME_AND_COUNT);
            dishNames.add(dishNameAndCount.get(DISH_NAME));
            validateCount(dishNameAndCount, dishCounts);
        }
    }

    private void validateCount(List<String> dishNameAndCount, List<Integer> dishCounts) {
        try {
            String dishCount = dishNameAndCount.get(DISH_COUNT);
            dishCounts.add(toNumber(dishCount));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(ERROR_HEADER.getErrorMessage() + " 예시에 나온 형식대로 입력하세요.");
        }
    }

    private int toNumber(String userInput) {
        Validator.validateIsNumber(userInput);
        return StringChanger.toInteger(userInput);
    }
}
