package com.example.CashRegister;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import java.sql.Date;
import java.sql.Time;

@Entity
@javax.persistence.Table(name = "\"Order\"", schema = "SBD_ST_PS11_4", catalog = "")
public class OrderEntity {
    private long orderId;

    @javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    @javax.persistence.Column(name = "ORDER_ID", nullable = false, precision = 0)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    private Date orderdate;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "ORDERDATE", nullable = false)
    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    private double ordertotalprice;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "ORDERTOTALPRICE", nullable = false, precision = 2)
    public double getOrdertotalprice() {
        return ordertotalprice;
    }

    public void setOrdertotalprice(double ordertotalprice) {
        this.ordertotalprice = ordertotalprice;
    }

    private long cashregisternumber;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "CASHREGISTERNUMBER", nullable = false, precision = 0)
    public long getCashregisternumber() {
        return cashregisternumber;
    }

    public void setCashregisternumber(long cashregisternumber) {
        this.cashregisternumber = cashregisternumber;
    }

    private String transactiontype;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "TRANSACTIONTYPE", nullable = false, length = 30)
    public String getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }

    private long membershipId;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "MEMBERSHIP_ID", nullable = false, precision = 0)
    public long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
    }

    private long invoiceId;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "INVOICE_ID", nullable = false, precision = 0)
    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    private long couponId;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "COUPON_ID", nullable = false, precision = 0)
    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    private long employeeId;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "EMPLOYEE_ID", nullable = false, precision = 0)
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (orderId != that.orderId) return false;
        if (Double.compare(that.ordertotalprice, ordertotalprice) != 0) return false;
        if (cashregisternumber != that.cashregisternumber) return false;
        if (membershipId != that.membershipId) return false;
        if (invoiceId != that.invoiceId) return false;
        if (couponId != that.couponId) return false;
        if (employeeId != that.employeeId) return false;
        if (orderdate != null ? !orderdate.equals(that.orderdate) : that.orderdate != null) return false;
        if (transactiontype != null ? !transactiontype.equals(that.transactiontype) : that.transactiontype != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (orderdate != null ? orderdate.hashCode() : 0);
        temp = Double.doubleToLongBits(ordertotalprice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (cashregisternumber ^ (cashregisternumber >>> 32));
        result = 31 * result + (transactiontype != null ? transactiontype.hashCode() : 0);
        result = 31 * result + (int) (membershipId ^ (membershipId >>> 32));
        result = 31 * result + (int) (invoiceId ^ (invoiceId >>> 32));
        result = 31 * result + (int) (couponId ^ (couponId >>> 32));
        result = 31 * result + (int) (employeeId ^ (employeeId >>> 32));
        return result;
    }

    public void setOrderdate(java.util.Date date) {
    }
}
