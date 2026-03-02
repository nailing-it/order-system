package com.cheolgabang.codingtest.search;

import java.util.List;

public record StoreSearchResponse(
        List<StoreSummaryResponse> stores,
        int page,
        int size,
        long totalCount
) {
}
