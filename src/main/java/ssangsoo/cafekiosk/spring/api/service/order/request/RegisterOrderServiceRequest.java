package ssangsoo.cafekiosk.spring.api.service.order.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterOrderServiceRequest {

    private List<String> productNumbers;

    @Builder
    private RegisterOrderServiceRequest(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
