
import java.io.IOException;
import java.io.PrintWriter;
import java.security.cert.X509Certificate;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
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
 * @author mtrepat
 */
//revisar el fitxer del exemple stock.zip
public class Filtre implements Filter {

    private X509Certificate x509Certificate;
    private net.java.dev.sommer.foafssl.j2ee.filter.FoafSSLFilter foaf = new net.java.dev.sommer.foafssl.j2ee.filter.FoafSSLFilter();

    public void init(FilterConfig filterConfig) throws ServletException {
        this.foaf.init(filterConfig);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response = (HttpServletResponse)response;
        if (request.getAttribute("javax.servlet.request.X509Certificate") == null) {
            request.setAttribute("javax.servlet.request.X509Certificate",
                    new X509Certificate[]{this.x509Certificate});
            String authorization = ((HttpServletRequest)request).getHeader("Authorization");
            if (authorization == null) {
                askForPassword((HttpServletResponse)response);
            } else {
                // Authorization headers looks like "Basic blahblah",
                // where blahblah is the base64 encoded username and
                // password. We want the part after "Basic ".
                String userInfo = authorization.substring(6).trim();
                BASE64Decoder decoder = new BASE64Decoder();
                String nameAndPassword =
                        new String(decoder.decodeBuffer(userInfo));
                // Decoded part looks like "username:password".
                int index = nameAndPassword.indexOf(":");
                String user = nameAndPassword.substring(0, index);
                String password = nameAndPassword.substring(index + 1);
                // High security: username must be reverse of password.
                if (areEqualReversed(user, password)) {
                    showStock((HttpServletRequest)request, (HttpServletResponse)response);
                } else {
                    askForPassword((HttpServletResponse)response);
                }
            }
            chain.doFilter(request, response);
        } else {
            foaf.doFilter(request, response, chain);
        }
    }

    private void askForPassword(HttpServletResponse response) {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // I.e., 401
    response.setHeader("WWW-Authenticate",
                       "BASIC realm=\"Insider-Trading\"");
  }
    private boolean areEqualReversed(String s1, String s2) {
    s2 = (new StringBuffer(s2)).reverse().toString();
    return((s1.length() > 0) && s1.equals(s2));
  }
    private void showStock(HttpServletRequest request,
                         HttpServletResponse response)
      throws ServletException, IOException {
        response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>Hot Stock Tip!</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1>Today's Hot Stock:");
    for(int i=0; i<3; i++) {
      out.print(randomLetter());
    }
    out.println("</H1>\n" +
                "</BODY></HTML>");
  }
    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private char randomLetter() {
    return(ALPHABET.charAt(randomInt(ALPHABET.length())));
  }
private int randomInt(int n) {
    return((int)(Math.random() * n));
  }
    public void destroy() {
        this.foaf.destroy();
    }
}
