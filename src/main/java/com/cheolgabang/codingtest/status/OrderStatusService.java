package com.cheolgabang.codingtest.status;

public interface OrderStatusService {

    StatusUpdateResponse updateStatus(String orderId, String idempotencyKey, StatusUpdateRequest request);
}
