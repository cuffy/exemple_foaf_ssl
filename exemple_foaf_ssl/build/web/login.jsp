<%-- 
    Document   : login
    Created on : 06-oct-2011, 18:17:14
    Author     : marc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Aquí ens hem de loguejar!</h1>
        <% //Si entre per primer cop creo una sessio per a que a la seguent F5 vaigui al index.jsp
        session.setAttribute("nom", "Marc");
out.println("Hola, "+session.getAttribute("nom")+" ara ja estas loguejat."); %>
    </body>
</html>
