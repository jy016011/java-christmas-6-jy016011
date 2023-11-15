package christmas.service;

import christmas.constants.Badge;
import christmas.constants.EventBoard;
import christmas.constants.event.Discount;
import christmas.constants.event.Event;
import christmas.constants.event.Gift;
import christmas.domain.Benefits;
import christmas.domain.Dish;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.utils.ArgumentValidator;
import christmas.utils.StringChanger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultService {
    private final static String DISH_SEPARATOR = ",";
    private final static String DISH_NAME_AND_COUNT_SEPARATOR = "-";

    private final static int NOTHING = 0;
    private final static int VALID_SIZE = 2;
    private final static int VALID_DIFFERENCE = 1;

    private final static int DISH_NAME = 0;
    private final static int DISH_COUNT = 1;
    private final static int GIFT_COUNT = 1;
    private static final int FIRST_DAY = 1;

    private Benefits benefits;
    private VisitingDate visitingDate;
    private Order order;

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

    public void setBenefits() {
        benefits = new Benefits();
    }

    public int getTotalPrice() {
        return order.getTotalPrice();
    }

    public String getGiftWithCount() {
        Gift gift = benefits.getGift(order);
        if (gift == Gift.NONE) {
            return gift.getMenu();
        }
        return gift.getItemBy(GIFT_COUNT);
    }

    public int getTotalDiscountedPrice() {
        return order.getTotalPrice() - getTotalDiscount();
    }

    public int getTotalBenefit() {
        return getTotalDiscount() + benefits.getGiftBenefit(order);
    }

    public Map<String, Integer> getSynthesizedAllBenefits() {
        Map<String, Integer> benefitsDetails = new LinkedHashMap<>();
        for (Event event : EventBoard.getAllEvent()) {
            int benefit = getBenefitBy(event);
            if (benefit > NOTHING) {
                benefitsDetails.put(event.getEventName(), benefit);
            }
        }
        return benefitsDetails;
    }

    public Badge getBadge() {
        return Badge.getBy(getTotalBenefit());
    }

    private int getTotalDiscount() {
        int totalDiscount = NOTHING;
        totalDiscount += benefits.getChristmasDDayDiscount(order, visitingDate);
        totalDiscount += benefits.getWeekdayDiscount(order, visitingDate);
        totalDiscount += benefits.getWeekendDiscount(order, visitingDate);
        totalDiscount += benefits.getSpecialDiscount(order, visitingDate);
        return totalDiscount;
    }

    private int getBenefitBy(Event event) {
        if (event == Discount.CHRISTMAS_D_DAY) {
            return benefits.getChristmasDDayDiscount(order, visitingDate);
        }
        if (event == Discount.WEEKDAY) {
            return benefits.getWeekdayDiscount(order, visitingDate);
        }
        if (event == Discount.WEEKEND) {
            return benefits.getWeekendDiscount(order, visitingDate);
        }
        if (event == Discount.SPECIAL) {
            return benefits.getSpecialDiscount(order, visitingDate);
        }
        return benefits.getGiftBenefit(order);
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

    private void separateNameAndCount(List<String> orderInput, List<String> dishNames, List<Integer> dishCounts) {
        for (String eachOrder : orderInput) {
            List<String> dishNameAndCount = StringChanger.toTrimmedStringList(eachOrder, DISH_NAME_AND_COUNT_SEPARATOR);
            validateIsSeparated(dishNameAndCount);
            dishNames.add(dishNameAndCount.get(DISH_NAME));
            dishCounts.add(toNumber(dishNameAndCount.get(DISH_COUNT)));
        }
    }

    private void validateIsSeparated(List<String> dishNameAndCount) {
        ArgumentValidator.isEqual(dishNameAndCount.size(), VALID_SIZE);
    }

    private int toNumber(String userInput) {
        ArgumentValidator.isNumber(userInput);
        return StringChanger.toInteger(userInput);
    }
}
