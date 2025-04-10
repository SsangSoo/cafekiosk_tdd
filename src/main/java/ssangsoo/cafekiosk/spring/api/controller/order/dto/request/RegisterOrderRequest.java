package ssangsoo.cafekiosk.spring.api.controller.order.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssangsoo.cafekiosk.spring.api.service.order.request.RegisterOrderServiceRequest;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterOrderRequest {

    @NotEmpty(message = "상품 번호 리스트는 필수입니다.")
    private List<String> productNumbers;

    @Builder
    private RegisterOrderRequest(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }

    public RegisterOrderServiceRequest toServiceRequest() {
        return RegisterOrderServiceRequest.builder()
                .productNumbers(productNumbers)
                .build();
    }
}
