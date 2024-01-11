package treenipk;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * crs sisällöt pitäs kirjottaa viel tähän ylös
 * ei tehty harrastuksia viel tälläkertaa, riittää 5.1. kertaa
 * @author santerisalmela
 * @version 25 Feb 2022
 *
 */
public class Treenipaivakirja {
    private Treenit treenit = new Treenit();
    private Muistiinpanot muistiinpanot = new Muistiinpanot();
    
    private String hakemisto = "treenit";
    
    /**
     * Lisää treenipaivakirjaon uuden jäsenen
     * @param treeni lisättävä jäsen
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Treenipaivakirja treenipaivakirja = new Treenipaivakirja();
     * Treeni aku1 = new Treeni(), aku2 = new Treeni();
     * treenipaivakirja.lisaa(aku1); 
     * treenipaivakirja.lisaa(aku2); 
     * treenipaivakirja.lisaa(aku1);
     * Collection<Treeni> loytyneet = treenipaivakirja.etsi("",-1); 
     * </pre>
     */
    public void lisaa(Treeni treeni)  throws SailoException{
        treenit.lisaa(treeni); 
    }
    
    
    /**
     * Haetaan kaikki jäsen harrastukset
     * @param treeni jäsen jolle harrastuksia haetaan
     * @return tietorakenne jossa viiteet löydetteyihin harrastuksiin
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import java.util.*;
     * 
     *  Treenipaivakirja treenipaivakirja = new Treenipaivakirja();
     *  Treeni aku1 = new Treeni(), aku2 = new Treeni(), aku3 = new Treeni();
     *  aku1.rekisteroi(); aku2.rekisteroi(); aku3.rekisteroi();
     *  int id1 = aku1.getTunnusNro();
     *  int id2 = aku2.getTunnusNro();
     *  Muistiinpano pitsi11 = new Muistiinpano(id1); treenipaivakirja.lisaa(pitsi11);
     *  Muistiinpano pitsi12 = new Muistiinpano(id1); treenipaivakirja.lisaa(pitsi12);
     *  Muistiinpano pitsi21 = new Muistiinpano(id2); treenipaivakirja.lisaa(pitsi21);
     *  Muistiinpano pitsi22 = new Muistiinpano(id2); treenipaivakirja.lisaa(pitsi22);
     *  Muistiinpano pitsi23 = new Muistiinpano(id2); treenipaivakirja.lisaa(pitsi23);
     *  
     *  List<Muistiinpano> loytyneet;
     *  loytyneet = treenipaivakirja.annaMuistiinpanot(aku3);
     *  loytyneet.size() === 0; 
     *  loytyneet = treenipaivakirja.annaMuistiinpanot(aku1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == pitsi11 === true;
     *  loytyneet.get(1) == pitsi12 === true;
     *  loytyneet = treenipaivakirja.annaMuistiinpanot(aku2);
     *  loytyneet.size() === 3; 
     *  loytyneet.get(0) == pitsi21 === true;
     * </pre> 
     */

    public List<Muistiinpano> annaMuistiinpanot(Treeni treeni) {
        return muistiinpanot.annaMuistiinpanot(treeni.getTunnusNro());
    }
    
    /**
     * Lisätään uusi muistiinpano
     * @param v lisättävä muistiinpano
     */
    public void lisaa (Muistiinpano v) {
        muistiinpanot.lisaa(v);
    }
    
    /**
     * 
     * @return emt
     */
    public int getTreenit() {
        return treenit.getLkm(); //vois myös olla this.treenit.getlkm
    }
    
