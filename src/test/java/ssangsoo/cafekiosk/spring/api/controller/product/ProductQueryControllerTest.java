package ssangsoo.cafekiosk.spring.api.controller.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ssangsoo.cafekiosk.spring.api.service.product.ProductQueryUsecase;
import ssangsoo.cafekiosk.spring.api.service.product.response.ProductResponse;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ProductQueryController.class)
class ProductQueryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductQueryUsecase productQueryUsecase;

    @Test
    @DisplayName("판매 상품을 조회한다.")
    void registerProduct() throws Exception {
        // given
        List<ProductResponse> result = List.of();
        when(productQueryUsecase.retrieveSellingProducts()).thenReturn(result);

        // when // then
        mockMvc.perform(get("/api/v1/products/selling")
//                        .queryParam("name", "이름") // 이런 식으로 parameter 받음
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data").isArray())
                ;
    }


}