package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodayTest {
    Today today = new Today();

    @Test
    @DisplayName("주말 여부 확인 테스트 - 주말인 경우")
    void testIsWeekendTrue() {
        today.setDate(2);
        assertTrue(today.isWeekend(), "금요일과 토요일은 주말이다.");
    }

    @Test
    @DisplayName("주말 여부 확인 테스트 - 주말이 아닌 경우")
    void testIsWeekendFalse() {
        today.setDate(9); // 예를 들어 12월 9일이 월요일이라고 가정
        assertFalse(today.isWeekend(), "월요일은 주말이 아니어야 합니다.");
    }

    @Test
    @DisplayName("12월 날짜 유효성 검사 - 잘못된 날짜")
    void testValidateDecemberDateInvalid() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Today().setDate(32); // 12월에는 31일까지만 존재
        });
        assertEquals("[ERROR] 1~31일 범위 내 숫자만 입력해주세요.", exception.getMessage());
    }
}