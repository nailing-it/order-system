package com.cheolgabang.codingtest.quote;

import java.util.Map;
import java.util.Set;

public final class QuoteFixture {

    public static final String STORE_ID = "S1";
    public static final int STORE_MIN_ORDER = 12_000;

    public static final Map<String, Integer> MENU_PRICES = Map.of(
            "M1", 6_000,
            "M2", 4_500,
            "M3", 5_500
    );

    public static final Set<String> ACTIVE_MENU_IDS = Set.of("M1", "M2");

    private QuoteFixture() {
    }
}
