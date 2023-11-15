package christmas.domain.validator;

import christmas.domain.Menu;

import java.util.Map;

public class OrderValidator {
    private final int ORDER_COUNT_MAX = 20;

    public void validateOnlyDrink(boolean allDrinks, Map<Menu, Integer> orderDetail) {
        if (allDrinks && !orderDetail.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다.");
        }
    }

    public void validateOrderAmount(Map<String, Integer> splitOrderMenu) {
        int totalQuantity = splitOrderMenu.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (totalQuantity > ORDER_COUNT_MAX) {
            throw new IllegalArgumentException("[ERROR] 주문 가능한 메뉴의 최대 수량은 20개입니다.");
        }
    }

    public void validateParse(String[] part) {
        validateInputFormat(part);
        validateStringToNumber(part[1].trim());
    }

    private void validateInputFormat(String[] part) {
        if (part.length != 2) {
            throw new IllegalArgumentException("[ERROR] 주문메뉴-수량 형식으로 입력해주세요.");
        }
    }

    private void validateStringToNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
