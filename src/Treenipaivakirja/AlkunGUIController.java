package Treenipaivakirja;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import Treenipaivakirja.AlkunGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Alkunäytön controlleri
 * @author elsal
 * @author salmelsa
 * @version 17.2.2022
 *
 */
public class AlkunGUIController implements ModalControllerInterface<String> {

@FXML private TextField textVastaus;

@Override
public String getResult() {
    return textVastaus.getText();
}

/**
 * Mitä tehdään kun dialogi on näytetty
 */
@Override
public void handleShown() {
    textVastaus.requestFocus();
    
}

@Override
public void setDefault(String oletus) {
    textVastaus.setText(oletus);
    
}

/**
 * Luodaan nimenkysymisdialogi ja palautetaan siihen kirjoitettu nimi tai null
 * @param modalityStage mille ollaan modaalisia, null = sovellukselle
 * @param oletus mitä nimeä näytetään oletuksena
 * @return null jos painetaan Cancel, muuten kirjoitettu nimi
 */
public static String aloita(Stage modalityStage, String oletus) {
    return ModalController.showModal(
            AlkunGUIController.class.getResource("Alkun.fxml"),
            "Aloitus", modalityStage, oletus);
}
}


