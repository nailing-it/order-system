package com.cheolgabang.codingtest.status;

import java.time.LocalDateTime;

public record StatusUpdateResponse(
        String orderId,
        OrderStatus fromStatus,
        OrderStatus toStatus,
        long version,
        String idempotencyKey,
        LocalDateTime updatedAt
) {
}
