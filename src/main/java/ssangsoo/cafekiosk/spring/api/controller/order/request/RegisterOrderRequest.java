package ssangsoo.cafekiosk.spring.api.controller.order.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterOrderRequest {

    private List<String> productNumbers;

    @Builder
    private RegisterOrderRequest(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
