package ssangsoo.cafekiosk.spring.api.controller.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ssangsoo.cafekiosk.spring.ControllerTestSupport;
import ssangsoo.cafekiosk.spring.api.controller.order.dto.request.RegisterOrderRequest;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderCommandControllerTest extends ControllerTestSupport {


    @Test
    @DisplayName("신규 주문을 등록한다.")
    void registerOrder() throws Exception {
        // given
        RegisterOrderRequest request = RegisterOrderRequest.builder()
                .productNumbers(List.of("001"))
                .build();


        // when // then
        mockMvc.perform(post("/api/v1/orders/new")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"))
        ;
    }

    @Test
    @DisplayName("신규 주문을 등록할 때 상품번호는 1개 이상이어야 한다.")
    void registerOrderWithEmptyProductNumbers() throws Exception {
        // given
        RegisterOrderRequest request = RegisterOrderRequest.builder()
                .productNumbers(List.of())
                .build();


        // when // then
        mockMvc.perform(post("/api/v1/orders/new")
                        .content(objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("상품 번호 리스트는 필수입니다."))
                .andExpect(jsonPath("$.data").isEmpty())
        ;
    }

}