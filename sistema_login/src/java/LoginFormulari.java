
import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.misc.BASE64Decoder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marc
 */
public class LoginFormulari {
    private String autoritzacio;
    private String usuari,pswd;

    public boolean te_autentificacio(ServletRequest request){
        this.autoritzacio = ((HttpServletRequest)request).getHeader("Authorization");
        return (this.autoritzacio == null);
    }

    public void mostrar_formulari(ServletResponse r) {
        HttpServletResponse response = (HttpServletResponse) r;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // I.e., 401
        response.setHeader("WWW-Authenticate",
                "BASIC realm=\"Insider-Trading\"");
    }
    public void obtenir_dades() throws IOException{
        String info = this.autoritzacio.substring(6).trim();
        BASE64Decoder decoder = new BASE64Decoder();
        String nameAndPassword = new String(decoder.decodeBuffer(info));
        // Decoded part looks like "username:password".
        int index = nameAndPassword.indexOf(":");
        this.usuari = nameAndPassword.substring(0, index);
        this.pswd = nameAndPassword.substring(index + 1);
    }

    public boolean son_valides(){
        return this.usuari.equals((new StringBuffer(this.pswd)).reverse().toString());
    }
}
