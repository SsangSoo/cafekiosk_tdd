package ssangsoo.cafekiosk.spring.api.service.order;

import ssangsoo.cafekiosk.spring.api.controller.order.dto.request.RegisterOrderRequest;
import ssangsoo.cafekiosk.spring.api.service.order.response.OrderResponse;

import java.time.LocalDateTime;

public interface OrderUsecase {

    OrderResponse registerOrder(RegisterOrderRequest request, LocalDateTime registeredDateTime);
}
