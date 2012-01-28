<%@page pageEncoding="UTF-8"%>
<%@page import="java.security.cert.Certificate"%>
<%@page import="net.java.dev.sommer.foafssl.principals.DereferencedFoafSslPrincipal"%>
<%@page import="net.java.dev.sommer.foafssl.verifier.FoafSslVerifier"%>
<%@ page import="java.security.cert.X509Certificate"%>
<%@ page import="javax.security.auth.x500.X500Principal"%>
<%@ page import="java.net.URI"%>
<%
/*
System.out.println(request.getAttribute("javax.servlet.request.X509Certificate"));
	X509Certificate[] certs = (X509Certificate[]) request
			.getAttribute("javax.servlet.request.X509Certificate");
	if (certs == null) {
		System.out.println("Cannot find any client certificate.");
	} else {
		System.out.println("Certificate chain:");
		for (X509Certificate cert : certs) {
			out.println(" - "
					+ cert.getSubjectX500Principal().getName());
		}
		X509Certificate clientCert = certs[0];

		DereferencedFoafSslPrincipal verifier = new DereferencedFoafSslPrincipal(new URI(""));
		out.println("Verified URIs:");
		for (Certificate verifiedUri : verifier.getFoafServerCertificateChain()) {
			out.println(" - " + verifiedUri.getType());
		}
                out.println(certs[0].getSubjectDN());
	}*/
%>