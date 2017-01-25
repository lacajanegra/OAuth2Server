(function(){
	
	var constants = angular.module('utils.constants', []);

	constants.constant('CONSTANTS', {

		//RESOURCE NAME [WEB SERVICES]
		"SERVICES_URL": "http://localhost:8080",


		//DOMAIN SET COOKIES
		"DOMAIN" : "localhost",


        //PATTERNS 
        "EMAIL_PATTERN" : /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/,



        //TERMS AND CONDITIONS
        "TITLE_BOX_TERMS" : "TÉRMINOS Y CONDICIONES", // título en la ventana modal de terminos y condiciones
        "TITLE_TERMS" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", //Subtítulo en ventana modal de térinos y condiciones
        "TERMS" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ac velit ac nisi lobortis blandit. Suspendisse potenti. Fusce ornare metus dui, vel malesuada lectus convallis vel. Sed ullamcorper mi felis, at auctor lacus tincidunt vitae. Morbi non consequat ante. Quisque non diam sodales risus feugiat fermentum. Integer mollis venenatis sapien, ac bibendum est varius nec. Aliquam eu augue congue, faucibus nunc non, tincidunt ligula. Nam facilisis ligula eget lacus porttitor, vel eleifend neque facilisis. Mauris nisl diam, euismod non imperdiet vitae, malesuada ac odio. Nulla sed lacus at ligula convallis tincidunt et vel leo. Suspendisse scelerisque rhoncus orci, id facilisis tellus commodo eu. Aliquam erat volutpat. Phasellus eget elit a lacus accumsan ultricies. Ut efficitur iaculis purus id luctus. Quisque a justo a ex mollis auctor vel imperdiet magna."

                        + "Aenean accumsan, augue a ultricies aliquam, elit sapien maximus ipsum, ut elementum odio ligula in mauris. Donec hendrerit ex quis libero venenatis, vel pharetra lorem tristique. Vestibulum blandit vitae ex eu vulputate. Vivamus vitae purus placerat, ullamcorper ex sed, volutpat mi. Vivamus efficitur a turpis ut eleifend. Aenean ac commodo metus, eu gravida velit. Aliquam facilisis arcu eget magna porta varius id in tortor."

                        + "Duis luctus massa ac orci semper, id fermentum purus laoreet. Sed non posuere est, vel scelerisque augue. Integer efficitur consectetur lacus in commodo. Etiam ornare congue nulla et porttitor. Mauris finibus tellus sed mattis tempus. Duis tincidunt accumsan malesuada. Ut sem erat, scelerisque sed leo sed, fringilla blandit purus. Sed a augue non mi pulvinar varius. In vestibulum volutpat risus, eu fringilla diam sodales nec."

                        + "Sed fermentum, leo sed commodo gravida, ante justo malesuada odio, at tempor dolor ante nec tellus. Nulla sed eleifend arcu. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin malesuada, urna a sodales sodales, risus orci ullamcorper neque, a hendrerit nunc dolor in eros. Sed eleifend justo elit, rhoncus laoreet sem suscipit ac. Donec maximus, diam at accumsan faucibus, ex turpis blandit turpis, a ornare nulla ligula eu nisi. Suspendisse porttitor vulputate gravida. Proin pretium a tellus ut consectetur. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Ut risus massa, iaculis non orci vel, rhoncus tincidunt ligula. Nam viverra justo et sapien euismod, vehicula molestie nulla malesuada. Nullam nec tellus urna. Nam dapibus enim sed erat feugiat, at tincidunt diam aliquet."

                        + "Donec faucibus mauris vel nisl blandit, ut sagittis erat aliquam. Mauris pharetra lacus urna, eu hendrerit eros interdum a. Nulla facilisi. Integer gravida tortor sed velit ultrices congue. Mauris vitae nisi turpis. Suspendisse sit amet libero consectetur est gravida lacinia. Duis nec lectus euismod, facilisis lacus ac, vulputate metus. Sed non efficitur sapien. Fusce et felis ipsum. Aenean tempus sagittis dolor, luctus tempor lacus aliquet sed. Ut euismod odio in turpis volutpat egestas. Phasellus fermentum metus eu enim placerat sodales.",

        //CALL CENTER PROVIDA               
        "CALL_CENTER_PROVIDA" : "600 20 10 150",

        // ********************ERROR DESCRIPTIONS********************

        //GENERIC ERRORS
	    "SERVER_ERROR" : "Ocurrio un problema al procesar tu solicitud. Intenta en otro momento.", //Error de conexión con el servidor
	    "FORM_ERROR" : "Hay campos que presentan errores. Corrígelos para continuar", ////Error en campos de entrada
	    "MISSING_FIELDS_ERROR" : "Debes rellenar los siguientes campos: ", //Se deben rellenar todos los campos



        //CHECK RUT VIEW ERRORS (index.html)
		"UNKNOWN_ERROR" : "Ocurrió un error desconocido. Intenta en otro momento.", //El servidor responde un error desconocido


		//PROVIDA REG VIEW (registro-provida.html) & PROVIDABOX REG VIEW ERRORS (registro-infinity.html) 
		"KN31_ERROR" : "Hubo un problema al procesar tu solicitud, por favor inténtalo en otro momento.", //El servidor responde un error desconocido
		"ACC_CREATE_ERROR" : "Hubo un problema al procesar tu solicitud, por favor inténtalo en otro momento.", //El servidor responde un error desconocido
		"KNZ2_ERROR" : "Hubo un problema al procesar tu solicitud, por favor inténtalo en otro momento.", //El servidor responde un error desconocido
		"ACC_CONFIRM_ERROR" : "Hubo un problema al procesar tu solicitud, por favor inténtalo en otro momento.", //El servidor responde un error desconocido



		//PASS VIEW (conecta-con-provida-clave.html)
		"PASS_ERROR" : "Los datos ingresados son incorrectos, por favor revísalos e intenta nuevamente.",
		// *********************************************************




		// ********************LINKS********************
		"PROV_REG_LINK": "registro-provida.html",
		"PROV_BOX_REG_LINK": "registro-provida-box.html",
		"PASS_LINK" : "conecta-con-provida-clave.html",
		"WITHOUT_INVITATION_LINK" : "registro-provida-box-sin-invitacion.html",
		"OK_GET_PASS_LINK": "obten-tu-clave-de-acceso-revisar-mail.html",
		"WHITHOUT_URILINK_LINK" : "error.html",
		"LINK_PROVIDA" : "www.test.com",
		"RECOVERY_PASS" : "www.test.com/recovery"


		// *********************************************

	});

})()