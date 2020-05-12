package ba.unsa.etf.rs.tutorijal8;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BusModel {
    private ObservableList<Bus> busevi = FXCollections.observableArrayList();
    private ObjectProperty<Bus> trenutniBus = null;

    private static BusModel instanca = null;

    private Connection conn;
    private PreparedStatement stmt;

    private BusModel(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            stmt = conn.prepareStatement("SELECT manufacturer, series, number_of_seats FROM bus");
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
