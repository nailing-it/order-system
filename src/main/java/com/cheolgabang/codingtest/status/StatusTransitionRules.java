package com.cheolgabang.codingtest.status;

import java.util.Map;
import java.util.Set;

public final class StatusTransitionRules {

    public static final Map<OrderStatus, Set<OrderStatus>> ALLOWED_TRANSITIONS = Map.of(
            OrderStatus.CREATED, Set.of(OrderStatus.PAID, OrderStatus.CANCELED),
            OrderStatus.PAID, Set.of(OrderStatus.ACCEPTED, OrderStatus.CANCELED),
            OrderStatus.ACCEPTED, Set.of(OrderStatus.COOKING, OrderStatus.CANCELED),
            OrderStatus.COOKING, Set.of(OrderStatus.PICKED_UP),
            OrderStatus.PICKED_UP, Set.of(OrderStatus.DELIVERED),
            OrderStatus.DELIVERED, Set.of(),
            OrderStatus.CANCELED, Set.of()
    );

    private StatusTransitionRules() {
    }
}