    /**
     * 
     * @param i emt
     * @return treenien lkm
     */
    public Treeni annaTreeni(int i) {
        return treenit.anna(i);
    }
    
    
    /**
     * Lukee treenipaivakirjan tiedot tiedostosta
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.*;
     * #import java.util.*;
     * 
     *  Treenipaivakirja treenipaivakirja = new Treenipaivakirja();
     *  
     *  Treeni aku1 = new Treeni(); aku1.vastaaAkuAnkka(); aku1.rekisteroi();
     *  Treeni aku2 = new Treeni(); aku2.vastaaAkuAnkka(); aku2.rekisteroi();
     *  Muistiinpano pitsi21 = new Muistiinpano(); pitsi21.taytahiihtoTreeniTiedoilla(aku2.getTunnusNro());
     *  Muistiinpano pitsi11 = new Muistiinpano(); pitsi11.taytahiihtoTreeniTiedoilla(aku1.getTunnusNro());
     *  Muistiinpano pitsi22 = new Muistiinpano(); pitsi22.taytahiihtoTreeniTiedoilla(aku2.getTunnusNro()); 
     *  Muistiinpano pitsi12 = new Muistiinpano(); pitsi12.taytahiihtoTreeniTiedoilla(aku1.getTunnusNro()); 
     *  Muistiinpano pitsi23 = new Muistiinpano(); pitsi23.taytahiihtoTreeniTiedoilla(aku2.getTunnusNro());
     *   
     *  String hakemisto = "testikelmit";
     *  File dir = new File(hakemisto);
     *  File ftied  = new File(hakemisto+"/nimet.dat");
     *  File fhtied = new File(hakemisto+"/harrastukset.dat");
     *  dir.mkdir();  
     *  ftied.delete();
     *  fhtied.delete();
     *  treenipaivakirja.lueTiedostosta(hakemisto); #THROWS SailoException
     *  treenipaivakirja.lisaa(aku1);
     *  treenipaivakirja.lisaa(aku2);
     *  treenipaivakirja.lisaa(pitsi21);
     *  treenipaivakirja.lisaa(pitsi11);
     *  treenipaivakirja.lisaa(pitsi22);
     *  treenipaivakirja.lisaa(pitsi12);
     *  treenipaivakirja.lisaa(pitsi23);
     *  treenipaivakirja.tallenna();
     *  treenipaivakirja = new Treenipaivakirja();
     *  treenipaivakirja.lueTiedostosta(hakemisto);
     *  Collection<Treeni> kaikki = treenipaivakirja.etsi("",-1); 
     *  List<Muistiinpano> loytyneet = treenipaivakirja.annaMuistiinpanot(aku1);
     *  treenipaivakirja.lisaa(aku2);
     *  treenipaivakirja.lisaa(pitsi23);
     *  treenipaivakirja.tallenna();
     *  ftied.delete()  === true;
     *  fhtied.delete() === true;
     *  File fbak = new File(hakemisto+"/nimet.bak");
     *  File fhbak = new File(hakemisto+"/harrastukset.bak");
     *  fbak.delete() === true;
     *  fhbak.delete() === true;
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        File dir = new File(nimi);
        dir.mkdir();
        treenit = new Treenit();
        muistiinpanot = new Muistiinpanot();
        
        hakemisto = nimi;
        treenit.lueTiedostosta(nimi);
        muistiinpanot.lueTiedostosta(nimi);
    }
    
    /**
     * @throws SailoException jos tallentamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            treenit.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        
        try {
            muistiinpanot.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe += ex.getMessage();
        }
        if(!"".equals(virhe)) throw new SailoException(virhe);
    }

    /**
     * Poistaa jäsenistöstä ja harrasteista jäsenen tiedot 
     * @param treeni lol
     * @return montako jäsentä poistettiin
     * @example
     * <pre name="test">
     * #THROWS Exception
     *   alustaKerho();
     *   kerho.etsi("*",0).size() === 2;
     *   kerho.annaHarrastukset(aku1).size() === 2;
     *   kerho.poista(aku1) === 1;
     *   kerho.etsi("*",0).size() === 1;
     *   kerho.annaHarrastukset(aku1).size() === 0;
     *   kerho.annaHarrastukset(aku2).size() === 3;
     * </pre>
     */
    public int poista(Treeni treeni) {
        if ( treeni == null ) return 0;
        int ret = treenit.poista(treeni.getTunnusNro()); 
        return ret; 
    }
    
    
/**
 * 
 * @param args ei käytössä
 */
    public static void main(String [] args) {
       Treenipaivakirja  treenipaivakirja = new Treenipaivakirja(); //treenipvkirja
       
       try {
           treenipaivakirja.lueTiedostosta("koetreenipk");
       } catch (SailoException ex) {
           System.err.println(ex.getMessage());
       }
       
       Treeni hiihto = new Treeni();
       hiihto.rekisteroi();
       hiihto.taytahiihtoTreeniTiedoilla(); //vastaaAkuAnkka
       Treeni lenkki = new Treeni();
       lenkki.rekisteroi();
       lenkki.taytahiihtoTreeniTiedoilla(); 
       
       try {
           treenipaivakirja.lisaa(hiihto);
           treenipaivakirja.lisaa(lenkki);
           
           for (int i = 0; i < treenipaivakirja.getTreenit(); i++) {
               Treeni treeni = treenipaivakirja.annaTreeni(i);
               treeni.tulosta(System.out);;
           }
              
              treenipaivakirja.tallenna();
    } catch (SailoException e) {
        System.err.println(e.getMessage()); //tulostaa liikaa alkoita ilma ylimääräistä tekstiä
    }

      
 
    }
}
