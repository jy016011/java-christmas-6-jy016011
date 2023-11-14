package christmas.constants;

import christmas.constants.events.Discount;
import christmas.constants.events.Gift;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Event {
    DISCOUNT(Discount.getDiscounts().stream()
            .map(Discount::getEventName).toList()),
    GIFT(Gift.getGifts().stream()
            .map(Gift::getEventName).toList());

    private final List<String> names;

    Event(List<String> names) {
        this.names = names;
    }

    public static List<String> getAllEvents() {
        return Stream.of(DISCOUNT.getNames(), GIFT.getNames())
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<String> getNames() {
        return names;
    }
}
