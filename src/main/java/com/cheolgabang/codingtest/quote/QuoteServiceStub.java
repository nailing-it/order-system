package com.cheolgabang.codingtest.quote;

import com.cheolgabang.codingtest.common.NotImplementedYetException;
public class QuoteServiceStub implements QuoteService {

    @Override
    public QuoteResponse quote(QuoteRequest request) {
        throw new NotImplementedYetException(
                "Problem 1 TODO: implement quote calculation rules in QuoteServiceStub#quote."
        );
    }
}
