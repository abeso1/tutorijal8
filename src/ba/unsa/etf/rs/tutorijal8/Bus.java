package ba.unsa.etf.rs.tutorijal8;

public class Bus {
    private String Maker;
    private String series;
    private int SeatNumber;

    public Bus(String manufacturer, String series, int numberOfSeats) {
        this.Maker = manufacturer;
        this.series = series;
        this.SeatNumber = numberOfSeats;
    }

    public Bus() {
    }

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String manufacturer) {
        this.Maker = manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getSeatNumber() {
        return SeatNumber;
    }

    public void setSeatNumber(int numberOfSeats) {
        this.SeatNumber = numberOfSeats;
    }

    public Driver getDriverOne() {
        return null;
    }

    public Driver getDriverTwo() {
        return null;
    }
}
