<%@page pageEncoding="UTF-8"%>
<%@page import="java.security.cert.Certificate"%>
<%@page import="net.java.dev.sommer.foafssl.principals.DereferencedFoafSslPrincipal"%>
<%@page import="net.java.dev.sommer.foafssl.verifier.FoafSslVerifier"%>
<%@ page import="java.security.cert.X509Certificate"%>
<%@ page import="javax.security.auth.x500.X500Principal"%>
<%@ page import="java.net.URI"%>
<%
out.println("<h1>ES valid</h1>");
/*
X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
if (certs == null) {
    out.println("Cannot find any client certificate.");
}else {
    System.out.println("Certificate chain:");
   //Si ens hem loguejat pel formulari hi haura null
   //out.println(certs[0].getSubjectDN());
}*/
%>