<%-- 
    Document   : conversacion
    Created on : 10-jun-2018, 10:06:04
    Author     : Alvaro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Conversacion</title>
            <link rel="stylesheet" href="../css/reseteo.css">
            <link rel="stylesheet" href="../css/font.css">
            <link rel="stylesheet" href="../css/comun.css">
            <link rel="stylesheet" href="../css/perfil.css">
            <link rel="stylesheet" href="../css/preparador/conversacion.css">
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

                    <div id="chat">
                        <c:forEach items="${sessionScope.conversacion}" var="item">
                            <c:if test="${item.estado.toString() eq '1'}">
                                <div id="mensajePreparador">
                                    <p class="no_leido preparador">${item.nomUsuarioPrep}: ${item.texto} <span class="derecha">${usuActivo.getFech(item.fechaEnvio)} <span class="ui-icon ui-icon-mail-closed"</span></span></p><br />
                                </div>
                            </c:if>
                            <c:if test="${item.estado.toString() eq '2'}">
                                <div id="mensajePreparador">
                                    <p class="leido preparador">${item.nomUsuarioPrep}: ${item.texto} <span class="derecha">${usuActivo.getFech(item.fechaEnvio)}<span class="ui-icon ui-icon-mail-open"></span></span></p><br />
                                </div>
                            </c:if>
                            <c:if test="${item.estado.toString() eq '3'}">
                                <div id="mensajeAtleta">
                                    <p class="no_leido atleta">${item.nomUsuario}: ${item.texto} <span class="derecha">${usuActivo.getFech(item.fechaEnvio)}</span></p><br />
                                </div>
                            </c:if>
                            <c:if test="${item.estado.toString() eq '4'}">
                                <div id="mensajeAtleta">
                                    <p class="leido atleta">${item.nomUsuario}: ${item.texto} <span class="derecha">${usuActivo.getFech(item.fechaEnvio)}</span></p><br />
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <div id="mandaMensaje">
                        <h:form id="formMandaMensaje" prependId="false">
                            <h:inputText value="#{chat.mensaje.texto}" 
                                         id="textoMandaMensaje"
                                         required="true"
                                         requiredMessage="debe escribir algún mensaje" />
                            <p id="boton">
                                <h:commandButton id="botonEnviar" value="Enviar" styleClass="btn" action="#{chat.mandaMensajeAtleta()}"/>
                            </p>
                        </h:form>
                    </div>
                </div>
            </div>
                <jsp:include page="/include/pie2.jsp" />                
        </body>
    </html>
</f:view>
