package com.vollino.data.analyser.core.model;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author Bruno Vollino
 */
@Entity(name = "customer")
public class Customer {

    @Id
    private String cnpj;
    private String name;
    @Column(name = "business_area")
    private String businessArea;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCnpj(), customer.getCnpj()) &&
                Objects.equals(getName(), customer.getName()) &&
                Objects.equals(getBusinessArea(), customer.getBusinessArea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCnpj(), getName(), getBusinessArea());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cnpj", cnpj)
                .add("name", name)
                .add("businessArea", businessArea)
                .toString();
    }
}
