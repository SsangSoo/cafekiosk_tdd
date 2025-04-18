package ssangsoo.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ssangsoo.cafekiosk.spring.domain.orderproduct.OrderProductRepository;
import ssangsoo.cafekiosk.spring.domain.product.Product;
import ssangsoo.cafekiosk.spring.domain.product.ProductRepository;
import ssangsoo.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static ssangsoo.cafekiosk.spring.domain.order.OrderStatus.PAYMENT_COMPLETED;
import static ssangsoo.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static ssangsoo.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
    }
    
    @Test
    @DisplayName("지정한 날짜의 범위에서 결제 완료인 상품들만 조회할 수 있다.")
    void findByOrderStatusIn() {
        // given
        LocalDateTime registeredDateTime1 = LocalDateTime.of(2025, 4,10, 0, 0, 0);
        LocalDateTime registeredDateTime2 = LocalDateTime.of(2025, 4,12, 0, 0, 0);
        LocalDateTime registeredDateTime3 = LocalDateTime.of(2025, 4,15, 0, 0, 0);

        Product product1 = createProduct(HANDMADE, "001", 4000, "아메리카노");
        Product product2 = createProduct(HANDMADE, "002", 4500, "카페라떼");
        Product product3 = createProduct(HANDMADE, "003", 5000, "콜드 브루");
        List<Product> products = productRepository.saveAll(List.of(product1, product2, product3));

        Order order1 = Order.create(products, registeredDateTime1);
        Order order2 = Order.create(products, registeredDateTime2);
        Order order3 = Order.create(products, registeredDateTime3);
        order1.paymentComplete();
        order2.paymentComplete();
        orderRepository.saveAll(List.of(order1, order2, order3));

        // when
        List<Order> orders = orderRepository.findOrdersBy(registeredDateTime2, registeredDateTime3, PAYMENT_COMPLETED);

        // then
        assertThat(orders).hasSize(1);
        assertThat(orders).extracting("totalPrice", "orderStatus")
                .containsExactlyInAnyOrder(
                        tuple(13500, PAYMENT_COMPLETED)
                );

    }


    private Product createProduct(ProductType type, String productNumber, int price, String name) {
        return Product.builder()
                .type(type)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name(name)
                .build();
    }
}