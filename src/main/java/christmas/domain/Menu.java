package christmas.domain;

import java.util.Map;

public enum Menu {
    APPETIZER(0, 0, "애피타이저"),
    MAIN_DISH(1, 2023, "메인"),
    DESSERT(2, 2023, "디저트"),
    DRINK(3, 0, "음료"),
    MUSHROOM_SOUP(0, 6000, "양송이수프"),
    TAPAS(0, 5500, "타파스"),
    CAESAR_SALAD(0, 8000, "시저샐러드"),
    T_BONE_STEAK(1, 55000, "티본스테이크"),
    BBQ_RIBS(1, 54000, "바비큐립"),
    SEAFOOD_PASTA(1, 35000, "해산물파스타"),
    CHRISTMAS_PASTA(1, 25000, "크리스마스파스타"),
    CHOCO_CAKE(2, 15000, "초코케이크"),
    ICE_CREAM(2, 5000, "아이스크림"),
    ZERO_COKE(3, 3000, "제로콜라"),
    RED_WINE(3, 60000, "레드와인"),
    CHAMPAGNE(3, 25000, "샴페인");

    private final int type;
    private final int price;
    private final String name;

    Menu(int type, int price, String name) {
        this.type = type;
        this.price = price;
        this.name = name;
    }

    public static Menu matchingMenu(String inputMenuName) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equalsIgnoreCase(inputMenuName)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("[Error] 입력하신 메뉴는 없는 메뉴입니다.");
    }

    public static boolean isAllDrinks(Map<Menu, Integer> orderDetail) {
        return orderDetail.keySet().stream()
                .allMatch(item -> item.type == DRINK.type);
    }

    public static int calculateTotalPrice(Map<Menu, Integer> orderDetail){
        return orderDetail.entrySet().stream()
                .mapToInt(entry -> entry.getKey().price * entry.getValue())
                .sum();
    }
}
