/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fitxerUsuaris;

import junit.framework.TestCase;

/**
 *
 * @author mtrepat
 */
public class CreacioUsuarisTest extends TestCase {
    
    public CreacioUsuarisTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of main method, of class CreacioUsuaris.
     */
    public void testMain_usuari_no_existent() {
        Usuari u = new Usuari();
        try {
            u.setUsuari("");
            fail();
        } catch (Exception ex) {
            assertTrue(true);
        }

        try{
            u.setUsuari("usuari");
            u.setPass(null);
            Fitxer f = new Fitxer();
            assertFalse(f.existeix_usuari("usuari"));
            f.guardar(u);
        }catch(Exception ex){
            fail();
        }
    }

    public void testMain_usuari_existent() {
        Usuari u = new Usuari();
        try{
            u.setUsuari("usuari");
            u.setPass("12");
            Fitxer f = new Fitxer();
            if(f.existeix_usuari("usuari")){
                assertTrue(true);
            }else{
                fail();
            }
        }catch(Exception ex){
            fail();
        }
    }

}
