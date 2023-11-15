package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constants.Error;
import christmas.utils.StringChanger;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DishesTest {
    private static final Error ERROR_HEADER = Error.ERROR_HEADER;

    private final static String DISH_SEPARATOR = ",";
    private final static String DISH_NAME_AND_COUNT_SEPARATOR = "-";
    private final static int DISH_NAME = 0;
    private final static int DISH_COUNT = 1;

    @DisplayName(
            "메뉴가 중복되거나,"
                    + "음료만 주문하거나,"
                    + "각 메뉴의 개수가 하나라도 1개 미만이거나,"
                    + "메뉴의 총 개수가 20개 초과면 예외가 발생할 것이다."
    )
    @ValueSource(strings = {
            "양송이수프-1,양송이수프-2",
            "제로콜라-2,샴페인-1,레드와인-3",
            "제로콜라-0,양송이수프-2,바비큐립-2",
            "제로콜라-7,양송이수프-8,바비큐립-6"
    })
    @ParameterizedTest
    void createOrderByDuplicatedMenu(String userInput) {
        List<String> dishNames = new ArrayList<>();
        List<Integer> dishCounts = new ArrayList<>();
        changeFormat(userInput, dishNames, dishCounts);
        assertThatThrownBy(() -> new Dishes(dishNames, dishCounts))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER.getMessage());
    }

    @DisplayName("티본스테이크와 샴페인을 하나씩 시키면 할인 전 총금액은 80,000원일 것이다.")
    @Test
    void orderTBoneSteakAndChampagne() {
        List<String> dishNames = new ArrayList<>();
        List<Integer> dishCounts = new ArrayList<>();
        changeFormat("티본스테이크-1,샴페인-1", dishNames, dishCounts);
        Dishes dishes = new Dishes(dishNames, dishCounts);
        assertThat(dishes.getTotalPrice()).isEqualTo(80_000);
    }

    @DisplayName("초코케이크 2개를 시키면 주문 내용을 초코케이크 = 2개와 같이 저장할 것이다.")
    @Test
    void checkOrderDetails() {
        List<String> dishNames = new ArrayList<>();
        List<Integer> dishCounts = new ArrayList<>();
        changeFormat("초코케이크-2", dishNames, dishCounts);
        Dishes dishes = new Dishes(dishNames, dishCounts);
        assertThat(dishes.getOrderDetails().get(new Dish("초코케이크"))).isEqualTo(2);
    }

    private void changeFormat(String userInput, List<String> dishNames, List<Integer> dishCounts) {
        List<String> orderInput = StringChanger.toTrimmedStringList(userInput, DISH_SEPARATOR);
        separateNameAndCount(orderInput, dishNames, dishCounts);
    }

    private void separateNameAndCount(List<String> orderInput, List<String> dishNames, List<Integer> dishCounts) {
        for (String eachOrder : orderInput) {
            List<String> dishNameAndCount = StringChanger.toTrimmedStringList(eachOrder, DISH_NAME_AND_COUNT_SEPARATOR);
            dishNames.add(dishNameAndCount.get(DISH_NAME));
            dishCounts.add(StringChanger.toInteger(dishNameAndCount.get(DISH_COUNT)));
        }
    }

}
