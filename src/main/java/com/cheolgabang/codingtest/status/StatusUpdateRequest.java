package com.cheolgabang.codingtest.status;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StatusUpdateRequest {

    @NotNull
    private OrderStatus toStatus;

    private String reason;

    @NotNull
    @Min(0)
    private Long version;

    public OrderStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(OrderStatus toStatus) {
        this.toStatus = toStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
