package ssangsoo.cafekiosk.spring.domain.order;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    INIT("주문생성"),
    CANCELED("주문취소"),
    PAYMENT_COMPLETED("결제 완료"),
    PAYMENT_FAILED("결제 실패"),
    RECEIVED("주문접수"),
    COMPLETE("처리완료");

    private final String text;

}
