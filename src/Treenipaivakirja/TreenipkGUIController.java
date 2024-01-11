package Treenipaivakirja;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import Treenipaivakirja.TreenipkGUIController;
import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import treenipk.SailoException;
import treenipk.Treeni;
import treenipk.Treenipaivakirja;
import treenipk.Muistiinpano;

/**
 * Päänäytön controlleri
 * @author salmelsa
 * @author elsal
 * @version 17.2.2022
 *
 */
public class TreenipkGUIController implements Initializable {
    
    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private ListChooser<Treeni> chooserTreenit;
    @FXML private ScrollPane panelTreeni;  
    @FXML TextField textHarjoitus;
    @FXML TextField textKesto;
    @FXML TextField textPvm;
    @FXML StringGrid<Muistiinpano> tableTiedot;
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    @FXML private void handleHakuehto() {
        hae(0); 
    }

    @FXML void handleAvaa() {
        avaaPaaikkuna();
    }
    
    @FXML void handleAvaaAlkuikkuna() {
        avaaAlkuikkuna();
    }
    
    @FXML private void handlePoistaTreeni() {
        poistaTreeni();
    }
    
    @FXML private void handlePoistaMp() {
        //poistaMp();
    }
    
    /**
     * Kun painetaan listan päivämäärästä
     */
    @FXML
    void handleMuokkaa() {
        muokkaa();
    }
    
    /**
     * Tallenna-napin toiminto
     */
    @FXML void handleTallenna() {
        tallenna();
    }
     
    /**
     * Apua-napin toiminto
     */
    @FXML void handleApua() {
        avustus();
    }
    
    /**
     * Tietoja-napin toiminto
     */
    @FXML void handleTietoja() {
        avaaAlkuikkuna();
    }
      
    /**
     * Lopeta-napin toiminto
     */
    @FXML void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    /**
     * Lisää uusi treeni-napin toiminto
     */
    @FXML void handleUusitreeni() {
    uusiTreeni();
        // ModalController.showModal(lisaatreeniGUIController.class.getResource("lisaatreeni.fxml"), "Treeni", null, "");
    }
    
    
    /**
     * lisää päivämäärä
     */
    @FXML
    void handlePVM() {
        uusiMuistiinpano();
    }
    
    
    /**
     * Henkilötiedot-napin toiminto
     */
    @FXML void handleHenkilo() {
        ModalController.showModal(HenkiloGUIController.class.getResource("henkilo.fxml"), "henkilo", null, "");
    }

//========================================================================================
    
    private String treeninnimi = "tpk";
    private Treenipaivakirja treenipaivakirja; //pitäs olal viissii tpkirja koska kerho
    private Treeni treeniKohdalla;
    private TextArea areaTreeni = new TextArea();
    private TextField[] edits;
    private static Treeni aputreeni = new Treeni();
    /**
     * Avaa alkuikkunan
     */
    private void avaaAlkuikkuna() {
        ModalController.showModal(TreenipkGUIController.class.getResource(
                "Alkun.fxml"), "Aloitus", null, "");
    }
    

    /**
     * Alustaa kerhon lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    protected String lueTiedosto(String nimi) {
        treeninnimi = nimi;
        //setTitle("Kerho - " + treeninnimi);
        try {
            treenipaivakirja.lueTiedostosta(nimi);
            hae(0);
            return null;
        } catch (SailoException e) {
            hae(0);
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
     }

    
    private void alusta() {
        panelTreeni.setContent(areaTreeni);
        areaTreeni.setFont(new Font ("Times New Roman", 12));
        panelTreeni.setFitToHeight(true);
        chooserTreenit.clear();
        chooserTreenit.addSelectionListener(e -> naytaTreeni());
        chooserTreenit.addSelectionListener(e -> naytaMuistiinpanot());
//        cbKentat.clear();
//        for(int k = aputreeni.ekaKentta(); k < aputreeni.getKenttia(); k++) {
//            cbKentat.add(aputreeni.getKysymys(k));
//        }
//        cbKentat.setSelectedIndex(0);
        TextField[] edts = {textPvm, textHarjoitus, textKesto};
        edits = edts;
    }


    /**
     * Avaa pääikkunan
     */
    private void avaaPaaikkuna() {
        ModalController.showModal(TreenipkGUIController.class.getResource(
                "TreenipkGUIView.fxml"), "Treeni", null, "");
    }


    /**
     * @return palauttaa false
     * 
     */
    public boolean avaa() {
        String tpk = "tpk";
        ModalController.showModal (TreenipkGUIController.class.getResource(
                "Alkun.fxml"), "Aloitus", null, "");
        lueTiedosto(tpk);
        return true;
    }


