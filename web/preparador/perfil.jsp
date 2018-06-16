<%-- 
    Document   : perfil
    Created on : 26-may-2018, 10:59:54
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
            <link rel="stylesheet" href="../css/preparador/perfil.css">
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

                    <div id="datosPersonales">
                        <h1>Mis Datos Personales</h1>
                        <p>Nombre: <span><h:outputText value="#{sessionScope.usuActivo.nombre}" /></span></p>
                        <p>Apellidos: <span><h:outputText value="#{sessionScope.usuActivo.apellidos}" /></span></p>
                        <p>Localidad: <span><h:outputText value="#{sessionScope.usuActivo.localidad}" /></span></p>
                        <p>Edad: <span><h:outputText value="#{sessionScope.usuActivo.edad}" /></span></p>
                        <p>Email: <span><h:outputText value="#{sessionScope.usuActivo.email}" /></span></p>
                        <p>Titulación: <span><h:outputText value="#{sessionScope.usuActivo.titulacion}" /></span></p>
                        <p>Especialidad: <span><h:outputText value="#{sessionScope.usuActivo.especialidad}" /></span></p>
                        <p>Experiencia: <span><h:outputText value="#{sessionScope.usuActivo.experiencia}" /></span></p>
                        <p>Sobre ti: <span><h:outputText value="#{sessionScope.usuActivo.sobreTi}" /></span></p>
                        <p>Fecha Incorporacion: <span><h:outputText value="#{sessionScope.usuActivo.fech}" /></span></p>
                    </div>
                    <div id="modificacionDatos">
                        <h1>Modificacion de Perfil</h1>
                        <h:form prependId="false" id="addtitulacion">

                            <p>
                                <h:outputLabel for="modificaTit">Añadir titulación: </h:outputLabel>
                                <h:inputText value="#{modificaDatosPreparador.titulacion}" />
                                <h:commandButton value="Añadir" styleClass="btn" action="#{modificaDatosPreparador.addTitulacion()}"/>
                            </p>
                        </h:form>
                        <h:form prependId="false" id="addExperiencia">

                            <p>
                                <h:outputLabel for="modificaTit">Añadir Experiencia: </h:outputLabel>
                                <h:inputText value="#{modificaDatosPreparador.experiencia}" />
                                <h:commandButton value="Añadir" styleClass="btn" action="#{modificaDatosPreparador.addExperiencia()}"/>
                            </p>
                        </h:form>

                        <h:form prependId="false" id="sobreTi">
                            <p>
                                <h:outputLabel for="modificaTit">Sustituir Información Sobre Tí: </h:outputLabel>
                                <h:inputText value="#{modificaDatosPreparador.experiencia}" />
                                <h:commandButton value="Añadir" styleClass="btn" action="#{modificaDatosPreparador.addSobreTi()}"/>
                            </p>
                        </h:form>

                    </div>
                </div>
            </div>
            <jsp:include page="/include/pie2.jsp" />    
        </body>
    </html>
</f:view>
