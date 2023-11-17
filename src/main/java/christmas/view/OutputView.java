package christmas.view;

import christmas.domain.Menu;

import java.util.Map;

public class OutputView {
    public static void printMenu(Map<Menu, Integer> orderDetail) {
        System.out.println("<주문 메뉴>");

        for (Map.Entry<Menu, Integer> entry : orderDetail.entrySet()) {
            Menu menu = entry.getKey();
            int discountAmount = entry.getValue();
            System.out.println(menu.getName() + " " + discountAmount + "개");
        }

        System.out.println();
    }

    public static void printTotalOrderPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", totalPrice);
        System.out.println();
    }

    public static void printGiveAway(String giveaway) {
        System.out.println("<증정 메뉴>");

        if (giveaway != null) {
            System.out.println(giveaway);
        }

        if (giveaway == null) {
            System.out.println(Message.PRINT_NON_PASS_EVENT.getMessage());
        }

        System.out.println();
    }

    public static void printBenefitDetails(Map<String, Integer> totalDiscount) {
        System.out.println("<혜택 내역>");

        if (totalDiscount.isEmpty()) {
            System.out.println(Message.PRINT_NON_PASS_EVENT.getMessage());
        }

        if (!totalDiscount.isEmpty()) {
            for (Map.Entry<String, Integer> entry : totalDiscount.entrySet()) {
                String event = entry.getKey();
                int discountAmount = entry.getValue();
                System.out.printf("%s: -%,d원\n", event, discountAmount);
            }
        }

        System.out.println();
    }

    public static void printTotalBenefit(int totalDiscount) {
        System.out.println("<총혜택 금액>");
        System.out.printf("%,d원\n", -totalDiscount);
        System.out.println();
    }

    public static void printResultPayment(int payment) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n", payment);
        System.out.println();
    }

    public static void printEventBadge(String badge) {
        System.out.println("<12월 이벤트 배지>");

        if (badge != null) {
            System.out.println(badge);
        }

        if (badge == null) {
            System.out.println(Message.PRINT_NON_PASS_EVENT.getMessage());
        }
    }
}
