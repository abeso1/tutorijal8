package ba.unsa.etf.rs.tutorijal8;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class BusModel {
    private ObservableList<Bus> busevi = FXCollections.observableArrayList();
    private ObjectProperty<Bus> trenutniBus = null;

    private static BusModel instanca = null;

    private Connection conn;
    private PreparedStatement stmt, stmt2, stmt3, stmt4;

    private BusModel(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            resetDatabase();
            stmt = conn.prepareStatement("SELECT manufacturer, series, number_of_seats FROM bus");
            stmt2 = conn.prepareStatement("insert into bus(id,manufacturer,series,number_of_seats) values (7,'Man','X63',24)");
            stmt3 = conn.prepareStatement("insert into bus(id,manufacturer,series,number_of_seats) values (6,'Scania','V23',255)");
            stmt4 = conn.prepareStatement("insert into bus(id,manufacturer,series,number_of_seats) values (1,'Zaga','V23',124)");
            stmt2.executeUpdate();
            stmt3.executeUpdate();
            stmt4.executeUpdate();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Bus b = new Bus(rs.getString(1),rs.getString(2),rs.getInt(3));
                busevi.add(b);
                if(trenutniBus == null) trenutniBus = new SimpleObjectProperty<Bus>(b);
            }
        } catch (SQLException e) {
            System.out.println("Nije uspjelo citanje iz baze. Izuzetak: " + e.getMessage());
        }

        if(trenutniBus == null) trenutniBus = new SimpleObjectProperty<>();

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
        instanca = new BusModel();
    }

    public static BusModel dajInstancu(){
        if(instanca == null) initialize();
        return instanca;
    }

    public ObjectProperty<Bus> trenutniBusProperty(){
        return trenutniBus;
    }
    public Bus getTrenutniBus(){
        return trenutniBus.get();
    }
    public void setTrenutniBus(Bus b){
        trenutniBus.set(b);
    }
    public ObservableList<Bus> getBusevi(){
        return busevi;
    }

    public void ispisiBuseve() {
        System.out.println("Busevi su:");
        for (Bus b : busevi)
            System.out.println(b);
    }
}
