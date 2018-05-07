<%-- 
    Document   : registro
    Created on : 06-may-2018, 11:59:45
    Author     : Alvaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html xmlns="http://www.w3.org/1999/xhtml"
          xmlns:h="http://xmlns.jcp.org/jsf/html"
          xmlns:pt="">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Registro</title>
            <link rel="stylesheet" href="css/reseteo.css">
            <link rel="stylesheet" href="css/font.css">
            <link rel="stylesheet" href="css/comun.css">
            <link rel="stylesheet" href="css/registro.css">
            <script src="js/jquery.js"></script>
            <script>
                $(document).ready(function () {
                    $("select").change(function () {
                        var valorSelect = $("select option:selected").val();
                        if (valorSelect == 'Otros') {
                            $("#objetivoOtro").slideDown("slow");
                        } else {
                            $("#objetivoOtro").slideUp("slow");
                        }
                    });

                    $('input[name="formularioRegistro:alergia"]').change(function () {
                        var valorRadio = $(this).val();
                        if (valorRadio == 'Si') {
                            $("#textoAlergia").slideDown("slow");
                        } else {
                            $("#textoAlergia").slideUp("slow");
                        }
                    });
                    $('input[name="formularioRegistro:comidaNoGusta"]').change(function () {
                        var valorRadio = $(this).val();
                        if (valorRadio == 'Si') {
                            $("#textoComida").slideDown("slow");
                        } else {
                            $("#textoComida").slideUp("slow");
                        }
                    });

                    $('input[name="formularioRegistro:enfermedad"]').change(function () {
                        var valorRadio = $(this).val();
                        if (valorRadio == 'Si') {
                            $("#textoEnfermedad").slideDown("slow");
                        } else {
                            $("#textoEnfermedad").slideUp("slow");
                        }
                    });

                    $('input[name="formularioRegistro:deporteComple"]').change(function () {
                        var valorRadio = $(this).val();
                        if (valorRadio == 'Si') {
                            $("#textoDeporteComple").slideDown("slow");
                        } else {
                            $("#textoDeporteComple").slideUp("slow");
                        }
                    });
                });
            </script>
        </head>
        <body>
            <div id="contenedor">
                <jsp:include page="/include/menu.jsp" />
                <p id="titulo">Registro de Usuario</p>
                <div id="contenido">
                    <h:form id="formularioRegistro">
                        <div id="datosPersonales">
                            <h1>Datos Personales</h1>
                            <div id="datosPersonalesIzquierda">
                                <p>
                                    <h:outputLabel for="nombre">Nombre </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.nombre}" 
                                                 id="nombre"
                                                 required="true"
                                                 requiredMessage="El campo del nombre es obligatorio"></h:inputText><br />
                                    <h:message id="reqnombre" for="nombre" style="color:red"/>
                                </p>
                                <p>
                                    <h:outputLabel for="apellidos">Apellidos </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.apellidos}" 
                                                 id="apellidos"
                                                 required="true"
                                                 requiredMessage="El campo de apellidos es obligatorio"></h:inputText><br />
                                    <h:message id="reqape" for="apellidos" style="color:red"/>
                                </p>
                                <h:selectOneRadio value="#{registro.atleta.sexo}" 
                                                  id="sexo"
                                                  required="true"
                                                  requiredMessage="El campo de sexo es obligatorio">
                                    <f:selectItem itemValue="h" itemLabel="Hombre" />
                                    <f:selectItem itemValue="m" itemLabel="Mujer" />
                                </h:selectOneRadio>
                                <p><h:message id="reqsexo" for="sexo" style="color:red"/></p>
                                <p>
                                    <h:outputLabel for="nombreUsu">Nombre de usuario </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.nomUsuario}" 
                                                 id="nombreUsu"
                                                 required="true"
                                                 requiredMessage="El campo del nombre de usuario es obligatorio"></h:inputText><br />
                                    <h:message id="reqnombreusu" for="nombreUsu" style="color:red"/>
                                </p>
                            </div> 
                            <div id="datosPersonalesDerecha">
                                <p>
                                    <h:outputLabel for="edad">Edad </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.edad}" 
                                                 id="edad" 
                                                 validatorMessage="La edad debe estar entre 16 y 120"
                                                 required="true"
                                                 requiredMessage="El campo de edad es obligatorio">
                                        <f:convertNumber minIntegerDigits="16" maxIntegerDigits="120" />
                                    </h:inputText><br />
                                    <h:message id="msgedad" for="edad" style="color:red" />
                                </p>
                                <p>
                                    <h:outputLabel for="localidad">Localidad </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.localidad}" 
                                                 id="localidad"
                                                 required="true"
                                                 requiredMessage="El campo de sexo es obligatorio"></h:inputText><br />
                                    <h:message id="msgloc" for="localidad" style="color:red"/>
                                </p>
                                <p>
                                    <h:outputLabel for="email">Email </h:outputLabel>                                 
                                    <h:inputText value="#{registro.atleta.email}" 
                                                 id="email"
                                                 required="true"
                                                 requiredMessage="El campo de email es obligatorio"></h:inputText><br />
                                    <h:message id="msgemail" for="email" style="color:red" />                                
                                </p>
                                <p>
                                    <h:outputLabel for="pass">Contraseña</h:outputLabel>
                                    <h:inputSecret value="#{registro.atleta.pass}" 
                                                   id="pass" 
                                                   required="true"
                                                   requiredMessage="El campo de contraseña es obligatorio">                                
                                    </h:inputSecret><br /><h:message id="reqpass" for="pass" style="color:red"/>
                                </p>
                                <p>
                                    <h:outputLabel for="pass2">Repite la contraseña</h:outputLabel>
                                    <h:inputSecret value="#{registro.atleta.pass}" 
                                                   id="pass2" 
                                                   required="true"
                                                   requiredMessage="El campo de contraseña es obligatorio">                                
                                    </h:inputSecret><br /><h:message id="reqpass2" for="pass2" style="color:red"/>
                                </p>
                            </div>                                  
                        </div>
                        <div id="datosFisicos">
                            <h1>Datos Físicos</h1>
                            <div id="datosFisicosIzq">
                                <p>
                                    <h:outputLabel for="altura">Altura </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.altura}" 
                                                 id="altura" 
                                                 validatorMessage="El campo de altura debe tener fomato 'X,XX'"
                                                 required="true"
                                                 requiredMessage="El campo de altura es obligatorio">
                                        <f:convertNumber pattern="#,##" />
                                    </h:inputText><br />
                                    <h:message id="msgaltura" for="altura" style="color:red" />
                                </p>
                                <p>
                                    <h:outputLabel for="peso">Peso </h:outputLabel>
                                    <h:inputText value="#{registro.atleta.pesoActual}" 
                                                 id="peso" 
                                                 validatorMessage="El campo de peso debe tener fomato 'XX,X'"
                                                 required="true"
                                                 requiredMessage="El campo de peso es obligatorio">
                                        <f:convertNumber pattern="##,#" />
                                    </h:inputText><br />
                                    <h:message id="msgpeso" for="peso" style="color:red" />
                                </p>
                                <p>
                                    <h:outputLabel for="deporte">Deporte </h:outputLabel>
                                    <h:selectOneMenu value="#{registro.atleta.deporte}"
                                                     id="deporte"
                                                     required="true"
                                                     requiredMessage="El campo de Deporte es obligatorio">                                                     
                                    </h:selectOneMenu> <br /> 
                                    <h:message id="msgdeporte" for="deporte" style="color:red" />
                                </p>
                            </div>
                            <div id="datosFisicosDer">
                                <p>
                                    <h:outputLabel for="objetivo">Objetivos </h:outputLabel>
                                    <h:selectOneMenu value="#{registro.atleta.objetivo}"
                                                     id="objetivo"
                                                     required="true"
                                                     requiredMessage="El campo de objetivo es obligatorio">    
                                        <f:selectItem itemValue = "1" itemLabel = "Selecciona un Objetivo" />                                        
                                        <f:selectItem itemValue = "Aumento de Rendimiento" itemLabel = "Aumento de Rendimiento" />
                                        <f:selectItem itemValue = "Bajada de Peso" itemLabel = "Bajada de Peso" />
                                        <f:selectItem itemValue = "Subida de Peso" itemLabel = "Subida de Peso" />
                                        <f:selectItem itemValue = "Entretenimiento" itemLabel = "Entretenimiento" />
                                        <f:selectItem itemValue = "Problemas de Salud" itemLabel = "Problemas de Salud, físico o mental" />
                                        <f:selectItem itemValue = "Otros" itemLabel = "Otros" />
                                    </h:selectOneMenu> <br /> 
                                    <h:message id="msgobjetivo" for="objetivo" style="color:red" />
                                </p>
                                <p id="objetivoOtro">
                                    <h:outputLabel for="objetivoOtro">Indicanos el objetivo 'Otros'</h:outputLabel>
                                    <h:inputText value="#{registro.atleta.objetivo}" 
                                                 id="objetivoOtro"> 
                                    </h:inputText>
                                </p>
                            </div>                            
                        </div>
                        <div id="datosPlan">
                            <h1>Datos para la planificación de su Plan Deportivo</h1>
                            <p>
                                <h:outputLabel for="planDeportivo">Selecciona tu Plan Deportivo</h:outputLabel>
                                <h:selectOneMenu value="#{registro.atleta.tipoUsuario}"
                                                 id="planDeportivo"
                                                 required="true"
                                                 requiredMessage="El campo de plan deportivo es obligatorio">    
                                    <f:selectItem itemValue = "0" itemLabel = "Selecciona un Plan Deportivo " />                                        
                                    <f:selectItem itemValue = "1" itemLabel = "Básico - Gratis" />
                                    <f:selectItem itemValue = "2" itemLabel = "Intermedio - 29,99€" />
                                    <f:selectItem itemValue = "3" itemLabel = "Pro - 39,99€" />
                                </h:selectOneMenu><br /> 
                                <h:message id="msgplandepor" for="planDeportivo" style="color:red" />            
                            </p>

                            <div id="datosPlanIzq">
                                <p>
                                    <h:outputLabel for="alergia">¿Tiene alergia alguna alergia?</h:outputLabel>
                                    <h:selectOneRadio value="#{registro.atleta.alergia}"
                                                      id="alergia"
                                                      required="true"
                                                      requiredMessage="El campo alergia es obligatorio">
                                        <f:selectItem itemValue = "Si" itemLabel = "Si" />                                            
                                        <f:selectItem itemValue = "No" itemLabel = "No" />                                            
                                    </h:selectOneRadio><br /> 
                                    <h:message id="msgalergia" for="alergia" style="color:red" />                                      
                                </p>
                                <p id="textoAlergia">
                                    <h:outputLabel for="textAlergia">Indicanos su alergia</h:outputLabel>
                                    <h:inputText value="#{registro.atleta.alergia}" 
                                                 id="textAlergia"> 
                                    </h:inputText>
                                </p>

                                <p>
                                    <h:outputLabel for="comidaNoGusta">¿Hay alguna comida que no le guste?</h:outputLabel>
                                    <h:selectOneRadio value="#{registro.atleta.comidaNoGusta}"
                                                      id="comidaNoGusta"
                                                      required="true"
                                                      requiredMessage="El campo de comida que no le guste es obligatorio">
                                        <f:selectItem itemValue = "Si" itemLabel = "Si" />                                            
                                        <f:selectItem itemValue = "No" itemLabel = "No" />                                            
                                    </h:selectOneRadio><br /> 
                                    <h:message id="msgcomidanogusta" for="comidaNoGusta" style="color:red" />                                      
                                </p>
                                <p id="textoComida">
                                    <h:outputLabel for="textComida">Indicanos la comida que no le gusta</h:outputLabel>
                                    <h:inputText value="#{registro.atleta.comidaNoGusta}" 
                                                 id="textComida"> 
                                    </h:inputText>
                                </p>
                                <p>
                                    <h:outputLabel for="enfermedad">¿Padece alguna enfermedad que le dificulte la práctica de alguna actividad física?</h:outputLabel>
                                    <h:selectOneRadio value="#{registro.atleta.enfermedad}"
                                                      id="enfermedad"
                                                      required="true"
                                                      requiredMessage="El campo de enfermedad es obligatorio">
                                        <f:selectItem itemValue = "Si" itemLabel = "Si" />                                            
                                        <f:selectItem itemValue = "No" itemLabel = "No" /><br /> 
                                        <h:message id="msgenfermedad" for="enfermedad" style="color:red" />                                              
                                    </h:selectOneRadio>                                    
                                </p>
                                <p id="textoEnfermedad">
                                    <h:outputLabel for="textEnfermedad">Indicanos dicha enfermedad</h:outputLabel>
                                    <h:inputText value="#{registro.atleta.enfermedad}" 
                                                 id="textEnfermedad"> 
                                    </h:inputText>
                                </p>

                            </div>
                            <div id="datosPlanDer">
                                <p>
                                    <h:outputLabel for="deporteComple">¿Desea complementar el deporte seleccionado con otro?</h:outputLabel>
                                    <h:selectOneRadio value="#{registro.atleta.deporteComplementado}"
                                                      id="deporteComple"
                                                      required="true"
                                                      requiredMessage="El campo de deporte complementario es obligatorio">
                                        <f:selectItem itemValue = "Si" itemLabel = "Si" />                                            
                                        <f:selectItem itemValue = "No" itemLabel = "No" />                                            
                                    </h:selectOneRadio><br />   
                                    <h:message id="msgdeportecomp" for="textDeporteComple" style="color:red" />
                                </p>
                                <p id="textoDeporteComple">
                                    <h:outputLabel for="textDeporteComple">Selecciona el deporte que quieres complementar</h:outputLabel>
                                    <h:selectOneMenu value="#{registro.atleta.deporteComplementado}"
                                                     id="textDeporteComple">                                                     
                                    </h:selectOneMenu>  
                                </p> 
                                <p>
                                    <h:outputLabel for="suplementacion">¿Desea utilizar suplementación?</h:outputLabel>
                                    <h:selectOneMenu value="#{registro.atleta.suplementacion}"
                                                     id="suplementacion"> 
                                        <f:selectItem itemValue = "0" itemLabel = "No" />                                            
                                        <f:selectItem itemValue = "1" itemLabel = "Si - Solo antes del entrenamiento" />                                            
                                        <f:selectItem itemValue = "2" itemLabel = "Si - Antes y después del entrenamiento" />                                            
                                    </h:selectOneMenu>  
                                </p> 
                                <p>
                                    <h:outputLabel for="sobre_ti">Dinos algo sobre tí</h:outputLabel>
                                    <h:inputTextarea value="#{registro.atleta.observacionesAtleta}">
                                    </h:inputTextarea>
                                </p>
                            </div>
                        </div>
                        <h:commandButton value="Entrar" styleClass="btn" action="#{registro.registro()}"/>
                        </p>
                    </h:form>

                </div>            
            </div>
            <jsp:include page="/include/pie.jsp" />
        </body>
    </html>
</f:view>
