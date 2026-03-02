package com.cheolgabang.codingtest.quote;

import com.cheolgabang.codingtest.quote.enums.QuoteReasonCode;

import java.math.BigDecimal;

public record QuoteResponse(
        boolean available,
        QuoteReasonCode reasonCode,
        BigDecimal subtotal,
        BigDecimal discount,
        BigDecimal deliveryFee,
        BigDecimal finalPrice
) {

}
