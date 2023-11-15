package christmas.service;

import christmas.domain.Menu;
import christmas.domain.OrderDAO;
import christmas.domain.Today;

import java.util.Map;

public class EventPrice {
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private final Map<Menu, Integer> orderDetail = orderDAO.getOrder();
    private final int eventPriceLimit;
    private final int specialDiscount;
    private final int giveawayPriceLimit;
    private final int totalPrice;
    private final Today today;
    private final int nonPassDiscount = 0;

    public EventPrice(int eventPriceLimit, int specialDiscount, int giveawayPriceLimit, Today today) {
        this.eventPriceLimit = eventPriceLimit;
        this.specialDiscount = specialDiscount;
        this.giveawayPriceLimit = giveawayPriceLimit;
        this.today = today;
        this.totalPrice = Menu.calculateTotalPrice(orderDetail);
    }

    public int christmasDiscount() {
        if (!isEventPriceLimit()) return nonPassDiscount;
        return today.getChristmasEventToday();
    }

    public int weekdayDiscount() {
        if (!isEventPriceLimit() && !today.isWeekend()) return nonPassDiscount;
        return Menu.calculateDessertDiscount(orderDetail);
    }

    public int weekendDiscount() {
        if (!isEventPriceLimit() && today.isWeekend()) return nonPassDiscount;
        return Menu.calculateMainDishDiscount(orderDetail);
    }

    public int specialDiscount() {
        if (!isEventPriceLimit() && today.isSpecialDay()) return nonPassDiscount;
        return specialDiscount;
    }

    public String giveawayEventItem(String nonPassEvent) {
        if (isGiveawayLimit()) {
            return Menu.giveawayChampagne().getKey();
        }
        return nonPassEvent;
    }

    public int giveawayEventPrice() {
        if (isGiveawayLimit()) {
            return Menu.giveawayChampagne().getValue();
        }
        return nonPassDiscount;
    }

    private boolean isEventPriceLimit() {
        return totalPrice >= eventPriceLimit;
    }

    private boolean isGiveawayLimit() {
        return totalPrice >= giveawayPriceLimit;
    }
}
