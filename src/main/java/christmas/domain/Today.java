package christmas.domain;

import christmas.service.EventDatable;

public class Today {
    private final int INDEX_ALIGN = 1;
    private final int WEEK_LENGTH = 7;
    private final DayOfWeek startDayOfMonth = DayOfWeek.FRIDAY;
    private DayOfWeek todayOfWeek;
    private int todayDate;
    private final EventDatable eventDatable;

    enum DayOfWeek {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    public Today(int inputDate, EventDatable eventDatable) {
        this.eventDatable = eventDatable;
        setDate(inputDate);
    }

    public void setDate(int inputDate) {
        eventDatable.validateDecemberDate(inputDate);
        this.todayDate = inputDate;
        this.todayOfWeek = getDayOfWeek(inputDate, startDayOfMonth);
    }

    public int getChristmasEventToday() {
        if (eventDatable.isChristmasEventDay(todayDate)) {
            return (todayDate * 100) + 900;
        }
        return 0;
    }

    public boolean isWeekend() {
        return todayOfWeek == DayOfWeek.FRIDAY || todayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay() {
        return todayOfWeek == DayOfWeek.SUNDAY || eventDatable.isChristmasDDay(todayDate);
    }

    private DayOfWeek getDayOfWeek(int inputDate, DayOfWeek startDate) {
        int startDayIndex = startDate.ordinal();
        int dayOfWeekIndex = (startDayIndex + inputDate - INDEX_ALIGN) % WEEK_LENGTH;
        return DayOfWeek.values()[dayOfWeekIndex];
    }
}
