package treenipk;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
  * 
  * @author santerisalmela
  * @version 23 Feb 2022
  *
  */
public class Muistiinpano {
    
    private int muistiNro;
    private int treeniNro;
    private String muistiinpano;
    
    private static int seuraavaNro  = 1; //viikkonumerot?
    
    private void setTunnusNro(int nr) {
        muistiNro = nr;
        if (muistiNro >= seuraavaNro) seuraavaNro = muistiNro + 1;
    }
    
    /**
     * Selvitää harrastuksen tiedot | erotellusta merkkijonosta.
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusnro.
     * @param rivi josta harrastuksen tiedot otetaan
     * @example
     * <pre name="test">
     *   Muistiinpano muistiinpano = new Muistiinpano();
     *   muistiinpano.parse("   2   | olin Santerin kanssa ");
     *   muistiinpano.toString()    === "2|olin Santerin kanssa";
     *   
     *   muistiinpano.rekisteroi();
     *   int n = muistiinpano.getTunnusNro();
     *   muistiinpano.parse(""+(n+20));
     *   muistiinpano.rekisteroi();
     *   muistiinpano.getTunnusNro() === n+20+1;
     *   muistiinpano.toString()     === "" + (n+20+1) + "|olin Santerin kanssa";
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        treeniNro = Mjonot.erota(sb, '|', getTreeniNro());
        setTunnusNro(Mjonot.erota(sb, '|', getMuistiNro()));
        muistiinpano = Mjonot.erota(sb, '|', muistiinpano);
    }
    
    public int getTreeniNro() {
        return treeniNro;
    }
    
    
   
    
    /**
     * Palauttaa harrastuksen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return muistiinpano tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Muistiinpano muistiinpano = new Muistiinpano();
     *   muistiinpano.parse("   2   |  olin Santerin kanssa ");
     *   muistiinpano.toString()    === "2|olin Santerin kanssa";
     * </pre>
     */
    @Override
    public String toString() {
        return "" + treeniNro + "|" +
                getMuistiNro() + "|" +
                muistiinpano;
    }
    
    
    /**
     * alustaa jäsenen tiedot tyhjiksi
     */
    public Muistiinpano() {
        ///ei edes tarvis oikeesti, koska kaikki valmiiksi alustettuna ylhäällä
    }
    
    /**
     * 
     * @param nro tunnus numero
     */
    public Muistiinpano(int nro) {
        this.treeniNro = nro;
    }
    
    /**
     * 
     * @return muistiinpano
     */
    public String getMuistiinpano() {
        return muistiinpano;
    }
    
    /**
     * tulostetaan henkilön tiedot
     * @param out teietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out){
        out.println(String.format("%01d", treeniNro) + String.format("%01d", muistiNro) + " " + muistiinpano);
        
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
     *  Muistiinpano pitsi1 = new Muistiinpano();
     *  pitsi1.getTunnusNro() === 0;
     *  pitsi1.rekisteroi();
     *  Muistiinpano pitsi2 = new Muistiinpano();
     *  pitsi2.rekisteroi();
     *  int n1 = pitsi1.getTunnusNro();
     *  int n2 = pitsi2.getTunnusNro();
     *  n1 === n2-1;

     * </pre>
     */
    public int rekisteroi() {
        this.muistiNro = seuraavaNro;
        seuraavaNro++;
        return this.muistiNro;
    }
    
    
    /**
     * 
     * @return tunnusnumero
     */
    public int getMuistiNro() {
        return muistiNro;
    }
    
//    /**
//     * 
//     */
//    public void taytaViikkoTiedoilla(){
//        muistiinpano = 100;
//    }
    
    /**
     * 
     * @param nro numero
     */
    public void vastaaMuistiinpano(int nro) {
        muistiNro = nro;
        muistiinpano = "olikivaa";
    }
    
    /**
     * 
     * @param args ei käytös
     */
public static void main (String [] args) {
    Muistiinpano yksi = new Muistiinpano();
    yksi.vastaaMuistiinpano(1);
    yksi.tulosta(System.out);

    }

    public String setMP(String s) {
        muistiinpano = s;
        return null;
    }


}
