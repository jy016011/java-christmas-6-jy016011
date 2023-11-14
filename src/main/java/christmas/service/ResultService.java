package christmas.service;

import christmas.constants.Badge;
import christmas.constants.EventHandler;
import christmas.constants.event.Discount;
import christmas.constants.event.Event;
import christmas.constants.event.Gift;
import christmas.constants.menu.Drink;
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

    private final static String NONE = "없음";
    private final static int NOTHING = 0;

    private final static int VALID_SIZE = 2;
    private final static int VALID_DIFFERENCE = 1;

    private final static int DISH_NAME = 0;
    private final static int DISH_COUNT = 1;
    private final static int FIRST_DAY = 1;

    private final static int GIFT_COUNT = 1;
    private final static Drink GIFT = Drink.CHAMPAGNE;
    private final static List<Event> events = EventHandler.getAllEvent();

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
            Gift gift = Gift.getBy(GIFT.getName());
            return gift.getItemBy(GIFT_COUNT);
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
        for (Event event : events) {
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
        totalDiscount += getChristmasDDayDiscount();
        totalDiscount += getWeekdayDiscount();
        totalDiscount += getWeekendDiscount();
        totalDiscount += getSpecialDiscount();
        return totalDiscount;
    }

    private int getBenefitBy(Event event) {
        if (event == Discount.CHRISTMAS_D_DAY) {
            return getChristmasDDayDiscount();
        }
        if (event == Discount.WEEKDAY) {
            return getWeekdayDiscount();
        }
        if (event == Discount.WEEKEND) {
            return getWeekendDiscount();
        }
        if (event == Discount.SPECIAL) {
            return getSpecialDiscount();
        }
        return getGiftBenefit();
    }

    private int getChristmasDDayDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isChristmasDDay()) {
            int additionalCount = visitingDate.getDifferenceFromFirstDay();
            discount = Discount.CHRISTMAS_D_DAY.getBenefit(additionalCount);
        }
        return discount;
    }

    private int getWeekdayDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isWeekday()) {
            int additionalCount = order.getDessertCount();
            discount = Discount.WEEKDAY.getBenefit(additionalCount);
        }
        return discount;
    }

    private int getWeekendDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isWeekend()) {
            int additionalCount = order.getMainDishCount();
            discount = Discount.WEEKDAY.getBenefit(additionalCount);
        }
        return discount;
    }

    private int getSpecialDiscount() {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isSpecialDay()) {
            discount = Discount.SPECIAL.getBenefit(NOTHING);
        }
        return discount;
    }

    private int getGiftBenefit() {
        int benefit = NOTHING;
        if (getGift().contains(GIFT.getName())) {
            Gift gift = Gift.getBy(GIFT.getName());
            benefit = gift.getBenefit(GIFT_COUNT);
        }
        return benefit;
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
