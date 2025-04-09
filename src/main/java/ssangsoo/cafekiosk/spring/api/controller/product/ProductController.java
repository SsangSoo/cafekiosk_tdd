package ssangsoo.cafekiosk.spring.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssangsoo.cafekiosk.spring.api.controller.product.dto.request.RegisterProductRequest;
import ssangsoo.cafekiosk.spring.api.service.product.ProductService;
import ssangsoo.cafekiosk.spring.api.service.product.ProductUsecase;
import ssangsoo.cafekiosk.spring.api.service.product.response.ProductResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductUsecase productUsecase;

    @PostMapping("/api/v1/products/new")
    public void registerProduct(RegisterProductRequest request) {
        productUsecase.registerProduct(request);
    }


    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> retrieveSellingProducts() {
        return productUsecase.retrieveSellingProducts();
    }
}
