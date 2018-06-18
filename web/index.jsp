<%-- 
    Document   : index
    Created on : 04-may-2018, 21:30:25
    Author     : Alvaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
    <html>
        <head>
            <meta charset="utf-8">
            <title>Inicio</title>
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <link rel="stylesheet" href="css/index.css">
            <script src="js/jquery.js"></script>
            <script src="js/responsiveslides.min.js"></script>	
            <script>
                $(document).ready(function () {
                    $(".rslides").responsiveSlides({
                        auto: true, // Boolean: Animate automatically, true or false
                        speed: 500, // Integer: Speed of the transition, in milliseconds
                        timeout: 4000, // Integer: Time between slide transitions, in milliseconds
                        pager: false, // Boolean: Show pager, true or false
                        nav: false, // Boolean: Show navigation, true or false
                        random: false, // Boolean: Randomize the order of the slides, true or false
                        pause: false, // Boolean: Pause on hover, true or false
                        pauseControls: true, // Boolean: Pause when hovering controls, true or false
                        prevText: "Previous", // String: Text for the "previous" button
                        nextText: "Next", // String: Text for the "next" button
                        maxwidth: "", // Integer: Max-width of the slideshow, in pixels
                        navContainer: "", // Selector: Where controls should be appended to, default is after the 'ul'
                        manualControls: "", // Selector: Declare custom pager navigation
                        namespace: "rslides", // String: Change the default namespace used
                        before: function () {
                        }, // Function: Before callback
                        after: function () {
                        }     // Function: After callback
                    });
                });
            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menu.jsp" />

                <div id="contenido">
                    <div id="slider">
                        <ul class="rslides">
                            <li><img src="img/slider/1.jpg" alt=""></li>
                            <li><img src="img/slider/2.jpg" alt=""></li>
                            <li><img src="img/slider/3.png" alt=""></li>
                        </ul>
                        <h:form id="registro" prependId="false">
                            <h:commandLink styleClass="registro" value="REGÍSTRATE" action="registro"></h:commandLink>
                        </h:form>
                    </div>

                    <div id="quienes_somos">
                        <h1>¿Quiénes somos?</h1>
                        <p>Somos asesores deportivos que no solo buscamos la mejora estética de un cuerpo, buscamos la satisfacción del atleta por haber conseguido el objetivo propuesto al trabajar con nosotros.</p>
                        <img src="img/index/izq.png" alt="" />
                        <h:form id="quienesSomos" prependId="false">
                            <h:commandLink styleClass="registro" value="Saber Más" action="sobre_nosotros" />
                        </h:form>
                    </div>
                    <div id="que_ofrecemos">
                        <h1>¿Qué ofrecemos?</h1>
                        <p>Ofrecemos distintos planes de entrenamientos que se adapten en todos los sentidos con el atleta, con un plan básico, intermedio o pro. El plan intermedio y pro es totalmente personalizado para el atleta.</p>
                        <img src="img/index/cen.png" alt="" />

                        <h:form id="queOfrecemos" prependId="false">
                            <h:commandLink styleClass="registro" value="Saber Más" action="que_ofrecemos" />
                        </h:form>
                    </div>
                    <div id="que_conseguiras">
                        <h1>¿Qué conseguirás?</h1>
                        <p>Conseguirás tu objetivo, ya sea pérdida o ganancia de peso, mejora del rendimiento y/o mejora de la salud, cualquier objetivo es bueno para una mayor calidad de vida.</p>
                        <img src="img/index/der.png" alt="" />
                        <h:form id="queConseguiras" prependId="false">
                            <h:commandLink styleClass="registro" value="Saber Más" action="que_conseguiras" />
                        </h:form>
                    </div>
                </div>

            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>
