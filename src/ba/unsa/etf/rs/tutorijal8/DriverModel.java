package ba.unsa.etf.rs.tutorijal8;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class DriverModel {
    private ObservableList<Driver> vozaci = FXCollections.observableArrayList();
    private ObjectProperty<Driver> trenutniVozac = null;

    private static DriverModel instanca = null;

    private Connection conn;
    private PreparedStatement stmt, stmt2, stmt3, stmt4;

    private DriverModel(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            resetDatabase();
            stmt = conn.prepareStatement("SELECT name, surname, jmb, date_of_birth, date_of_employment FROM driver");
            stmt2 = conn.prepareStatement("insert into driver values (7,'Amer','Beso','123123','1-1-1 2:2:2.4','1-1-1 2:2:2.4')");
            stmt3 = conn.prepareStatement("insert into driver values (6,'Vedran','Ljubovic','456456','1-1-1 2:2:2.4','1-1-1 2:2:2.4')");
            stmt4 = conn.prepareStatement("insert into driver values (1,'Tarik','Sijercic','789789','1-1-1 2:2:2.4','1-1-1 2:2:2.4')");
            stmt2.executeUpdate();
            stmt3.executeUpdate();
            stmt4.executeUpdate();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Driver d = new Driver(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4).toLocalDate(),rs.getDate(5).toLocalDate());
                vozaci.add(d);
                if(trenutniVozac == null) trenutniVozac = new SimpleObjectProperty<Driver>(d);
            }
        } catch (SQLException e) {
            System.out.println("Nije uspjelo citanje iz baze. Izuzetak: " + e.getMessage());
            e.printStackTrace();
        }

        if(trenutniVozac == null) trenutniVozac = new SimpleObjectProperty<>();

    }

    public void resetDatabase() {
        Scanner ulaz = null;

        try {
            ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while(ulaz.hasNext()){
                sqlUpit+=ulaz.nextLine();
                if(sqlUpit.charAt(sqlUpit.length()-1)==';'){
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit="";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ulaz.close();
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
