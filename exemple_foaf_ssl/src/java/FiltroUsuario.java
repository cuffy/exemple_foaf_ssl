import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.java.dev.sommer.foafssl.j2ee.filter.FoafSSLFilter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marc
 */
public class FiltroUsuario extends FoafSSLFilter{
    private String urlLogin;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //chain.doFilter(request, response);
        System.out.println("Holaa"); 
        // Extraer Sesión
        //HttpSession session = ((HttpServletRequest)request).getSession();
        //if(session.getAttribute("nom") == null){
          //  System.out.println("no hi ha usuari!");
            //NO s'ha loguejat ningu rediregim al login
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/"+this.urlLogin);
            //dispatcher.forward(request, response);
        //}
        super.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
