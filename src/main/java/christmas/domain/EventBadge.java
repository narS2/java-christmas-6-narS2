package christmas.domain;

public enum EventBadge {
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

    private final int discountPrice;
    private final String badgeName;

    EventBadge(int discountPrice, String badgeName) {
        this.discountPrice = discountPrice;
        this.badgeName = badgeName;
    }

    public static String getBadgeForDiscount(int totalDiscount, String nonPassEvent) {
        for (EventBadge eventBadge : EventBadge.values()) {
            if (totalDiscount >= eventBadge.discountPrice) {
                return eventBadge.badgeName;
            }
        }
        return nonPassEvent;
    }
}
