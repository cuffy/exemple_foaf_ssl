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
public class FitxerTest extends TestCase {
    Fitxer f = new Fitxer();
    
    public FitxerTest(String testName) {
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
     * Test of existeix_usuari method, of class Fitxer.
     */
    public void testExisteix_usuari() {
        assertTrue(this.f.existeix_usuari("ex"));
        assertFalse(this.f.existeix_usuari("mtrepat"));
    }

    /**
     * Test of guardar method, of class Fitxer.
     */
    public void testGuardar() {
        Usuari u = new Usuari();
        try{
            u.setUsuari(null);
            fail();
            u.setPass(null);
            this.f.guardar(u);
        }catch(Exception ex){
            assertTrue(true);
        }

        try{
            u.setUsuari("root");
            u.setPass("11");
            assertTrue(this.f.guardar(u));
            assertTrue(this.f.existeix_usuari("root"));
        }catch(Exception ex){
            fail();
        }
        
    }

}
