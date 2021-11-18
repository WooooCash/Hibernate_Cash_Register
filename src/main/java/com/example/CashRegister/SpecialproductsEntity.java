package com.example.CashRegister;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "SPECIALPRODUCTS", schema = "SBD_ST_PS11_4", catalog = "")
public class SpecialproductsEntity {
    private long specialproductId;

    @Id
    @javax.persistence.Column(name = "SPECIALPRODUCT_ID", nullable = false, precision = 0)
    public long getSpecialproductId() {
        return specialproductId;
    }

    public void setSpecialproductId(long specialproductId) {
        this.specialproductId = specialproductId;
    }

    private String specialproductname;

    @Basic
    @javax.persistence.Column(name = "SPECIALPRODUCTNAME", nullable = true, length = 255)
    public String getSpecialproductname() {
        return specialproductname;
    }

    public void setSpecialproductname(String specialproductname) {
        this.specialproductname = specialproductname;
    }

    private Long rewardthreshold;

    @Basic
    @javax.persistence.Column(name = "REWARDTHRESHOLD", nullable = true, precision = 2)
    public Long getRewardthreshold() {
        return rewardthreshold;
    }

    public void setRewardthreshold(Long rewardthreshold) {
        this.rewardthreshold = rewardthreshold;
    }

    private long specialcategoryId;

    @Basic
    @javax.persistence.Column(name = "SPECIALCATEGORY_ID", nullable = false, precision = 0)
    public long getSpecialcategoryId() {
        return specialcategoryId;
    }

    public void setSpecialcategoryId(long specialcategoryId) {
        this.specialcategoryId = specialcategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialproductsEntity that = (SpecialproductsEntity) o;

        if (specialproductId != that.specialproductId) return false;
        if (specialcategoryId != that.specialcategoryId) return false;
        if (specialproductname != null ? !specialproductname.equals(that.specialproductname) : that.specialproductname != null)
            return false;
        if (rewardthreshold != null ? !rewardthreshold.equals(that.rewardthreshold) : that.rewardthreshold != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (specialproductId ^ (specialproductId >>> 32));
        result = 31 * result + (specialproductname != null ? specialproductname.hashCode() : 0);
        result = 31 * result + (rewardthreshold != null ? rewardthreshold.hashCode() : 0);
        result = 31 * result + (int) (specialcategoryId ^ (specialcategoryId >>> 32));
        return result;
    }
}
