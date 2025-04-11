package ssangsoo.cafekiosk.spring.api.service.order;

import ssangsoo.cafekiosk.spring.api.service.order.request.RegisterOrderServiceRequest;
import ssangsoo.cafekiosk.spring.api.service.order.response.OrderResponse;

import java.time.LocalDateTime;

public interface OrderCommandUsecase {

    OrderResponse registerOrder(RegisterOrderServiceRequest request, LocalDateTime registeredDateTime);
}
