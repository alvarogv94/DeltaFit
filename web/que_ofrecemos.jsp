<%-- 
    Document   : que_ofrecemos
    Created on : 04-may-2018, 22:14:33
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
            <title>¿Qué Ofrecemos?</title>
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <link rel="stylesheet" href="css/que_ofrecemos.css">

            <script src="js/jquery.js"></script>
        </head>
        <body>
            <div id="contenedor">

                <jsp:include page="/include/menu.jsp" />
                <div id="contenido">
                    <div id="cabecera">
                        <img src="img/4.png" alt="" />
                        <h:form id="registro" prependId="false">
                            <h:commandLink styleClass="registro" value="REGÍSTRATE" action="registro"></h:commandLink>
                        </h:form>
                    </div>
                    <div id="plan">
                        <h1>Planes de Entrenamiento Disponibles</h1>
                        <div id="plan1">
                            <h1>Gratuito</h1>
                            <p>Plan Gratuito. Va dirigido a quienes no necesitan una atención constante y les gusta entrenar por libre. Con este tipo de plan podrás acceder a una serie de planes de entrenamientos predefinidos, es decir, ya están creados por defecto, no tienen asignados ningún preparador. Podrás seguir tu progreso subiendo diferentes imágenes sobre tí, e ir viendo el cambio mes a mes, posibilidad de almacenar tu peso, y visualizarlo en forma de gráfica para una mejor interpretación.<br /><span>Precio: Gratuito</span></p>
                        </div>
                        <div id="plan2">
                            <h1>Intermedio</h1>
                            <p>Plan Intermedio. Va dirigido a quienes no son capaces de realizar un entrenamiento libre, se le asignará un preparador, que le guiará durante su estapa con nosotros, con rutinas de entrenamiento y dietas, y podrá comunicarse con él mediante un chat. Podrás seguir tu progreso subiendo diferentes imágenes sobre tí, e ir viendo el cambio mes a mes, posibilidad de almacenar tu peso, y visualizarlo en forma de gráfica para una mejor interpretación.<br /><span>Precio: 30€</span></p>
                        </div>
                        <div id="plan3">
                            <h1>Pro</h1>
                            <p>Plan Intermedio. Va dirigido a quienes no son capaces de realizar un entrenamiento libre, y además necesitan una atención mas estricta que los demás usuarios. Como los usuarios intermedios, se le asignará un preparador, que le guiará durante su estapa con nosotros, con rutinas de entrenamiento y dietas, y podrá comunicarse con él mediante un chat. Podrás seguir tu progreso subiendo diferentes imágenes sobre tí, e ir viendo el cambio mes a mes, posibilidad de almacenar tu peso, y visualizarlo en forma de gráfica para una mejor interpretación. <br /><span>Precio: 40€</span></p>
                        </div>
                    </div>
                </div>            
            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>
