<%-- 
    Document   : menuAdmin
    Created on : 13-jun-2018, 20:03:56
    Author     : Alvaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<div id="lateral">
    <h1>Menú de Administración</h1>
    <p>Administrador: <span>${sessionScope.usuActivo.usuario}</span></p>
    <ul>
        <li><h:form><h:commandLink action="inicio">Inicio</h:commandLink></h:form></li>
        <li><h:form><h:commandLink action="atletas">Atletas</h:commandLink></h:form></li>
        <li><h:form><h:commandLink action="preparadores">Preparadores</h:commandLink></h:form></li>
        <li><h:form><h:commandLink action="seleccion">Seleccion de Preparadores</h:commandLink></h:form></li>
        <li><h:form><h:commandLink action="estadisticas">Estadisticas</h:commandLink></h:form></li>
        <li><h:form><h:commandLink action="#{login.logout()}">Salir</h:commandLink></h:form></li>                        
    </ul>
</div>
