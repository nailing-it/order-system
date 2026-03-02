package com.cheolgabang.codingtest.status;

import com.cheolgabang.codingtest.common.NotImplementedYetException;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceStub implements OrderStatusService {

    @Override
    public StatusUpdateResponse updateStatus(String orderId, String idempotencyKey, StatusUpdateRequest request) {
        throw new NotImplementedYetException(
                "Problem 2 TODO: implement state transition, version check, and idempotency."
        );
    }
}
