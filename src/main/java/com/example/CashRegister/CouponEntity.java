package com.example.CashRegister;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
@javax.persistence.Table(name = "COUPON", schema = "SBD_ST_PS11_4", catalog = "")
public class CouponEntity {
    private long couponId;

    @Id
    @javax.persistence.Column(name = "COUPON_ID", nullable = false, precision = 0)
    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    private long couponcode;

    @Basic
    @javax.persistence.Column(name = "COUPONCODE", nullable = false, precision = 0)
    public long getCouponcode() {
        return couponcode;
    }

    public void setCouponcode(long couponcode) {
        this.couponcode = couponcode;
    }

    private String typeofcoupon;

    @Basic
    @javax.persistence.Column(name = "TYPEOFCOUPON", nullable = false, length = 1)
    public String getTypeofcoupon() {
        return typeofcoupon;
    }

    public void setTypeofcoupon(String typeofcoupon) {
        this.typeofcoupon = typeofcoupon;
    }

    private double couponamount;

    @Basic
    @javax.persistence.Column(name = "COUPONAMOUNT", nullable = false, precision = 2)
    public double getCouponamount() {
        return couponamount;
    }

    public void setCouponamount(double couponamount) {
        this.couponamount = couponamount;
    }

    private Time exprationdate;

    @Basic
    @javax.persistence.Column(name = "EXPRATIONDATE", nullable = true)
    public Time getExprationdate() {
        return exprationdate;
    }

    public void setExprationdate(Time exprationdate) {
        this.exprationdate = exprationdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CouponEntity that = (CouponEntity) o;

        if (couponId != that.couponId) return false;
        if (couponcode != that.couponcode) return false;
        if (Double.compare(that.couponamount, couponamount) != 0) return false;
        if (typeofcoupon != null ? !typeofcoupon.equals(that.typeofcoupon) : that.typeofcoupon != null) return false;
        if (exprationdate != null ? !exprationdate.equals(that.exprationdate) : that.exprationdate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (couponId ^ (couponId >>> 32));
        result = 31 * result + (int) (couponcode ^ (couponcode >>> 32));
        result = 31 * result + (typeofcoupon != null ? typeofcoupon.hashCode() : 0);
        temp = Double.doubleToLongBits(couponamount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (exprationdate != null ? exprationdate.hashCode() : 0);
        return result;
    }
}
