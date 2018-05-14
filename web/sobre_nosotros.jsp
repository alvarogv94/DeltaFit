<%-- 
    Document   : sobre_nosotros
    Created on : 04-may-2018, 22:15:53
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
            <title>Sobre Nosotros</title>
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <script src="js/jquery.js"></script>
        </head>
        <body>
            <div id="contenedor">

                <jsp:include page="/include/menu.jsp" />
            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>
