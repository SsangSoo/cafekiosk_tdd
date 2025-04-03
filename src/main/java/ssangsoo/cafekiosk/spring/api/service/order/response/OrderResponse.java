package ssangsoo.cafekiosk.spring.api.service.order.response;

import lombok.Builder;
import lombok.Getter;
import ssangsoo.cafekiosk.spring.api.service.product.response.ProductResponse;
import ssangsoo.cafekiosk.spring.domain.order.Order;
import ssangsoo.cafekiosk.spring.domain.order.OrderStatus;
import ssangsoo.cafekiosk.spring.domain.product.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponse {
    private Long id;
    private OrderStatus orderStatus;
    private int totalPrice;
    private LocalDateTime registerDateTime;
    private List<ProductResponse> products;

    @Builder
    public OrderResponse(final Long id, final OrderStatus orderStatus, final int totalPrice, final LocalDateTime registerDateTime, final List<ProductResponse> products) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.registerDateTime = registerDateTime;
        this.products = products;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .registerDateTime(order.getRegisterDateTime())
                .products(order.getOrderProducts().stream()
                        .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
                        .collect(Collectors.toList())
                )
                .build();
    }

}
