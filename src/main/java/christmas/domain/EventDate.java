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

    public static boolean isChristmasDDay(int todayDate) {
        return todayDate == CHRISTMAS_D_DAY_END.day;
    }

    public static boolean isChristmasEventDay(int todayDate) {
        return todayDate >= CHRISTMAS_D_DAY_END.day;
    }

    public static void validateDecemberDate(int inputDate) {
        if (inputDate < DECEMBER_DAY_START.day || inputDate > DECEMBER_DAY_END.day) {
            throw new IllegalArgumentException("[ERROR] 날짜는 1~31일 범위 내에서만 유효합니다.");
        }
    }
}
