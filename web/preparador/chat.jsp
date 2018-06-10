<%-- 
    Document   : chat
    Created on : 26-may-2018, 11:00:38
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
            <link rel="stylesheet" href="../css/reseteo.css">
            <link rel="stylesheet" href="../css/font.css">
            <link rel="stylesheet" href="../css/comun.css">
            <link rel="stylesheet" href="../css/perfil.css">
            <link rel="stylesheet" href="../css/preparador/chat.css">
            <link rel="stylesheet" href="../css/jquery-ui.min.css">
            <script src="../js/jquery.js"></script>
            <script src="../js/jquery-ui.min.js"></script>
            <script>
                $(document).ready(function () {
                    //Click en el icono de tu perfil para desplegar el menu
                    $("#der").click(function () {
                        $("#menuPer").slideToggle("fast");
                    });
                });
            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menuPerfil.jsp" />
                <div id="contenido">
                    <h:form id="formPreparacion" prependId="false">
                        <h1>Mensajes sin Leer</h1>
                        <h:dataTable id="tablaChat" 
                                     binding="#{seleccionaChat.tabla}" 
                                     value="#{seleccionaChat.listaChat}" 
                                     var="campo" 
                                     border="1" >
                            <h:column>
                                <h:graphicImage rendered="#{campo.fotoPerfil ne ''}" value="/img/perfil/a#{campo.codAtleta}/#{campo.fotoPerfil}" />                            
                                <h:graphicImage rendered="#{campo.fotoPerfil eq ''}" value="/img/perfil/no-image.png" />
                                <span><h:outputText value="#{seleccionaChat.getMensajesNoLeidos(campo.nomUsuario)}"/></span>
                            </h:column>
                            <h:column>
                                <h:outputText value="#{campo.nomUsuario}"/>
                            </h:column>

                            <h:column>
                                <h:commandButton value="Abrir Chat" styleClass="btn" action="#{seleccionaChat.seleccionaAtleta()}"/>
                            </h:column>
                        </h:dataTable>
                    </h:form>

                </div>
                <jsp:include page="/include/pie2.jsp" /> 
        </body>
    </html>
</f:view>
