
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8" />
        <meta name="description" content="Web que conté un apartat public, un altre de privat amb certificat i per acabar un altre amb login." />
        <meta name="keywords" content="certificat, foafssl, ssl, login, private" />
        <title>Pàgina principal</title>
        <link rel="shortcut icon" href="/favicon.ico" />
        <link rel="stylesheet" media="all" href="../estil.css" />
    </head>
    <body onload="">
        <div id="wrap">
            <div id="content">
                <header>
                    <div>
                        <h1 id="title">Exemple senzill</h1>
                        <nav>
                            <div id="nav">
                                <ul>
                                    <li><a href="../index.jsp">Apartat public</a></li>
                                    <li><a href="../editor/">Apartat private</a></li>
                                    <li><a href="../editor/login.jsp">Apartat private login</a></li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </header>
                <section>
                    <div id="section">
                        <h2>Apartat privat amb sistema de login</h2>
                        <form id="formLogin" name="formLogin" method="post" action="">
                            <div><strong>Usuario:</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="text" name="user" id="usuari" /></div>
<div>&nbsp;</div>
                            <div><strong>Contrase&ntilde;a:</strong>&nbsp;&nbsp;
                                <input type="password" name="pswd" id="pswd" /></div>
                            <input type="submit" name="button" id="button" value="Enviar" />
                        </form>
                    </div>
                </section>
                <footer>
                    <div id="footer">
                        <a href="#" >Terms</a> -
                        <a href="#" >Privacy</a> -
                        <a href="#">Report bug or request a feature</a> -
                        <a href="#">Follow us on Twitter</a> -
                        <a href="#" >About Authors</a>
                        <span class="madeby">Made by M</span>
                   </div>
                </footer>
            </div>
        </div>
    </body>
</html>