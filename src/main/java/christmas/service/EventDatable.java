package christmas.service;

public interface EventDatable {
    boolean isChristmasDDay(int date);

    boolean isChristmasEventDay(int date);

    void validateDecemberDate(int date);
}
