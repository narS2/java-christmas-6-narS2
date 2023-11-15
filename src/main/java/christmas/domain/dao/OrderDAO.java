package christmas.domain.dao;

import christmas.domain.Menu;
import christmas.domain.parser.OrderParsable;
import christmas.domain.parser.OrderParser;

import java.util.Map;
import java.util.Collections;

public class OrderDAO {
    private final OrderParsable orderParsable;
    private static final OrderDAO ORDER_DAO = new OrderDAO();
    private static Map<Menu, Integer> orderDetail;

    private OrderDAO() {
        this.orderParsable = new OrderParser();
    }

    public static OrderDAO getInstance(){
        return ORDER_DAO;
    }

    public Map<Menu, Integer> getOrder(){
        return Collections.unmodifiableMap(orderDetail);
    }

    public void addOrderDetail(String inputOrder) {
        orderDetail = orderParsable.getOrderDetail(inputOrder);
    }
}
