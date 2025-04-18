package ssangsoo.cafekiosk.spring.api.service.product;

import ssangsoo.cafekiosk.spring.api.controller.product.dto.request.RegisterProductRequest;
import ssangsoo.cafekiosk.spring.api.service.product.response.ProductResponse;

import java.util.List;

public interface ProductQueryUsecase {


    List<ProductResponse> retrieveSellingProducts();
}
