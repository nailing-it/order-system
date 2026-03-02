package com.cheolgabang.codingtest.search;

import java.time.LocalDateTime;

public record StoreSearchRequest(
        double lat,
        double lng,
        LocalDateTime orderedAt,
        double minRating,
        int page,
        int size
) {
}
