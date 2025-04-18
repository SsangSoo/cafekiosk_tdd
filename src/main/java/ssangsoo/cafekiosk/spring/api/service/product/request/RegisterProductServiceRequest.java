package ssangsoo.cafekiosk.spring.api.service.product.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssangsoo.cafekiosk.spring.domain.product.Product;
import ssangsoo.cafekiosk.spring.domain.product.ProductSellingStatus;
import ssangsoo.cafekiosk.spring.domain.product.ProductType;


@Getter
@NoArgsConstructor
public class RegisterProductServiceRequest {

    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;

    @Builder
    private RegisterProductServiceRequest(ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public Product toEntity(String nextProductNumber) {
        return Product.builder()
                .productNumber(nextProductNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}
