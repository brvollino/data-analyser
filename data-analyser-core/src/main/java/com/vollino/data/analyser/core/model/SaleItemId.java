package com.vollino.data.analyser.core.model;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Bruno Vollino
 */
@Embeddable
public class SaleItemId implements Serializable {

    private static final long serialVersionUID = -457619787482211262L;

    @Column(name = "sale_id")
    private Long saleId;
    @Column(name = "item_id")
    private Long itemId;

    public SaleItemId() {
    }

    public SaleItemId(Long saleId, Long itemId) {
        this.saleId = saleId;
        this.itemId = itemId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItemId that = (SaleItemId) o;
        return Objects.equals(getSaleId(), that.getSaleId()) &&
                Objects.equals(getItemId(), that.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSaleId(), getItemId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("saleId", saleId)
                .add("itemId", itemId)
                .toString();
    }
}
