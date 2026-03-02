package com.cheolgabang.codingtest.search;

import com.cheolgabang.codingtest.common.NotImplementedYetException;
import org.springframework.stereotype.Service;

@Service
public class StoreSearchServiceStub implements StoreSearchService {

    @Override
    public StoreSearchResponse search(StoreSearchRequest request) {
        throw new NotImplementedYetException(
                "Problem 3 TODO: implement filtering, sorting, and pagination with optimized lookup."
        );
    }
}
