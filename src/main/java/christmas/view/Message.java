package christmas.view;

public enum Message {
    PRINT_INTRODUCTION("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    REQUEST_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    REQUEST_ORDER_ACCEPT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PRINT_BENEFIT_PREVIEW("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    PRINT_NON_PASS_EVENT("없음");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
