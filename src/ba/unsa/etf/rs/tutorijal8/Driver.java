package ba.unsa.etf.rs.tutorijal8;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.Objects;

public class Driver {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty surname = new SimpleStringProperty("");
    private SimpleStringProperty jmb = new SimpleStringProperty("");
    private SimpleObjectProperty<LocalDate> Birthday;
    private SimpleObjectProperty<LocalDate> dateOfEmployment;

    public Driver(String n, String s, String j, LocalDate doB, LocalDate doE) {
        name = new SimpleStringProperty(n);
        surname = new SimpleStringProperty(s);
        jmb = new SimpleStringProperty(j);
        Birthday = new SimpleObjectProperty<LocalDate>(doB);
        dateOfEmployment = new SimpleObjectProperty<LocalDate>(doE);
    }

    public Driver() {
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getJmb() {
        return jmb.get();
    }

    public SimpleStringProperty jmbProperty() {
        return jmb;
    }

    public void setJmb(String jmb) {
        this.jmb.set(jmb);
    }

    public LocalDate getBirthday() {
        return Birthday.get();
    }

    public SimpleObjectProperty<LocalDate> birthdayProperty() {
        return Birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.Birthday.set(birthday);
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment.get();
    }

    public SimpleObjectProperty<LocalDate> dateOfEmploymentProperty() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment.set(dateOfEmployment);
    }

    @Override
    public String toString() {
        return " - ("+getName()+" "+getSurname()+" ( "+getJmb()+" )"+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name) &&
                Objects.equals(surname, driver.surname) &&
                Objects.equals(jmb, driver.jmb) &&
                Objects.equals(Birthday, driver.Birthday) &&
                Objects.equals(dateOfEmployment, driver.dateOfEmployment);
    }
}
