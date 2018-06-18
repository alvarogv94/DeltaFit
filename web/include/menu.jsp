<%-- 
    Document   : menu
    Created on : 04-may-2018, 21:44:54
    Author     : Alvaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<header id="menu">
    <nav>
        <ul>
            <li><h:form><h:commandLink action="que_ofrecemos">¿Qué ofrecemos?</h:commandLink></h:form></li>
            <li><h:form><h:commandLink action="que_conseguiras">¿Qué conseguirás?</h:commandLink></h:form></li>
            <li><h:form><h:commandLink action="index"><h:graphicImage value="/img/logo.png" /></h:commandLink></h:form>
            </li>
            <li><h:form><h:commandLink action="sobre_nosotros">Sobre Nosotros</h:commandLink></h:form></li>
            <li><h:form><h:commandLink action="faq">FAQ</h:commandLink></h:form></li>
                </ul>
                <div id="contenedorLogin">
            <h:form><h:commandLink action="login"><h:graphicImage value="/img/user.png" /></h:commandLink></h:form>
                </div>
            </nav>
                <span class="tag_line" id="tl_izquierda">"No Prometemos Milagros</span>
                <span class="tag_line" id="tl_derecha">Solo Resultados Reales"</span>
            <span class="titulo_web" id="letras_titulo_izquierda">Delta</span>
            <span class="titulo_web" id="letras_titulo_derecha">Fit</span>
        </header>


        <!--Html Movil-->
        <header class="cab_movil">
            <div class="menu_bar">
                <a href="#" class="bt-menu"></a>
        <h:form>
            <h:commandLink styleClass="usu" action="login">
                <h:graphicImage styleClass="usu" value="/img/user.png" />
            </h:commandLink>
        </h:form>

    </div>

    <nav>
        <ul>
            <li><h:form><h:commandLink action="index">Inicio</h:commandLink></h:form></li>
            <li><h:form><h:commandLink action="que_ofrecemos">¿Qué ofrecemos?</h:commandLink></h:form></li>
            <li><h:form><h:commandLink action="que_conseguiras">¿Qué conseguirás?</h:commandLink></h:form></li>
            <li><h:form><h:commandLink action="sobre_nosotros">Sobre Nosotros</h:commandLink></h:form></li>
            <li><h:form><h:commandLink action="faq">FAQ</h:commandLink></h:form></li>
        </ul>
    </nav>
</header>