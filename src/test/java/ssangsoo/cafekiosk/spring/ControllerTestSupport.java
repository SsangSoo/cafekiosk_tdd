package ssangsoo.cafekiosk.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ssangsoo.cafekiosk.spring.api.controller.order.OrderCommandController;
import ssangsoo.cafekiosk.spring.api.controller.product.ProductCommandController;
import ssangsoo.cafekiosk.spring.api.service.order.OrderCommandUsecase;
import ssangsoo.cafekiosk.spring.api.service.product.ProductCommandUsecase;

@WebMvcTest(controllers = {
        OrderCommandController.class,
        ProductCommandController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockitoBean
    protected OrderCommandUsecase orderUsecase;

    @MockitoBean
    protected ProductCommandUsecase productCommandUsecase;

}
