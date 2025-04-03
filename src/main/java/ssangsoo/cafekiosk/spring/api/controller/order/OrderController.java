package ssangsoo.cafekiosk.spring.api.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ssangsoo.cafekiosk.spring.api.controller.order.request.RegisterOrderRequest;
import ssangsoo.cafekiosk.spring.api.service.order.OrderService;
import ssangsoo.cafekiosk.spring.api.service.order.response.OrderResponse;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public OrderResponse registerOrder(@RequestBody RegisterOrderRequest request)  {
        LocalDateTime registeredDateTime = LocalDateTime.now();
        return orderService.registerOrder(request, registeredDateTime);
    }
}
