package Treenipaivakirja;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Henkilötiedot-näytön controlleri 
 * @author salmelsa
 * @author elsal
 * @version 17.2.2022
 *
 */
public class HenkiloGUIController implements ModalControllerInterface<String>{
    
    @FXML private TextField textIka;

    @FXML private TextField textNimi;

    @FXML private TextField textPaino;

    @FXML private TextField textPituus;

    @FXML private TextField textSp;
    
    /**
     * Peruuta-napin toiminto
     */
    @FXML private void handlePeruuta() {
        ModalController.closeStage(textNimi);
    }
    
    /**
     * Tallenna-napin toiminnot
     */
    @FXML private void handleTallenna() {
        tallenna();
        ModalController.closeStage(textNimi);
    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }
  
//====================================================================================

    /**
     * Tallennuksen toiminnallisuus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetaan! Mutta ei toimi vielä.");
    }

}