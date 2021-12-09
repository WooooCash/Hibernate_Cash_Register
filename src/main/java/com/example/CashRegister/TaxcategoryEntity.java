package com.example.CashRegister;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "TAXCATEGORY", schema = "SBD_ST_PS11_4", catalog = "")
public class TaxcategoryEntity {
    private long taxcategoryId;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @javax.persistence.Column(name = "TAXCATEGORY_ID", nullable = false, precision = 0)
    public long getTaxcategoryId() {
        return taxcategoryId;
    }

    public void setTaxcategoryId(long taxcategoryId) {
        this.taxcategoryId = taxcategoryId;
    }

    private float percentagetax;

    @Basic
    @javax.persistence.Column(name = "PERCENTAGETAX", nullable = false, precision = 2)
    public float getPercentagetax() {
        return percentagetax;
    }

    public void setPercentagetax(float percentagetax) {
        this.percentagetax = percentagetax;
    }

    private String taxname;

    @Basic
    @javax.persistence.Column(name = "TAXNAME", nullable = true, length = 100)
    public String getTaxname() {
        return taxname;
    }

    public void setTaxname(String taxname) {
        this.taxname = taxname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxcategoryEntity that = (TaxcategoryEntity) o;

        if (taxcategoryId != that.taxcategoryId) return false;
        if (Float.compare(that.percentagetax, percentagetax) != 0) return false;
        if (taxname != null ? !taxname.equals(that.taxname) : that.taxname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (taxcategoryId ^ (taxcategoryId >>> 32));
        result = 31 * result + (percentagetax != +0.0f ? Float.floatToIntBits(percentagetax) : 0);
        result = 31 * result + (taxname != null ? taxname.hashCode() : 0);
        return result;
    }
}
