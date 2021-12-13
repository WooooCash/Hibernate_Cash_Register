package com.example.CashRegister;

import javax.persistence.Entity;
import javax.persistence.GenerationType;

@Entity
@javax.persistence.Table(name = "INVOICE", schema = "SBD_ST_PS11_4", catalog = "")
public class InvoiceEntity {
    private long invoiceId;

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Column(name = "INVOICE_ID", nullable = false, precision = 0)
    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    private long nip;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "NIP", nullable = false, precision = 0)
    public long getNip() {
        return nip;
    }

    public void setNip(long nip) {
        this.nip = nip;
    }

    private float netprice;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "NETPRICE", nullable = false, precision = 2)
    public float getNetprice() {
        return netprice;
    }

    public void setNetprice(float netprice) {
        this.netprice = netprice;
    }

    private double taxamount;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "TAXAMOUNT", nullable = false, precision = 2)
    public double getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(double taxamount) {
        this.taxamount = taxamount;
    }

    private String firstname;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "FIRSTNAME", nullable = false, length = 100)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String lastname;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "LASTNAME", nullable = false, length = 100)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String address;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "ADDRESS", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceEntity that = (InvoiceEntity) o;

        if (invoiceId != that.invoiceId) return false;
        if (nip != that.nip) return false;
        if (netprice != that.netprice) return false;
        if (Double.compare(that.taxamount, taxamount) != 0) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (invoiceId ^ (invoiceId >>> 32));
        result = 31 * result + (int) (nip ^ (nip >>> 32));
        result = Float.floatToIntBits(netprice);
        temp = Double.doubleToLongBits(taxamount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
