package com.cheolgabang.codingtest.quote;

import com.cheolgabang.codingtest.quote.enums.Coupon;
import com.cheolgabang.codingtest.quote.enums.Store;
import com.cheolgabang.codingtest.quote.impl.QuoteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuoteServiceImplTest {

    private QuoteServiceImpl quoteServiceImpl;
    private QuoteRequest quoteRequest;


    @BeforeEach
    public void setUp() {
        // given
        quoteServiceImpl = new QuoteServiceImpl();
        List<QuoteItemRequest> quoteItemRequestList = List.of(new QuoteItemRequest("M1", 2));
        quoteRequest = new QuoteRequest(Store.S1, LocalDateTime.of(2026, 2, 25, 11, 1), 1.3, quoteItemRequestList, Coupon.WELCOME3000);
    }

    @Test
    @DisplayName("M1 2개 주문 1.3km거리 배달이면서 웰컴 쿠폰 사용 시 11000결제")
    void M1_주문2개_배송거리_일점삼km_웰컴쿠폰사용한_총결제할_금액_검증() {
        // when
        QuoteResponse quoteResponse = quoteServiceImpl.quote(quoteRequest);
        // then
        assertEquals(quoteResponse.available(), true);
        assertEquals(BigDecimal.valueOf(11000),quoteResponse.finalPrice());

    }
}
