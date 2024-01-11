package treenipk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author elsal
 * @version 8.3.2022
 *
 */
public class Muistiinpanot {
    
    private Collection<Muistiinpano> alkiot = new ArrayList<Muistiinpano>();
    private boolean muutettu = false;
    
    /**
     *  Alustaminen
     */
    public Muistiinpanot() {
        //ei tarvitse tehdä mitään
    }
    
    /**
     * 
     * @param v (muistiinpano)
     */
    public void lisaa(Muistiinpano v)  {
        alkiot.add(v);
        muutettu = true;
    }
    
    
    /**
     * Haetaan kaikki jäsen harrastukset
     * @param tunnusnro jäsenen tunnusnumero jolle harrastuksia haetaan
     * @return tietorakenne jossa viiteet löydetteyihin harrastuksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Muistiinpanot muistiinpanoot = new Muistiinpanot();
     *  Muistiinpano pitsi21 = new Muistiinpano(2); muistiinpanoot.lisaa(pitsi21);
     *  Muistiinpano pitsi11 = new Muistiinpano(1); muistiinpanoot.lisaa(pitsi11);
     *  Muistiinpano pitsi22 = new Muistiinpano(2); muistiinpanoot.lisaa(pitsi22);
     *  Muistiinpano pitsi12 = new Muistiinpano(1); muistiinpanoot.lisaa(pitsi12);
     *  Muistiinpano pitsi23 = new Muistiinpano(2); muistiinpanoot.lisaa(pitsi23);
     *  Muistiinpano pitsi51 = new Muistiinpano(5); muistiinpanoot.lisaa(pitsi51);
     *  
     *  List<Muistiinpano> loytyneet;
     *  loytyneet = muistiinpanoot.annaMuistiinpanot(3);
     *  loytyneet.size() === 0; 
     *  loytyneet = muistiinpanoot.annaMuistiinpanot(1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == pitsi11 === true;
     *  loytyneet.get(1) == pitsi12 === true;
     *  loytyneet = muistiinpanoot.annaMuistiinpanot(5);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == pitsi51 === true;
     * </pre> 
     */

    public List<Muistiinpano> annaMuistiinpanot (int tunnusnro) {
        List<Muistiinpano> loydetyt = new ArrayList<Muistiinpano>();
        for (Muistiinpano v : alkiot) 
            if(v.getMuistiNro() == tunnusnro) loydetyt.add(v);
        return loydetyt;
        }
    
    /**
     * Lukee harrastukset tiedostosta.
     * @param hakemisto tiedoston nimen alkuosa
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *  Muistiinpanot muistiinpanoot = new Muistiinpanot();
     *  Muistiinpano pitsi21 = new Muistiinpano(); pitsi21.vastaaMuistiinpano(2);
     *  Muistiinpano pitsi11 = new Muistiinpano(); pitsi11.vastaaMuistiinpano(1);
     *  Muistiinpano pitsi22 = new Muistiinpano(); pitsi22.vastaaMuistiinpano(2); 
     *  Muistiinpano pitsi12 = new Muistiinpano(); pitsi12.vastaaMuistiinpano(1); 
     *  Muistiinpano pitsi23 = new Muistiinpano(); pitsi23.vastaaMuistiinpano(2); 
     *  String tiedNimi = "tpk";
     *  File ftied = new File(tiedNimi+".dat");
     *  ftied.delete();
     *  muistiinpanoot.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  muistiinpanoot.lisaa(pitsi21);
     *  muistiinpanoot.lisaa(pitsi11);
     *  muistiinpanoot.lisaa(pitsi22);
     *  muistiinpanoot.lisaa(pitsi12);
     *  muistiinpanoot.lisaa(pitsi23);
     *  muistiinpanoot.tallenna("tpk");
     *  muistiinpanoot = new Muistiinpanot();
     *  muistiinpanoot.lueTiedostosta(tiedNimi);
     *  muistiinpanoot.lisaa(pitsi23);
     *  muistiinpanoot.tallenna("tpk");
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     * </pre>
     */

    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/muistiinpanot.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext() ) {
                String s = fi.nextLine();
                if (s == null || "".equals(s) || s.charAt(0)==';') continue;
                Muistiinpano muistiinpano = new Muistiinpano();
                muistiinpano.parse(s); //kertoisi onnistumista?
                lisaa(muistiinpano);
            }
            
            muutettu = false;
            
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        }
    }
        

    /**
     * Tallentaa treenit tiedostoon
     * @param hakemisto tallennettavan tiedoston hakemisto
     * @throws SailoException on
     */
    public void tallenna(String hakemisto) throws SailoException {
        if(!muutettu)return;
        File ftied = new File(hakemisto + "/muistiinpanot.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, true))) {
        
            for (var v: alkiot) {
                fo.println(v.toString()); 
            }
    } catch (FileNotFoundException ex) {
        throw new SailoException("Tiedosto " + ftied.getAbsolutePath());
    }
        
        muutettu = false;
    }

/**
 * 
 * @param args ei käytössä
 */
    public static void main (String [] args) {
        Muistiinpanot muistiinpanot = new Muistiinpanot();
        
        try {
            muistiinpanot.lueTiedostosta("muistiinpanot");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        
        Muistiinpano yksi = new Muistiinpano();
        yksi.vastaaMuistiinpano(1);
        Muistiinpano kaksi = new Muistiinpano();
        kaksi.vastaaMuistiinpano(2);
        

        muistiinpanot.lisaa(yksi);
        muistiinpanot.lisaa(kaksi);
        
        
        System.out.println("==== Treenit testi====");
            var viikot2 = muistiinpanot.annaMuistiinpanot(1);
            
            for (Muistiinpano v : viikot2) {
                System.out.print(v.getMuistiNro() + " ");
                v.tulosta(System.out);
                
            }
            
            try {
                muistiinpanot.tallenna("tpk");
                } catch (SailoException e) {
                    System.err.println(e.getMessage());
                }
    }
}
