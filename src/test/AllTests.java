package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite rekisteri-ohjelmalle
 * @author emiliarantonen ja mineanupponen
 * @version 29.3.2022
 */
@RunWith(Suite.class)
@SuiteClasses({
    //treenipk.test.MuistiinpanoTest.class,
    treenipk.test.MuistiinpanotTest.class,
    treenipk.test.TreenipaivakirjaTest.class,
    treenipk.test.TreeniTest.class,
    treenipk.test.TreenitTest.class
    })

public class AllTests {
    //
    
}

