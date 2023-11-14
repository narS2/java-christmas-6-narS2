package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

class OrderTest {
    Order order = new Order();

    @Test
    @DisplayName("주문 항목의 총 개수 / 주문 내역의 메뉴, 수량에 대한 데이터 처리 정상 작동 테스트")
    void testOrderDetailsConversion() {
        String inputOrder = "제로콜라-1,티본스테이크-2";
        Map<Menu, Integer> orderDetails = order.getOrderDetail(inputOrder);

        assertEquals(2, orderDetails.size(), "주문 항목은 총 2개(제로콜라,티본스테이크)여야 한다.");

        assertTrue(orderDetails.containsKey(Menu.ZERO_COKE), "주문 내역에 제로콜라가 포함되어야 한다.");
        assertEquals(1, orderDetails.get(Menu.ZERO_COKE).intValue(), "제로콜라의 수량은 1이어야 한다.");

        assertTrue(orderDetails.containsKey(Menu.T_BONE_STEAK), "주문 내역에 티본스테이크가 포함되어야 한다.");
        assertEquals(2, orderDetails.get(Menu.T_BONE_STEAK).intValue(), "티본스테이크의 수량은 2이어야 한다.");
    }

    @DisplayName("잘못된 형식으로 주문하는 경우 IllegalArgumentException 발생 테스트")
    @Test
    void testInvalidMenuInputFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            order.addOrderDetail("티본스테이크:2");
        });

        String expectedMessage = "[Error] 주문메뉴-수량 형식으로 입력해주세요.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("없는 메뉴를 주문하는 경우 IllegalArgumentException 발생 테스트")
    @Test
    void testInvalidMenuNameFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            order.addOrderDetail("잘못된메뉴-2");
        });

        String expectedMessage = "[Error] 입력하신 메뉴는 없는 메뉴입니다.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("주문 수량이 숫자가 아닌 경우 IllegalArgumentException 발생 테스트")
    @Test
    void testInvalidMenuAmountFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            order.addOrderDetail("양송이수프-두개");
        });

        String expectedMessage = "[Error] 수량은 숫자로 입력해주세요.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("음료만 주문하는 경우 IllegalArgumentException 발생 테스트")
    @Test
    void testOnlyDrinksOrder() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            order.addOrderDetail("제로콜라-3,레드와인-2");
        });

        String expectedMessage = "[Error] 음료만 주문할 수 없습니다.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("주문 메뉴를 20개 초과하는 경우 IllegalArgumentException 발생 테스트")
    @Test
    void testExceedingOrderAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            order.addOrderDetail("양송이수프-10,제로콜라-11");
        });

        String expectedMessage = "[Error] 주문 가능한 메뉴의 최대 수량은 20개입니다.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}