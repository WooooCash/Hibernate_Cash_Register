package com.example.CashRegister;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class EmployeeassistancerequestEntityPK implements Serializable {
    private long requestId;
    private long employeeId;

    @Column(name = "REQUEST_ID", nullable = false, precision = 0)
    @Id
    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    @Column(name = "EMPLOYEE_ID", nullable = false, precision = 0)
    @Id
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

        EmployeeassistancerequestEntityPK that = (EmployeeassistancerequestEntityPK) o;

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
