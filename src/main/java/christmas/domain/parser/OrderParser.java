package christmas.domain.parser;

import christmas.domain.Menu;
import christmas.domain.validator.OrderValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderParser implements OrderParsable {
    private final OrderValidator orderValidator;

    public OrderParser() {
        this.orderValidator = new OrderValidator();
    }

    @Override
    public Map<Menu, Integer> getOrderDetail(String inputOrder) {
        Map<Menu, Integer> orderDetail = convertOrderMenu(inputOrder);
        orderValidator.validateOnlyDrink(Menu.isAllDrinks(orderDetail), orderDetail);
        return orderDetail;
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
            orderValidator.validateParse(part);
            splitOrderMenu.put(part[0].trim(), Integer.parseInt(part[1].trim()));
        }

        orderValidator.validateOrderAmount(splitOrderMenu);

        return splitOrderMenu;
    }
}

