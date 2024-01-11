package treenipk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.WildChars;

/**
 * 
 * @author santerisalmela
 * @version 23 Feb 2022
 *
 */
public class Treenit {
    
    private static final int MAX_TREENEJA = 5;
    private boolean muutettu = false;
    private int lkm = 0;
    private Treeni[] alkiot = new Treeni[MAX_TREENEJA];
    
    /**
     *  luodaan alustava taulukko
     */
    public Treenit() {
        
    }
    

    /**
     * Lisää uuden jäsenen tietorakenteeseen.  Ottaa jäsenen omistukseensa.
     * @param treeni lisätäävän jäsenen viite.  Huom tietorakenne muuttuu omistajaksi
     * @example
     * <pre name="test">
     * Treenit treenit = new Treenit();
     * Treeni hiihto1 = new Treeni(), hiihto2 = new Treeni();
     * treenit.getLkm() === 0;
     * treenit.lisaa(hiihto1); treenit.getLkm() === 1;
     * treenit.lisaa(hiihto2); treenit.getLkm() === 2;
     * treenit.lisaa(hiihto1); treenit.getLkm() === 3;
     * treenit.anna(0) === hiihto1;
     * treenit.anna(1) === hiihto2;
     * treenit.anna(2) === hiihto1;
     * treenit.anna(1) == hiihto1 === false;
     * treenit.anna(1) == hiihto2 === true;
     * treenit.lisaa(hiihto1); treenit.getLkm() === 4;
     * treenit.lisaa(hiihto1); treenit.getLkm() === 5;
     * </pre>
     */
    public void lisaa(Treeni treeni ) {
        if(lkm >=alkiot.length) {
            alkiot = Arrays.copyOf(alkiot,alkiot.length+10);
        }
        alkiot[lkm] = treeni;   
        lkm++;
        muutettu = true;
    }
    
    /**
     * 
     * @return lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    /** 
     * Poistaa jäsenen jolla on valittu tunnusnumero  
     * @param id poistettavan jäsenen tunnusnumero 
     * @return 1 jos poistettiin, 0 jos ei löydy 
     *  
     */ 
    public int poista(int id) { 
        int ind = etsiId(id); 
        if (ind < 0) return 0; 
        lkm--; 
        for (int i = ind; i < lkm; i++) 
            alkiot[i] = alkiot[i + 1]; 
        alkiot[lkm] = null; 
        muutettu = true; 
        return 1; 
    }
    
    /** 
     * Etsii jäsenen id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return jäsen jolla etsittävä id tai null 
     */ 
    public Treeni annaId(int id) { 
        for (Treeni treeni : alkiot) { 
            if (id == treeni.getTunnusNro()) return treeni; 
        } 
        return null; 
    } 


    /** 
     * Etsii jäsenen id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return löytyneen jäsenen indeksi tai -1 jos ei löydy 
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getTunnusNro()) return i; 
        return -1; 
    }
    
    
    /**
     * Tallentaa jäsenistön tiedostoon.  
     * @param hakemisto hakemisto
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna(String hakemisto) throws SailoException {
        if (!muutettu) return;
        File ftied = new File(hakemisto + "/treenit.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, true))) {
        
            for (int i = 0; i < getLkm(); i++) {
                Treeni treeni = this.anna(i);
                fo.println(treeni.toString());
                
            }
    } catch (FileNotFoundException ex) {
        throw new SailoException("Tiedosto " + ftied.getAbsolutePath());
    }
        muutettu = false;
    }
    
    /**
     * Palauttaa viitteen i:teen jäseneen.
     * @param i monennenko jäsenen viite halutaan
     * @return viite jäseneen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Treeni anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    

    /**
     * Lukee jäsenistön tiedostosta. 
     * @param hakemisto tiedoston perusnimi
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Treenit treenit = new Treenit();
     *  Treeni hiihto1 = new Treeni(), hiihto2 = new Treeni();
     *  hiihto1.taytahiihtoTreeniTiedoilla();
     *  hiihto2.taytahiihtoTreeniTiedoilla();
     *  String hakemisto = "tpk";
     *  String tiedNimi = hakemisto+"/treenit";
     *  File ftied = new File(tiedNimi+".dat");
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  ftied.delete();
     *  treenit.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  treenit.lisaa(hiihto1);
     *  treenit.lisaa(hiihto2);
     *  treenit.tallenna();
     *  treenit = new Treenit();            // Poistetaan vanhat luomalla uusi
     *  treenit.lueTiedostosta(tiedNimi);  // johon ladataan tiedot tiedostosta.
     *  treenit.lisaa(hiihto2);
     *  treenit.tallenna();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/treenit.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext() ) {
                String s = fi.nextLine();
                if (s == null || "".equals(s) || s.charAt(0)==';') continue;
                Treeni treeni = new Treeni();
                treeni.parse(s); //kertoisi onnistumista?
                lisaa(treeni);
            }
            muutettu = false;
            
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        }
    }
    


/**
 * 
 * @param args ei käytössä
 */
    public static void main (String [] args) {
        Treenit treenit = new Treenit();
        
        try {
            treenit.lueTiedostosta("treenit");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        
        Treeni hiihto = new Treeni();
        Treeni lenkki = new Treeni();

        
        hiihto.rekisteroi();
        lenkki.rekisteroi();
        
        hiihto.taytahiihtoTreeniTiedoilla(); //taytahiihtoTreeniTiedoilla
        lenkki.taytahiihtoTreeniTiedoilla(); 
        
        try {
            treenit.lisaa(hiihto);
            treenit.lisaa(lenkki);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        } // ei käytetä missää 
        
        
        System.out.println("==== Treenit testi====");
        
        for (int i = 0; i < treenit.getLkm(); i++) {
            Treeni treeni = treenit.anna(i);
            System.out.println("Treenin indeksi: " + i);
            treeni.tulosta(System.out);
        }
        
        try {
        treenit.tallenna("tpk");
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
    }
}
