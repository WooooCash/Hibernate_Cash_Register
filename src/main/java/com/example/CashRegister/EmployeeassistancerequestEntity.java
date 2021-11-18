package com.example.CashRegister;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "EMPLOYEEASSISTANCEREQUEST", schema = "SBD_ST_PS11_4", catalog = "")
@javax.persistence.IdClass(com.example.CashRegister.EmployeeassistancerequestEntityPK.class)
public class EmployeeassistancerequestEntity {
    private long requestId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "REQUEST_ID", nullable = false, precision = 0)
    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    private long employeeId;

    @javax.persistence.Id
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

        EmployeeassistancerequestEntity that = (EmployeeassistancerequestEntity) o;

        if (requestId != that.requestId) return false;
        if (employeeId != that.employeeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (requestId ^ (requestId >>> 32));
        result = 31 * result + (int) (employeeId ^ (employeeId >>> 32));
        return result;
    }
}
