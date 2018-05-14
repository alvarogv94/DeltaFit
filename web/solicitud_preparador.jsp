<%-- 
    Document   : solicitud_preparador
    Created on : 09-may-2018, 19:16:02
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
            <title>Solcitud Preparador Físico</title>
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <link rel="stylesheet" href="css/solicitud_preparador.css">
            <script src="js/jquery.js"></script>
            <script src="js/jquery.validate.min.js"/></script>
        <script src="js/additional-methods.min.js" /></script>
    <script>
        var email = $("#email").val();

        $(document).ready(function () {

            $("#formularioRegistro").validate({
                errorElement: "span",
                rules: {
                    nombre: {
                        required: true,
                        minlength: 4,
                        maxlength: 15
                    },
                    apellidos: {
                        required: true,
                        mixlength: 6,
                        maxlength: 25
                    },
                    localidad: {
                        required: true,
                        minlength: 3,
                        maxlength: 15
                    },
                    edad: {
                        required: true,
                        number: true,
                        min: 18,
                        max: 99
                    },
                    sexo: {
                        required: true
                    },
                    email: {
                        required: true,
                        remote: {
                            url: "CompruebaDatos",
                            type: "POST",
                            data: {
                                Email: function () {
                                    return $("#email").val();
                                }
                            },
                            success: function (data, status, xhr) {
                                if (data.Email) {
                                    $("#textoEmail").text("El email ya tiene una solicitud realizada.");
                                } else {
                                    $("#textoEmail").text("");
                                }
                            }
                        }
                    }, //email
                    titulacion: {
                        required: true,
                        minlength: 10,
                        maxlength: 200
                    },
                    experiencia: {
                        required: true,
                        minlength: 10,
                        maxlength: 100
                    },
                    sobre_ti: {
                        required: true,
                        minlength: 10,
                        maxlength: 100
                    }
                }, //RULES
                messages: {
                    nombre: {
                        required: "Nombre es obligatorio",
                        rangelength: "El nombre tiene que tener minimo 4 caracteres y maximo 15."
                    },
                    apellidos: {
                        required: "Apellido es obligatorio",
                        rangelength: "El Apellido tiene que tener minimo 4 caracteres y maximo 15."
                    },
                    localidad: {
                        required: "La localidad es obligatorio",
                        rangelength: "La localidad tiene un minimo de 3 caracteres y maximo 15"
                    },
                    edad: {
                        required: "La edad debe ser obligatoria",
                        range: "La edad esta comprendida entre 18 y 99."
                    },
                    sexo: {
                        required: "El sexo es obligatorio"
                    },
                    email: {
                        required: "El email debe ser obligatorio."
                    },
                    titulacion: {
                        required: "Es obligatorio el campo de titulacion",
                        rangelength: "La titulacion tiene un mínimo de 10 caracteres y un maximo de 200"
                    },
                    experiencia: {
                        required: "El campo de experiencia es obligatorio",
                        rangelength: "El campo de experiencia tiene un minimo de 10 caracteres y un maximo de 100"
                    },
                    sobre_ti: {
                        required: "El campo sobre tí es obligatorio",
                        rangelength: "El campo sobre ti tiene un minimo de 10 caracteres y un maximo de 200"
                    }
                }//MESSAGES
            });


        });

    </script>
</head>
<body>
    <div id="contenedor">
        <jsp:include page="/include/menu.jsp" />
        <p id="titulo">Solicitud para prepararte en preparador</p>
        <p id="resultado"><h:outputText styleClass="#{solicitudPreparador.clase}" value="#{solicitudPreparador.resultadoAlta}" /></p>
        <div id="contenido">
            <h:form id="formularioRegistro" prependId="false">
                <div id="datosPersonales">
                    <h1>Datos Personales</h1>
                    <div id="datosPersonalesIzquierda">
                        <p>
                            <h:outputLabel for="nombre">Nombre </h:outputLabel>
                            <h:inputText value="#{solicitudPreparador.solicitud.nombre}" 
                                         id="nombre">                                    
                            </h:inputText><br />
                            <span id="spanNombre"></span>
                        </p>
                        <p>
                            <h:outputLabel for="apellidos">Apellidos </h:outputLabel>
                            <h:inputText value="#{solicitudPreparador.solicitud.apellidos}" 
                                         id="apellidos">                                    
                            </h:inputText><br />
                        </p>
                        <p>
                            <h:outputLabel for="localidad">Localidad </h:outputLabel>
                            <h:inputText value="#{solicitudPreparador.solicitud.localidad}" 
                                         id="localidad">                                    
                            </h:inputText><br />
                        </p>
                    </div> 
                    <div id="datosPersonalesDerecha">
                        <p>
                            <h:outputLabel for="edad">Edad </h:outputLabel>
                            <h:inputText value="#{solicitudPreparador.solicitud.edad}" 
                                         id="edad">
                            </h:inputText><br />
                        </p>
                        <h:selectOneRadio value="#{solicitudPreparador.solicitud.sexo}" 
                                          id="sexo">
                            <f:selectItem itemValue="h" itemLabel="Hombre" />
                            <f:selectItem itemValue="m" itemLabel="Mujer" />
                        </h:selectOneRadio>
                        <p>
                            <h:outputLabel for="email">Email </h:outputLabel>                                 
                            <h:inputText value="#{solicitudPreparador.solicitud.email}" 
                                         id="email">                                    
                            </h:inputText><br />
                            <span id="textoEmail"></span>
                        </p>                            
                    </div> 
                </div>
                <div id="datos">
                    <p>
                        <h:outputLabel for="titulacion">Titulación </h:outputLabel>
                        <h:inputTextarea value="#{solicitudPreparador.solicitud.titulacion}"
                                         id="titulacion">
                        </h:inputTextarea><br /> 
                    </p>
                    <p>
                        <h:outputLabel for="deporte">Elige sobre que actividad deseas ser preparador </h:outputLabel>
                        <h:selectOneMenu value="#{solicitudPreparador.solicitud.especialidad}"
                                         id="deporte">  
                            <f:selectItems value="#{solicitudPreparador.listaDeporte}" />
                        </h:selectOneMenu> <br /> 
                    </p>
                    <p>
                        <h:outputLabel for="experiencia">Experiencia </h:outputLabel>
                        <h:inputTextarea value="#{solicitudPreparador.solicitud.experiencia}"
                                         id="experiencia">
                        </h:inputTextarea><br /> 
                    </p>
                    <p>
                        <h:outputLabel for="sobreti">Dinos algo sobre tí </h:outputLabel>
                        <h:inputTextarea value="#{solicitudPreparador.solicitud.sobreTi}"
                                         id="sobreti">
                        </h:inputTextarea><br /> 
                    </p>
                </div>
                <p id="boton">
                    <h:commandButton value="Entrar" styleClass="btn" action="#{solicitudPreparador.registro()}"/>
                </p>
            </h:form>
        </div>
    </div>
    <jsp:include page="/include/pie.jsp" />
</body>
</html>
</f:view>
