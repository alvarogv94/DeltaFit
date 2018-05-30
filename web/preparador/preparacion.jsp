<%-- 
    Document   : preparacion
    Created on : 27-may-2018, 11:50:04
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
            <title>Preparacion de Atleta</title>
            <link rel="stylesheet" href="../css/reseteo.css">
            <link rel="stylesheet" href="../css/font.css">
            <link rel="stylesheet" href="../css/comun.css">
            <link rel="stylesheet" href="../css/perfil.css">
            <link rel="stylesheet" href="../css/preparador/preparacion.css">
            <link rel="stylesheet" href="../css/jquery-ui.min.css">
            <script src="../js/jquery.js"></script>
            <script src="../js/jquery-ui.min.js"></script>
            <script src="../js/jquery.e-calendar.js"></script>

            <script>
                $(document).ready(function () {
                    //Click en el icono de tu perfil para desplegar el menu
                    $("#der").click(function () {
                        $("#menuPer").slideToggle("fast");
                    });

                    $("#muestra").click(function () {
                        $("#fichaAtleta").slideToggle("fast");
                    });

                });
            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menuPerfil.jsp" />
                <div id="contenido">
                    <p id="muestra">Mostrar Ficha de Atleta</p>
                    <div id="fichaAtleta">
                        <p>Nombre ${sessionScope.atletaPreparacion.nombre}</p>
                        <p>Apellidos ${sessionScope.atletaPreparacion.apellidos}</p>
                        <p>Localidad ${sessionScope.atletaPreparacion.localidad}</p>
                        <p>Edad ${sessionScope.atletaPreparacion.edad}</p>
                        <p>Sexo ${sessionScope.atletaPreparacion.sexo}</p>
                        <p>Nombre de Usuario ${sessionScope.atletaPreparacion.nomUsuario}</p>
                        <p>Email ${sessionScope.atletaPreparacion.nomUsuario}</p>
                        <p>Peso Actual ${sessionScope.atletaPreparacion.pesoActual}</p>
                        <p>Altura ${sessionScope.atletaPreparacion.altura}</p>
                        <p>Deporte ${sessionScope.atletaPreparacion.deporte}</p>
                        <p>Lesion ${sessionScope.atletaPreparacion.lesionSi}</p>
                    </div>
                    <div id="menuPreparacion">
                        <h:form rendered="#{RealizaPreparacion.lesionNo()}">
                            <h1>Preparación para Atleta No Lesionado</h1>
                            <div id="dieta">
                                <h1>Dieta</h1>
                                <p>
                                    <h:outputLabel for="desayuno">Desayuno </h:outputLabel>
                                    <h:inputText value="#{RealizaPreparacion.dietaEntreno.desayuno}" 
                                                 id="desayuno" /><br />
                                </p>
                                <p>
                                    <h:outputLabel for="mediaManhana">Media Mañana </h:outputLabel>
                                    <h:inputText value="#{RealizaPreparacion.dietaEntreno.mediaManhana}" 
                                                 id="mediaManhana" /><br />
                                </p>
                                <p>
                                    <h:outputLabel for="almuerzo">Almuerzo </h:outputLabel>
                                    <h:inputText value="#{RealizaPreparacion.dietaEntreno.almuerzo}" 
                                                 id="almuerzo" /><br />
                                </p>  
                                <p>
                                    <h:outputLabel for="preEntreno">Pre Entreno </h:outputLabel>
                                    <h:inputText value="#{RealizaPreparacion.dietaEntreno.preEntreno}" 
                                                 id="preEntreno" /><br />
                                </p>   
                                <p>
                                    <h:outputLabel for="postEntreno">Post Entreno </h:outputLabel>
                                    <h:inputText value="#{RealizaPreparacion.dietaEntreno.postEntreno}" 
                                                 id="postEntreno" /><br />
                                </p>  
                                <p>
                                    <h:outputLabel for="cena">Cena </h:outputLabel>
                                    <h:inputText value="#{RealizaPreparacion.dietaEntreno.cena}" 
                                                 id="cena" /><br />
                                </p> 
                            </div> 
                            <p>
                                <h:commandButton value="Añadir Dieta a la Preparacion" action="#{RealizaPreparacion.guardaPlan()}"/>
                            </p>
                        </h:form>

                        <h:form rendered="#{RealizaPreparacion.lesionSi()}">
                            <h1>Preparación para Atleta No Lesionado</h1>
                            <div id="dieta">
                                <h1>Dieta</h1>
                                <p>
                                    <h:outputLabel for="desayuno">Desayuno </h:outputLabel>
                                    <h:inputText value="#{RealizaPreparacion.dietaRecuperacion.desayuno}" 
                                                 id="desayuno" /><br />
                                </p>
                                <p>
                                    <h:outputLabel for="mediaManhana">Media Mañana </h:outputLabel>
                                    <h:inputText value="#{RealizaPreparacion.dietaRecuperacion.mediaManhana}" 
                                                 id="mediaManhana" /><br />
                                </p>
                                <p>
                                    <h:outputLabel for="almuerzo">Almuerzo </h:outputLabel>
                                    <h:inputText value="#{RealizaPreparacion.dietaRecuperacion.almuerzo}" 
                                                 id="almuerzo" /><br />
                                </p>  
                                <p>
                                    <h:outputLabel for="cena">Cena </h:outputLabel>
                                    <h:inputText value="#{RealizaPreparacion.dietaRecuperacion.cena}" 
                                                 id="cena" /><br />
                                </p>  
                            </div>
                            <p>
                                <h:commandButton value="Añadir Dieta a la Preparacion" action="#{RealizaPreparacion.guardaPlan()}"/>
                            </p>
                        </h:form>
                        <div id="rutina">
                            <p>Ejercicios para el dia <h:outputText value="#{RealizaPreparacion.dia}" /> de entrenamiento</p>
                            <h:form id="rutinaMusculo">
                                <p>
                                    <h:outputLabel for="deporte">Musculo </h:outputLabel>
                                    <h:selectOneMenu value="#{RealizaPreparacion.musculo}"
                                                     id="deporte"
                                                     valueChangeListener="#{RealizaPreparacion.cambio}" onchange="submit()">
                                        <f:selectItem itemValue = "1" itemLabel = "Selecciona un Músculo" />                                        
                                        <f:selectItems value="#{RealizaPreparacion.listaMusculo}" 
                                                       var="c" itemLabel="#{c.nombreMusculo}" itemValue="#{c.nombreMusculo}"/>
                                    </h:selectOneMenu> <br /> 
                                </p>
                            </h:form>
                            <h:form id="rutinaEjercicio" rendered="#{RealizaPreparacion.lesionNo()}">
                                <p>
                                    <h:outputLabel for="ejercicio">Ejercicio </h:outputLabel>
                                    <h:selectOneMenu value="#{RealizaPreparacion.rutinaEntreno.ejercicio}"
                                                     id="ejercicio">
                                        <f:selectItems value="#{RealizaPreparacion.listaEjercicios}" 
                                                       var="c" itemLabel="#{c.nombre}" itemValue="#{c.nombre}"/>
                                    </h:selectOneMenu> <br /> 
                                </p>
                                <p>
                                    <h:inputText value="#{RealizaPreparacion.rutinaEntreno.anotacion}" id="anotacion" />
                                </p>
                                <p>                                    
                                    <h:commandButton value="Añadir Ejercicio" action="#{RealizaPreparacion.añadeEjercicioNoLesion()}"/>
                                </p>
                                <p>                                    
                                    <h:commandButton value="Finalizar Día" action="#{RealizaPreparacion.siguienteDia()}"/>
                                </p>
                            </h:form>
                            <h:form id="rutinaEjercicioLesion" rendered="#{RealizaPreparacion.lesionSi()}">
                                <p>
                                    <h:outputLabel for="ejercicio">Ejercicio </h:outputLabel>
                                    <h:selectOneMenu value="#{RealizaPreparacion.rutinaRecuperacion.ejercicio}"
                                                     id="ejercicio">
                                        <f:selectItems value="#{RealizaPreparacion.listaEjercicios}" 
                                                       var="c" itemLabel="#{c.nombre}" itemValue="#{c.nombre}"/>
                                    </h:selectOneMenu> <br /> 
                                </p>
                                <p>
                                    <h:inputText value="#{RealizaPreparacion.rutinaRecuperacion.anotacion}" id="anotacion" />
                                </p>
                                <p>                                    
                                    <h:commandButton value="Añadir Ejercicio" action="#{RealizaPreparacion.añadeEjercicioLesion()}"/>
                                </p>
                                <p>                                    
                                    <h:commandButton value="Finalizar Día" action="#{RealizaPreparacion.siguienteDia()}"/>
                                </p>
                            </h:form>
                        </div>

                        <p id="muestra">Mostrar Plan de Entrenamiento Actual</p>
                        <div id="resumenPreparacion">
                            <h1>Plan de Entrenamiento</h1>
                            <div id="resumenDieta">
                                <h1>Resumen Dieta</h1>
                                <h:dataTable rendered="#{RealizaPreparacion.lesionNo()}" value="#{RealizaPreparacion.dietaEntreno}" var="campo" border="1" >
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

                                <h:dataTable rendered="#{RealizaPreparacion.lesionSi()}" value="#{RealizaPreparacion.dietaRecuperacion}" var="campo" border="1" >
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
                                            <h:outputText value="Cena" />
                                        </f:facet>
                                        <h:outputText value="#{campo.cena}" />
                                    </h:column>
                                </h:dataTable>
                            </div>

                            <div id="resumenRutina">
                                <h1>Resumen Rutina de Entrenamiento</h1>
                                <h:dataTable rendered="#{RealizaPreparacion.lesionNo()}" value="#{RealizaPreparacion.listaEntreno}" var="campo" border="1" >
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Dia"/>
                                        </f:facet>
                                        <h:outputText value="#{campo.dia}"/>
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

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Orden" />
                                        </f:facet>
                                        <h:outputText value="#{campo.orden}" />
                                    </h:column>                                   
                                </h:dataTable>

                                <h:dataTable rendered="#{RealizaPreparacion.lesionSi()}" value="#{RealizaPreparacion.listaEntrenoLesion}" var="campo" border="1" >
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Dia"/>
                                        </f:facet>
                                        <h:outputText value="#{campo.dia}"/>
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

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Orden" />
                                        </f:facet>
                                        <h:outputText value="#{campo.orden}" />
                                    </h:column>                                   
                                </h:dataTable>
                            </div>
                            <h:form id="guardaPlan" prependId="false">
                                <p>
                                    <h:commandButton rendered="#{RealizaPreparacion.lesionSi()}" value="Guardar Preparacion" action="#{RealizaPreparacion.guardaPlanLesion()}"/>
                                    <h:commandButton rendered="#{RealizaPreparacion.lesionNo()}" value="Guardar Preparacion" action="#{RealizaPreparacion.guardaPlanLesionNo()}"/>
                                </p>
                            </h:form>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/include/pie2.jsp" />        
        </body>
    </html>
</f:view>
