package ssangsoo.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssangsoo.cafekiosk.spring.api.controller.order.request.RegisterOrderRequest;
import ssangsoo.cafekiosk.spring.api.service.order.response.OrderResponse;
import ssangsoo.cafekiosk.spring.domain.order.Order;
import ssangsoo.cafekiosk.spring.domain.order.OrderRepository;
import ssangsoo.cafekiosk.spring.domain.product.Product;
import ssangsoo.cafekiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse registerOrder(RegisterOrderRequest request, LocalDateTime registeredDateTime) {
        List<String> productNumbers = request.getProductNumbers();

        List<Product> duplicateProducts = findProductBy(productNumbers);

        // Order
        Order order = Order.create(duplicateProducts, registeredDateTime);
        Order savedOrder = orderRepository.save(order);
        return OrderResponse.of(savedOrder);

    }

    private List<Product> findProductBy(final List<String> productNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductNumber, p -> p));

        return productNumbers.stream()
                .map(productNumber -> productMap.put(productNumber, productMap.get(productNumber)))
                .collect(Collectors.toList());
    }
}
