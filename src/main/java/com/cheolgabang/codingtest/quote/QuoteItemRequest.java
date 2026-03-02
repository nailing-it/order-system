package com.cheolgabang.codingtest.quote;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuoteItemRequest {

    @NotBlank
    private String menuId;

    @NotNull
    @Min(1)
    @Max(20)
    private Integer qty;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public QuoteItemRequest (String menuId, Integer qty) {
        this.menuId = menuId;
        this.qty = qty;
    }
}
