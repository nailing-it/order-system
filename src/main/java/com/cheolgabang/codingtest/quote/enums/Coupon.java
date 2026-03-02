package com.cheolgabang.codingtest.quote.enums;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public enum Coupon {
    LUNCH10("percent", 0.1, 15000, 3000, new OrderTime(LocalTime.of(11,0), LocalTime.of(14,0))),
    WELCOME3000("fixed", 3000, 12000, 0, null);

    private String calculationMethod;
    private BigDecimal calculateAmount;
    private BigDecimal minimumAmount;
    private BigDecimal maximumAmount;
    private OrderTime orderTime;

    Coupon(String calculationMethod, double calculateAmount, int minimumAmount, int maximumAmount, OrderTime orderTime) {
        this.calculationMethod = calculationMethod;
        this.calculateAmount = new BigDecimal(calculateAmount);
        this.minimumAmount = new BigDecimal(minimumAmount);
        this.maximumAmount = new BigDecimal(maximumAmount);
        this.orderTime = orderTime;

    }

    public String getCalculationMethod() {
        return this.calculationMethod;
    }

    public boolean isApply(Coupon coupon, BigDecimal totalAmount, LocalDateTime localDateTime) {
        if("percent".equals(coupon.calculationMethod)) {
            if(totalAmount.compareTo(this.minimumAmount) >= 0 && isLunchTime(localDateTime)) {
                return true;
            }
        } else if("fixed".equals(coupon.calculationMethod)) {
            if(totalAmount.compareTo(this.minimumAmount) >= 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isLunchTime(LocalDateTime localDateTime) {

        LocalTime currentTime = LocalTime.of(localDateTime.getHour(), localDateTime.getMinute());

        return !currentTime.isBefore(LUNCH10.orderTime.start) && !currentTime.isAfter(LUNCH10.orderTime.end);
    }

    public BigDecimal calculateSum(Coupon coupon, BigDecimal totalAmount) {
        if("percent".equals(coupon.calculationMethod)) {
            BigDecimal discoutAmout = totalAmount.multiply(coupon.calculateAmount);
            if(discoutAmout.compareTo(coupon.maximumAmount) >= 0) {
                return totalAmount.subtract(coupon.maximumAmount);
            }
            return totalAmount.multiply(BigDecimal.ONE.subtract(coupon.calculateAmount));
        } else if("fixed".equals(coupon.calculationMethod)) {
            return totalAmount.subtract(coupon.calculateAmount);
        } else {
            return totalAmount;
        }
    }

    record OrderTime(LocalTime start, LocalTime end) {}
}
