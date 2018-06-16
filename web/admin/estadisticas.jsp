<%-- 
    Document   : estadisticas
    Created on : 13-jun-2018, 19:41:50
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
            <title>Estadísticas</title>
            <link rel="stylesheet" href="../css/reseteo.css">
            <link rel="stylesheet" href="../css/font.css">
            <link rel="stylesheet" href="../css/comun.css">
            <link rel="stylesheet" href="../css/admin/menu.css">
            <link rel="stylesheet" href="../css/admin/estadisticas.css">
            <link rel="stylesheet" href="../css/jquery-ui.min.css">
            <script src="../js/jquery.js"></script>
            <script src="../js/jquery-ui.min.js"></script>
            <script src="../js/Chart.bundle.min.js"></script>  
            <script>
                $(document).ready(function () {

                    //Peticion para grafico de visitas
                    var datosVisitas = [];
                    $.ajax({
                        dataType: "json",
                        url: "../CargaEstadisticas",
                        async: true,
                        type: "POST",
                        data: {Visita: "0"},
                        success: getDatosVisitas,
                        error: function (datos, status, xhr) {
                            console.log("error en la peticion:" + xhr)
                        }
                    }); //Fin peticion

                    function getDatosVisitas(datos, status, xhr) {
                        var longitud = Object.keys(datos);
                        for (var i = 0; i < longitud.length; i++) {
                            datosVisitas[i] = datos[longitud[i]];
                        }
                        /*Carga de grafico visitas*/
                        new Chart(document.getElementById("doughnut"), {
                            type: 'doughnut',
                            data: {
                                labels: ["0-10min", "10-20min", "Más de 20m"],
                                datasets: [{
                                        data: datosVisitas,
                                        backgroundColor: [
                                            "red",
                                            "blue",
                                            "green"
                                        ],
                                        fill: false
                                    }
                                ]
                            },
                            options: {
                                title: {
                                    display: true,
                                    text: 'Tiempo que pasan los usuarios en la aplicación'
                                }
                            }
                        });//Fin carga grafico
                    }
                    var daotsUsuarios = [];

                    //Peticion para tipo de usuarios
                    $.ajax({
                        dataType: "json",
                        url: "../CargaEstadisticas2",
                        async: true,
                        type: "POST",
                        data: {Usuario: "0"},
                        success: getDatosUsuarios,
                        error: function (datos, status, xhr) {
                            console.log("error en la peticion:" + xhr)
                        }
                    }); //Fin peticion

                    function getDatosUsuarios(datos, status, xhr) {
                        var longitud = Object.keys(datos);
                        for (var i = 0; i < longitud.length; i++) {
                            daotsUsuarios[i] = datos[longitud[i]];
                        }


                        /*Carga de grafico visitas*/
                        var datosGrafico = {
                            labels: [
                                "Gratis",
                                "Medio",
                                "Pro"
                            ],
                            datasets: [
                                {
                                    label: "Grafico",
                                    data: daotsUsuarios,
                                    backgroundColor: ["blue", "green", "yellow"],
                                    hoverBackgroundColor: ["grey", "grey", "grey"]
                                }]
                        };
                        new Chart(document.getElementById("vertical"), {
                            type: 'horizontalBar',
                            data: datosGrafico,
                            options: {
                                title: {
                                    display: true,
                                    text: 'Numero de usuarios por Tipo'
                                },
                                scales: {
                                    xAxes: [{
                                            ticks: {
                                                min: 0
                                            }
                                        }],
                                    yAxes: [{
                                            stacked: true
                                        }]
                                }
                            }
                        });//Fin carga grafico
                    }

                });
            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="../include/menuAdmin.jsp" />
                <div id="datos">
                    <div id="grafico1">
                        <canvas id="doughnut" width="700" height="150"></canvas>
                    </div>
                    <div id="grafico2">
                        <canvas id="vertical" width="700" height="150"></canvas>
                    </div>
                </div>
            </div>
        </body>
    </html>
</f:view>
