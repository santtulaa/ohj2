package treenipk;

/**
 * 
 * @author santerisalmela
 * @version 23 Feb 2022
 *
 */
public class SailoException  extends Exception{
    private static final long serialVersionUID = 1L;


/**
 * 
 * @param viesti poikkeuksen viesti
 */
public SailoException(String viesti) {
        super(viesti);
    }
}
