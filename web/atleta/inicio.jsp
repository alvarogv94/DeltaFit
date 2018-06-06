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
            <link rel="stylesheet" href="../css/atleta/inicio.css">
            <link rel="stylesheet" href="../css/jquery-ui.min.css">
            <script src="../js/jquery.js"></script>
            <script src="../js/jquery-ui.min.js"></script>
            <script src="../js/Chart.bundle.min.js"></script>     

            <script>
                $(document).ready(function () {
                    //Click en el icono de tu perfil para desplegar el menu
                    $("#der").click(function () {
                        $("#menuPer").slideToggle("fast");
                    });
                    /*Acordeon de atleta y preparador*/
                    $("#resumen").accordion();
                    /*tabs del atleta*/
                    $("#tabs").tabs();
                    $("#tabs2").tabs();

                    /*Grafico de peso*/
                    /*Peticion AJAXpara obtener todos los pesos de usuario*/
                    var lab = [];
                    var pesos = [];
                    $.ajax({
                        dataType: "json",
                        url: "../CargaGrafico",
                        async: true,
                        type: "POST",
                        data: {DatosPeso: "0"},
                        success: getDatos,
                        error: function (datos, status, xhr) {
                            console.log("error en la peticion:" + xhr)
                        }
                    });
                    /*function que llama la peticion ajax que obtiene todos los pesos del usuario
                     * y los guarda en 2 array, el primer array almacenamos los pesos
                     * y el segundo array los meses*/
                    function getDatos(datos, status, xhr) {
                        var longitud = Object.keys(datos);

                        longitud.sort();
                        for (var i = 0; i < longitud.length; i++) {

                            var mes = dameMes(longitud[i].split("/")[1]);
                            lab[i] = mes;
                            pesos[i] = datos[longitud[i]];
                        }
                    }

                    function dameMes(cadena) {
                        var mes;

                        if (cadena == "1") {
                            mes = "Enero";
                        }
                        if (cadena == "2") {
                            mes = "Febrero";
                        }
                        if (cadena == "3") {
                            mes = "Marzo";
                        }
                        if (cadena == "4") {
                            mes = "Abril";
                        }
                        if (cadena == "5") {
                            mes = "Mayo";
                        }
                        if (cadena == "6") {
                            mes = "Junio";
                        }
                        if (cadena == "7") {
                            mes = "Julio";
                        }
                        if (cadena == "8") {
                            mes = "Agosto";
                        }
                        if (cadena == "9") {
                            mes = "Septiembre";
                        }
                        if (cadena == "10") {
                            mes = "Octubre";
                        }
                        if (cadena == "11") {
                            mes = "Noviembre";
                        }
                        if (cadena == "12") {
                            mes = "Diciembre";
                        }

                        return mes;
                    }

                    /*Ventana de dialogo que aparece cuando añadimos un peso*/
                    $("#dialog").dialog({
                        autoOpen: false,
                        width: 400,
                        buttons: [
                            {
                                text: "Ok",
                                click: function () {

                                    var kilos = $("#spinner").val();
                                    var gramos = $("#gramos").val();

                                    var peso = kilos + "." + gramos;
                                    //Comprobamos que haya introducido algún peso
                                    if (peso == "") {
                                        $("#dialog").append("<p id ='errorPeso' style='color: red;'>Debes indicar algún peso</p>")
                                    } else {
                                        //peticion ajax para dar de alta el peso
                                        $.ajax({
                                            dataType: "json",
                                            url: "../AltaPeso",
                                            async: true,
                                            type: "POST",
                                            data: {Peso: peso},
                                            success: altaPeso,
                                            error: function (datos, status, xhr) {
                                                console.log("error en la peticion:" + xhr)
                                            }
                                        });

                                        function altaPeso() {

                                            //Obtenemos el valor de la longitud de las etiquetas
                                            var i = grafico.data.labels.length;

                                            var date = new Date();
                                            var mes = dameMes(date.getMonth() + 1);
                                            grafico.data.labels[i] = mes;

                                            //Obtenemos el valor de la longitud de los datos
                                            var j = Object.keys(grafico.data.datasets[0].data).length;
                                            grafico.data.datasets[0].data[j] = peso;
                                            grafico.update();
                                            $("#errorPeso").remove();
                                        } //Fin alta peso
                                        $(this).dialog("close");
                                    }
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
                    $("#gramos").attr("placeholder", "Gramos");

                    /*Carga de grafico*/
                    var grafico = new Chart(document.getElementById("line-chart"), {
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
                });
            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menuPerfil.jsp" />
                <div id="contenido">
                    <h1>Resumen</h1>
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
                            <h2 class="demoHeaders">Dieta</h2>

                            <div id="tabs2">
                                <ul>
                                    <li><a href="#tabs-1">Desayuno</a></li>
                                    <li><a href="#tabs-2">Media Mañana</a></li>
                                    <li><a href="#tabs-3">Almuerzo</a></li>
                                    <li><a href="#tabs-4">Pre Entreno</a></li>
                                    <li><a href="#tabs-5">Post Entreno</a></li>
                                    <li><a href="#tabs-6">Cena</a></li>
                                </ul>
                                <div id="tabs-1"></div>
                                <div id="tabs-2"></div>
                                <div id="tabs-3"></div>
                                <div id="tabs-4"></div>
                                <div id="tabs-5"></div>
                                <div id="tabs-6"></div>
                            </div>
                        </div>
                        <h3>Mi Progresión</h3>
                        <div id="grafico">
                            <canvas id="line-chart" width="700" height="150"></canvas>
                            <button id="dialog-link" class="ui-button ui-corner-all ui-widget">
                                <span class="ui-icon ui-icon-circle-plus"></span>Añadir Peso
                            </button>
                            <div id="dialog" title="Confirmación alta de Peso">
                                <input id="spinner"><input id="gramos" value="0">                                        
                                <p id="textoDialogo">Si añades varios pesos en el mismo mes, estos no serán sustituídos, podrás añadir varios pesos en un mismo mes.</p>
                            </div>
                        </div>
                        <h3>Mensajes con mi Preparador</h3>
                        <div>
                            <p>Aquí irá los mensajes sin leer</p>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/include/pie2.jsp" />        
        </body>
    </html>
</f:view>
