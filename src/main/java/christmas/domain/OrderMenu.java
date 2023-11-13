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
        orderDetail = convertOrderMenu(inputOrder);
    }

    private Map<EnumMenu, Integer> convertOrderMenu(String inputOrder) {
        return decodeOrderMenu(inputOrder).entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> EnumMenu.matchingMenu(entry.getKey()),
                        Map.Entry::getValue
                ));
    }

    //주문 입력 예시 : "양송이수프-2,티본스테이크-2"
    private Map<String, Integer> decodeOrderMenu(String inputOrder) {
        String[] orderParts = inputOrder.split(",");
        //쉼표 예외 처리
        return parseInputOrder(orderParts);
    }

    private Map<String, Integer> parseInputOrder(String[] orderParts) {
        Map<String, Integer> splitOrderMenu = new HashMap<>();

        for (String orderPart : orderParts) {
            String[] part = orderPart.split("-");
            //주문 형식 예외 처리
            //수량 숫자화 예외 처리
            splitOrderMenu.put(part[0], Integer.parseInt(part[1]));
        }

        return splitOrderMenu;
    }
}
