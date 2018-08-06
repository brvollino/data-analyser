package com.vollino.data.analyser.core.model;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Bruno Vollino
 */
public class SaleItem {
    private Long saleId;
    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;

    public SaleItem() {
    }

    public SaleItem(Long saleId, Long itemId, Integer quantity, BigDecimal unitPrice) {
        this.saleId = saleId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem saleItem = (SaleItem) o;
        return Objects.equals(getSaleId(), saleItem.getSaleId()) &&
                Objects.equals(getItemId(), saleItem.getItemId()) &&
                Objects.equals(getQuantity(), saleItem.getQuantity()) &&
                Objects.equals(getUnitPrice(), saleItem.getUnitPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSaleId(), getItemId(), getQuantity(), getUnitPrice());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("saleId", saleId)
                .add("itemId", itemId)
                .add("quantity", quantity)
                .add("unitPrice", unitPrice)
                .toString();
    }
}
