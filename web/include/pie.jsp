<%-- 
    Document   : pie
    Created on : 04-may-2018, 21:46:16
    Author     : Alvaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<div id="footer">

    <div id="logo_pie">
        <img src="img/logo.png" />
    </div>
    <div id="derecha">
        <div id="arriba">
            <p>informacion@informacion.com</p>
        </div>
        <div id="abajo">
                <span><h:form><h:commandLink action="politica_privacidad">Políticas de Privacidad</h:commandLink></h:form></span>            
                <span><h:form><h:commandLink action="politica_cookies">Políticas de Cookies</h:commandLink></h:form></span>
        </div>
    </div>
</div>