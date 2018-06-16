<%-- 
    Document   : inicio
    Created on : 13-jun-2018, 19:35:05
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
            <title>Menu Administracion</title>
            <link rel="stylesheet" href="../css/reseteo.css">
            <link rel="stylesheet" href="../css/font.css">
            <link rel="stylesheet" href="../css/comun.css">
            <link rel="stylesheet" href="../css/admin/menu.css">
            <link rel="stylesheet" href="../css/admin/inicio.css">
            <link rel="stylesheet" href="../css/jquery-ui.min.css">
            <script src="../js/jquery.js"></script>
            <script src="../js/jquery-ui.min.js"></script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="../include/menuAdmin.jsp" />
                <div id="datos">
                    <div id="addEjercicio">
                        <h1>Añadir Ejercicio Para un Músculo Determinado</h1>
                        <h:form id="altaNuevoEjercicio" prependId="false">
                            <p>
                                <h:outputLabel for="musculoN">Musculo </h:outputLabel>
                                <h:selectOneMenu value="#{addNuevoEjercicio.nombreMusculo}"
                                                 id="musculoN" >
                                    <f:selectItem itemValue = "1" itemLabel = "Selecciona un Músculo" />                                        
                                    <f:selectItems value="#{addNuevoEjercicio.listaMusculos}" 
                                                   var="c" itemLabel="#{c.nombreMusculo}" itemValue="#{c.nombreMusculo}"/>
                                </h:selectOneMenu> 
                            </p>
                            <p>
                                <h:outputLabel for="ejercicioN">Introduce el Nombre del Ejercicio: </h:outputLabel>
                                <h:inputText id="ejercicioN" value="#{addNuevoEjercicio.ejercicio.nombre}" />
                            </p>
                            <p>
                                <h:outputLabel for="descripcion">Introduce La Descripción del Ejercicio: </h:outputLabel>
                                <h:inputText id="descripcion" value="#{addNuevoEjercicio.ejercicio.descripcion}" />
                            </p>
                            <p>
                                <h:commandButton styleClass="btn" value="Añadir Nuevo Ejercicio" action="#{addNuevoEjercicio.altaEjercicio()}"/>
                            </p>
                        </h:form>
                        <h:outputText value="#{addNuevoEjercicio.error}" styleClass="#{addNuevoEjercicio.clase}" />
                    </div>
                    <div id="addPlan">
                        <h1>Añadir Plan Predeterminado al Sistema</h1>
                        <h:form prependId="false" id="datosP">
                            <h:outputLabel for="deporte">Indica el Deporte:</h:outputLabel>
                            <h:inputText id="deporte" value="#{altaPlanPred.deporte}" />

                            <h:outputLabel for="objetivo">Indica el Objetivo:</h:outputLabel>
                            <h:inputText id="objetivo" value="#{altaPlanPred.objetivo}" />
                            <h:commandButton styleClass="btn" value="Añadir" action="#{altaPlanPred.saveDatos()}"/>
                        </h:form>
                        <div id="addDieta">
                            <h1>Dieta</h1>
                            <h:form prependId="false" id="dieta">
                                <p>
                                    <h:outputLabel for="desayuno">Desayuno </h:outputLabel>
                                    <h:inputText value="#{altaPlanPred.nuevaDieta.desayuno}" 
                                                 id="desayuno" /><br />
                                </p>
                                <p>
                                    <h:outputLabel for="mediaManhana">Media Mañana </h:outputLabel>
                                    <h:inputText value="#{altaPlanPred.nuevaDieta.mediaManhana}" 
                                                 id="mediaManhana" /><br />
                                </p>
                                <p>
                                    <h:outputLabel for="almuerzo">Almuerzo </h:outputLabel>
                                    <h:inputText value="#{altaPlanPred.nuevaDieta.almuerzo}" 
                                                 id="almuerzo" /><br />
                                </p>  
                                <p>
                                    <h:outputLabel for="preEntreno">Pre Entreno </h:outputLabel>
                                    <h:inputText value="#{altaPlanPred.nuevaDieta.preEntreno}" 
                                                 id="preEntreno" /><br />
                                </p>   
                                <p>
                                    <h:outputLabel for="postEntreno">Post Entreno </h:outputLabel>
                                    <h:inputText value="#{altaPlanPred.nuevaDieta.postEntreno}" 
                                                 id="postEntreno" /><br />
                                </p>  
                                <p>
                                    <h:outputLabel for="cena">Cena </h:outputLabel>
                                    <h:inputText value="#{altaPlanPred.nuevaDieta.cena}" 
                                                 id="cena" /><br />
                                </p> 
                                <p id="dietaAdd">
                                    <h:commandButton styleClass="btn" value="Añadir Dieta al Plan" action="#{altaPlanPred.añadeDieta()}"/>
                                </p>
                            </h:form>                            
                        </div>
                        <div id="rutina">
                            <h1>Rutina de Ejercicios</h1>                        
                            <p>Ejercicios para el dia <h:outputText value="#{altaPlanPred.dia}" /> de entrenamiento</p>
                            <h:form id="rutinaMusculo" prependId="false">
                                <p>
                                    <h:outputLabel for="musculo">Musculo </h:outputLabel>
                                    <h:selectOneMenu value="#{altaPlanPred.musculo}"
                                                     id="musculo"
                                                     valueChangeListener="#{altaPlanPred.cambio}" onchange="submit()">
                                        <f:selectItem itemValue = "1" itemLabel = "Selecciona un Músculo" />                                        
                                        <f:selectItems value="#{altaPlanPred.listaMusculo}" 
                                                       var="c" itemLabel="#{c.nombreMusculo}" itemValue="#{c.nombreMusculo}"/>
                                    </h:selectOneMenu> <br /> 
                                </p>
                            </h:form>
                            <h:form id="rutinaEjercicio" prependId="false">
                                <p>
                                    <h:outputLabel for="ejercicio">Ejercicio </h:outputLabel>
                                    <h:selectOneMenu value="#{altaPlanPred.nuevaRutina.ejercicio}"
                                                     id="ejercicio">
                                        <f:selectItems value="#{altaPlanPred.listaEjerciciosMusculo}" 
                                                       var="c" itemLabel="#{c.nombre}" itemValue="#{c.nombre}"/>
                                    </h:selectOneMenu> 
                                </p>
                                <p>
                                    <h:outputLabel for="ejercicioAlternativo">Ejercicio Alternativo</h:outputLabel>
                                    <h:inputText value="#{altaPlanPred.ejercicioAlternativo}" id="ejercicioAlternativo" />
                                </p>
                                <p>
                                    <h:outputLabel for="anotacion">Anotacion </h:outputLabel>
                                    <h:inputText value="#{altaPlanPred.nuevaRutina.anotacion}" id="anotacion" />
                                </p>
                                <p id="aEjercicio">                                    
                                    <h:commandButton styleClass="btn" value="Añadir Ejercicio" action="#{altaPlanPred.añadeEjercicioPlan()}"/>
                                    <h:outputText value="#{altaPlanPred.errorEjercicio}" styleClass="#{altaPlanPred.clase}"/>
                                </p>
                                <p id="finalizarDia">                                    
                                    <h:commandButton styleClass="btn" value="Finalizar Día" action="#{altaPlanPred.siguienteDia()}"/>
                                </p>
                            </h:form>
                        </div>
                        <div id="resumenPreparacion">
                            <h1>Resumen General</h1>
                            <p>Deporte <span><h:outputText value="#{altaPlanPred.deporte}" /></span></p>
                            <p>Objetivo <span><h:outputText value="#{altaPlanPred.objetivo}" /></span></p>
                            <div id="resumenDieta">
                                <h1>Resumen Dieta</h1>
                                <h:dataTable value="#{altaPlanPred.nuevaDieta}" var="campo" border="1" >
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Desayuno"/>
                                        </f:facet>
                                        <h:outputText value="#{campo.desayuno}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Media Mañana" />
                                        </f:facet>
                                        <h:outputText value="#{campo.mediaManhana}" />
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Almuerzo"/>
                                        </f:facet>
                                        <h:outputText value="#{campo.almuerzo}" />
                                    </h:column>

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Pre Entreno" />
                                        </f:facet>
                                        <h:outputText value="#{campo.preEntreno}" />
                                    </h:column>

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Post Entreno" />
                                        </f:facet>
                                        <h:outputText value="#{campo.postEntreno}" />
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Cena" />
                                        </f:facet>
                                        <h:outputText value="#{campo.cena}" />
                                    </h:column>
                                </h:dataTable>
                            </div>
                            <div id="resumenRutina">
                                <h1>Resumen Rutina de Entrenamiento</h1>
                                <h:dataTable value="#{altaPlanPred.listaEjercicios}" var="campo" border="1" >
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Dia"/>
                                        </f:facet>
                                        <h:outputText value="#{campo.dia}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Orden" />
                                        </f:facet>
                                        <h:outputText value="#{campo.orden}" />
                                    </h:column> 
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Ejercicio" />
                                        </f:facet>
                                        <h:outputText value="#{campo.ejercicio}" />
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Anotacion"/>
                                        </f:facet>
                                        <h:outputText value="#{campo.anotacion}" />
                                    </h:column>
                                </h:dataTable>
                            </div>
                            <h:form id="guardaPlan" prependId="false">
                                <p id="guardaPlanBoton">
                                    <h:commandButton styleClass="btn" value="Guardar Preparacion" action="#{altaPlanPred.guardaPlan()}"/>
                                </p>
                                <p id="volver">
                                    <h:commandButton styleClass="btn" value="Reiniciar" action="#{altaPlanPred.volver()}"/>
                                </p>
                            </h:form>
                        </div>
                    </div>
                </div>
        </body>
    </html>
</f:view>
