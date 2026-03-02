package com.cheolgabang.codingtest.quote.enums;

import java.math.BigDecimal;

public enum DeliveryFee {
    near(1.5),
    far(3.0);

    private double distance;

    DeliveryFee(double distance) {
        this.distance = distance;
    }

    public static BigDecimal calculateDeliveryFee(double distance) {
        if(distance <= near.distance) {
            return BigDecimal.valueOf(2000);
        } else if(distance <= far.distance) {
            return BigDecimal.valueOf(3000);
        } else {
            return BigDecimal.valueOf(4500);
        }
    }
}
