package christmas.service;

import christmas.constants.Badge;
import christmas.constants.Event;
import christmas.domain.Dish;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.utils.StringChanger;
import christmas.utils.Validator;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultService {
    private final static String DISH_SEPARATOR = ",";
    private final static String DISH_NAME_AND_COUNT_SEPARATOR = "-";
    private final static String NONE = "없음";
    private final static int NOTHING = 0;
    private final static int DISH_NAME = 0;
    private final static int DISH_COUNT = 1;
    private final static int VALID_SIZE = 2;
    private final static int VALID_DIFFERENCE = 1;
    private final static int FIRST_DAY = 1;

    private Order order;
    private VisitingDate visitingDate;

    public void setDate(String userInput) {
        int day = toNumber(userInput);
        visitingDate = new VisitingDate(day);
    }

    public int getDate() {
        return visitingDate.getDifferenceFromFirstDay() + FIRST_DAY;
    }

    public void setOrder(String userInput) {
        List<String> dishNames = new ArrayList<>();
        List<Integer> dishCounts = new ArrayList<>();
        validateFormat(userInput);
        List<String> orderInput = StringChanger.toTrimmedStringList(userInput, DISH_SEPARATOR);
        separateNameAndCount(orderInput, dishNames, dishCounts);
        order = new Order(dishNames, dishCounts);
    }

    public Map<Dish, Integer> getUserOrder() {
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

    public int getTotalDiscountedPrice() {
        return order.getTotalPrice() - getTotalDiscount();
    }

    public int getTotalBenefit() {
        return getTotalDiscount() + getGiftBenefit();
    }

    public Map<String, Integer> getSynthesizedAllBenefits() {
        Map<String, Integer> benefitsDetails = new LinkedHashMap<>();
        for (Event event : Event.values()) {
            int benefit = getBenefitBy(event);
            if (benefit > NOTHING) {
                benefitsDetails.put(event.getName(), benefit);
            }
        }
        return benefitsDetails;
    }

    public Badge getBadge() {
        return Badge.getBy(getTotalBenefit());
    }

    private int getTotalDiscount() {
        int totalDiscount = NOTHING;
        totalDiscount += getChristmasDDayDiscount();
        totalDiscount += getWeekdayDiscount();
        totalDiscount += getWeekendDiscount();
        totalDiscount += getSpecialDiscount();
        return totalDiscount;
    }

    private int getBenefitBy(Event event) {
        if (event == Event.CHRISTMAS_D_DAY) {
            return getChristmasDDayDiscount();
        }
        if (event == Event.WEEKDAY) {
            return getWeekdayDiscount();
        }
        if (event == Event.WEEKEND) {
            return getWeekendDiscount();
        }
        if (event == Event.SPECIAL) {
            return getSpecialDiscount();
        }
        return getGiftBenefit();
    }

    private int getChristmasDDayDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isChristmasDDay()) {
            int additionalCount = visitingDate.getDifferenceFromFirstDay();
            discount = Event.CHRISTMAS_D_DAY.getBaseDiscount() +
                    Event.CHRISTMAS_D_DAY.getUnitOfChange() * additionalCount;
        }
        return discount;
    }

    private int getWeekdayDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isWeekday()) {
            int additionalCount = order.getDessertCount();
            discount = Event.WEEKDAY.getUnitOfChange() * additionalCount;
        }
        return discount;
    }

    private int getWeekendDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isWeekend()) {
            int additionalCount = order.getMainDishCount();
            discount = Event.WEEKEND.getUnitOfChange() * additionalCount;
        }
        return discount;
    }

    private int getSpecialDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isSpecialDay()) {
            discount = Event.SPECIAL.getBaseDiscount();
        }
        return discount;
    }

    private int getGiftBenefit() {
        if (order.isEventTarget() && order.canGetGift()) {
            return Event.PRESENT.getBaseDiscount();
        }
        return NOTHING;
    }

    private void validateFormat(String userInput) {
        int dishSeparatorCount = (int) userInput.chars()
                .filter(c -> c == StringChanger.toChar(DISH_SEPARATOR)).count();
        int dishNameAndCountSeparatorCount = (int) userInput.chars()
                .filter(c -> c == StringChanger.toChar(DISH_NAME_AND_COUNT_SEPARATOR)).count();
        Validator.validateIsEqual(
                dishSeparatorCount,
                dishNameAndCountSeparatorCount - VALID_DIFFERENCE
        );
    }

    private void separateNameAndCount(List<String> orderInput, List<String> dishNames, List<Integer> dishCounts) {
        for (String eachOrder : orderInput) {
            List<String> dishNameAndCount = StringChanger.toTrimmedStringList(eachOrder, DISH_NAME_AND_COUNT_SEPARATOR);
            validateIsSeparated(dishNameAndCount);
            dishNames.add(dishNameAndCount.get(DISH_NAME));
            dishCounts.add(toNumber(dishNameAndCount.get(DISH_COUNT)));
        }
    }

    private void validateIsSeparated(List<String> dishNameAndCount) {
        Validator.validateIsEqual(dishNameAndCount.size(), VALID_SIZE);
    }

    private int toNumber(String userInput) {
        Validator.validateIsNumber(userInput);
        return StringChanger.toInteger(userInput);
    }
}
