<%-- 
    Document   : perfil
    Created on : 08-may-2018, 19:33:12
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
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <link rel="stylesheet" href="css/perfil.css">
            <link rel="stylesheet" href="css/jquery-ui.min.css">
            <script src="js/jquery.js"></script>
            <script src="js/jquery-ui.min.js"></script>
            <script src="js/Chart.bundle.min.js"></script>
            <script>
                $(document).ready(function () {
                    $("#der").click(function () {
                        $("#menuPer").slideToggle("fast");
                    });

                    $("#resumen").accordion();
                    $("#tabs").tabs();

                    var lab = [1500, 1600, 1700, 1750, 1800, 1850, 1900, 1950, 1999];
                    var datos = [85.7, 82.5, 91.7, 78.1, 76.7];

                    new Chart(document.getElementById("line-chart"), {
                        type: 'line',
                        data: {
                            labels: lab,
                            datasets: [{
                                    data: datos,
                                    label: "Peso",
                                    borderColor: "#3e95cd",
                                    fill: false
                                }
                            ]
                        },
                        options: {
                            title: {
                                display: true,
                                text: 'Progresión de mi Peso'
                            }
                        }
                    });

                    $("#spinner").spinner();
                    $("#dialog").dialog({
                        autoOpen: false,
                        width: 400,
                        buttons: [
                            {
                                text: "Ok",
                                click: function () {
                                    $(this).dialog("close");
                                }
                            },
                            {
                                text: "Cancel",
                                click: function () {
                                    $(this).dialog("close");
                                }
                            }
                        ]
                    });

                    // Link to open the dialog
                    $("#dialog-link").click(function (event) {
                        $("#dialog").dialog("open");
                        event.preventDefault();
                    });
                });

            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menuPerfil.jsp" />
                <div id="contenido">
                    <h1>Resumen</h1>
                    <c:choose>
                        <c:when test = "${sessionScope.tipoUsuario eq 'atleta'}">
                            <div id="resumen">
                                <h3>Plan de Entrenamiento Actual</h3>
                                <div>
                                    <h2 class="demoHeaders">Días de entrenamiento</h2>
                                    <div id="tabs">
                                        <ul>
                                            <li><a href="#tabs-1">Día 1</a></li>
                                            <li><a href="#tabs-2">Día 2</a></li>
                                            <li><a href="#tabs-3">Día 3</a></li>
                                            <li><a href="#tabs-4">Día 4</a></li>
                                            <li><a href="#tabs-5">Día 5</a></li>
                                        </ul>
                                        <div id="tabs-1"></div>
                                        <div id="tabs-2"></div>
                                        <div id="tabs-3"></div>
                                        <div id="tabs-4"></div>
                                        <div id="tabs-5"></div>
                                    </div>
                                </div>
                                <h3>Mi Progresión</h3>
                                <div id="grafico">
                                    <canvas id="line-chart" width="700" height="150"></canvas>
                                    <button id="dialog-link" class="ui-button ui-corner-all ui-widget">
                                        <h:graphicImage value="/img/icons/add.png" />Añadir Peso
                                    </button>
                                    <div id="dialog" title="Confirmación alta de Peso">
                                        <input id="spinner">
                                    </div>
                                </div>
                                <h3>Mensajes con mi Preparador</h3>
                                <div>
                                    <p>Aquí irá los mensajes sin leer</p>
                                </div>
                            </div>

                        </c:when>

                        <c:when test = "${sessionScope.tipoUsuario eq 'preparador'}">

                        </c:when>

                        <c:otherwise>
                            <p>Error al cargar tu Perfil</p> 
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>
