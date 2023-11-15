package christmas.service;

import christmas.domain.EventBadge;
import christmas.domain.Menu;
import christmas.domain.dao.OrderDAO;
import christmas.domain.Today;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventPrice {
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private final Map<Menu, Integer> orderDetail = orderDAO.getOrder();
    private final Map<String, Integer> discountDetails = new LinkedHashMap<>();
    private final int eventPriceLimit;
    private final int specialDiscount;
    private final int giveawayPriceLimit;
    private final int nonPassDiscount;
    private final int totalPrice;
    private final Today today;

    public EventPrice(int eventPriceLimit, int specialDiscount,
                      int giveawayPriceLimit, int nonPassDiscount, Today today) {
        this.eventPriceLimit = eventPriceLimit;
        this.specialDiscount = specialDiscount;
        this.giveawayPriceLimit = giveawayPriceLimit;
        this.nonPassDiscount = nonPassDiscount;
        this.today = today;
        this.totalPrice = Menu.calculateTotalPrice(orderDetail);
        applyEvent();
    }

    public String getEventBadge() {
        return EventBadge.getBadgeForDiscount(totalDiscount());
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Map<String, Integer> getDiscountDetails() {
        return discountDetails;
    }

    public int totalDiscount() {
        return discountDetails.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int totalDiscountExcludingGiveaway() {
        return totalPrice - (discountDetails.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("증정 이벤트"))
                .mapToInt(Map.Entry::getValue)
                .sum());
    }

    public boolean isGiveawayLimit() {
        return totalPrice >= giveawayPriceLimit;
    }

    private void applyEvent() {
        if (isEventPriceLimit()) {
            christmasDiscount();
            weekdayDiscount();
            weekendDiscount();
            specialDiscount();
            giveawayEventItem();
        }
    }

    private void christmasDiscount() {
        discountDetails.put("크리스마스 디데이 할인", today.getChristmasEventToday());
    }

    private void weekdayDiscount() {
        int dessertDiscount = Menu.calculateDessertDiscount(orderDetail);

        if (!today.isWeekend() && dessertDiscount > nonPassDiscount) {
            discountDetails.put("평일 할인", dessertDiscount);
        }
    }

    private void weekendDiscount() {
        int mainDishDiscount = Menu.calculateMainDishDiscount(orderDetail);

        if (today.isWeekend() && mainDishDiscount > nonPassDiscount) {
            discountDetails.put("주말 할인", mainDishDiscount);
        }
    }

    private void specialDiscount() {
        if (today.isSpecialDay()) {
            discountDetails.put("특별 할인", specialDiscount);
        }
    }

    private void giveawayEventItem() {
        if (isGiveawayLimit()) {
            discountDetails.put("증정 이벤트", Menu.giveawayChampagne().getValue());
        }
    }

    private boolean isEventPriceLimit() {
        return totalPrice >= eventPriceLimit;
    }
}
