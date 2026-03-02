package com.cheolgabang.codingtest.quote.enums;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public enum Store {
    S1(BigDecimal.valueOf(12000), List.of(new Menu("M1", BigDecimal.valueOf(6000), true), new Menu("M2", BigDecimal.valueOf(4500), true), new Menu("M3", BigDecimal.valueOf(5500), false)), new TimeRange(LocalTime.of(10,0), LocalTime.of(22,0)) , new TimeRange(LocalTime.of(15,0), LocalTime.of(16,0)));

    private final BigDecimal minimumPrice;
    private final List<Menu> menu;
    private final TimeRange openHours;
    private final TimeRange breakHours;

    Store(BigDecimal minimumPrice,List<Menu> menus, TimeRange openHours, TimeRange breakHours) {
        this.menu = menus;
        this.minimumPrice = minimumPrice;
        this.openHours = openHours;
        this.breakHours = breakHours;
    }

    static class Menu {
        final String name;
        final BigDecimal price;
        final boolean active;

        Menu(String name, BigDecimal price, boolean active) {
            this.name = name;
            this.price = price;
            this.active = active;
        }
    }

    record TimeRange(LocalTime start, LocalTime end) {

    }


    // 현재시간이 breakTime, openTime시간의 가능한 시간인지 체크
    public static boolean isOpen(Store storeId, LocalTime currentTime) {
        // 이렇게 하면 모든 조건에 대해서 다 만족해야하니깐 당연히 안됨
        return !currentTime.isBefore(storeId.openHours.start) && !currentTime.isAfter(storeId.breakHours.start) || !currentTime.isBefore(storeId.breakHours.end()) && !currentTime.isAfter(storeId.openHours.end);
    }

    // 1개라도 주문 불가능하면 false
    public static boolean canOrder(List<String> menuIds) {
        long count = 0;
        for(String menuId : menuIds) {
            count += S1.menu.stream().filter(m -> m.active && menuId.equals(m.name)).count();

        }
        return count == menuIds.size();
    }

    public static boolean isAvailableStore(Store storeId) {
        for(Store store : Store.values()) {
            if(storeId.equals(store)) {
                return true;
            }
        }
        return false;
    }

    public static List<Menu> getMenus() {
        return S1.menu;
    }




}
