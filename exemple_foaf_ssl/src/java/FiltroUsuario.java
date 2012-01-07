
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marc
 */
public class FiltroUsuario implements Filter{
    private FilterConfig config;
    private String urlLogin;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        this.urlLogin = filterConfig.getInitParameter("urlLogin");
        if (urlLogin == null || urlLogin.trim().length() == 0) {
            //Error al cargar la url de login
            throw new ServletException("No se ha configurado URL de login");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        // Extraer Sesión
        HttpSession session = ((HttpServletRequest)request).getSession();
        if(session.getAttribute("nom") == null){
            System.out.println("no hi ha usuari!");
            //NO s'ha loguejat ningu rediregim al login
            RequestDispatcher dispatcher = request.getRequestDispatcher("/"+this.urlLogin);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        config = null;
    }
}
