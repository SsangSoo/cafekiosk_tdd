package ssangsoo.cafekiosk.spring.api.service.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ssangsoo.cafekiosk.spring.api.controller.order.request.RegisterOrderRequest;
import ssangsoo.cafekiosk.spring.api.service.order.response.OrderResponse;
import ssangsoo.cafekiosk.spring.domain.product.Product;
import ssangsoo.cafekiosk.spring.domain.product.ProductRepository;
import ssangsoo.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static ssangsoo.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static ssangsoo.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;



    @Test
    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    void registerData() {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 3000);
        Product product3 = createProduct(HANDMADE, "003", 5000);
        productRepository.saveAll(List.of(product1, product2, product3));

        RegisterOrderRequest request = RegisterOrderRequest.builder()
                .productNumbers(List.of("001", "002"))
                .build();

        //

        OrderResponse orderResponse = orderService.registerOrder(request, registeredDateTime);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("registerDateTime", "totalPrice")
                .contains(registeredDateTime, 4000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 1000),
                        tuple("002", 3000)
                );
    }

    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .type(type)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }

}