<%-- 
    Document   : inicio
    Created on : 23-may-2018, 20:02:40
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
            <title>Perfil Personal de Preparador</title>
            <link rel="stylesheet" href="../css/reseteo.css">
            <link rel="stylesheet" href="../css/font.css">
            <link rel="stylesheet" href="../css/comun.css">
            <link rel="stylesheet" href="../css/perfil.css">
            <link rel="stylesheet" href="../css/jquery-ui.min.css">
            <link rel="stylesheet" href="../css/jquery.dynatable.css">
            <link rel="stylesheet" href="../css/preparador/inicio.css">  
            <script src="../js/jquery.js"></script>
            <script src="../js/jquery-ui.min.js"></script>
            <script src="../js/jquery.dynatable.js"></script>

            <script>
                $(document).ready(function () {
                    //Click en el icono de tu perfil para desplegar el menu
                    $("#der").click(function () {
                        $("#menuPer").slideToggle("fast");
                    });

                    $('#formPreparacion table').dynatable();
                });
            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menuPerfil.jsp" />
                <div id="contenido">
                    <h:form id="formPreparacion" prependId="false">
                        <h:dataTable id="tablaPreparacion" 
                                     binding="#{seleccionaAtleta.tabla}" 
                                     value="#{seleccionaAtleta.listaAtletas}" 
                                     var="campo" 
                                     border="1" >
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Nombre Atleta"/>
                                </f:facet>
                                <h:outputText value="#{campo.nombre}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Nombre de Usuario" />
                                </f:facet>
                                <h:outputText value="#{campo.nomUsuario}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Deporte"/>
                                </f:facet>
                                <h:outputText value="#{campo.deporte}" />
                            </h:column>

                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Objetivo" />
                                </f:facet>
                                <h:outputText value="#{campo.objetivo}" />
                            </h:column>

                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha del Ultimo Pago" />
                                </f:facet>
                                <h:outputText value="#{campo.fechUltPago}">         
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Preparacion para antes de" />
                                </f:facet>
                                <h:outputText value="#{campo.fechPrep}">         
                                </h:outputText>
                            </h:column>

                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Preparar" />                            
                                </f:facet>
                                <h:commandButton value="Preparar" styleClass="btn" action="#{seleccionaAtleta.seleccionaAtleta()}"/>
                            </h:column>
                        </h:dataTable>
                    </h:form>

                </div>
            </div>
        </div>
        <jsp:include page="/include/pie2.jsp" />        
    </body>
</html>
</f:view>
