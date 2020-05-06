package ba.unsa.etf.rs.tutorijal8;

import java.sql.Date;
import java.time.LocalDate;

public class Driver {
    private String name;
    private String surname;
    private String jmb;
    private LocalDate Birthday;
    private LocalDate dateOfEmployment;

    public Driver(String name, String surname, String jmb, LocalDate dateOfBirth, LocalDate dateOfEmployment) {
        this.name = name;
        this.surname = surname;
        this.jmb = jmb;
        this.Birthday = dateOfBirth;
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

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate dateOfBirth) {
        this.Birthday = dateOfBirth;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    @Override
    public String toString() {
        return " - ("+name+" "+surname+" ( "+jmb+" )"+")";
    }
}
