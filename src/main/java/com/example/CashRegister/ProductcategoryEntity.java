package com.example.CashRegister;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "PRODUCTCATEGORY", schema = "SBD_ST_PS11_4", catalog = "")
public class ProductcategoryEntity {
    private long productcategoryId;

    @Id
    @javax.persistence.Column(name = "PRODUCTCATEGORY_ID", nullable = false, precision = 0)
    public long getProductcategoryId() {
        return productcategoryId;
    }

    public void setProductcategoryId(long productcategoryId) {
        this.productcategoryId = productcategoryId;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "NAME", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private long taxcategoryId;

    @Basic
    @javax.persistence.Column(name = "TAXCATEGORY_ID", nullable = false, precision = 0)
    public long getTaxcategoryId() {
        return taxcategoryId;
    }

    public void setTaxcategoryId(long taxcategoryId) {
        this.taxcategoryId = taxcategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductcategoryEntity that = (ProductcategoryEntity) o;

        if (productcategoryId != that.productcategoryId) return false;
        if (taxcategoryId != that.taxcategoryId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (productcategoryId ^ (productcategoryId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (taxcategoryId ^ (taxcategoryId >>> 32));
        return result;
    }
}
