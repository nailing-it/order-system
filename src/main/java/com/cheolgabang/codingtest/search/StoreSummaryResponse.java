package com.cheolgabang.codingtest.search;

public record StoreSummaryResponse(
        String storeId,
        String name,
        double rating,
        double distanceKm,
        int deliveryFee,
        int etaMin
) {
}
