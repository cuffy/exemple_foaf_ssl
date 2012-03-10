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
public class UsuariTest extends TestCase {
    Usuari u = new Usuari();
    
    public UsuariTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.u.setUsuari("boo");
        this.u.setPass("12");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getPass method, of class Usuari.
     */
    public void testGetPass() {
        Encriptar e = new Encriptar();
        e.setPass("12");
        assertEquals(this.u.getPass(), e.getPass());

        this.u.setPass(null);
        assertEquals(this.u.getPass(), "d41d8cd98f00b204e9800998ecf8427e");
    }
    /**
     * Test of getUsuari method, of class Usuari.
     */
    public void testGetUsuari() {
        assertEquals(this.u.getUsuari(), "boo");
    }

    /**
     * Test of es_valid method, of class Usuari.
     */
    public void testEs_valid() {
        assertTrue(this.u.es_valid());
        try {
            this.u.setUsuari("usu:ari");
            assertFalse(this.u.es_valid());
        } catch (Exception ex) {
            fail();
        }

        try {
            this.u.setUsuari("ex");
            assertFalse(this.u.es_valid());
        } catch (Exception ex) {
            fail();
        }

        try {
            this.u.setUsuari("");
            assertTrue(false);
        } catch (Exception ex) {
            assertTrue(true);
        }

        try {
            this.u.setUsuari(null);
            assertTrue(false);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }
    
}
