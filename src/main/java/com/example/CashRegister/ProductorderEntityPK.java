package com.example.CashRegister;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ProductorderEntityPK implements Serializable {
    private long productId;
    private long orderId;

    @Column(name = "PRODUCT_ID", nullable = false, precision = 0)
    @Id
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Column(name = "ORDER_ID", nullable = false, precision = 0)
    @Id
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

        ProductorderEntityPK that = (ProductorderEntityPK) o;

        if (productId != that.productId) return false;
        if (orderId != that.orderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (productId ^ (productId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        return result;
    }
}
