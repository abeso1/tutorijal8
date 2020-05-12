package ba.unsa.etf.rs.tutorijal8;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Bus {
    private SimpleStringProperty Maker;
    private SimpleStringProperty series;
    private SimpleIntegerProperty SeatNumber;

    public Bus(String m, String s, int noS) {
        Maker = new SimpleStringProperty(m);
        series = new SimpleStringProperty(s);
        SeatNumber = new SimpleIntegerProperty(noS);
    }

    public Bus() {
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
        return Maker+" "+ series+" ( seats: "+SeatNumber+" )";
    }
}
