package org.leiber.entity;

import java.math.BigDecimal;

/**
 * Entity employee representing employee data <br>
 * Created on 10/10/2024 at 8:54 p.m.
 *
 * @author Leiber Bertel
 */
public class EmployeeEntity {
    private Integer employeeId;
    private String firstname;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private BigDecimal salary;
    private String curp;

    public EmployeeEntity() {
    }

    public EmployeeEntity(String firstname, String firstSurname, String secondSurname, String email, BigDecimal salary, String curp) {
        this.firstname = firstname;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.email = email;
        this.salary = salary;
        this.curp = curp;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }


    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "employeeId=" + employeeId +
                ", firstname='" + firstname + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary + '\'' +
                ", curp=" + curp +
                '}';
    }
}
