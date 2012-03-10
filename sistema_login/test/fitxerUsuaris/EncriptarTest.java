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
public class EncriptarTest extends TestCase {
    Encriptar e = new Encriptar();
    
    public EncriptarTest(String testName) {
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
     * Test of setPass method, of class Encriptar.
     */
    public void testSetPass() {
        this.e.setPass(null);

        this.e.setPass("");

        this.e.setPass("12");

        assertTrue(true);
    }

    /**
     * Test of getPass method, of class Encriptar.
     */
    public void testGetPass() {
        this.e.setPass(null);
        assertEquals(this.e.getPass(),"d41d8cd98f00b204e9800998ecf8427e");

        this.e.setPass("");
        assertEquals(this.e.getPass(),"d41d8cd98f00b204e9800998ecf8427e");

        this.e.setPass("12");
        assertEquals(this.e.getPass(),"c20ad4d76fe97759aa27a0c99bff6710");

        this.e.setPass("c♦1$aaaa9ç♫");
        assertEquals(this.e.getPass(),"ab71fd405893a699c5e0b9949983bf70");
    }
}