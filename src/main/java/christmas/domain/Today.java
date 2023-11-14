package christmas.domain;

public class Today {
    private final int INDEX_ALIGN = 1;
    private final int WEEK_LENGTH = 7;
    private final DayOfWeek startDayOfMonth = DayOfWeek.FRIDAY;
    private DayOfWeek todayOfWeek;
    private int todayDate;

    enum DayOfWeek {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    public void setDate(int inputDate) {
        EventDate.validateDecemberDate(inputDate);
        this.todayDate = inputDate;
        this.todayOfWeek = getDayOfWeek(inputDate, startDayOfMonth);
    }

    public boolean isChristmasEventDay() {
        return EventDate.isChristmasEventDay(todayDate);
    }

    public boolean isWeekend() {
        return todayOfWeek == DayOfWeek.FRIDAY || todayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay() {
        return todayOfWeek == DayOfWeek.SUNDAY || EventDate.isChristmasDDay(todayDate);
    }

    private DayOfWeek getDayOfWeek(int inputDate, DayOfWeek startDate) {
        int startDayIndex = startDate.ordinal();
        int dayOfWeekIndex = (startDayIndex + inputDate - INDEX_ALIGN) % WEEK_LENGTH;
        return DayOfWeek.values()[dayOfWeekIndex];
    }
}
