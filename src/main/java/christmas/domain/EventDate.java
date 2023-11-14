package christmas.domain;

public enum EventDate {
    DECEMBER_DAY_START(1),
    DECEMBER_DAY_END(31),
    CHRISTMAS_D_DAY_START(1),
    CHRISTMAS_D_DAY_END(25);

    private final int day;

    EventDate(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
