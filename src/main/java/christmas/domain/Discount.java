package christmas.domain;

import java.util.Map;

public class Discount {
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private final Map<Menu, Integer> orderDetail = orderDAO.getOrder();
    private final int eventPriceLimit;
    private final int specialDiscount;
    private final int totalPrice;
    private final Today today;

    public Discount(int eventPriceLimit, int specialDiscount, Today today) {
        this.eventPriceLimit = eventPriceLimit;
        this.specialDiscount = specialDiscount;
        this.today = today;
        this.totalPrice = Menu.calculateTotalPrice(orderDetail);
    }

    private boolean isEventPriceLimit() {
        return totalPrice >= eventPriceLimit;
    }

    public int christmasDiscount() {
        if (!isEventPriceLimit()) return 0;
        return today.getChristmasEventToday();
    }

    public int weekdayDiscount() {
        if (!isEventPriceLimit() && !today.isWeekend()) return 0;
        return Menu.calculateDessertDiscount(orderDetail);
    }

    public int weekendDiscount() {
        if (!isEventPriceLimit() && today.isWeekend()) return 0;
        return Menu.calculateMainDishDiscount(orderDetail);
    }

    public int specialDiscount() {
        if (!isEventPriceLimit() && today.isSpecialDay()) return 0;
        return specialDiscount;
    }
}
