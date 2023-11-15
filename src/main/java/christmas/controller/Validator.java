package christmas.controller;

public class Validator {
    public static void validateStringToNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자로 입력해주세요.");
        }
    }
}
