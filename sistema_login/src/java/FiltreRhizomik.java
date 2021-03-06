import java.io.IOException;
import javax.security.cert.X509Certificate;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import net.java.dev.sommer.foafssl.j2ee.filter.FoafSSLFilter;

/**
 * Classe que valida que estiguis identificat mitjançant un certificat o a través d'un login
 * @author <mtp1268@gmail.com>
 */
public class FiltreRhizomik implements Filter {
    private LoginFormulari login = null;
    private final FoafSSLFilter foaf_ssl = new FoafSSLFilter();
    private X509Certificate x509Certificate;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.foaf_ssl.init(filterConfig);
        this.login = new LoginFormulari(filterConfig.getServletContext().getRealPath("WEB-INF"));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getAttribute("javax.servlet.request.X509Certificate") == null) {
            request.setAttribute("javax.servlet.request.X509Certificate", new X509Certificate[]{this.x509Certificate});
            if (this.login.sha_de_autentificar(request)) {
                this.login.mostrar_formulari(response);
            } else {
                this.login.obtenir_dades();
                try{
                    if (this.login.son_valides()) {
                        chain.doFilter(request, response);
                    } else {
                        this.login.mostrar_formulari(response);
                    }
                }catch(NullPointerException ex){
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            this.foaf_ssl.doFilter(request, response, chain);
        }
    }
    public void destroy() {
        this.foaf_ssl.destroy();
    }
}