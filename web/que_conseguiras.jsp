<%-- 
    Document   : que_conseguiras
    Created on : 04-may-2018, 22:15:14
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
            <title>¿Qué conseguirás?</title>
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <link rel="stylesheet" href="css/que_conseguiras.css">            
            <script src="js/jquery.js"></script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menu.jsp" />
                <div id="contenido">
                    <div id="cabecera">
                        <img src="img/3.png" alt="" />
                        <h:form id="registro" prependId="false">
                            <h:commandLink styleClass="registro" value="REGÍSTRATE" action="registro" />
                        </h:form>
                    </div>
                    <div id="objetivos">
                        <h1>Objetivos Principales</h1>
                        <div id="objetivo1">
                            <img src="img/queConseguiras/1.jpg" alt />
                            <span>Si tu objetivo es aumentar tu rendimiento, desde DeltaFit te ayudaremos a lograr el rendimiento que deseas en tu deporte.</span>
                        </div>
                        <div id="objetivo2">
                            <img src="img/queConseguiras/2.jpg" alt />
                            <span>Si no estas contento con tu peso, DeltaFit te ayuda a encontrar tu peso ideal, ya sea aumentandolo, o disminuyéndolo.</span>
                        </div>
                        <div id="objetivo3">
                            <img src="img/queConseguiras/3.jpg" alt />
                            <span>Si tu objetivo es mejorar tu salud, desde DeltaFit y nuestros planes deportivos, te ayudaremos a mejorar tanto la salud física como mental.</span>
                        </div>
                        <div id="objetivo4">
                            <img src="img/queConseguiras/4.png" alt />
                            <span>Con nosotros podrás conseguir el objetivo que siempre quisiste, esta vez nosotros te ayudamos a conseguirlo.</span>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>
