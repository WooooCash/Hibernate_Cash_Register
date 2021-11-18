package com.example.CashRegister;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "SUPPLIER", schema = "SBD_ST_PS11_4", catalog = "")
public class SupplierEntity {
    private long nip;

    @javax.persistence.Id
    @javax.persistence.Column(name = "NIP", nullable = false, precision = 0)
    public long getNip() {
        return nip;
    }

    public void setNip(long nip) {
        this.nip = nip;
    }

    private String countryorigin;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "COUNTRYORIGIN", nullable = true, length = 56)
    public String getCountryorigin() {
        return countryorigin;
    }

    public void setCountryorigin(String countryorigin) {
        this.countryorigin = countryorigin;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupplierEntity that = (SupplierEntity) o;

        if (nip != that.nip) return false;
        if (countryorigin != null ? !countryorigin.equals(that.countryorigin) : that.countryorigin != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (nip ^ (nip >>> 32));
        result = 31 * result + (countryorigin != null ? countryorigin.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
