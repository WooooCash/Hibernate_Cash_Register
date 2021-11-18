package com.example.CashRegister;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "PRODUCTORDER", schema = "SBD_ST_PS11_4", catalog = "")
@javax.persistence.IdClass(com.example.CashRegister.ProductorderEntityPK.class)
public class ProductorderEntity {
    private long productamount;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "PRODUCTAMOUNT", nullable = false, precision = 0)
    public long getProductamount() {
        return productamount;
    }

    public void setProductamount(long productamount) {
        this.productamount = productamount;
    }

    private Long taxcategoryname;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "TAXCATEGORYNAME", nullable = true, precision = 0)
    public Long getTaxcategoryname() {
        return taxcategoryname;
    }

    public void setTaxcategoryname(Long taxcategoryname) {
        this.taxcategoryname = taxcategoryname;
    }

    private double taxprice;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "TAXPRICE", nullable = false, precision = 2)
    public double getTaxprice() {
        return taxprice;
    }

    public void setTaxprice(double taxprice) {
        this.taxprice = taxprice;
    }

    private double priceforproduct;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "PRICEFORPRODUCT", nullable = false, precision = 2)
    public double getPriceforproduct() {
        return priceforproduct;
    }

    public void setPriceforproduct(double priceforproduct) {
        this.priceforproduct = priceforproduct;
    }

    private long productId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "PRODUCT_ID", nullable = false, precision = 0)
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    private long orderId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "ORDER_ID", nullable = false, precision = 0)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductorderEntity that = (ProductorderEntity) o;

        if (productamount != that.productamount) return false;
        if (Double.compare(that.taxprice, taxprice) != 0) return false;
        if (Double.compare(that.priceforproduct, priceforproduct) != 0) return false;
        if (productId != that.productId) return false;
        if (orderId != that.orderId) return false;
        if (taxcategoryname != null ? !taxcategoryname.equals(that.taxcategoryname) : that.taxcategoryname != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (productamount ^ (productamount >>> 32));
        result = 31 * result + (taxcategoryname != null ? taxcategoryname.hashCode() : 0);
        temp = Double.doubleToLongBits(taxprice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(priceforproduct);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (productId ^ (productId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        return result;
    }
}
