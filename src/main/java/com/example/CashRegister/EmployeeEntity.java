package com.example.CashRegister;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "EMPLOYEE", schema = "SBD_ST_PS11_4", catalog = "")
public class EmployeeEntity {
    private long employeeId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "EMPLOYEE_ID", nullable = false, precision = 0)
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    private String gender;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "GENDER", nullable = true, length = 1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String name;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "NAME", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private String password;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "PASSWORD", nullable = false, length = 16)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Long managerid;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "MANAGERID", nullable = true, precision = 0)
    public Long getManagerid() {
        return managerid;
    }

    public void setManagerid(Long managerid) {
        this.managerid = managerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (employeeId != that.employeeId) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (managerid != null ? !managerid.equals(that.managerid) : that.managerid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (employeeId ^ (employeeId >>> 32));
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (managerid != null ? managerid.hashCode() : 0);
        return result;
    }
}
