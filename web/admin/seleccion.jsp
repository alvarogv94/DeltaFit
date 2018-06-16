<%-- 
    Document   : seleccion
    Created on : 13-jun-2018, 20:07:20
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
            <title>Seleccion de Preparador</title>
            <link rel="stylesheet" href="../css/reseteo.css">
            <link rel="stylesheet" href="../css/font.css">
            <link rel="stylesheet" href="../css/comun.css">
            <link rel="stylesheet" href="../css/admin/menu.css">
            <link rel="stylesheet" href="../css/admin/seleccion.css">
            <link rel="stylesheet" href="../css/jquery-ui.min.css">
            <script src="../js/jquery.js"></script>
            <script src="../js/jquery-ui.min.js"></script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="../include/menuAdmin.jsp" />
                <div id="datos">
                    <h:form id="atletas" prependId="false">
                        <h:dataTable id="tablaPreparacion" 
                                     binding="#{datosTablas.tabla}" 
                                     value="#{datosTablas.dameSeleccion()}" 
                                     var="campo" 
                                     border="1" >
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Codigo de Seleccion"/>
                                </f:facet>
                                <h:outputText value="#{campo.codPreparadorSeleccion}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Nombre"/>
                                </f:facet>
                                <h:outputText value="#{campo.nombre}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Localidad"/>
                                </f:facet>
                                <h:outputText value="#{campo.localidad}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Edad"/>
                                </f:facet>
                                <h:outputText value="#{campo.edad}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Sexo"/>
                                </f:facet>
                                <h:outputText value="#{campo.sexo}"/>
                            </h:column>  
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Email"/>
                                </f:facet>
                                <h:outputText value="#{campo.email}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Titulacion"/>
                                </f:facet>
                                <h:outputText value="#{campo.titulacion}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Especialidad"/>
                                </f:facet>
                                <h:outputText value="#{campo.especialidad}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Experiencia"/>
                                </f:facet>
                                <h:outputText value="#{campo.experiencia}"/>
                            </h:column> 
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Contratar" />                            
                                </f:facet>
                                <h:commandButton value="Contratar" styleClass="btn" action="#{datosTablas.contrataPreparador()}"/>
                            </h:column>
                        </h:dataTable>
                    </h:form>
                </div>
            </div>
        </body>
    </html>
</f:view>