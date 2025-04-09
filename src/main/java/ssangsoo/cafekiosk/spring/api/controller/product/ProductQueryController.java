package ssangsoo.cafekiosk.spring.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ssangsoo.cafekiosk.spring.api.ApiResponse;
import ssangsoo.cafekiosk.spring.api.service.product.ProductQueryUsecase;
import ssangsoo.cafekiosk.spring.api.service.product.response.ProductResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductQueryController {

    private final ProductQueryUsecase productQueryUsecase;

    @GetMapping("/api/v1/products/selling")
    public ApiResponse<List<ProductResponse>> retrieveSellingProducts() {
        return ApiResponse.of(productQueryUsecase.retrieveSellingProducts());
    }

}