package ssangsoo.cafekiosk.spring.api.controller.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssangsoo.cafekiosk.spring.api.service.product.request.RegisterProductServiceRequest;
import ssangsoo.cafekiosk.spring.domain.product.Product;
import ssangsoo.cafekiosk.spring.domain.product.ProductSellingStatus;
import ssangsoo.cafekiosk.spring.domain.product.ProductType;

@Getter
@NoArgsConstructor
public class RegisterProductRequest {

    @NotNull(message = "상품 타입은 필수입니다.")
    private ProductType type;

    @NotNull(message = "상품 판매상태는 필수입니다.")
    private ProductSellingStatus sellingStatus;

    @NotBlank(message = "상품 이름은 필수입니다.")
    private String name;

    @Positive(message = "상품 가격은 양수여야 합니다.")
    private int price;

    @Builder
    private RegisterProductRequest(final ProductType type, final ProductSellingStatus sellingStatus, final String name, final int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public RegisterProductServiceRequest toServiceRequest() {
        return RegisterProductServiceRequest.builder()
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }


}