    /**
     * Tallennuksen toiminnallisuus
     */
    private String tallenna() {
        try {
            treenipaivakirja.tallenna();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
    }



    /**
     * Näytetään ohjelman suunnitelma erillisessä selaimessa
     */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI(
                    "https://tim.jyu.fi/view/kurssit/tie/ohj2/2022k/ht/elsgronb");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }

    }
    
    
    /**
     * treeni
     */
    private void naytaTreeni() {
        treeniKohdalla = chooserTreenit.getSelectedObject();
        
        if (treeniKohdalla == null) return;
        muokkaaGUIController.naytaTreeni(edits, treeniKohdalla);
        

    }
    
    private void naytaMuistiinpanot() {
        treeniKohdalla = chooserTreenit.getSelectedObject();
        
        if (treeniKohdalla == null) return;
        
        areaTreeni.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaTreeni)) {
            List<Muistiinpano> muistiinpanot = treenipaivakirja.annaMuistiinpanot(treeniKohdalla);
            for (Muistiinpano v : muistiinpanot) 
                v.tulosta(os);
        }
        
    }
    
    
//    private void naytaTiedot(Treeni treeni) {
//        tableTiedot.clear();
//        if (treeni == null) return;
//        List<Muistiinpano> muistiinpanot = treenipaivakirja.annaMuistiinpanot(treeni);
//        if (muistiinpanot.size() == 0) return;
//        for (Muistiinpano m : muistiinpanot) {
//            naytaTieto(m);
//            
//        }
//    }
//    
//    private void naytaTieto(Muistiinpano mu) {
//        String[] rivi = mu.toString().split("\\|");
//        tableTiedot.add(mu, rivi[2]);
//    }
    
    
    private void muokkaa() {
        Treeni treeniKohdalla = chooserTreenit.getSelectedObject();
        if (treeniKohdalla == null) return; 
        muokkaaGUIController.kysyTreeni(null, treeniKohdalla);
    }
    
    /**
     * asetetaan käytettävä treeni
     * @param treenipaivakirja jota käytetään
     */
    public void setTreeni(Treenipaivakirja treenipaivakirja) { 
        this.treenipaivakirja = treenipaivakirja;
    }

    /**
     * 
     * @return palauttaa true 
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    /**
     * Hakee jäsenten tiedot listaan
     * @param jnro jäsenen numero, joka aktivoidaan haun jälkeen  
     */
    protected void hae(int jnro) {
        chooserTreenit.clear();
        String ehto = hakuehto.getText();
        int index = 0;
        for (int i = 0; i < treenipaivakirja.getTreenit(); i++) {
            Treeni treeni = treenipaivakirja.annaTreeni(i);
            if (!treeni.getPvm().contains(ehto)) continue;
            if (treeni.getTunnusNro() == jnro) index = i;
            chooserTreenit.add(""+treeni.getPvm(), treeni);
        }
        chooserTreenit.setSelectedIndex(index);
    }
    
    /*
     * ju
     */
    private void uusiTreeni() {
        try {
        Treeni uusi = new Treeni();
        uusi = lisaatreeniGUIController.kysyTreeni(null, uusi, 1);
        if (uusi == null) return;
        uusi.rekisteroi();
        treenipaivakirja.lisaa(uusi);
        hae(uusi.getTunnusNro());
        } catch (SailoException e) {
        Dialogs.showMessageDialog("Ongelmia uuden luomisessa" + e.getMessage());
        return;
        }

    }
    
    /*
     * Poistetaan listalta valittu jäsen
     */
    private void poistaTreeni() {
        Treeni treeni = treeniKohdalla;
        if ( treeni == null ) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko treeni: " + treeni.getPvm(), "Kyllä", "Ei") )
            return;
        treenipaivakirja.poista(treeni);
        int index = chooserTreenit.getSelectedIndex();
        hae(0);
        chooserTreenit.setSelectedIndex(index);
    }
    
    
    /**
     * Tekee uuden tyhjän viikon
     */
    public void uusiMuistiinpano() {
        if (treeniKohdalla == null) return;
        //try {
        Muistiinpano uusi = new Muistiinpano(treeniKohdalla.getTunnusNro());
        uusi = MuistiinpanotGUIController.kysyMP(null, uusi, 1);
        if (uusi == null) return;
        uusi.rekisteroi();
        treenipaivakirja.lisaa(uusi);
        hae(0);
        //naytaMuistiinpanot(treeniKohdalla);
//        } catch (SailoException e) {
//        Dialogs.showMessageDialog("Ongelmia uuden luomisessa" + e.getMessage());
//        }
//        
        
        
//        ModalController.showModal(MuistiinpanotGUIController.class.getResource("muistiinpanot.fxml"), "Yhteenveto", null, "");
////        treeniKohdalla = chooserTreenit.getSelectedObject();
////        if (treeniKohdalla == null) return;
////        Muistiinpano v = new Muistiinpano();
////        v.rekisteroi();
////        v.vastaaMuistiinpano(treeniKohdalla.getTunnusNro());
//////        try {
//////            treenipaivakirja.lisaa(v);
//////        } catch (SailoException e) {
//////            Dialogs.showMessageDialog("Ongelmia lisäämisessä! " + e.getMessage());
//////        } 
////        treenipaivakirja.lisaa(v);
////        hae(treeniKohdalla.getTunnusNro());
    }
}
