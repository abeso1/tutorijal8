package ba.unsa.etf.rs.tutorijal8;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

public class DriverController {

    private DriverModel model;
    private BusModel model2;

    public ChoiceBox<Driver> izborVozaca;
    public ChoiceBox<Bus> izborBusa;
    public TextField imeVozac;
    public TextField prezimeVozac;
    public TextField vozacJmb;
    public TextField busNaziv;
    public TextField busSeries;
    public TextField busMjesta;

    public DriverController(DriverModel m, BusModel m2){
        model = m;
        model2 = m2;
    }



    @FXML
    public void initialize(){
        izborVozaca.setItems(model.getVozaci());
        izborBusa.setItems(model2.getBusevi());

        model.trenutniVozacProperty().addListener((observable, oldVozac, newVozac) ->{
            if(oldVozac != null){
                imeVozac.textProperty().unbindBidirectional(oldVozac.nameProperty());
                prezimeVozac.textProperty().unbindBidirectional(oldVozac.surnameProperty());
                vozacJmb.textProperty().unbindBidirectional(oldVozac.jmbProperty());
            }
            if(newVozac == null){
                imeVozac.setText("");
                prezimeVozac.setText("");
                vozacJmb.setText("");
            }
            else {
                imeVozac.textProperty().bindBidirectional(newVozac.nameProperty());
                prezimeVozac.textProperty().bindBidirectional(newVozac.surnameProperty());
                vozacJmb.textProperty().bindBidirectional(newVozac.jmbProperty());
            }
        });

        model2.trenutniBusProperty().addListener((observable, oldBus, newBus) -> {
            if(oldBus != null){
                busNaziv.textProperty().unbindBidirectional(oldBus.makerProperty());
                busSeries.textProperty().unbindBidirectional(oldBus.seriesProperty());
                busMjesta.textProperty().unbindBidirectional(oldBus.seatNumberProperty());
            }
            if(newBus == null){
                busNaziv.setText("");
                busSeries.setText("");
                busMjesta.setText("");
            }
            else {
                busNaziv.textProperty().bindBidirectional(newBus.makerProperty());
                busSeries.textProperty().bindBidirectional(newBus.seriesProperty());
                busMjesta.textProperty().bindBidirectional(new SimpleStringProperty(newBus.seatNumberProperty().toString()));
            }
        });
    }

    public void izabraniBus(ActionEvent actionEvent){
        System.out.println("Trenutni bus je: " + izborBusa.getValue());
        model2.setTrenutniBus(izborBusa.getValue());
    }

    public void ispisiBuseve(ActionEvent actionEvent){
        model2.ispisiBuseve();
    }

    public void izabraniVozac(ActionEvent actionEvent){
        System.out.println("Trenutni vozac je: " + izborVozaca.getValue());
        model.setTrenutniVozac(izborVozaca.getValue());
    }

    public void ispisiVozace(ActionEvent actionEvent){
        model.ispisiVozace();
    }


}
