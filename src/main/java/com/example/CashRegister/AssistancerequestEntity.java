package com.example.CashRegister;

import javax.persistence.Entity;
import java.sql.Time;

@Entity
@javax.persistence.Table(name = "ASSISTANCEREQUEST", schema = "SBD_ST_PS11_4", catalog = "")
public class AssistancerequestEntity {
    private long requestId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "REQUEST_ID", nullable = false, precision = 0)
    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    private String description;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "DESCRIPTION", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Time datetimeofrequest;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "DATETIMEOFREQUEST", nullable = false)
    public Time getDatetimeofrequest() {
        return datetimeofrequest;
    }

    public void setDatetimeofrequest(Time datetimeofrequest) {
        this.datetimeofrequest = datetimeofrequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssistancerequestEntity that = (AssistancerequestEntity) o;

        if (requestId != that.requestId) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (datetimeofrequest != null ? !datetimeofrequest.equals(that.datetimeofrequest) : that.datetimeofrequest != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (requestId ^ (requestId >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (datetimeofrequest != null ? datetimeofrequest.hashCode() : 0);
        return result;
    }
}
