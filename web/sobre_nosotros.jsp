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
            <link rel="stylesheet" href="css/sobre_nosotros.css">
            <script src="js/jquery.js"></script>
        </head>
        <body>
            <div id="contenedor">

                <jsp:include page="/include/menu.jsp" />
                <div id="contenido">
                    <div id="cabecera">
                        <img src="img/5.png" alt="" />
                        <h:form id="registro" prependId="false">
                            <h:commandLink styleClass="registro" value="REGÍSTRATE" action="registro" />
                        </h:form>
                    </div>
                    <h1>Sobre Nosotros</h1>
                    <div id="quienes_somos">
                        <h1>Quienes Somos</h1>
                        <p>Un equipo completamente a tu disposición que te ayudará a cumplir cualquier objetivo deportivo que te plantees.</p>
                        <p>No prometemos resultados rápidos y milagrosos que luego nunca llegan, sino resultados reales que con trabajo y constancia acabarán llegando. Ya que como se suele decir...</p>
                        <span>"Roma no se hizo en dos días"</span>
                    </div>
                    <div id="filosofia">
                        <h1>Filosofía de Trabajo</h1>
                        <p>La salud, lo primero. Lo primero dentro de nuestro método de trabajo es la salud del cliente, siempre se brindará a cada usuario una alimentación y una rutina totalmente saludable.</p>
                        <p>Creemos que cualquier objetivo debe ser logrado sin sacrificar la salud de nuestros clientes, aunque esto implique mayor tiempo de trabajo.</p>
                    </div>
                    <div id="fases">
                        <h1>Fases de Trabajo</h1>
                        <div id="uno">
                            <div id="uno_izq">
                                <img src="img/uno.jpg" alt="" />
                                <p>Una vez registrado tendrás acceso a la plataforma y nuestro equipo de profesionales en primer lugar harán una evaluación del estado físico de la persona y su objetivo.</p>
                                <p>Una vez analizado empezarán a trabajar para personalizar totalmente a cada persona su preparación. Este proceso puede durar desde 1 a un máximo de 5 días laborables.</p>
                            </div>
                            <div id="uno_der">
                                <img src="img/der.png" alt="" />
                            </div>
                        </div>
                        <div id="dos">
                            <div id="dos_izq">
                                <img src="img/izq.png" alt="" />
                            </div>
                            <div id="dos_der">
                                <img src="img/dos.jpg" alt="" />
                                <p>Una vez completado este análisis, el usuario recibirá su preparación en la pestaña de asesoramientos dentro de la plataforma de Pegasus. A partir de entonces empezamos a trabajar mes a mes para cumplir el objetivo de cada usuario.</p>
                                <p>Mensualmente el usuario deberá añadir las tres fotos que pedimos en la pestaña de progreso para comprobar el avance que se está teniendo, además de añadir su peso.</p>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>
