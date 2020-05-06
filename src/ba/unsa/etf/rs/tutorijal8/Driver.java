package ba.unsa.etf.rs.tutorijal8;

import java.time.LocalDate;
import java.util.Date;

public class Driver {
    private String name;
    private String surname;
    private String jmb;
    private LocalDate dateOfBirth;
    private LocalDate dateOfEmployment;

    public Driver(String name, String surname, String jmb, LocalDate dateOfBirth, LocalDate dateOfEmployment) {
        this.name = name;
        this.surname = surname;
        this.jmb = jmb;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEmployment = dateOfEmployment;
    }

    public Driver() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJmb() {
        return jmb;
    }

    public void setJmb(String jmb) {
        this.jmb = jmb;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }
}
