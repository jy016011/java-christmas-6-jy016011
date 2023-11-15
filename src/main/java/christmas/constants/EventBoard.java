package christmas.constants;

import christmas.constants.event.Discount;
import christmas.constants.event.Event;
import christmas.constants.event.Gift;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventBoard {
    public static List<Event> getAllContents() {
        return Stream.of(Discount.getAll(), Gift.getAll())
                .flatMap(Collection::stream).collect(Collectors.toList());
    }
}
