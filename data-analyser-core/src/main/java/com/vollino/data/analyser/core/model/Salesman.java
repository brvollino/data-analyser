package com.vollino.data.analyser.core.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Bruno Vollino
 */
@Entity(name = "salesman")
public class Salesman {

    @Id
    private String cpf;
    private String name;
    private BigDecimal salary;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesman salesman = (Salesman) o;
        return Objects.equals(getCpf(), salesman.getCpf()) &&
                Objects.equals(getName(), salesman.getName()) &&
                Objects.equals(getSalary(), salesman.getSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf(), getName(), getSalary());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cpf", cpf)
                .add("name", name)
                .add("salary", salary)
                .toString();
    }
}
