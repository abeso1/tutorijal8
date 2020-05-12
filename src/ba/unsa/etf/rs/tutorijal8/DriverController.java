package ba.unsa.etf.rs.tutorijal8;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

public class DriverController {

    private DriverModel model;

    public ChoiceBox<Driver> izborVozaca;
    public TextField imeVozac;
    public TextField prezimeVozac;
    public TextField vozacJmb;

    public DriverController(DriverModel m){
        model = m;
    }

    @FXML
    public void initialize(){
        izborVozaca.setItems(model.getVozaci());

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
    }

    public void izabraniVozac(ActionEvent actionEvent){
        System.out.println("Trenutni vozac je: " + izborVozaca.getValue());
        model.setTrenutniVozac(izborVozaca.getValue());
    }

    public void ispisiVozace(ActionEvent actionEvent){
        model.ispisiVozace();
    }
}
