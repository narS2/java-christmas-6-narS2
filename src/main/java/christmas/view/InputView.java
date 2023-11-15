package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static void printStartMessage() {
        System.out.println(Message.PRINT_INTRODUCTION.getMessage());
    }

    public static String readVisitDate() {
        System.out.println(Message.REQUEST_VISIT_DATE.getMessage());
        return Console.readLine();
    }

    public static String readOrderAccept() {
        System.out.println(Message.REQUEST_ORDER_ACCEPT.getMessage());
        return Console.readLine();
    }

    public static void printResultMessage(){
        System.out.println(Message.PRINT_BENEFIT_PREVIEW.getMessage());
        System.out.println();
    }
}
