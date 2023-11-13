package christmas.controller;

import christmas.constants.Badge;
import christmas.service.ResultService;
import christmas.views.ErrorView;
import christmas.views.InputView;
import christmas.views.OutputView;

public class EventPlanner {
    private ResultService resultService;

    public void init() {
        resultService = new ResultService();
    }

    public void run() {
        OutputView.printGreetings();
        getInputs();
        printResult();
    }

    private void getInputs() {
        getVisitingDay();
        getOrder();
    }

    private void getVisitingDay() {
        while (true) {
            try {
                String visitingDay = InputView.getVisitingDayInput();
                resultService.setDate(visitingDay);
                break;
            } catch (IllegalArgumentException e) {
                ErrorView.printInvalidDayInput();
            }
        }
    }

    private void getOrder() {
        while (true) {
            try {
                String userOrder = InputView.getOrderInput();
                resultService.setOrder(userOrder);
                break;
            } catch (IllegalArgumentException e) {
                ErrorView.printInvalidOrderInput();
            }
        }
    }

    private void printResult() {
        printUserInput();
        printAllBenefits();
        printBadge();
    }

    private void printUserInput() {
        OutputView.printPrefaceOfResultWith(resultService.getDate());
        OutputView.printEachOrderBy(resultService.getUserOrder());
        OutputView.printBeforeDiscount(resultService.getTotalPrice());
    }

    private void printAllBenefits() {
        OutputView.printPresent(resultService.getGift());
        OutputView.printDetailsOf(resultService.synthesizeAllBenefits());
        OutputView.printTheSumOf(resultService.getTotalBenefit());
        OutputView.printExpected(resultService.getTotalDiscountedPrice());
    }

    private void printBadge() {
        Badge badge = resultService.getBadge();
        OutputView.printLastMessageWith(badge);
    }
}
