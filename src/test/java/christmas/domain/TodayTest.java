package christmas.domain;

import christmas.service.EventDatable;
import christmas.service.EventDateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodayTest {
    EventDatable eventDatable = new EventDateService();

    @Test
    @DisplayName("12월 날짜 유효성 검사 - 잘못된 날짜")
    void testValidateDecemberDateInvalid() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Today today = new Today(32, eventDatable);
        });
        assertEquals("[ERROR] 날짜는 1~31일 범위 내에서만 유효합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("주말(금,토) 여부 확인 테스트 - 주말인 경우")
    void testIsWeekendTrue() {
        Today today = new Today(2, eventDatable);
        assertTrue(today.isWeekend(), "2일(금요일), 금요일과 토요일은 주말이다.");
    }

    @Test
    @DisplayName("주말(금,토) 여부 확인 테스트 - 주말이 아닌 경우")
    void testIsWeekendFalse() {
        Today today = new Today(3, eventDatable);
        assertFalse(today.isWeekend(), "3일(일요일), 금요일과 토요일은 주말이며 일요일은 평일이다.");
    }

    @Test
    @DisplayName("특별 할인 날짜 테스트 - 일요일")
    void testIsSpecialSunday() {
        Today today = new Today(3, eventDatable);
        assertTrue(today.isSpecialDay(), "일요일(3일)은 특별 할인 데이다.");
    }

    @Test
    @DisplayName("특별 할인 날짜 테스트 - 크리스마스 당일")
    void testIsSpecialChristmas() {
        Today today = new Today(25, eventDatable);
        assertTrue(today.isSpecialDay(), "크리스마스 당일(25일)은 특별 할인 데이다.");
    }

    @Test
    @DisplayName("특별 할인 날짜 테스트 - 일요일, 크리스마스가 아닌 경우")
    void testIsSpecialFalse() {
        Today today = new Today(27, eventDatable);
        assertFalse(today.isSpecialDay(), "27일(수요일)은 특별 이벤트에 속하지 않는다.");
    }

    @Test
    @DisplayName("크리스마스 D-Day 테스트 - 할인 금액 정상 처리")
    void testChristmasEventDiscount() {
        Today firstDay = new Today(1, eventDatable);
        assertEquals(firstDay.getChristmasEventToday(), 1000);

        Today LastDay = new Today(25, eventDatable);
        assertEquals(LastDay.getChristmasEventToday(), 3400);

        Today OverDay = new Today(26, eventDatable);
        assertEquals(OverDay.getChristmasEventToday(), 0);
    }
}