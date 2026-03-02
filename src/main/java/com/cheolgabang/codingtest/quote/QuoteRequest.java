package com.cheolgabang.codingtest.quote;

import com.cheolgabang.codingtest.quote.enums.Coupon;
import com.cheolgabang.codingtest.quote.enums.Store;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class QuoteRequest {

    @NotNull
    private Store storeId;

    @NotNull
    private LocalDateTime orderedAt;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double distanceKm;

    @NotEmpty
    private List<@Valid QuoteItemRequest> items;

    private Coupon couponCode;

    public QuoteRequest() {
    }

    public QuoteRequest(Coupon couponCode) {
        this.couponCode = couponCode;
    }

    public QuoteRequest(Store storeId, LocalDateTime orderedAt, Double distanceKm, List<@Valid QuoteItemRequest> items, Coupon couponCode) {
        this.storeId = storeId;
        this.orderedAt = orderedAt;
        this.distanceKm = distanceKm;
        this.items = items;
        this.couponCode = couponCode;
    }

    public Store getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public List<QuoteItemRequest> getItems() {
        return items;
    }

    public void setItems(List<QuoteItemRequest> items) {
        this.items = items;
    }

    public Coupon getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Coupon couponCode) {
        this.couponCode = couponCode;
    }

}
