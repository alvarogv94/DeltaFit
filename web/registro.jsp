<%-- 
    Document   : registro
    Created on : 06-may-2018, 11:59:45
    Author     : Alvaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:pt="">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Registro</title>
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <link rel="stylesheet" href="css/registro.css">
            <script src="js/jquery.js"></script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menu.jsp" />
                <div id="contenido">
                    <h:form id="formularioRegistro">
                        <div id="datosPersonales">
                            <h1>Datos Personales</h1>
                            <div id="datosPersonalesIzquierda">
                                <p>
                                    <h:outputLabel for="nombre">Nombre </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.nombre}" id="nombre"></h:inputText><br />
                                </p>
                                <p>
                                    <h:outputLabel for="apellidos">Apellidos </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.apellidos}" id="apellidos"></h:inputText><br />
                                </p>
                                <p>
                                    <h:selectOneRadio value="#{registro.atleta.sexo}">
                                        <f:selectItem itemValue="h" itemLabel="Hombre" />
                                        <f:selectItem itemValue="m" itemLabel="Mujer" />
                                    </h:selectOneRadio>
                                </p>
                            </div> 
                            <div id="datosPersonalesDerecha">
                                <p>
                                    <h:outputLabel for="edad">Edad </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.edad}" id="edad" validatorMessage="La edad debe estar entre 16 y 120">
                                        <f:convertNumber minIntegerDigits="16" maxIntegerDigits="120" />
                                    </h:inputText><h:message for="edad" style="color:red" /><br />
                                </p>
                                <p>
                                    <h:outputLabel for="localidad">Localidad </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.localidad}" id="localidad"></h:inputText><br />
                                </p>
                                <p>
                                    <h:outputLabel for="email">Email </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.email}" id="email"></h:inputText><br />
                                </p>
                            </div>                                  
                        </div>
                    </h:form>

                </div>            
            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>
