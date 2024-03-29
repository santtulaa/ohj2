package treenipk.test;
// Generated by ComTest BEGIN
import java.util.*;
import java.io.File;
import static org.junit.Assert.*;
import org.junit.*;
import treenipk.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.04.04 13:27:12 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class MuistiinpanotTest {



  // Generated by ComTest BEGIN
  /** testAnnaMuistiinpanot47 */
  @Test
  public void testAnnaMuistiinpanot47() {    // Muistiinpanot: 47
    Muistiinpanot muistiinpanoot = new Muistiinpanot(); 
    Muistiinpano pitsi21 = new Muistiinpano(2); muistiinpanoot.lisaa(pitsi21); 
    Muistiinpano pitsi11 = new Muistiinpano(1); muistiinpanoot.lisaa(pitsi11); 
    Muistiinpano pitsi22 = new Muistiinpano(2); muistiinpanoot.lisaa(pitsi22); 
    Muistiinpano pitsi12 = new Muistiinpano(1); muistiinpanoot.lisaa(pitsi12); 
    Muistiinpano pitsi23 = new Muistiinpano(2); muistiinpanoot.lisaa(pitsi23); 
    Muistiinpano pitsi51 = new Muistiinpano(5); muistiinpanoot.lisaa(pitsi51); 
    List<Muistiinpano> loytyneet; 
    loytyneet = muistiinpanoot.annaMuistiinpanot(3); 
    assertEquals("From: Muistiinpanot line: 60", 0, loytyneet.size()); 
    loytyneet = muistiinpanoot.annaMuistiinpanot(1); 
    assertEquals("From: Muistiinpanot line: 62", 2, loytyneet.size()); 
    assertEquals("From: Muistiinpanot line: 63", true, loytyneet.get(0) == pitsi11); 
    assertEquals("From: Muistiinpanot line: 64", true, loytyneet.get(1) == pitsi12); 
    loytyneet = muistiinpanoot.annaMuistiinpanot(5); 
    assertEquals("From: Muistiinpanot line: 66", 1, loytyneet.size()); 
    assertEquals("From: Muistiinpanot line: 67", true, loytyneet.get(0) == pitsi51); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta84 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta84() throws SailoException {    // Muistiinpanot: 84
    Muistiinpanot muistiinpanoot = new Muistiinpanot(); 
    Muistiinpano pitsi21 = new Muistiinpano(); pitsi21.vastaaMuistiinpano(2); 
    Muistiinpano pitsi11 = new Muistiinpano(); pitsi11.vastaaMuistiinpano(1); 
    Muistiinpano pitsi22 = new Muistiinpano(); pitsi22.vastaaMuistiinpano(2); 
    Muistiinpano pitsi12 = new Muistiinpano(); pitsi12.vastaaMuistiinpano(1); 
    Muistiinpano pitsi23 = new Muistiinpano(); pitsi23.vastaaMuistiinpano(2); 
    String tiedNimi = "testimp"; 
    File ftied = new File(tiedNimi+".dat"); 
    ftied.delete(); 
    try {
    muistiinpanoot.lueTiedostosta(tiedNimi); 
    } catch(SailoException _e_){ _e_.getMessage(); }
    muistiinpanoot.lisaa(pitsi21); 
    muistiinpanoot.lisaa(pitsi11); 
    muistiinpanoot.lisaa(pitsi22); 
    muistiinpanoot.lisaa(pitsi12); 
    muistiinpanoot.lisaa(pitsi23); 
    muistiinpanoot.tallenna("testitpk"); 
    muistiinpanoot = new Muistiinpanot(); 
    muistiinpanoot.lueTiedostosta("testitpk"); 
    muistiinpanoot.lisaa(pitsi23); 
    muistiinpanoot.tallenna("testitpk"); 
    assertEquals("From: Muistiinpanot line: 107", false, ftied.delete()); 
    File fbak = new File(tiedNimi+".bak"); 
    assertEquals("From: Muistiinpanot line: 109", false, fbak.delete()); 
  } // Generated by ComTest END
}