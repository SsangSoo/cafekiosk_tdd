package ssangsoo.cafekiosk.spring.api.service.product;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ssangsoo.cafekiosk.spring.IntegrationTestSupport;
import ssangsoo.cafekiosk.spring.api.controller.product.dto.request.RegisterProductRequest;
import ssangsoo.cafekiosk.spring.api.service.product.response.ProductResponse;
import ssangsoo.cafekiosk.spring.domain.product.Product;
import ssangsoo.cafekiosk.spring.domain.product.ProductRepository;
import ssangsoo.cafekiosk.spring.domain.product.ProductSellingStatus;
import ssangsoo.cafekiosk.spring.domain.product.ProductType;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static ssangsoo.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static ssangsoo.cafekiosk.spring.domain.product.ProductType.HANDMADE;

class ProductServiceTest extends IntegrationTestSupport {

    @Autowired
    private ProductCommandUsecase productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    static void beforeAll() {
        // before class
    }

    @BeforeEach
    void setUp() {
        // before method

        // 각 테스트 입장에서 봤을 떄 : 아예 몰라도 테스트 내용을 이애하는 데에 문제가 없는가?
        // 수정해도 모든 테스트에 영향을 주지 않는가?
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }


    @Test
    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품의 상품번호에서 1 증가한 값이다")
    void registerProduct() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4500);
        productRepository.save(product1);

        RegisterProductRequest request = RegisterProductRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        // when
        ProductResponse productResponse = productService.registerProduct(request.toServiceRequest());

        // then
        assertThat(productResponse)
                .extracting("productNumber", "type", "sellingStatus", "name", "price")
                .contains("002", HANDMADE, SELLING, "카푸치노", 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products)
                .hasSize(2)
                .extracting("productNumber", "type", "sellingStatus", "name", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", HANDMADE, SELLING, "아메리카노", 4500),
                        tuple("002", HANDMADE, SELLING, "카푸치노", 5000)
                );

    }


    @Test
    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다.")
    void registerProductWhenProductsIsEmpty() {
        // given
        RegisterProductRequest request = RegisterProductRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        // when
        ProductResponse productResponse = productService.registerProduct(request.toServiceRequest());

        // then
        assertThat(productResponse)
                .extracting("productNumber", "type", "sellingStatus", "name", "price")
                .contains("001", HANDMADE, SELLING, "카푸치노", 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products)
                .hasSize(1)
                .extracting("productNumber", "type", "sellingStatus", "name", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", HANDMADE, SELLING, "카푸치노", 5000)
                );
    }


    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, final int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }

}