package ba.unsa.etf.rs.tutorijal8;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DriverModel {
    private ObservableList<Driver> vozaci = FXCollections.observableArrayList();
    private ObjectProperty<Driver> trenutniVozac = null;

    private static DriverModel instanca = null;

    private Connection conn;
    private PreparedStatement stmt;

    private DriverModel(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            stmt = conn.prepareStatement("SELECT name, surname, jmb, date_of_birth, date_of_employment FROM driver");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Driver d = new Driver(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4).toLocalDate(),rs.getDate(5).toLocalDate());
                vozaci.add(d);
                if(trenutniVozac == null) trenutniVozac = new SimpleObjectProperty<Driver>(d);
            }
        } catch (SQLException e) {
            System.out.println("Nije uspjelo citanje iz baze. Izuzetak: " + e.getMessage());
        }

        if(trenutniVozac == null) trenutniVozac = new SimpleObjectProperty<>();

    }

    public static void initialize() {
        instanca = new DriverModel();
    }

    public static DriverModel dajInstancu(){
        if(instanca == null) initialize();
        return instanca;
    }

    public ObjectProperty<Driver> trenutniVozacProperty(){
        return trenutniVozac;
    }
    public Driver getTrenutniVozac(){
        return trenutniVozac.get();
    }
    public void setTrenutniVozac(Driver d){
        trenutniVozac.set(d);
    }
    public ObservableList<Driver> getVozaci(){
        return vozaci;
    }

    public void ispisiVozace() {
        System.out.println("Vozaci su:");
        for (Driver d : vozaci)
            System.out.println(d);
    }
}
