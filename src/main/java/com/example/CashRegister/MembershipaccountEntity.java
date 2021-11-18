package com.example.CashRegister;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "MEMBERSHIPACCOUNT", schema = "SBD_ST_PS11_4", catalog = "")
public class MembershipaccountEntity {
    private long membershipId;

    @Id
    @javax.persistence.Column(name = "MEMBERSHIP_ID", nullable = false, precision = 0)
    public long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
    }

    private long phonenumber;

    @Basic
    @javax.persistence.Column(name = "PHONENUMBER", nullable = false, precision = 0)
    public long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }

    private String email;

    @Basic
    @javax.persistence.Column(name = "EMAIL", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MembershipaccountEntity that = (MembershipaccountEntity) o;

        if (membershipId != that.membershipId) return false;
        if (phonenumber != that.phonenumber) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (membershipId ^ (membershipId >>> 32));
        result = 31 * result + (int) (phonenumber ^ (phonenumber >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
