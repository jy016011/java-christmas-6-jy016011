package christmas.domain;

import christmas.constants.event.Discount;
import christmas.constants.event.Gift;

public class Benefits {
    private static final int NOTHING = 0;
    private static final int GIFT_COUNT = 1;

    public Gift getGift(Order order) {
        if (order.isEventTarget() && order.canGetGift()) {
            return Gift.CHAMPAGNE;
        }
        return Gift.NONE;
    }

    public int getChristmasDDayDiscount(Order order, VisitingDate visitingDate) {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isChristmasDDay()) {
            int additionalCount = visitingDate.getDifferenceFromFirstDay();
            discount = Discount.CHRISTMAS_D_DAY.getBenefit(additionalCount);
        }
        return discount;
    }

    public int getWeekdayDiscount(Order order, VisitingDate visitingDate) {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isWeekday()) {
            int additionalCount = order.getDessertCount();
            discount = Discount.WEEKDAY.getBenefit(additionalCount);
        }
        return discount;
    }

    public int getWeekendDiscount(Order order, VisitingDate visitingDate) {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isWeekend()) {
            int additionalCount = order.getMainDishCount();
            discount = Discount.WEEKDAY.getBenefit(additionalCount);
        }
        return discount;
    }

    public int getSpecialDiscount(Order order, VisitingDate visitingDate) {
        int discount = NOTHING;
        if (order.isEventTarget() && visitingDate.isSpecialDay()) {
            discount = Discount.SPECIAL.getBenefit(NOTHING);
        }
        return discount;
    }

    public int getGiftPrice(Order order) {
        int benefit = NOTHING;
        Gift gift = getGift(order);
        if (gift == Gift.CHAMPAGNE) {
            benefit = gift.getBenefit(GIFT_COUNT);
        }
        return benefit;
    }
}
