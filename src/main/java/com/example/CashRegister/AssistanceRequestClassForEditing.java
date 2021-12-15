package com.example.CashRegister;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

public class AssistanceRequestClassForEditing {
    private String name, lastname, description;
    private long employeeId;
    private Date dateOfRequest;
    public AssistanceRequestClassForEditing(String name, String lastname, long employeeId,
                                            String description, Date dateOfRequest){
        this.name = name;
        this.lastname = lastname;
        this.employeeId = employeeId;
        this.description = description;
        this.dateOfRequest = dateOfRequest;
    }
    public AssistanceRequestClassForEditing(Object [] parameters){
        this.name = (String)parameters[0];
        this.lastname = ( String )parameters[1];
        this.employeeId = ( long )parameters[2];
        if( parameters[3] == null )
            this.description = "Brak opisu";
        else
            this.description = ( String )parameters[3];
        this.dateOfRequest = ( Date )parameters[4];
    }
    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public String getDescription() {
        return description;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

}
