package christmas.service;

import christmas.constants.Error;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.utils.StringChanger;
import christmas.utils.Validator;
import java.util.ArrayList;
import java.util.List;

public class ResultService {
    private final static Error ERROR_HEADER = Error.ERROR_HEADER;
    private final static String DISH_SEPARATOR = ",";
    private final static String SEPARATOR_DISH_AND_COUNT = "-";
    private final int DISH_NAME = 0;
    private final int DISH_COUNT = 1;
    private Order order;
    private VisitingDate visitingDate;

    public void setDate(String userInput) {
        int day = toNumber(userInput);
        visitingDate = new VisitingDate(day);
    }

    public void setOrder(String userInput) {
        List<String> dishNames = new ArrayList<>();
        List<Integer> dishCounts = new ArrayList<>();
        List<String> orderInput = StringChanger.toTrimmedStringList(userInput, DISH_SEPARATOR);
        separateNameAndCount(orderInput, dishNames, dishCounts);
        order = new Order(dishNames, dishCounts);
    }

    private void separateNameAndCount(List<String> orderInput, List<String> dishNames, List<Integer> dishCounts) {
        for (String eachOrder : orderInput) {
            List<String> dishes = StringChanger.toTrimmedStringList(eachOrder, SEPARATOR_DISH_AND_COUNT);
            dishNames.add(dishes.get(DISH_NAME));
            validateCount(dishes, dishCounts);
        }
    }

    private int toNumber(String userInput) {
        Validator.validateIsNumber(userInput);
        return StringChanger.toInteger(userInput);
    }

    private void validateCount(List<String> dishes, List<Integer> dishCounts) {
        try {
            String dishCount = dishes.get(DISH_COUNT);
            Validator.validateIsNumber(dishCount);
            dishCounts.add(StringChanger.toInteger(dishCount));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(ERROR_HEADER.getErrorMessage());
        }
    }
}
