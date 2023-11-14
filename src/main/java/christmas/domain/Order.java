package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private final int ORDER_COUNT_MAX = 20;
    private Map<Menu, Integer> orderDetail;

    public Order() {
        this.orderDetail = new HashMap<>();
    }

    public boolean isPassEventPrice() {
        int totalPrice = Menu.calculateTotalPrice(orderDetail);
        return totalPrice >= 10000;
    }

    public void addOrderDetail(String inputOrder) {
        this.orderDetail = getOrderDetail(inputOrder);
    }

    public Map<Menu, Integer> getOrderDetail(String inputOrder) {
        Map<Menu, Integer> orderDetail = convertOrderMenu(inputOrder);
        validateOnlyDrink(Menu.isAllDrinks(orderDetail), orderDetail);
        return orderDetail;
    }

    private void validateOnlyDrink(boolean allDrinks, Map<Menu, Integer> orderDetail) {
        if (allDrinks && !orderDetail.isEmpty()) {
            throw new IllegalArgumentException("[Error] 음료만 주문할 수 없습니다.");
        }
    }

    private Map<Menu, Integer> convertOrderMenu(String inputOrder) {
        return decodeOrderMenu(inputOrder).entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> Menu.matchingMenu(entry.getKey()),
                        Map.Entry::getValue
                ));
    }

    private Map<String, Integer> decodeOrderMenu(String inputOrder) {
        String[] orderParts = inputOrder.split(",");
        return parseInputOrder(orderParts);
    }

    private Map<String, Integer> parseInputOrder(String[] orderParts) {
        Map<String, Integer> splitOrderMenu = new HashMap<>();

        for (String orderPart : orderParts) {
            String[] part = orderPart.split("-");
            validateParse(part);
            splitOrderMenu.put(part[0].trim(), Integer.parseInt(part[1].trim()));
        }

        validateOrderAmount(splitOrderMenu);

        return splitOrderMenu;
    }

    private void validateParse(String[] part) {
        validateInputFormat(part);
        validateStringToNumber(part[1].trim());
    }

    private void validateInputFormat(String[] part) {
        if (part.length != 2) {
            throw new IllegalArgumentException("[Error] 주문메뉴-수량 형식으로 입력해주세요.");
        }
    }

    private void validateStringToNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[Error] 수량은 숫자로 입력해주세요.");
        }
    }

    private void validateOrderAmount(Map<String, Integer> splitOrderMenu) {
        int totalQuantity = splitOrderMenu.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (totalQuantity > ORDER_COUNT_MAX) {
            throw new IllegalArgumentException("[Error] 주문 가능한 메뉴의 최대 수량은 20개입니다.");
        }
    }
}
