package ba.unsa.etf.rs.tutorijal8;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Bus {
    private SimpleStringProperty Maker;
    private SimpleStringProperty series;
    private SimpleIntegerProperty SeatNumber;
    private Driver driver;

    public Bus(String m, String s, int noS) {
        Maker = new SimpleStringProperty(m);
        series = new SimpleStringProperty(s);
        SeatNumber = new SimpleIntegerProperty(noS);
    }


    public Bus() {
    }

    public Bus(String string, String string1, int anInt, Driver d) {
        Maker = new SimpleStringProperty(string);
        series = new SimpleStringProperty(string1);
        SeatNumber = new SimpleIntegerProperty(anInt);
        driver = d;
    }

    public String getMaker() {
        return Maker.get();
    }

    public SimpleStringProperty makerProperty() {
        return Maker;
    }

    public void setMaker(String maker) {
        this.Maker.set(maker);
    }

    public String getSeries() {
        return series.get();
    }

    public SimpleStringProperty seriesProperty() {
        return series;
    }

    public void setSeries(String series) {
        this.series.set(series);
    }

    public int getSeatNumber() {
        return SeatNumber.get();
    }

    public SimpleIntegerProperty seatNumberProperty() {
        return SeatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.SeatNumber.set(seatNumber);
    }



    @Override
    public String toString() {
        if(driver==null){
            return getMaker()+" "+ getSeries()+" ( seats: "+getSeatNumber()+" )";
        }
        return getMaker()+" "+ getSeries()+" ( seats: "+getSeatNumber()+" ) - ("+ driver.getName()+" "+driver.getSurname()+" ( "+driver.getJmb()+" ))";
    }

    public Driver getDriverOne() {
        return driver;
    }

    public Driver getDriverTwo() {
        return driver;
    }
}
