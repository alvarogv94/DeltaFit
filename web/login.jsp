<%-- 
    Document   : login
    Created on : 04-may-2018, 22:07:40
    Author     : Alvaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Login</title>
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <link rel="stylesheet" href="css/login.css">
            <script src="js/jquery.js"></script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menu.jsp" />

                <div id="contenido">
                    <h:form id="formularioRegistro"><h:commandLink value="¿No tienes cuenta? Consigue tu cuenta y empieza tu desafío haciendo click aquí" action="registro"></h:commandLink></h:form>
                    <h:form id="formularioLogin">

                        <fieldset>
                            <legend>Acceso a tu Perfil</legend>
                            <p>
                            <h:outputLabel for="NomUsuario">Nombre de usuario:</h:outputLabel>
                            <h:inputText value="#{login.nomUsuario}" id="NomUsuario"></h:inputText><br />
                            </p>
                            <p>
                            <h:outputLabel for="Pass">Contraseña:</h:outputLabel>
                            <h:inputSecret value="#{login.pass}" id="Pass"></h:inputSecret><br />
                            </p>
                            
                            <p>
                            <h:commandButton value="Entrar" styleClass="btn" action="#{login.login()}"/>
                            </p>
                        </fieldset>

                    </h:form>

                </div>

            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>
