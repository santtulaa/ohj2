package Treenipaivakirja;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import treenipk.Muistiinpano;
import treenipk.Treeni;


/**
 * Yhteenveto (viikot.fxml) controlleri
 * @author elsal
 * @author salmelsa
 * @version 21.2.2022
 *
 */
public class MuistiinpanotGUIController implements ModalControllerInterface<Muistiinpano>, Initializable {
    
    @FXML private TextField textMp;
    @FXML Label labelVirhe;

    
    /**
     * Peruuta-napin toiminto
     */
    @FXML private void handlePeruuta() {
        mpKohdalla = null;
        ModalController.closeStage(textMp);
    }
    
    /**
     * Tallenna-napin toiminto
     */
    @FXML private void handleTallenna() {
        if (mpKohdalla != null && mpKohdalla.getMuistiinpano().trim().equals("")) {
            naytaVirhe("Muistiinpano ei saa olla tyhjä!");
            return;
        }
        ModalController.closeStage(labelVirhe);
        
    }
    
   // ============================================================================================
    
    private int kentta = 0;
    private Muistiinpano mpKohdalla;
    private TextField[] edits;
    
    @Override
    public Muistiinpano getResult() {
        return mpKohdalla;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Muistiinpano oletus) {
        this.mpKohdalla = oletus;
        naytaMp(edits, mpKohdalla);
        
    }
    
    /**
     * 
     * @param edits taulukko
     * @param mp treeni
     */
    public static void naytaMp(TextField[] edits, Muistiinpano mp) {
        if (mp == null) return;
                edits[0].setText(mp.getMuistiinpano());
    }
    
    
    private void alusta() {
        edits = new TextField[]{textMp};
        textMp.setOnKeyReleased(e -> kasitteleMuutosMuistiinpanoon(1, textMp));
    }
    
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    private void kasitteleMuutosMuistiinpanoon(@SuppressWarnings("unused") int k, TextField edit) {
        if (mpKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        virhe = mpKohdalla.setMP(s);
        if (virhe == null) {
            naytaVirhe(virhe);
        } else {
            naytaVirhe(virhe);
        }
    }
    
    private void setKentta(int kentta) {
        this.kentta = kentta;
    }
    
    /**
     * Luodaan tietueen kysymisdialogi ja palautetaan sama tietue muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä dataan näytetään oletuksena
     * @param kentta ok
     * @return null jos painetaan Cancel, muuten täytetty tietue
     */
    public static Muistiinpano kysyMP(Stage modalityStage, Muistiinpano oletus, int kentta) {
        return ModalController./*<Muistiinpano, MuistiinpanotGUIController>*/showModal(
                MuistiinpanotGUIController.class.getResource("muistiinpanot.fxml"),
                "Treenipaivakirja",
                modalityStage, oletus,
                ctrl -> ((MuistiinpanotGUIController) ctrl).setKentta(kentta) 
                );
    }
    


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }


}
