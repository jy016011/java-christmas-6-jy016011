package christmas.constants;

import christmas.constants.event.Discount;
import christmas.constants.event.Event;
import christmas.constants.event.Gift;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventHandler {
    public static List<Event> getAllEvent() {
        return Stream.of(Discount.getDiscounts(), Gift.getGifts())
                .flatMap(Collection::stream).collect(Collectors.toList());
    }
}
