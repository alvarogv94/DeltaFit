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
            <link rel="stylesheet" href="css/atleta.css">
            <link rel="stylesheet" href="css/jquery-ui.min.css">
            <script src="js/jquery.js"></script>
            <script src="js/jquery-ui.min.js"></script>
            <script src="js/Chart.bundle.min.js"></script>
            <script>
                $(document).ready(function () {
                    $("#der").click(function () {
                        $("#menuPer").slideToggle("fast");
                    });
                    /*Acordeon atleta*/
                    $("#resumen").accordion();
                    /*tabs del atleta*/
                    $("#tabs").tabs();

                    /*Grafico de peso*/
                    /*Peticion AJAXpara obtener todos los pesos de usuario*/
                    var lab = [];
                    var pesos = [];
                    $.ajax({
                        dataType: "json",
                        url: "CargaGrafico",
                        async: true,
                        type: "POST",
                        data: {DatosPeso: "0"},
                        success: getDatos,
                        error: function (datos, status, xhr) {
                            alert("error en la peticion:" + xhr)
                        }
                    });
                    function getDatos(datos, status, xhr) {
                        var longitud = Object.keys(datos);

                        for (var i = 0; i < longitud.length; i++) {
                            var mes;

                            if (longitud[i] == "1") {
                                mes = "Enero";
                            }
                            if (longitud[i] == "2") {
                                mes = "Febrero";
                            }
                            if (longitud[i] == "3") {
                                mes = "Marzo";
                            }
                            if (longitud[i] == "4") {
                                mes = "Abril";
                            }
                            if (longitud[i] == "5") {
                                mes = "Mayo";
                            }
                            if (longitud[i] == "6") {
                                mes = "Junio";
                            }
                            if (longitud[i] == "7") {
                                mes = "Julio";
                            }
                            if (longitud[i] == "8") {
                                mes = "Agosto";
                            }
                            if (longitud[i] == "9") {
                                mes = "Septiembre";
                            }
                            if (longitud[i] == "10") {
                                mes = "Octubre";
                            }
                            if (longitud[i] == "11") {
                                mes = "Noviembre";
                            }
                            if (longitud[i] == "12") {
                                mes = "Diciembre";
                            }
                            lab[i] = mes;
                            pesos[i] = datos[longitud[i]];
                        }
                    }
                    
                    /*Carga de grafico*/
                    new Chart(document.getElementById("line-chart"), {
                        type: 'line',
                        data: {
                            labels: lab,
                            datasets: [{
                                    data: pesos,
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
                    });//Fin carga grafico

                    /*Ventana de dialogo que aparece cuando añadimos un peso*/
                    $("#dialog").dialog({
                        autoOpen: false,
                        width: 400,
                        buttons: [
                            {
                                text: "Ok",
                                click: function () {

                                    var peso = $("#spinner").val();
                                    alert(peso);
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
                    /*propiedades de los spinner*/
                    $("#spinner").spinner({min: 50, max: 200});
                    $("#spinner").attr("placeholder", "Kilos");
                    $("#gramos").spinner({min: 0, max: 9});
                    $("#gramos").attr("placeholder", "gramos");
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
                                        <span class="ui-icon ui-icon-circle-plus"></span>Añadir Peso
                                    </button>
                                    <div id="dialog" title="Confirmación alta de Peso">
                                        <input id="spinner"><input id="gramos">                                        
                                        <p id="textoDialogo">Recuerda, que si ya has añadido un peso este mes será sustituido por este.</p>

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
