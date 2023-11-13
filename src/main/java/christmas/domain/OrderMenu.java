package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMenu {
    private Map<EnumMenu, Integer> orderDetail;

    public OrderMenu() {
        this.orderDetail = new HashMap<>();
    }

    public void addOrderDetail(String inputOrder) {
        this.orderDetail = validateOrderDetail(inputOrder);
    }

    public Map<EnumMenu, Integer> validateOrderDetail(String inputOrder) {
        Map<EnumMenu, Integer> orderDetail = convertOrderMenu(inputOrder);
        validateOnlyDrink(EnumMenu.isAllDrinks(orderDetail), orderDetail);
        return orderDetail;
    }

    private void validateOnlyDrink(boolean allDrinks, Map<EnumMenu, Integer> orderDetail) {
        if (allDrinks && !orderDetail.isEmpty()) {
            throw new IllegalArgumentException("[Error] 음료만 주문할 수 없습니다.");
        }
    }

    //주문 입력 예시 : "양송이수프-2,티본스테이크-2"
    private Map<EnumMenu, Integer> convertOrderMenu(String inputOrder) {
        return decodeOrderMenu(inputOrder).entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> EnumMenu.matchingMenu(entry.getKey()),
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
}
