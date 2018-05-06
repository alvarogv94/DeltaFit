<%-- 
    Document   : politica_cookies
    Created on : 04-may-2018, 22:16:38
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
            <title>Login</title>
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <script src="js/jquery.js"></script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menu.jsp" />
                <div id="contenido">
                    <h1>POLITICA DE COOKIES</h1>
                    <p>Cookie es un fichero que se descarga en su ordenador al acceder a determinadas páginas web. Las cookies permiten a una página web, entre otras cosas, almacenar y recuperar información sobre los hábitos de navegación de un usuario o de su equipo y, dependiendo de la información que contengan y de la forma en que utilice su equipo, pueden utilizarse para reconocer al usuario.. El navegador del usuario memoriza cookies en el disco duro solamente durante la sesión actual ocupando un espacio de memoria mínimo y no perjudicando al ordenador. Las cookies no contienen ninguna clase de información personal específica, y la mayoría de las mismas se borran del disco duro al finalizar la sesión de navegador (las denominadas cookies de sesión).
                        La mayoría de los navegadores aceptan como estándar a las cookies y, con independencia de las mismas, permiten o impiden en los ajustes de seguridad las cookies temporales o memorizadas.
                        Sin su expreso consentimiento –mediante la activación de las cookies en su navegador–XXXXX no enlazará en las cookies los datos memorizados con sus datos personales proporcionados en el momento del registro o la compra.</p>

                    <h1>¿Qué tipos de cookies utiliza esta página web? </h1>
                    <ul>
                        <li><span class="negrita_subrayado">Cookies técnicas:</span> Son aquéllas que permiten al usuario la navegación a través de una página web, plataforma o aplicación y la utilización de las diferentes opciones o servicios que en ella existan como, por ejemplo, controlar el tráfico y la comunicación de datos, identificar la sesión, acceder a partes de acceso restringido, recordar los elementos que integran un pedido, realizar el proceso de compra de un pedido, realizar la solicitud de inscripción o participación en un evento, utilizar elementos de seguridad durante la navegación, almacenar contenidos para la difusión de videos o sonido o compartir contenidos a través de redes sociales. </li>

                        <li><span class="negrita_subrayado">Cookies de personalización:</span> Son aquéllas que permiten al usuario acceder al servicio con algunas características de carácter general predefinidas en función de una serie de criterios en el terminal del usuario como por ejemplo serian el idioma, el tipo de navegador a través del cual accede al servicio, la configuración regional desde donde accede al servicio, etc.</li>

                        <li><span class="negrita_subrayado">Cookies de análisis: </span>Son aquéllas que bien tratadas por nosotros o por terceros, nos permiten cuantificar el número de usuarios y así realizar la medición y análisis estadístico de la utilización que hacen los usuarios del servicio ofertado. Para ello se analiza su navegación en nuestra página web con el fin de mejorar la oferta de productos o servicios que le ofrecemos.</li>
                    </ul>

                    <p><span class="negrita_subrayado">El Usuario acepta expresamente, por la utilización de este Site, 
                            el tratamiento de la información recabada en la forma y con los fines anteriormente mencionados.</span> 
                        Y así mismo reconoce conocer la posibilidad de rechazar el tratamiento de tales datos o información rechazando 
                        el uso de Cookies mediante la selección de la configuración apropiada a tal fin en su navegador. Si bien esta 
                        opción de bloqueo de Cookies en su navegador puede no permitirle el uso pleno de todas las funcionalidades del Website.

                        Puede usted permitir, bloquear o eliminar las cookies instaladas en su equipo mediante la configuración de las opciones del navegador instalado en su ordenador: </p>
                    <ul id="lista_navegadores">
                        <li><a href="https://support.google.com/chrome/answer/95647?hl=es">Chrome</a></li>
                        <li><a href="https://support.microsoft.com/es-es/help/17442/windows-internet-explorer-delete-manage-cookies">Internet Explorer</a></li>
                        <li><a href="https://support.mozilla.org/es/kb/habilitar-y-deshabilitar-cookies-sitios-web-rastrear-preferencias?redirectlocale=es&redirectslug=habilitar-y-deshabilitar-cookies-que-los-sitios-we">Firefox</a></li>
                        <li><a href="https://support.apple.com/kb/PH21411?locale=es_ES">Safari</a></li>
                    </ul>
                    <p>Si tiene dudas sobre esta política de cookies, puede contactar con XXXXX en info@XXXXX(punto)com </p>
                    <h1>Garantía de cumplimiento de la ley</h1>

                    <p>El modelo de texto mostrado ha intentado seguir todas las directrices de la AGPD, 
                        pero como he manifestado anteriormente en este post, el hacerlo así o con este texto a día de 
                        hoy no puede garantizar en modo alguno que se esté cumpliendo la ley de cookies de acuerdo con la 
                        interpretación de la AGPD (aunque entiendo que es lo más aproximado). Para ello habrá que esperar a 
                        las aclaraciones que sin duda nos proporcionará próximamente la AGPD.</p>
                </div>
            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>
