package com.vollino.data.analyser.core.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author Bruno Vollino
 */
@Entity(name = "sale")
public class Sale {

    @Id
    private Long id;
    @Column(name = "salesman_name")
    private String salesmanName;
    @Column(name = "total_value")
    private BigDecimal totalValue;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id")
    private List<SaleItem> saleItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(getId(), sale.getId()) &&
                Objects.equals(getSalesmanName(), sale.getSalesmanName()) &&
                Objects.equals(getTotalValue(), sale.getTotalValue()) &&
                Objects.equals(getSaleItems(), sale.getSaleItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSalesmanName(), getTotalValue(), getSaleItems());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("salesmanName", salesmanName)
                .add("totalValue", totalValue)
                .add("saleItems", saleItems)
                .toString();
    }
}
