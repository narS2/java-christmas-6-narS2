package christmas.service;

import christmas.domain.EventBadge;
import christmas.domain.Menu;
import christmas.domain.dao.OrderDAO;
import christmas.domain.Today;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventPrice {
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private final Map<Menu, Integer> orderDetail = orderDAO.getOrder();
    private final Map<String, Integer> discountDetails = new LinkedHashMap<>();
    private final int EVENT_PRICE_LIMIT = 10000;
    private final int SPECIAL_DISCOUNT = 1000;
    private final int GIVEAWAY_PRICE_LIMIT = 120000;
    private final int NON_PASS_DISCOUNT = 0;
    private final int TOTAL_PRICE;
    private final Today today;

    public EventPrice(Today today) {
        this.today = today;
        this.TOTAL_PRICE = Menu.calculateTotalPrice(orderDetail);
        applyEvent();
    }

    public String getEventBadge() {
        return EventBadge.getBadgeForDiscount(totalDiscount());
    }

    public int getTotalPrice() {
        return TOTAL_PRICE;
    }

    public Map<String, Integer> getDiscountDetails() {
        return Collections.unmodifiableMap(discountDetails);
    }

    public int totalDiscount() {
        return discountDetails.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int totalDiscountExcludingGiveaway() {
        return TOTAL_PRICE - (discountDetails.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("증정 이벤트"))
                .mapToInt(Map.Entry::getValue)
                .sum());
    }

    public boolean isGiveawayLimit() {
        return TOTAL_PRICE >= GIVEAWAY_PRICE_LIMIT;
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

        if (!today.isWeekend() && dessertDiscount > NON_PASS_DISCOUNT) {
            discountDetails.put("평일 할인", dessertDiscount);
        }
    }

    private void weekendDiscount() {
        int mainDishDiscount = Menu.calculateMainDishDiscount(orderDetail);

        if (today.isWeekend() && mainDishDiscount > NON_PASS_DISCOUNT) {
            discountDetails.put("주말 할인", mainDishDiscount);
        }
    }

    private void specialDiscount() {
        if (today.isSpecialDay()) {
            discountDetails.put("특별 할인", SPECIAL_DISCOUNT);
        }
    }

    private void giveawayEventItem() {
        if (isGiveawayLimit()) {
            discountDetails.put("증정 이벤트", Menu.giveawayChampagne().getValue());
        }
    }

    private boolean isEventPriceLimit() {
        return TOTAL_PRICE >= EVENT_PRICE_LIMIT;
    }
}
