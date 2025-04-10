package ssangsoo.cafekiosk.spring.api.controller.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ssangsoo.cafekiosk.spring.api.ApiResponse;
import ssangsoo.cafekiosk.spring.api.controller.product.dto.request.RegisterProductRequest;
import ssangsoo.cafekiosk.spring.api.service.product.ProductCommandUsecase;
import ssangsoo.cafekiosk.spring.api.service.product.ProductQueryUsecase;
import ssangsoo.cafekiosk.spring.api.service.product.response.ProductResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductCommandController {

    private final ProductCommandUsecase productCommandUsecase;

    @PostMapping("/api/v1/products/new")
    public ApiResponse<ProductResponse> registerProduct(@Valid @RequestBody RegisterProductRequest request) {
        return ApiResponse.of(productCommandUsecase.registerProduct(request.toServiceRequest()));
    }



    private final ProductQueryUsecase productQueryUsecase;


    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> retrieveSellingProducts() {
        return productQueryUsecase.retrieveSellingProducts();
    }
}
