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
import treenipk.Treeni;


/**
 * Muokkaa-näytön controlleri
 * @author elsal
 * @author salmelsa
 * @version 17.2.2022
 *
 */
public class muokkaaGUIController implements ModalControllerInterface<Treeni>, Initializable{
    
    @FXML private TextField textHarjoitus;

    @FXML private TextField textKesto;

    @FXML private TextField textPvm;
    @FXML Label labelVirhe;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }
    
    /**
     * Peruuta-napin toiminto
     */
    @FXML private void handlePeruuta() {
        treeniKohdalla = null;
        ModalController.closeStage(textHarjoitus);
    }
    
    /**
     * Tallenna-napin toiminto
     */
    @FXML private void handleTallenna() {
        if (treeniKohdalla != null && treeniKohdalla.getHarjoitus().trim().equals("")) {
            naytaVirhe("Harjoitus ei saa olla tyhjä!");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }

    @Override
    public Treeni getResult() {
        return treeniKohdalla;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Treeni oletus) {
        this.treeniKohdalla = oletus;
        naytaTreeni(edits, treeniKohdalla);
        
    }
//====================================================================================
    
    private Treeni treeniKohdalla;
    private TextField[] edits;
    
    
    /**
     * 
     * @param edits taulukko
     * @param treeni treeni
     */
    public static void naytaTreeni(TextField[] edits, Treeni treeni) {
        if (treeni == null) return;
                edits[0].setText(treeni.getPvm());
                edits[1].setText(treeni.getHarjoitus());
                edits[2].setText(treeni.getKesto());
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
    
    private void alusta() {
        edits = new TextField[]{textPvm, textHarjoitus, textKesto};
        textHarjoitus.setOnKeyReleased(e -> kasitteleMuutosTreeniin(1, textHarjoitus));
        textPvm.setOnKeyReleased(e -> kasitteleMuutosTreeniin1(1, textPvm));
        textKesto.setOnKeyReleased(e -> kasitteleMuutosTreeniin2(1, textKesto));
    }
    
    private void kasitteleMuutosTreeniin(@SuppressWarnings("unused") int k, TextField edit) {
        if (treeniKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        virhe = treeniKohdalla.setHarjoitus(s);
        if (virhe == null) {
            naytaVirhe(virhe);
        } else {
            naytaVirhe(virhe);
        }
    }
    
    private void kasitteleMuutosTreeniin1(@SuppressWarnings("unused") int k, TextField edit) {
        if (treeniKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        virhe = treeniKohdalla.setPvm(s);
        if (virhe == null) {
            naytaVirhe(virhe);
        } else {
            naytaVirhe(virhe);
        }
    }
    
    private void kasitteleMuutosTreeniin2(@SuppressWarnings("unused") int k, TextField edit) {
        if (treeniKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        virhe = treeniKohdalla.setKesto(s);
        if (virhe == null) {
            naytaVirhe(virhe);
        } else {
            naytaVirhe(virhe);
        }
    }


    
    /**
     * kommentti
     * @param modalityStage kommentti
     * @param oletus kommentti
     * @return treeni hommajuttu
     */
    public static Treeni kysyTreeni(Stage modalityStage, Treeni oletus) {
        return ModalController.showModal(muokkaaGUIController.class.getResource("muokkaa.fxml"), "Muokkaus", modalityStage, oletus);
        
    }


}
