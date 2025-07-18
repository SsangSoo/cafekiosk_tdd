package ssangsoo.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssangsoo.cafekiosk.spring.api.controller.product.dto.request.RegisterProductRequest;
import ssangsoo.cafekiosk.spring.api.service.product.request.RegisterProductServiceRequest;
import ssangsoo.cafekiosk.spring.api.service.product.response.ProductResponse;
import ssangsoo.cafekiosk.spring.domain.product.Product;
import ssangsoo.cafekiosk.spring.domain.product.ProductRepository;

import java.util.Objects;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductCommandService implements ProductCommandUsecase {

    private final ProductRepository productRepository;
    private final ProductNumberFactory productNumberFactory;


    @Override
    public ProductResponse registerProduct(RegisterProductServiceRequest request) {
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        productRepository.save(product);

        return ProductResponse.of(product);
    }

}
