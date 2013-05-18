package net.rhizomik.rhizomer.authentication;
/*[<<IFilter>>]^-.-[FiltreRizhomik|LoginFormulari;FOAFSSL|init();doFilter();destroy()], [FiltreRizhomik]-->[FOAFSSL], [FiltreRizhomik]-->[LoginFormulari|nuse|mostrar_formulari();validar_dades()]
[Iterator]-->[LoginFormulari]
[Iterator]^-.-[IteratorFitxer]*/
import java.io.IOException;
import java.io.PrintStream;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.java.dev.sommer.foafssl.claims.WebIdClaim;
import net.java.dev.sommer.foafssl.claims.X509Claim;
import net.java.dev.sommer.foafssl.j2ee.filter.FoafSSLFilter;
import net.rhizomik.rhizomer.authentication.Constants.Constants;

/**
 * Classe que valida que estiguis identificat mitjançant un certificat o a través d'un login
 * @author <mtp1268@gmail.com>
 */
public class FiltreRhizomik implements Filter {
    private LoginFormulari login = null;
    private X509Certificate x509Certificate;

    public static final String WEBIDCLAIMS_ATTR_NAME = "net.java.dev.sommer.foafssl.j2ee.webidclaims";

    public void init(FilterConfig filterConfig) throws ServletException {
        this.login = new LoginFormulari(filterConfig.getServletContext().getRealPath("WEB-INF"));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession(true);
        String usuari = null;
        if (request.getAttribute("javax.servlet.request.X509Certificate") == null) {
            request.setAttribute("javax.servlet.request.X509Certificate", new X509Certificate[]{this.x509Certificate});
            if (this.login.sha_de_autentificar(request)) {
                this.login.mostrar_formulari(response);
            } else {
                this.login.obtenir_dades();
                try{
                    if (this.login.son_valides()) {
                        chain.doFilter(request, response);
                        usuari = this.login.getUsuari();
                    } else {
                        this.login.mostrar_formulari(response);
                    }
                }catch(NullPointerException ex){
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            X509Certificate cert[] = (X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate");
            if(this.foafssl_doFilter(request, response, chain)){
                usuari = cert[0].getSubjectDN().toString();
            }else{
                ((HttpServletResponse)response).sendRedirect("../error.html");
            }
        }
        if(usuari != null){
            session.setAttribute(Constants.NOM_SESSIO_USUARI, usuari);
        }
    }
    private boolean foafssl_doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)throws IOException, ServletException {
        boolean esValid = false;
        X509Certificate[] certs = (X509Certificate[]) req.getAttribute("javax.servlet.request.X509Certificate");
        Collection<? extends WebIdClaim> pls = null;
        try {
            X509Claim x509Claim = new X509Claim(certs[0]);
            if (x509Claim.verify()) {
                pls = x509Claim.getVerified();
                if (pls == null || pls.isEmpty()) {
                    resp.getOutputStream().write("No foaf+ssl certificates".getBytes());
                    return esValid;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FoafSSLFilter.class.getName()).log(Level.SEVERE, null, ex);
            resp.getOutputStream().write("cought error doing verification:".getBytes());
            ex.printStackTrace(new PrintStream(resp.getOutputStream()));
            return esValid;
        }
        if(pls != null){
            esValid = true;
            req.setAttribute(WEBIDCLAIMS_ATTR_NAME, pls);
            chain.doFilter(req, resp);
        }
        return esValid;

    }
    public void destroy() {
        // do nothing yet
    }
}