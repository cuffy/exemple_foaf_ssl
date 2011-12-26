import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request == null){
            System.out.println();
            System.out.println();
            System.out.println("Error NULL al request");
        }else{
            System.out.println();
            System.out.println();
            System.out.println("OK request");
        }
        
        if(response == null){
            System.out.println();
            System.out.println();
            System.out.println("Error NULL al response");
        }else{
            System.out.println();
            System.out.println();
            System.out.println("OK response");
        }
        
        if(chain == null){
            System.out.println();
            System.out.println();
            System.out.println("Error NULL al chain");
        }else{
            System.out.println();
            System.out.println();
            System.out.println("OK chain");
        }
        super.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
