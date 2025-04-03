package ssangsoo.cafekiosk.spring.api.controller.order.request;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RegisterOrderRequest {

    private List<String> productNumbers;

    @Builder
    private RegisterOrderRequest(final List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
