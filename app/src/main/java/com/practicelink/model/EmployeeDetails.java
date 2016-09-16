package com.practicelink.model;

import java.util.List;

/**
 * Created by Ramesh on 9/16/16.
 */
public class EmployeeDetails {
    private List<EmployeeDetails> employeeDetailsList;
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile;
    private String password;
    private String created_at;
    private String updated_at;
    private String url;

//     Getters and Setters


    public List<EmployeeDetails> getEmployeeDetailsList() {
        return employeeDetailsList;
    }

    public void setEmployeeDetailsList(List<EmployeeDetails> employeeDetailsList) {
        this.employeeDetailsList = employeeDetailsList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}