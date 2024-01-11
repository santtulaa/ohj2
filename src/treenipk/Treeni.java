package treenipk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import fi.jyu.mit.ohj2.Mjonot;

/**
  * 
  * @author santerisalmela
  * @version 23 Feb 2022
  *
  */
public class Treeni {
    
    private int tunnusNro;
    private String harjoitus        = "";
    private String kesto            = "";
    private String pvm              = ""; //vai int?
    
    private static int seuraavaNro  = 1; //viikkonumerot?
    
    
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    
    /**
     * alustaa jäsenen tiedot tyhjiksi
     */
    public Treeni() {
        ///ei edes tarvis oikeesti, koska kaikki valmiiksi alustettuna ylhäällä
    }
    

    /**
     * @return jäsenen nimi
     * @example
     * <pre name="test">
     *   Treeni hiihto = new Treeni();
     *   hiihto.taytahiihtoTreeniTiedoilla();
     *   hiihto.getHarjoitus() =R= "hiihto .*";
     * </pre>
     */

    public String getHarjoitus() {
        return harjoitus;
    }
    
    public String getKesto() {
        return kesto;
    }
    
    public String getPvm() {
        return pvm;
    }
    
    public int getKenttia() {
        return 3;
    }
    
    public int ekaKentta() {
        return 1;
    }
    
    public String getKysymys(int k) {
        switch (k) {
        case 0: return "Tunnus nro";
        case 1: return "pvm";
        case 2: return "harjoitus";
        case 3: return "kesto";
        default: return "Ääliö";
        }
    }
    

    /**
     * Antaa k:n kentän sisällön merkkijonona
     * @param k monenenko kentän sisältö palautetaan
     * @return kentän sisältö merkkijonona
     */
    public String anna(int k) {
        switch ( k ) {
        case 0: return "" + tunnusNro;
        case 1: return "" + pvm;
        case 2: return "" + harjoitus;
        case 3: return "" + kesto;
        default: return "Äääliö";
        }
    }

    /**
     * Asettaa k:n kentän arvoksi parametrina tuodun merkkijonon arvon
     * @param k kuinka monennen kentän arvo asetetaan
     * @param jono jonoa joka asetetaan kentän arvoksi
     * @return null jos asettaminen onnistuu, muuten vastaava virheilmoitus.
     */
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        StringBuffer sb = new StringBuffer(tjono);
        switch ( k ) {
        case 0:
            setTunnusNro(Mjonot.erota(sb, '§', getTunnusNro()));
            return null;
        case 1:
            pvm = tjono;
            return null;
        case 2:
            harjoitus = tjono;
            return null;
        case 3:
            kesto = tjono;
            return null;
        default:
            return "ÄÄliö";
        }
    }

    
    /**
     * 
     * @param s joo
     * @return joo
     */
    public String setHarjoitus(String s) {
        harjoitus = s;
        return null;
    }
    
    /**
     * 
     * @param s joo
     * @return joo
     */
    public String setPvm(String s) {
        pvm = s;
        return null;
    }
    
    /**
     * 
     * @param s joo
     * @return joo
     */
    public String setKesto(String s) {
        kesto = s;
        return null;
    }
    
    
    
    /**
     * tulostetaan henkilön tiedot
     * @param out teietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out){
        out.println(String.format("%03d", tunnusNro) + ", Harjoitus: " + harjoitus +
               ", Kesto: " + kesto + "min, Päivämäärä: " + pvm);
        
    }
    
    
    /**
     * 
     * @param os pystyy monipuolisempiin tulostuksiin
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * 
     * @return "antaa seuraavalle jäsenelle seuraavan rekisterinumero? ehkä viikkoihin vois käyttää?
     * @example
     * <pre name="test">
     *  Treeni hiihto1 = new Treeni();
     *  hiihto1.getTunnusNro() === 0;
     *  hiihto1.rekisteroi();
     *  Treeni hiihto2 = new Treeni();
     *  hiihto2.rekisteroi();
     *  int n1 = hiihto1.getTunnusNro();
     *  int n2 = hiihto2.getTunnusNro();
     *  n1 === n2-1;

     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    /**
     * 
     * @return palauttaa viikkonumeron kai
     */
    public int getTunnusNro(){
        return tunnusNro;
    }
    
    /**
     * 
     */
    public void taytahiihtoTreeniTiedoilla(){
        harjoitus = "hiihto";
        kesto = "120";
        pvm="21.12.2021";
    }
    

    
    /**
     * Selvitää jäsenen tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta jäsenen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Treeni treeni = new Treeni();
     *   treeni.parse("   3  |  Hiihto   | 100  | 21.12.2022");
     *   treeni.getTunnusNro() === 3;
     *   treeni.toString().startsWith("3|Hiihto|100|21.12.2022|") === true; // on enemmäkin kuin 3 kenttää, siksi loppu |
     *
     *   treeni.rekisteroi();
     *   int n = treeni.getTunnusNro();
     *   treeni.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   treeni.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   treeni.getTunnusNro() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        harjoitus = Mjonot.erota(sb, '|', harjoitus);
        kesto = Mjonot.erota(sb, '|', kesto);
        pvm = Mjonot.erota(sb, '|', pvm);
    }
    
    @Override
    public boolean equals(Object treeni) {
        if ( treeni == null ) return false;
        return this.toString().equals(treeni.toString());
    }


    @Override
    public int hashCode() {
        return tunnusNro;
    }

    
    
    /**
     * Palauttaa jäsenen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return jäsen tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Treeni treeni = new Treeni();
     *   treeni.parse("   3  |  Hiihto   | 100  | 21.12.2022");
     *   treeni.toString().startsWith("3|Hiihto|100|21.12.2022|") === true; // on enemmäkin kuin 3 kenttää, siksi loppu |
     * </pre>  
     */
    @Override
    public String toString() {
        return "" +
                getTunnusNro() + "|" +
                harjoitus + "|" +
                kesto + "|" +
                pvm;
    }
    
    /**
     * 
     * @param args ei käytös
     */
public static void main (String [] args) {
    Treeni hiihto = new Treeni(), lenkki = new Treeni();

    
    hiihto.rekisteroi();
    lenkki.rekisteroi();
    hiihto.tulosta(System.out);
    hiihto.taytahiihtoTreeniTiedoilla(); //vastaaAkuAnkka
    hiihto.tulosta(System.out);
    lenkki.taytahiihtoTreeniTiedoilla(); 
    lenkki.tulosta(System.out);

    }




}
