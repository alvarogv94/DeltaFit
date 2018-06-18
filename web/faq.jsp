<%-- 
    Document   : faq
    Created on : 04-may-2018, 22:16:14
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
            <link rel="stylesheet" href="css/faq.css">
            <script src="js/jquery.js"></script>
        </head>
        <body>
            <div id="contenedor">

                <jsp:include page="/include/menu.jsp" />
                <div id="contenido">
                    <div id="cabecera">
                        <img src="img/6.png" alt="" />
                        <h:form id="registro" prependId="false">
                            <h:commandLink styleClass="registro" value="REGÍSTRATE" action="registro" />
                        </h:form>
                    </div>
                    <h1>FAQ - Preguntas y Respuestas Comunes</h1>
                    <div class="pregunta">
                        <p class="titulo">¿EN CUANTO CONSEGUIRÉ MI OBJETIVO?</p>
                        <p>Depende de la persona y de su disciplina. Nuestros profesionales pueden estimar en cuanto tiempo se puede lograr tu objetivo, pero también necesitan del compromiso y de la disciplina del usuario. Si el usuario cumple, el tiempo también.</p>
                    </div>
                    <div class="pregunta">
                        <p class="titulo">¿CUANDO EMPEZARÉ A VER RESULTADOS?</p>	
                        <p>Una vez más, depende de la persona y de su disciplina. Por lo general en 2/3 meses empezarás a ver tus primeros progresos visibles, pero esto es una meta a largo plazo, transformar un físico requiere de tiempo, pero dentro de un año agradecerás haber empezado hoy.</p>
                    </div>
                    <div class="pregunta">
                        <p class="titulo">¿SOY ADOLESCENTE, SI HAGO PESAS ME QUEDARÉ PEQUEÑO?</p>
                        <p>No, no te vas a quedar pequeño. Esto es un mito popular desmentido científicamente. Nosotros adaptaremos la rutina a tu edad y a los esfuerzos que puedes hacer.</p>
                    </div>
                    <div class="pregunta">
                        <p class="titulo">¿SOY CHICA, SI HAGO PESAS ME PONDRÉ COMO UN HOMBRE?</p>
                        <p>Claramente no, es más hará que te veas aún más femenina ya que moldearán y realzarán tus curvas. Las mujeres tienen niveles muy bajos de testosterona, y por mucho que hagan pesas nunca podrán adquirir la apariencia de un hombre.</p>
                    </div>
                    <div class="pregunta">
                        <p class="titulo">¿DONDE ESTARÁ MI PREPARACIÓN?</p>
                        <p>Dentro de la plataforma en tu Perfil.</p>
                    </div>
                    <div class="pregunta">
                        <p class="titulo">¿PUEDO SALTARME LA DIETA ALGÚN DÍA?</p>	
                        <p>Nuestros preparadores te indicarán cuando puedes hacer una comida libre sin que influya en tus resultados. El resto de veces que lo haga el usuario es responsabilidad suya y depende de lo comprometido que quiera estar con sus resultados.</p>
                    </div>
                </div>
            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>

