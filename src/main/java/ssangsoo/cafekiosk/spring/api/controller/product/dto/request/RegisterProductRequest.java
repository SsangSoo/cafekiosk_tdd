package ssangsoo.cafekiosk.spring.api.controller.product.dto.request;

import lombok.Builder;
import lombok.Getter;
import ssangsoo.cafekiosk.spring.domain.product.ProductSellingStatus;
import ssangsoo.cafekiosk.spring.domain.product.ProductType;

@Getter
public class RegisterProductRequest {


    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;

    @Builder
    private RegisterProductRequest(final ProductType type, final ProductSellingStatus sellingStatus, final String name, final int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }
}
