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
            <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Login</title>
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <link rel="stylesheet" href="css/login.css">
            <link rel="stylesheet" href="css/movil/login.css">
            <link rel="stylesheet" href="css/tablet/login.css">
            <script src="js/jquery.js"></script>
            <script>
                $(document).ready(function () {
                    
                    var contador = 1;

                    $('.menu_bar').click(function () {
                        // $('nav').toggle(); 

                        if (contador == 1) {
                            $('nav').animate({
                                left: '0'
                            });
                            contador = 0;
                        } else {
                            contador = 1;
                            $('nav').animate({
                                left: '-100%'
                            });
                        }

                    });

                });
            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menu.jsp" />
                <p id="resultadoRegistro"><h:outputText styleClass="#{registro.clase}" value="#{registro.resultadoAlta}" /></p>
                <div id="contenido">
                    <h:form id="formularioRegistro"><h:commandLink value="¿No tienes cuenta? Consigue tu cuenta y empieza tu desafío haciendo click aquí" action="registro"></h:commandLink></h:form>
                    <p id="resultadoLogin"><h:outputText styleClass="red" value="#{login.mensaje}" /></p>
                    <h:form id="formularioLogin">

                        <fieldset>
                            <legend>Acceso a tu Perfil</legend>
                            <p>
                                <h:outputLabel for="NomUsuario">Nombre de usuario:</h:outputLabel>
                                <h:inputText value="#{login.nomUsuario}" 
                                             id="NomUsuario" 
                                             required="true"                               
                                             requiredMessage="El campo de nombre de Usuario es obligatorio">
                                </h:inputText><br /><h:message id="reqnomusu" for="NomUsuario" style="color:red"/>
                            </p>
                            <p>
                                <h:outputLabel for="pass">Contraseña:</h:outputLabel>
                                <h:inputSecret value="#{login.pass}" 
                                               id="pass" 
                                               required="true"
                                               requiredMessage="El campo de contraseña es obligatorio">                                
                                </h:inputSecret><br /><h:message id="reqpass" for="pass" style="color:red"/>
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
