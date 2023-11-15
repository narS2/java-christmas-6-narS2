package christmas.service;

import christmas.domain.EventDate;

public class EventDateService implements EventDatable{
    @Override
    public boolean isChristmasDDay(int todayDate) {
        return todayDate == EventDate.CHRISTMAS_D_DAY_END.getDay();
    }

    @Override
    public boolean isChristmasEventDay(int todayDate) {
        return todayDate <= EventDate.CHRISTMAS_D_DAY_END.getDay();
    }

    @Override
    public void validateDecemberDate(int inputDate) {
        if (inputDate < EventDate.DECEMBER_DAY_START.getDay() || inputDate > EventDate.DECEMBER_DAY_END.getDay()) {
            throw new IllegalArgumentException("[ERROR] 날짜는 1~31일 범위 내에서만 유효합니다.");
        }
    }
}
