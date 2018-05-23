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
            <title>Perfil Personal</title>
            <link rel="stylesheet" href="../css/reseteo.css">
            <link rel="stylesheet" href="../css/font.css">
            <link rel="stylesheet" href="../css/comun.css">
            <link rel="stylesheet" href="../css/perfil.css">
            <link rel="stylesheet" href="../css/preparador/inicio.css">  
            <link rel="stylesheet" href="../css/jquery-ui.min.css">
            <link rel="stylesheet" href="../css/jquery.e-calendar.css">                      
            <script src="../js/jquery.js"></script>
            <script src="../js/jquery-ui.min.js"></script>
            <script src="../js/jquery.e-calendar.js"></script>

            <script>
                $(document).ready(function () {
                    //Click en el icono de tu perfil para desplegar el menu
                    $("#logo_pie img").attr("src", "../img/logo.png");

                    $("#der").click(function () {
                        $("#menuPer").slideToggle("fast");
                    });
                    /*Acordeon*/
                    $("#resumen").accordion();

                    var eventos = [
                        {title: 'Event Title 1', description: 'Description 1', datetime: new Date(2018, 4, 12)},
                        {title: 'Event Title 2', description: 'Description 2', datetime: new Date(2018, 4, 23)}
                    ];

                    $('#calendar').eCalendar({
                        weekDays: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
                        months: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                            'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                        textArrows: {previous: '<', next: '>'},
                        eventTitle: 'Preparaciones Para Hoy',
                        url: '',
                        events: eventos,
                        firstDayOfWeek: 1
                    });

                });
            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menuPerfil.jsp" />
                <div id="contenido">
                    <h1>Resumen</h1>
                    <div id="resumen">
                        <h3>Mi Calendario</h3>
                        <div id="calendario">
                            <div id="calendar"></div>
                        </div>
                        <h3>Mis Atletas</h3>
                        <div>
                            <p>
                                Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet
                                purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor
                                velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In
                                suscipit faucibus urna.
                            </p>
                        </div>

                    </div>
                </div>
            </div>
            <jsp:include page="/include/pie.jsp" />        
        </body>
    </html>
</f:view>
