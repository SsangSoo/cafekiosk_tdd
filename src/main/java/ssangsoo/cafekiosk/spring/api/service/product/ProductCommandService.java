package ssangsoo.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssangsoo.cafekiosk.spring.api.controller.product.dto.request.RegisterProductRequest;
import ssangsoo.cafekiosk.spring.api.service.product.response.ProductResponse;
import ssangsoo.cafekiosk.spring.domain.product.Product;
import ssangsoo.cafekiosk.spring.domain.product.ProductRepository;

import java.util.Objects;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductCommandService implements ProductCommandUsecase {

    private final ProductRepository productRepository;


    @Override
    public ProductResponse registerProduct(RegisterProductRequest request) {
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();

        if (Objects.isNull(latestProductNumber)) {
            return "001";
        }

        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);

    }
}
