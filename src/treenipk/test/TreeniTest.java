package treenipk.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import treenipk.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.04.04 13:47:42 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TreeniTest {


  // Generated by ComTest BEGIN
  /** testGetHarjoitus45 */
  @Test
  public void testGetHarjoitus45() {    // Treeni: 45
    Treeni hiihto = new Treeni(); 
    hiihto.taytahiihtoTreeniTiedoilla(); 
    { String _l_=hiihto.getHarjoitus(),_r_="hiihto"; if ( !_l_.matches(_r_) ) fail("From: Treeni line: 48" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi79 */
  @Test
  public void testRekisteroi79() {    // Treeni: 79
    Treeni hiihto1 = new Treeni(); 
    assertEquals("From: Treeni line: 81", 0, hiihto1.getTunnusNro()); 
    hiihto1.rekisteroi(); 
    Treeni hiihto2 = new Treeni(); 
    hiihto2.rekisteroi(); 
    int n1 = hiihto1.getTunnusNro(); 
    int n2 = hiihto2.getTunnusNro(); 
    assertEquals("From: Treeni line: 87", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse122 */
  @Test
  public void testParse122() {    // Treeni: 122
    Treeni treeni = new Treeni(); 
    treeni.parse("   3  |  Hiihto   | 100  | 21.12.2022"); 
    assertEquals("From: Treeni line: 125", 3, treeni.getTunnusNro()); 
    assertEquals("From: Treeni line: 126", false, treeni.toString().startsWith("3|Hiihto|100|21.12.2022|"));  // on enemmäkin kuin 3 kenttää, siksi loppu |
    treeni.rekisteroi(); 
    int n = treeni.getTunnusNro(); 
    treeni.parse(""+(n+20));  // Otetaan merkkijonosta vain tunnusnumero
    treeni.rekisteroi();  // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
    assertEquals("From: Treeni line: 132", n+20+1, treeni.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString162 */
  @Test
  public void testToString162() {    // Treeni: 162
    Treeni treeni = new Treeni(); 
    treeni.parse("   3  |  Hiihto   | 100  | 21.12.2022"); 
    assertEquals("From: Treeni line: 165", false, treeni.toString().startsWith("3|Hiihto|100|21.12.2022|"));  // on enemmäkin kuin 3 kenttää, siksi loppu |
  } // Generated by ComTest END
}