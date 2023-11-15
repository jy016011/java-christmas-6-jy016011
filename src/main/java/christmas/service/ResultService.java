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
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultService {
    private final static int NOTHING = 0;
    private final static int GIFT_COUNT = 1;
    private static final int FIRST_DAY = 1;

    private Benefits benefits;
    private VisitingDate visitingDate;
    private Order order;

    public void setDate(String userInput) {
        visitingDate = new VisitingDate(userInput);
    }

    public int getDate() {
        return visitingDate.getDifferenceFromFirstDay() + FIRST_DAY;
    }

    public void setOrder(String userInput) {
        order = new Order(userInput);
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
}
