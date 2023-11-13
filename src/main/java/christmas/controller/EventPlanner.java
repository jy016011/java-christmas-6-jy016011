package christmas.controller;

import christmas.constants.Badge;
import christmas.service.ResultService;
import christmas.views.InputView;
import christmas.views.OutputView;

public class EventPlanner {
    private static final int NOTHING = 0;
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
                System.out.println("\n[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.\n");
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
                System.out.println("\n[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.\n");
            }
        }
    }

    private void printResult() {
        printUserInput();
        printBenefits();
        printBadge();
    }

    private void printUserInput() {
        OutputView.printPrefaceOfResultOf(resultService.getDate());
        OutputView.printOrderedMenu(resultService.getOrderedMenu());
        OutputView.printBeforeDiscount(resultService.getTotalPrice());
    }

    private void printBenefits() {
        OutputView.printPresent(resultService.getGift());
        OutputView.printBenefitsDetails(resultService.synthesizeAllBenefits());
        OutputView.printTotalBenefits(resultService.getTotalBenefit());
        OutputView.printExpectedPriceToPay(resultService.getTotalDiscountedPrice());
    }

    private void printBadge() {
        Badge badge = resultService.getBadge();
        OutputView.printBadge(badge);
    }
}
