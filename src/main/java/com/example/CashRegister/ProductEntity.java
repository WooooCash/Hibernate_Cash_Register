package com.example.CashRegister;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "PRODUCT", schema = "SBD_ST_PS11_4", catalog = "")
public class ProductEntity {
    private long productId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "PRODUCT_ID", nullable = false, precision = 0)
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    private String name;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "NAME", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String unitscale;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "UNITSCALE", nullable = false, length = 20)
    public String getUnitscale() {
        return unitscale;
    }

    public void setUnitscale(String unitscale) {
        this.unitscale = unitscale;
    }

    private double price;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "PRICE", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private long instock;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "INSTOCK", nullable = false, precision = 0)
    public long getInstock() {
        return instock;
    }

    public void setInstock(long instock) {
        this.instock = instock;
    }

    private String requiresamount;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "REQUIRESAMOUNT", nullable = false, length = 1)
    public String getRequiresamount() {
        return requiresamount;
    }

    public void setRequiresamount(String requiresamount) {
        this.requiresamount = requiresamount;
    }

    private long productcategoryId;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "PRODUCTCATEGORY_ID", nullable = false, precision = 0)
    public long getProductcategoryId() {
        return productcategoryId;
    }

    public void setProductcategoryId(long productcategoryId) {
        this.productcategoryId = productcategoryId;
    }

    private long suppliernip;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "SUPPLIERNIP", nullable = false, precision = 0)
    public long getSuppliernip() {
        return suppliernip;
    }

    public void setSuppliernip(long suppliernip) {
        this.suppliernip = suppliernip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (productId != that.productId) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (instock != that.instock) return false;
        if (productcategoryId != that.productcategoryId) return false;
        if (suppliernip != that.suppliernip) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (unitscale != null ? !unitscale.equals(that.unitscale) : that.unitscale != null) return false;
        if (requiresamount != null ? !requiresamount.equals(that.requiresamount) : that.requiresamount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (productId ^ (productId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (unitscale != null ? unitscale.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (instock ^ (instock >>> 32));
        result = 31 * result + (requiresamount != null ? requiresamount.hashCode() : 0);
        result = 31 * result + (int) (productcategoryId ^ (productcategoryId >>> 32));
        result = 31 * result + (int) (suppliernip ^ (suppliernip >>> 32));
        return result;
    }
}
