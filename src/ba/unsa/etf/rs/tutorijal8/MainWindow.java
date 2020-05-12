package ba.unsa.etf.rs.tutorijal8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
    //kada se zele testovi pokrenuti ova se klasa treba zakomentarisati
    @Override
    public void start(Stage primaryStage) throws Exception{
        DriverModel model = DriverModel.dajInstancu();
        BusModel model2 = BusModel.dajInstancu();

        DriverController ctrl = new DriverController(model,model2);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        primaryStage.setTitle("ETF Transport");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
