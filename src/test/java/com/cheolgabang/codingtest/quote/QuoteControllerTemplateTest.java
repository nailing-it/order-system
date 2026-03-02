package com.cheolgabang.codingtest.quote;

import com.cheolgabang.codingtest.quote.enums.QuoteReasonCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = QuoteController.class)
class QuoteControllerTemplateTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    @Test
    @DisplayName("Template: quote endpoint returns response shape")
    void quoteTemplate() throws Exception {
        given(quoteService.quote(any())).willReturn(
                new QuoteResponse(true, QuoteReasonCode.OK, BigDecimal.valueOf(14000), BigDecimal.valueOf(3000), BigDecimal.valueOf(2000), BigDecimal.valueOf(11000))
        );

        String payload = """
                {
                  "storeId": "S1",
                  "orderedAt": "2026-02-25T11:30:00",
                  "distanceKm": 1.3,
                  "items": [
                    {"menuId": "M1", "qty": 2}
                  ],
                  "couponCode": "WELCOME3000"
                }
                """;

        mockMvc.perform(
                        post("/api/v1/orders/quote")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(true))
                .andExpect(jsonPath("$.reasonCode").value("OK"))
                .andExpect(jsonPath("$.finalPrice").value(11000));
    }
}
