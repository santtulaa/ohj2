package Treenipaivakirja;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import treenipk.Treenipaivakirja;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * Treenipäiväkirjan Main-luokka
 * @author elsal
 * @author salmelsa
 * @version 17.2.2022
 *
 */
public class TreenipkMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("TreenipkGUIView.fxml"));
            final Pane root = ldr.load();
            final TreenipkGUIController treeniCtrl = (TreenipkGUIController)ldr.getController();
            Scene scene = new Scene(root);
            
            //final Scene scene = new Scene(root); miks vesalla näin?
            scene.getStylesheets().add(getClass().getResource("treenipk.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Treenipäiväkirja");
            
            Treenipaivakirja treenipaivakirja = new Treenipaivakirja();
            treeniCtrl.setTreeni(treenipaivakirja); //miks valittaa tästä
            
            primaryStage.setOnCloseRequest((event) -> {
                if (!treeniCtrl.voikoSulkea() ) event.consume();
            });
            
            primaryStage.show();
            if ( !treeniCtrl.avaa()) Platform.exit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}