package com.example.CashRegister;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "SPECIALPRODUCTCATEGORY", schema = "SBD_ST_PS11_4", catalog = "")
public class SpecialproductcategoryEntity {
    private long specialcategoryId;

    @Id
    @javax.persistence.Column(name = "SPECIALCATEGORY_ID", nullable = false, precision = 0)
    public long getSpecialcategoryId() {
        return specialcategoryId;
    }

    public void setSpecialcategoryId(long specialcategoryId) {
        this.specialcategoryId = specialcategoryId;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "NAME", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String description;

    @Basic
    @javax.persistence.Column(name = "DESCRIPTION", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Date begindate;

    @Basic
    @javax.persistence.Column(name = "BEGINDATE", nullable = false)
    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    private Date enddate;

    @Basic
    @javax.persistence.Column(name = "ENDDATE", nullable = false)
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialproductcategoryEntity that = (SpecialproductcategoryEntity) o;

        if (specialcategoryId != that.specialcategoryId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (begindate != null ? !begindate.equals(that.begindate) : that.begindate != null) return false;
        if (enddate != null ? !enddate.equals(that.enddate) : that.enddate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (specialcategoryId ^ (specialcategoryId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (begindate != null ? begindate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        return result;
    }
}
