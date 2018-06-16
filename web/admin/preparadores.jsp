<%-- 
    Document   : preparadores
    Created on : 13-jun-2018, 19:41:25
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
            <title>Preparadores</title>
            <link rel="stylesheet" href="../css/reseteo.css">
            <link rel="stylesheet" href="../css/font.css">
            <link rel="stylesheet" href="../css/comun.css">
            <link rel="stylesheet" href="../css/admin/menu.css">
            <link rel="stylesheet" href="../css/jquery.dynatable.css">
            <link rel="stylesheet" href="../css/admin/preparadores.css">
            <link rel="stylesheet" href="../css/jquery-ui.min.css">
            <script src="../js/jquery.js"></script>
            <script src="../js/jquery-ui.min.js"></script>
            <script src="../js/jquery.dynatable.js"></script>
            <script>
                $(document).ready(function () {
                    $('#atletas table').dynatable();
                });
            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="../include/menuAdmin.jsp" />
                <div id="datos">
                    <h:form id="atletas" prependId="false">
                        <h:dataTable id="tablaPreparacion" 
                                     binding="#{datosTablas.tabla}" 
                                     value="#{datosTablas.damePreparadores()}" 
                                     var="campo" 
                                     border="1" >
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Codigo de Preparador"/>
                                </f:facet>
                                <h:outputText value="#{campo.codPreparador}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Nombre de Usuario"/>
                                </f:facet>
                                <h:outputText value="#{campo.nomUsuario}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Edad"/>
                                </f:facet>
                                <h:outputText value="#{campo.edad}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Localidad"/>
                                </f:facet>
                                <h:outputText value="#{campo.localidad}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Email"/>
                                </f:facet>
                                <h:outputText value="#{campo.email}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Especialidad"/>
                                </f:facet>
                                <h:outputText value="#{campo.especialidad}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha Incorporacion"/>
                                </f:facet>
                                <h:outputText value="#{campo.fech}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Eliminar" />                            
                                </f:facet>
                                <h:commandButton value="Eliminar" styleClass="btn" action="#{datosTablas.eliminaPreparador()}"/>
                            </h:column>
                        </h:dataTable>
                    </h:form>
                </div>
            </div>
        </body>
    </html>
</f:view>