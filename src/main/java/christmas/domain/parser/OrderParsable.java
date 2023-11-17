package christmas.domain.parser;

import christmas.domain.Menu;

import java.util.Map;

public interface OrderParsable {
    Map<Menu, Integer> getOrderDetail(String inputOrder);
}
