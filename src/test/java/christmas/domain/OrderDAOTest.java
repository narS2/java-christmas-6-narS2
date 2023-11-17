package christmas.domain;

import christmas.domain.dao.OrderDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*; //모든거 입력하지 말랬음

import java.util.Map;

class OrderDAOTest {
    private final OrderDAO orderDAO = OrderDAO.getInstance();

    @Test
    @DisplayName("주문 항목 개수 및 주문 내역의 메뉴, 수량 - 데이터 처리 성공 테스트")
    void testOrderDetailsConversion() {
        String inputOrder = "제로콜라-1,티본스테이크-2";
        orderDAO.addOrderDetail(inputOrder);
        Map<Menu, Integer> orderDetails = orderDAO.getOrder();

        assertEquals(2, orderDetails.size(), "주문 항목은 총 2개(제로콜라,티본스테이크)여야 한다.");

        assertTrue(orderDetails.containsKey(Menu.ZERO_COKE), "주문 내역에 제로콜라가 포함되어야 한다.");
        assertEquals(1, orderDetails.get(Menu.ZERO_COKE).intValue(), "제로콜라의 수량은 1이어야 한다.");

        assertTrue(orderDetails.containsKey(Menu.T_BONE_STEAK), "주문 내역에 티본스테이크가 포함되어야 한다.");
        assertEquals(2, orderDetails.get(Menu.T_BONE_STEAK).intValue(), "티본스테이크의 수량은 2이어야 한다.");
    }

    @DisplayName("고객 주문 유효성 검사 - 잘못된 형식 입력")
    @Test
    void testInvalidMenuInputFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderDAO.addOrderDetail("티본스테이크:2");
        });

        String expectedMessage = "[ERROR] 주문메뉴-수량 형식으로 입력해주세요.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("고객 주문 유효성 검사 - 없는 메뉴 입력")
    @Test
    void testInvalidMenuNameFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderDAO.addOrderDetail("없는메뉴-2");
        });

        String expectedMessage = "[ERROR] 입력하신 메뉴는 없는 메뉴입니다.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("고객 주문 유효성 검사 - 숫자가 아닌 값의 메뉴 수량 입력")
    @Test
    void testInvalidMenuAmountFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderDAO.addOrderDetail("양송이수프-두개");
        });

        String expectedMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("고객 주문 유효성 검사 - 음료만 주문")
    @Test
    void testOnlyDrinksOrder() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderDAO.addOrderDetail("제로콜라-3,레드와인-2");
        });

        String expectedMessage = "[ERROR] 음료만 주문할 수 없습니다.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("고객 주문 유효성 검사 - 주문 메뉴 20개 초과")
    @Test
    void testExceedingOrderAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderDAO.addOrderDetail("양송이수프-10,제로콜라-11");
        });

        String expectedMessage = "[ERROR] 주문 가능한 메뉴의 최대 수량은 20개입니다.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}