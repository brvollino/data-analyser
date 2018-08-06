package com.vollino.data.analyser.core.model;

import com.google.common.base.MoreObjects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Bruno Vollino
 */
@Entity(name = "sale_item")
public class SaleItem {

    @EmbeddedId
    private SaleItemId id;
    private Integer quantity;
    private BigDecimal unitPrice;

    public SaleItem() {
    }

    public SaleItem(SaleItemId id, Integer quantity, BigDecimal unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public SaleItemId getId() {
        return id;
    }

    public void setId(SaleItemId id) {
        this.id = id;
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
        return Objects.equals(getId(), saleItem.getId()) &&
                Objects.equals(getQuantity(), saleItem.getQuantity()) &&
                Objects.equals(getUnitPrice(), saleItem.getUnitPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuantity(), getUnitPrice());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("quantity", quantity)
                .add("unitPrice", unitPrice)
                .toString();
    }
}
