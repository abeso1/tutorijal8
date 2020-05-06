package ba.unsa.etf.rs.tutorijal8;

public class Bus {
    private String manufacturer;
    private String series;
    private int numberOfSeats;

    public Bus(String manufacturer, String series, int numberOfSeats) {
        this.manufacturer = manufacturer;
        this.series = series;
        this.numberOfSeats = numberOfSeats;
    }

    public Bus() {
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
