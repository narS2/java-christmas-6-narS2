package christmas.domain;

import christmas.service.EventDatable;
import christmas.service.EventDateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodayTest {
    EventDatable eventDatable = new EventDateService();

    @Test
    @DisplayName("주말 여부 확인 테스트 - 주말인 경우")
    void testIsWeekendTrue() {
        Today today = new Today(2, eventDatable);
        assertTrue(today.isWeekend(), "금요일과 토요일은 주말이다.");
    }

    @Test
    @DisplayName("주말 여부 확인 테스트 - 주말이 아닌 경우")
    void testIsWeekendFalse() {
        Today today = new Today(3, eventDatable);
        assertFalse(today.isWeekend(), "3일(일요일)은 주말이 아니다.");
    }

    @Test
    @DisplayName("12월 날짜 유효성 검사 - 잘못된 날짜")
    void testValidateDecemberDateInvalid() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Today today = new Today(32, eventDatable);
        });
        assertEquals("[ERROR] 날짜는 1~31일 범위 내에서만 유효합니다.", exception.getMessage());
    }
}