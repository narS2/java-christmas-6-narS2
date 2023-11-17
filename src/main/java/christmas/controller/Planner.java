package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Today;
import christmas.domain.dao.OrderDAO;
import christmas.service.EventDatable;
import christmas.service.EventDateService;
import christmas.service.EventPrice;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Planner {
    private final EventDatable eventDatable = new EventDateService();
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private Today today;
    private EventPrice eventPrice;

    public void run() {
        InputView.printStartMessage();
        dateRun();
        menuRun();
        eventPrice = new EventPrice(today);
        InputView.printResultMessage();
        discountDetailsRun();
    }

    private void menuRun() {
        while (true) {
            try {
                orderDAO.addOrderDetail(InputView.readOrderAccept());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void dateRun() {
        while (true) {
            try {
                String input = InputView.readVisitDate();
                Validator.validateDateToNumber(input);
                today = new Today(Integer.parseInt(input), eventDatable);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String giveaway() {
        if (eventPrice.isGiveawayLimit()) {
            return Menu.CHAMPAGNE.getName() + " 1개";
        }
        return null;
    }

    private void discountDetailsRun() {
        OutputView.printMenu(orderDAO.getOrder());
        OutputView.printTotalOrderPrice(eventPrice.getTotalPrice());
        OutputView.printGiveAway(giveaway());
        OutputView.printBenefitDetails(eventPrice.getDiscountDetails());
        OutputView.printTotalBenefit(eventPrice.totalDiscount());
        OutputView.printResultPayment(eventPrice.totalDiscountExcludingGiveaway());
        OutputView.printEventBadge(eventPrice.getEventBadge());
    }
}
