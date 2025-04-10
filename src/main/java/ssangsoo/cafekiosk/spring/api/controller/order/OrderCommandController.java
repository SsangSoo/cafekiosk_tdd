package ssangsoo.cafekiosk.spring.api.controller.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ssangsoo.cafekiosk.spring.api.ApiResponse;
import ssangsoo.cafekiosk.spring.api.controller.order.dto.request.RegisterOrderRequest;
import ssangsoo.cafekiosk.spring.api.service.order.OrderCommandUsecase;
import ssangsoo.cafekiosk.spring.api.service.order.response.OrderResponse;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class OrderCommandController {

    private final OrderCommandUsecase orderUsecase;

    @PostMapping("/api/v1/orders/new")
    public ApiResponse<OrderResponse> registerOrder(@Valid @RequestBody RegisterOrderRequest request)  {
        LocalDateTime registeredDateTime = LocalDateTime.now();
        OrderResponse orderResponse = orderUsecase.registerOrder(request.toServiceRequest(), registeredDateTime);
        return ApiResponse.of(orderResponse);
    }
}
