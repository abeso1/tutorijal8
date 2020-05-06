package ba.unsa.etf.rs.tutorijal8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TransportDAO {
    private static TransportDAO instance;
    private Connection conn;

    private PreparedStatement upit, addDriverUpit, driverIdUpit, addBusUpit, busIdUpit, getDriversUpit, getBussesUpit,
                                deleteBusUpit, deleteDriverUpit, deleteBusFromDriversUpit, selectDriversUpit;

    public static TransportDAO getInstance(){
        if(instance==null) instance = new TransportDAO();
        return instance;
    }
    private TransportDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            upit = conn.prepareStatement("SELECT * from bus");
        } catch (SQLException e) {
            resetDatabase();
            try {
                upit = conn.prepareStatement("SELECT * from bus");
            }
            catch (SQLException e1){
                e1.printStackTrace();
            }
        }

        try {
            addDriverUpit = conn.prepareStatement("INSERT INTO driver VALUES(?,?,?,?,?,?);");
            driverIdUpit = conn.prepareStatement("SELECT MAX(id) FROM driver;");
            addBusUpit = conn.prepareStatement("INSERT INTO bus VALUES(?,?,?,?,?);");
            busIdUpit = conn.prepareStatement("SELECT MAX(id) FROM bus;");
            getDriversUpit = conn.prepareStatement("SELECT * FROM driver;");
            getBussesUpit = conn.prepareStatement("SELECT * FROM bus;");
            deleteBusUpit = conn.prepareStatement("DELETE FROM bus WHERE manufacturer=? AND series=? AND number_of_seats=?;");
            selectDriversUpit = conn.prepareStatement("SELECT * FROM driver WHERE jmb=?;");
            deleteBusFromDriversUpit = conn.prepareStatement("DELETE FROM bus WHERE driver=?;");
            deleteDriverUpit = conn.prepareStatement("DELETE FROM driver WHERE jmb=?;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeInstance(){
        instance.close();
        instance = null;
    }

    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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


    public void addDriver(Driver driver) {
        try {
            selectDriversUpit.setString(1,driver.getJmb());
            ResultSet set = selectDriversUpit.executeQuery();
            if(set.next()) throw new IllegalArgumentException("Taj vozač već postoji!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = driverIdUpit.executeQuery();
            int id=1;
            if(rs.next()){
                id=rs.getInt(1)+1;
            }
            addDriverUpit.setInt(1,id);
            addDriverUpit.setString(2,driver.getName());
            addDriverUpit.setString(3,driver.getSurname());
            addDriverUpit.setString(4,driver.getJmb());
            addDriverUpit.setDate(5, Date.valueOf(driver.getBirthday()));
            addDriverUpit.setDate(6, Date.valueOf(driver.getDateOfEmployment()));

            addDriverUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Driver> getDrivers() {
        ArrayList<Driver> rezultat = new ArrayList<>();
        try {
            ResultSet rs = getDriversUpit.executeQuery();
            while(rs.next()){
                Driver driver = getDriversFromResultSet(rs);
                rezultat.add(driver);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rezultat;
    }

    private Driver getDriversFromResultSet(ResultSet rs) throws SQLException {
        return new Driver(rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate(),rs.getDate(6).toLocalDate());
    }

    public void addBus(Bus bus) {
        try {
            ResultSet rs = busIdUpit.executeQuery();
            int id=1;
            if(rs.next()){
                id=rs.getInt(1)+1;
            }
            int idDriver=1;
            addBusUpit.setInt(1,id);
            addBusUpit.setString(2,bus.getMaker());
            addBusUpit.setString(3,bus.getSeries());
            addBusUpit.setInt(4,bus.getSeatNumber());
            addBusUpit.setInt(5,idDriver);

            addBusUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bus> getBusses() {
        ArrayList<Bus> rezultat = new ArrayList<>();
        try {
            ResultSet rs = getBussesUpit.executeQuery();
            while(rs.next()){
                Bus bus = getBussesFromResultSet(rs);
                rezultat.add(bus);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rezultat;
    }

    private Bus getBussesFromResultSet(ResultSet rs) throws SQLException {
        return new Bus(rs.getString(2), rs.getString(3),rs.getInt(4));
    }

    public void deleteBus(Bus bus) {
        try {
            deleteBusUpit.setString(1,bus.getMaker());
            deleteBusUpit.setString(2,bus.getSeries());
            deleteBusUpit.setInt(3,bus.getSeatNumber());
            deleteBusUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDriver(Driver driver) {
        try {
            selectDriversUpit.setString(1,driver.getJmb());
            ResultSet rs = selectDriversUpit.executeQuery();
            if(!rs.next()) return;
            int id = getIdDriver(rs);
            deleteBusFromDriversUpit.setInt(1,id);
            deleteBusFromDriversUpit.executeUpdate();

            deleteDriverUpit.setString(1,driver.getJmb());
            deleteDriverUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getIdDriver(ResultSet rs) throws SQLException {
        return rs.getInt(1);
    }

    public void dodijeliVozacuAutobus(Driver driver, Bus bus, int which) {

    }
}
