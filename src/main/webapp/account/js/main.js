
if (!window.console) {window.console = {};}
if (!console.log) {console.log = function() {};}

//////////////////////////////////////////////////////////////////////////////////////////////////
// validador de ruts /////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////
(function($) {
    jQuery.fn.Rut = function(options) {
        var defaults = {
            digito_verificador: null,
            on_error: function() {},
            on_success: function() {},
            validation: true,
            format: true,
            format_on: 'change'
        };
        var opts = $.extend(defaults, options);
        return this.each(function() {
            if (defaults.format) {
                jQuery(this).bind(defaults.format_on, function() {
                    //jQuery(this).val(jQuery.Rut.formatear(jQuery(this).val(), defaults.digito_verificador == null));
                });
            }
            if (defaults.validation) {
                if (defaults.digito_verificador == null) {
                    jQuery(this).bind('blur', function() {
                        var rut = jQuery(this).val();
                        if (jQuery(this).val() != "" && !jQuery.Rut.validar(rut)) {
                            defaults.on_error();
                        } else if (jQuery(this).val() != "") {
                            defaults.on_success();
                        }
                    });
                } else {
                    var id = jQuery(this).attr("id");
                    jQuery(defaults.digito_verificador).bind('blur', function() {
                        var rut = jQuery("#" + id).val() + "-" + jQuery(this).val();
                        if (jQuery(this).val() != "" && !jQuery.Rut.validar(rut)) {
                            defaults.on_error();
                        } else if (jQuery(this).val() != "") {
                            defaults.on_success();
                        }
                    });
                }
            }
        });
    }
})(jQuery);
jQuery.Rut = {
    formatear: function(Rut, digitoVerificador) {
        var sRut = new String(Rut);
        var sRutFormateado = '';
        sRut = jQuery.Rut.quitarFormato(sRut);
        if (digitoVerificador) {
            var sDV = sRut.charAt(sRut.length - 1);
            sRut = sRut.substring(0, sRut.length - 1);
        }
        while (sRut.length > 3) {
            sRutFormateado = "." + sRut.substr(sRut.length - 3) + sRutFormateado;
            sRut = sRut.substring(0, sRut.length - 3);
        }
        sRutFormateado = sRut + sRutFormateado;
        if (sRutFormateado != "" && digitoVerificador) {
            sRutFormateado += "-" + sDV;
        } else if (digitoVerificador) {
            sRutFormateado += sDV;
        }
        return sRutFormateado;
    },
    quitarFormato: function(rut) {
        var strRut = new String(rut);
        while (strRut.indexOf(".") != -1) {
            strRut = strRut.replace(".", "");
        }
        while (strRut.indexOf("-") != -1) {
            strRut = strRut.replace("-", "");
        }
        return strRut;
    },
    digitoValido: function(dv) {
        if (dv != '0' && dv != '1' && dv != '2' && dv != '3' && dv != '4' && dv != '5' && dv != '6' && dv != '7' && dv != '8' && dv != '9' && dv != 'k' && dv != 'K') {
            return false;
        }
        return true;
    },
    digitoCorrecto: function(crut) {
        largo = crut.length;
        if (largo < 2) {
            return false;
        }
        if (largo > 2) {
            rut = crut.substring(0, largo - 1);
        } else {
            rut = crut.charAt(0);
        }
        dv = crut.charAt(largo - 1);
        jQuery.Rut.digitoValido(dv);
        if (rut == null || dv == null) {
            return 0;
        }
        dvr = jQuery.Rut.getDigito(rut);
        if (dvr != dv.toLowerCase()) {
            return false;
        }
        return true;
    },
    getDigito: function(rut) {
        var dvr = '0';
        suma = 0;
        mul = 2;
        for (i = rut.length - 1; i >= 0; i--) {
            suma = suma + rut.charAt(i) * mul;
            if (mul == 7) {
                mul = 2;
            } else {
                mul++;
            }
        }
        res = suma % 11;
        if (res == 1) {
            return 'k';
        } else if (res == 0) {
            return '0';
        } else {
            return 11 - res;
        }
    },
    validar: function(texto) {
        texto = jQuery.Rut.quitarFormato(texto);
        largo = texto.length;
        if (largo < 2) {
            return false;
        }
        for (i = 0; i < largo; i++) {
            if (!jQuery.Rut.digitoValido(texto.charAt(i))) {
                return false;
            }
        }
        var invertido = "";
        for (i = (largo - 1), j = 0; i >= 0; i--, j++) {
            invertido = invertido + texto.charAt(i);
        }
        var dtexto = "";
        dtexto = dtexto + invertido.charAt(0);
        dtexto = dtexto + '-';
        cnt = 0;
        for (i = 1, j = 2; i < largo; i++, j++) {
            if (cnt == 3) {
                dtexto = dtexto + '.';
                j++;
                dtexto = dtexto + invertido.charAt(i);
                cnt = 1;
            } else {
                dtexto = dtexto + invertido.charAt(i);
                cnt++;
            }
        }
        invertido = "";
        for (i = (dtexto.length - 1), j = 0; i >= 0; i--, j++) {
            invertido = invertido + dtexto.charAt(i);
        }
        if (jQuery.Rut.digitoCorrecto(texto)) {
            return true;
        }
        return false;
    }
};
// console.log polyfill
window.log=function(){log.history=log.history||[];log.history.push(arguments);if(this.console){console.log(Array.prototype.slice.call(arguments))}};

(function (window, document, $, undefined) {
    "use strict"; //Modo estricto para prevenir errores y malas prácticas

    //Boolean que permite saber si existe el método "classList" en el navegador
    var classListEnabled = typeof(document.createElement('div').classList) === 'undefined' ? false : true;
    
    //Se almacena window y document en variables al comienzo para no utilizar memoria después
    var $window = $(window),
        $document = $(document);
		
	
    
    //Detecta la versión de Internet Explorer según su User Agent y retorna su versión
    function getInternetExplorerVersion() {
        var rv = -1; // Return value assumes failure.
        if (navigator.appName == 'Microsoft Internet Explorer') {
            var ua = navigator.userAgent;
            var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
            if (re.exec(ua) != null)
                rv = parseFloat(RegExp.$1);
        }
        return rv;
    }

    // detecta IE10+ a través de la deteccion de los eventos pointers y coloca una clase "mspointers" en el HTML a través de Modernizr
    Modernizr.addTest('mspointers', function () { return window.navigator.msPointerEnabled; });
    // detecta IE9- a través del parseo del useragent y coloca una clase con la versión de IE en el HTML a través de Modernizr
    Modernizr.addTest('oldie', function () {
        var v = getInternetExplorerVersion();
        return v <= 9 && v > -1 ;
    });
    Modernizr.addTest('oldie-8', function () {
        var v = getInternetExplorerVersion();
        return v <= 8 && v > -1 ;
    });
    Modernizr.addTest('oldie-7', function () {
        var v = getInternetExplorerVersion();
        return v <= 7 && v > -1 ;
    });

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////// HANDLERS

    window.handler = function(){
       
    };

    //Se almacenan las funciones dentro del prototipo del objeto por convención, recomendación y performance por sobre todo
    window.handler.prototype = {

        /////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////// INICIALIZADORAS

        //Funciones que se inicializan en el document.ready
        onReadySetup : function() {
			
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			////// Estilos para los RadioButtons aplicables para los navegadores más modernos
			///////////////////////////////////////////////////////////////////////////////////////////// 
			
			//Activar radios (check)
	
			if(navigator.appName.indexOf("Internet Explorer")!=-1){     //usando iE
				var ieBrowser=(
					navigator.appVersion.indexOf("MSIE 9")==-1 &&   //versión 9 ok
					navigator.appVersion.indexOf("MSIE 1")==-1  //v10, 11, 12, etc. está bien también
				);
				if(ieBrowser){
					//de ser ie, esta funcionalidad no aplica
				}
			}else{
				$('input[type="radio"]').wrap('<div class="radio-wrap">');
			}
			
			if ($('.radio-wrap').length) {
				
				$('.radio-wrap input[type="radio"]').each(function(i){
					$(this).parent().addClass($(this).attr('class'));
				});
				
				$('.radio-wrap input[type="radio"]:checked').parent().addClass('active');
				
				$('.radio-wrap input[type="radio"]').change(function(){
					$('input[name="' + $(this).attr("name") + '"]').each(function(){
							if ($(this).is(":checked"))
						{
							$(this).parent().addClass('active');
							$(this).prop('checked',true);
							var selfId = $(this).attr('id');
						}
						else
						{
							$(this).parent().removeClass('active');
						}
					});
				});
			}

			$('body').append('<div class="overlay-fade"></div>');
			$('.overlay-fade').delay(100).fadeOut(function(){
				$(this).remove();	
			});
			
			///////////////////////////////////////////////////////////////////////////////////////////// 
			

			$('.hidden-element input, .hidden-element select').attr('required',false);

			$('.prevent-default').click(function(event){
				event.preventDefault();	
			});

			$('.switch input').change(function(e){

				if ($(this).parent().hasClass('s-active')) {
					$(this).parent().removeClass('s-active')
				}else{
					$(this).parent().addClass('s-active')
				}
			});
			
			$('.enviar-email').click(function(){	
			});
	
			$(".check-all").click(function(){
				$('input:checkbox').not(this).prop('checked', this.checked);
			});

			$('.prevenDefault').click(function(evt){
				evt.preventDefault();
			});
			
			$('#rut-input-2').Rut({
				digito_verificador: '#rut-verificador'
			});
			
			$('.rut-input').Rut({
			   format_on: 'keyup'
			});
			
			//Recorremos todos los campos de rut y si traen datos cargados
			//Disparamos el evento "keyup" en estos, por lo cual se les dará formato
			$('.rut-input').each(function(i,obj){
				if (!$(obj).val() == '') {
					$(obj).trigger('keyup');
				}
			});





			
			jQuery.fn.animateAuto = function(prop, speed, callback){
				var elem, height, width;
				return this.each(function(i, el){
					el = jQuery(el), elem = el.clone().css({"height":"auto","width":"auto"}).appendTo("body");
					height = elem.css("height"),
					width = elem.css("width"),
					elem.remove();
					
					if(prop === "height")
						el.animate({"height":height}, speed, callback);
					else if(prop === "width")
						el.animate({"width":width}, speed, callback);  
					else if(prop === "both")
						el.animate({"width":width,"height":height}, speed, callback);
				});  
			}

            var self = this; //Se almacena this com oel objeto para no confundir

            self.idleTime = 0;
            self.$body = $('body'); //Se almacena el body en una variable para ahorrar memoria
            self.relativeURL = this.$body.attr('data-relativeURL'); //Se almacena la URL base obtenida desde el atributo "data-relativeURL" del body, ejemplo: http://example.com

            self.loadScripts();

            self.eventsHandler( $('[data-func]') ); //Se ejecuta el método que permite la delegación de eventos automática desde el HTML
            if( ! Modernizr.svg ) { self.svgFallback( $('[data-svgfallback]') ); } //Se ejecuta el fallback para SVG's si es que el navegador no lo soporta
            
            //Validador de formularios
            // $('form[data-validate]').on('submit', function(event){
            //     alert(event)
            //     self.validateForms(event);
            // });
            $('form[data-validate]').find('[required]').on('blur keyup change', function(event){
                self.validateForms(event);
            });

            $('input[type="text"], input[type="email"]').on('blur', function(event){
                self.showCorrector(event);
            });

            $('input[type="password"]').on('keyup', function(event){
                self.showPassViewer(event);
            });
			
			
			$('.case-box').hide();
			$('.case-2').fadeIn();


			$('.search-with-submenu .remove-value').on('click',function(){
				$('.search-with-submenu').find('.result-box').removeClass('active');
			});
			
			//Carga de valor de opción seleccionada en el input
			$('.result-box li').not('.loading-text').click(function(e){
				e.preventDefault();
				var selfElement = $(e.currentTarget);
				var selfValue = selfElement.html();
				selfElement.parent().removeClass('active');
				selfElement.parents('.search-with-submenu').find('input').val(selfValue);
			});
			
			if ($('.loading-text').is(':visible')) {
				setInterval(transition, 300);
			}
			var textoCargando = 1;

			function transition() {
			
				if(textoCargando == 1) {
					$('.loading-text').html('Cargando.');
					 textoCargando = 2;
			
				} else if(textoCargando == 2) {
					$('.loading-text').html('Cargando..');
					 textoCargando = 3;
			
				} else if(textoCargando == 3) {
					$('.loading-text').html('Cargando...');
					textoCargando = 1;
				}
			
			}
			
			
			$.fn.clearHiddenInputs = function(){
				var target = $(this);
				target.attr('required',false);
				target.removeClass('invalid-input valid-input');
				target.parent().find('.icon-element').remove();
				target.not('select, input[type="radio"], input[type="checkbox"]').val('');
			};
			
			$.fn.activeInputs = function(){
				var target = $(this);
				target.attr('required',true);
			};
			
			$.fn.cleanRadio = function(){
				var item = $(this);
				item.removeClass('invalid-input valid-input');
				item.parent().removeClass('invalid-input valid-input');
				item.attr('required',false);
			}
			
			//limpiamos los radio despues de validarlos
			$('body').on('click','.invalid-input input[type="radio"]',function(){
				var selfName = $(this).attr('name');
				$('[name="'+selfName+'"]').cleanRadio();
			});
			






			
			//////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////
			
			//Ejemplos de alertas
			
			//Las alertas se ejecutarán al poseer la clase "active", al ser cerradas, estas
			//dejarán de existir en el Dom
			if ($('[data-alert]').length) {
				$('body').on('click','[activate-alert]',function(){
					var item = $(this);
					item.next().addClass('active');
				});
				$('body').on('click','[activate-all-alert]',function(){
					$('[data-alert]').addClass('active');
				});
			}
			
			
			$('[data-first-option], [data-sec-option], [data-cancel-button]').hide();
			
			
			$('body').on('click','[data-opciones]',function(){
				var item = $(this);
				if (item.val() == 1) {
					$('[data-sec-option]').hide();
					$('[data-first-option]').fadeIn();
				}
				if (item.val() == 2) {
					$('[data-first-option]').hide();
					$('[data-sec-option]').fadeIn();
					$('.tab-control').removeClass('active');
					$('[data-index="4"]').addClass('active');
				}
				
			});
			
			$('[data-cancel-button]').click(function(){
				var item = $(this);
				item.prev().prev().addClass('hidden-field');
				item.prev().html('Enviar a email');	
		        item.prev().on();
				item.fadeOut();
			});
			
			
			//Modal en contexto
			$('[data-active-modal]').click(function(){
				var item = $(this);
				var selfTarget = item.data('target');
				$('[data-element="'+selfTarget+'"]').fadeIn(function(){
					$(this).find('.confirm-tooltip').addClass('active');	
				});
			});
			
			//Cerrar modal
			$('[data-close-season-modal]').click(function(e){
				e.preventDefault();
				var item = $(this);
				item.parents('.confirm-tooltip').removeClass('active');
				item.parents('.confirm-overlay').fadeOut();
			});

			//Cerramos la alerta de carga de imagenes
			$(document).mouseup(function (e)
			{
				var container = $('[data-context-modal]');
				if (!container.is(e.target) && container.has(e.target).length === 0) {
					container.find('.confirm-tooltip').removeClass('active');
					container.parent().fadeOut();
				}
			});
			
			
			$('[data-close-disabled]').click(function(){
				var item = $(this);
				item.parents('.error-block').removeClass('active');
			});
			
			$('[data-search-button]').click(function(e){
				e.preventDefault();
				var resultados = $('.result-box');
				if (!$('.search-workers').val() == '') {
					resultados.addClass('active');
				}else{
					resultados.removeClass('active');
				}
			}); 
			
			$('.search-workers').on('keyup mouseup focusout',function(){
				var resultados = $('.result-box');
				var item = $(this);
				if (item.val() == '') {
					$('.search-workers').parent().find('.remove-value').remove();
					resultados.removeClass('active');
				}
			});
			
			
			
			///////NEW TABS

			$('.acc-mobile-tab-deploy-button').click(function(e){
				e.preventDefault();
				$(this).toggleClass('active');
				$(this).next().toggleClass('active');
				$('.acc-tab-button').removeClass('active');
				$('.acc-tab').removeClass('active');
			});
			
			$('.acc-tab').each(function (i,obj) {
				$(obj).addClass('deploy-' + i);
			});
		
			//Igualamos alturas en los botones a excepción de los sidetabs acc-tab-button
			//$('.acc-tab-button').not('.side-tabs .acc-tab-button').equalizeHeights();
			$('.acc-tab-control-holder button').click(function(e){
				e.preventDefault(); 
				var item = $(this).data("index"); //get data index del boton
				$('.acc-tab').removeClass('active'); //remove class active en los tabs content
	
				if($(window).width() <= 640){
					//MOBILE
					if( $(this).hasClass('active') ){ 
						//Si está activo lo contraigo como colapsable
						$(this).removeClass('active');
						$('.deploy-'+item).removeClass('active');
	
					}else{ 
						// Si no está activo lo despliego
						$('.acc-tab-control-holder button').removeClass('active');
						$('acc-tab').removeClass('active');
						$(this).addClass('active');
						$('.deploy-'+item).addClass('active');
	
					}
				}else{ //DESKTOP
					$('.acc-tab-control-holder button').removeClass('active');  //remove class active del los botones tabs
					$(this).addClass('active'); //addclass active al boton cliqueado
					$('.deploy-'+item).addClass('active'); // show content tab
				}
			});
			
			///////////////////////////////
			
			
			$(".number-validation").on('keydown', function(e){
				return self.justNumbers(e);
			});
			

        },
        //Funciones que se inicializan en el window.load
        onLoadSetup : function(){
            var self = this;
            
			//Generamos tablas dentro de retiro paso 2
			$('.inner-wrapp').wrapInner('<div class="d-table">');
			$('.d-table').wrapInner('<div class="t-core">');
			
            //Animaciones CSS al mostrar elemento en pantalla
            self.animateOnView($('[data-animate]'));         

            //Animaciones CSS con delay
            self.animateOnDelay($('[data-animate-delay]'));
			
			
			
			//NEW TABS
			
			tabsToAccordion();
			
        },
        //Funciones que se inicializan en el evento scroll
        onScrollSetup : function(){
            var self = this;
        },
        //Funciones que se inicializan en el evento resize
        onResizeSetup : function(){

			//NEW TABS
			tabsToAccordion();
            
        },
        //Setea delegaciones automáticas a través del HTML
        eventsHandler : function( $elements ){
            if( ! $elements.length ){ return; }
            var self = this;
            $.each( $elements, function( index, elem ){
                var $item = $(elem),
                    func = $item.data('func'),
                    events = $item.data('event') ? $item.data('event') : 'click.handler';
                if( func && typeof( self[func] ) === 'function' ){
                    $item.on( events, $.proxy( self[ func ], self ) );
                    $item.data('delegated', true);
                } 
            });
    	},
        //Fallback para imágenes SVG
        svgFallback : function( $elements ){
            if( ! $elements.length ){ return; }
            var $item;

            $elements.each(function(index, elem){
                $item = $(elem);
                $item.attr('src', $item.data('svgfallback'));
            });
        },
        //Formatea strings según parámetros
        // self.currency(value, 0, ['.', '.', '.'])
        currency : function(num) {
			if(isNaN(num)) {
				num = '';
			}
			
            var str = num.toString().replace("$", ""), parts = false, output = [], i = 1, formatted = null;
            if(str.indexOf(".") > 0) {
                parts = str.split(".");
                str = parts[0];
            }
            str = str.split("").reverse();
            for(var j = 0, len = str.length; j < len; j++) {
                if(str[j] != ".") {
                    output.push(str[j]);
                    if(i%3 == 0 && j < (len - 1)) {
                        output.push(".");
                    }
                    i++;
                }
            }
            formatted = output.reverse().join("");
            return(formatted + ((parts) ? "." + parts[1].substr(0, 2) : ""));
        },
        loadScripts : function(){
            var self = this;
            Modernizr.load([
                {
                    test: $('.slider').length,
                    yep: 'js/libs/ninjaSlider.js',
                    callback: function(url, result, key){
                        setTimeout(function(){},3000);
                        if(result){ self.setupSliders();}
                    }
                },
                {
                    test: $('body').length,
                    yep: ['js/libs/flipclock.js', 'css/flipbox.css'],
                    complete: function(){
                        self.startIdleTime();
                    }
                }

            ]);
        },
        startIdleTime : function(){
            var self = this;

            var idleInterval = setInterval(function(){
                self.idleTime = self.idleTime + 1;
                if (self.idleTime > 300) { 
                    self.startCounter();
                    clearInterval(idleInterval);
                }
            }, 1000);

            $document.mousemove(function (e) {
                self.idleTime = 0;
            });
            $document.keypress(function (e) {
                self.idleTime = 0;
            });
        },
        startCounter : function(){
            var self = this;

            if($('.screen').find('.lightbox').not('.counter').length){
                $('.screen').remove()
            }

            var $cortina = self.setScreen();

            $cortina.append('<div class="la-ball-beat"><div></div><div></div><div></div></div>').addClass('loaded');


            $.ajax({url: 'partials/counter.html',dataType: "html", success: function(result){
                $('.la-ball-beat').remove();
                $cortina.append(result);

                $cortina.find('.lightbox').css('top', $document.scrollTop() + 30).addClass('animated bounceInDown');

                $cortina.one('click', function(event){
                    event.stopPropagation();
                    $cortina.removeClass('loaded').remove();
                });

                $cortina.find('.lightbox').on('click',function(event){
                    event.stopPropagation();
                });

                self.eventsHandler( $cortina.find('[data-func]') );

                if($('.oldie-7').length == 0 && $('.oldie-8').length == 0){
                    self.createCounter();
                }else{
                    self.createCounterOnIE();
                }
                

            }});
        },
        createCounter : function(){
            var clock = $('.your-clock').FlipClock(60, {
                clockFace: 'Counter',
                countdown : true
            });

            setTimeout(function() {
                var clockTimer = setInterval(function() {
                    var currentTime  = clock.getTime().time
                    clock.decrement();
                    if(currentTime == 1){
                        clearInterval(clockTimer);
                        //Cerrar sesión
                    }
                }, 1000);
            });
        },
        createCounterOnIE : function(){
            var $clockWrapp = $('.your-clock');

            $clockWrapp.addClass('flip-clock-wrapper').html('<ul class="flip"><li class="flip-clock-active"><a><div class="up"><div class="shadow"></div><div class="inn number">60</div></div><div class="down"><div class="shadow"></div><div class="inn number">60</div></div></a></li></ul>');

            var count = 60;
            var timer = function(){
                count--;
                $clockWrapp.find('.inn.number').html(count);
                if(count == 0){
                    clearInterval(counter);
                    //Cerrar sesión
                }
            }

            var counter = setInterval(timer, 1000);

        },
        showCorrector : function(event){
            var self = this;
            var $item = $(event.currentTarget);
            var $parentContainer = $item.parent();

            if(($parentContainer.find('.remove-value').length == 0) && (!$item.hasClass('in-text')) && (!$item.attr('data-disable-img-error'))){
                $parentContainer.append('<span class="icon-element remove-value" data-func="removeValue"></span>');
                self.eventsHandler( $parentContainer.find('[data-func]') );
				
				
				$('.search-with-submenu .remove-value').on('click',function(){
					$('.search-with-submenu').find('.result-box').removeClass('active');
				});
				
            }

        },
        showPassViewer : function(event){
            var self = this;
            var $item = $(event.currentTarget);
            var $parentContainer = $item.parent();

            if($parentContainer.find('.show-pass').length == 0){
                $parentContainer.append('<span class="icon-element show-pass" data-func="showPass"></span>');
                self.eventsHandler( $parentContainer.find('[data-func]') );
            }
        },
        validateForms : function(event){
            event.preventDefault();
            var self = this;
            var $form = event.type == 'submit' ? $(event.currentTarget) : $(event.currentTarget).parents('form');//Se almacena el objeto del formulario, en caso de submit y en caso de otros eventos
            var $inputs = event.type == 'submit' ? $form.find('[required]') : $(event.currentTarget); //Se almacenan todos los elementos requeridos
            var isValid = true; //Flag para saber si el formulario finalmente es válido o no, al comienzo siempre es válido
            var emailRegEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; //Regex para comprobar email
            var numerosRegEx = /^\d+(?:\.\d{1,2})?$/; //Regex para comprobar números
            var letrasyNumerosRegEx = /^[0-9a-zA-Z]+$/ //Regex para numeros y letras;

			
            //Función que setea un input inválido
            var setToFalse = function($input){
                var customMessage = $input.data('custom-message'); //Mensaje customizado
                var $parentHolder = $input.parent(); //Elemento padre
                var type = $input.attr('type'); //Tipo de input
                isValid = false; //flag

				//if ($input.hasClass('number-validation')) {
				//	$input.parents('.bordered-dashed-box').find('.error-message-b').show();
				//}
				
                if(type == 'hidden'){return false;} //Si el tipo de input es hidden no hace nada
                $input.addClass('invalid-input').removeClass('valid-input'); //Agrega la clase de inválido y quita la clase de válido

                if(!$input.next().is('.error-message') && event.type == 'submit' && customMessage && !$input.hasClass('in-text')){
                    $input.after('<p class="error-message">'+ customMessage +'</p>'); //Agrega mensaje de error si es que este no existe
                }

                if( type == 'checkbox' || $input.data('disable-img-error')){return false;} //Si es un checkbox no hace nada.
				
                if(($parentHolder.find('.error-img').length == 0) && !$input.hasClass('in-text')){
                    $parentHolder.find('.valid-img').remove();
                    $parentHolder.append('<span class="icon-element error-img"></span>'); 
                }
				
				if( $input.hasClass('not-icon') ){
					$parentHolder.find('.error-img, .valid-img').remove();
				}
				
				if( type == 'radio' ){
					$parentHolder.addClass('invalid-input');
					$parentHolder.find('.error-img').remove();
				}
				
            }
            //Función que setea un input válido
            var setToTrue = function($input){
                var $parentHolder = $input.parent();
                var type = $input.attr('type');

                if(type == 'hidden' || type == 'checkbox'){return false;}

                $input.addClass('valid-input').removeClass('invalid-input'); //Agrega la clase valdia al input

                if($input.data('disable-img-error')){return false;}

                if($parentHolder.find('.valid-img').length == 0 && !$input.hasClass('in-text')){
                    $parentHolder.find('.error-img').remove();
                    $parentHolder.append('<span class="icon-element valid-img"></span>'); //Agrega imagen de éxito
                }
				
				if( $input.hasClass('not-icon') ){
					//$parentHolder.find('.error-img, .valid-img').remove();
					$parentHolder.find('.error-img, .valid-img, .remove-value').remove();
				}

            }
            //Función que valida radio buttons, comprobando si uno está marcado o no
            var validateRadio = function($element){
                var $radioPack = $('input[name="'+ $element.attr('name') +'"]');
                var isValidRadio = false;
                $.each($radioPack, function(index, element){
                    var $e = $(element);
                    if($e.prop('checked') == true){
                        isValidRadio = true;
                    }
                });

                if(isValidRadio == false){
                    setToFalse($element);
                }
            }

		

            //Se elimina la clase de error a los inputs y la clase de input válido
            $inputs.removeClass('invalid-input');
            $('[name="'+ $inputs.attr('name') +'"]').removeClass('invalid-input');
            $inputs.removeClass('valid-input');

            //Si no es click, elimina el mensaje de error
            if(event.type != 'submit'){
                var $currentItem = $(event.currentTarget);
                if($currentItem.next().is('.error-message')){
                    $currentItem.next().remove();
                }
            }

            $.each($inputs, function(index, element){
                var $element = $(element);
                var tagName = $(element).prop('tagName').toLowerCase();
                var limit = $element.data('limit') ? $element.data('limit') : 5;
                var elementValue = tagName == 'input' || tagName == 'textarea' ? $element.val() : $element.find('option:selected').val();
				
				if ($('[name="monto-de-ahorro-currency"]').length) {
					var rutValue = $('.hidded.active .rut-input').val();
				}else{
					//var rutValue = $('.rut-input').val();
				}

                if($element.attr('data-validate-on-show') == 'false' || $element.attr('readonly')){
                    return true;
                }

                //Select vacío
                if(tagName == 'select' && elementValue == ""){
                    setToFalse($element);
                }else if(tagName == 'select' && elementValue != ""){
                    setToTrue($element);
                }

                //Input vacío
                if((tagName == 'input' || tagName == 'textarea') && elementValue == ""){
                	
                    setToFalse($element);
                }else if((tagName == 'input' || tagName == 'textarea') && elementValue != "" && $element.attr('type') != 'radio'){
                    setToTrue($element);
                }
				
				//Si los datapairs están en 0
				if (tagName == 'input' && $element.hasClass('not-value') ) {
					setToFalse($element);
				}
				
				//Validar para que los inputs sólo puedan tener valores 0 y 100
				if (tagName == 'input' && $element.hasClass('only-100-or-0') && elementValue < 100 && elementValue > 0 ) {
					
					setToFalse($element);
				}
			
                //Radio buttons
                if(tagName == 'input' && $element.attr('type') == 'radio' && event.type == 'submit'){
                    validateRadio($element);
                }

                //Email
                if(tagName == 'input' && $element.attr('type') == 'email' && emailRegEx.test(elementValue) == false){
                    setToFalse($element);
                }
				
                //RUT
                 if((tagName == 'input' && $element.hasClass('rut-input') && $.Rut.validar(elementValue) == false) || (tagName == 'input' && $element.hasClass('rut-input') && elementValue == 19)){ 
                   setToFalse($element);
                }

                //Sólo números
                if(tagName == 'input' && $element.hasClass('number-validation') && elementValue != "" && numerosRegEx.test(elementValue) == false){
                    setToFalse($element);
                }

                //Sin cero
                if(tagName == 'input' && $element.hasClass('number-validation') && elementValue != "" && numerosRegEx.test(elementValue) == false){
                    setToFalse($element);
                }

                //Numeros consecutivos
                if(tagName == 'input' && $element.hasClass('currency') && elementValue < 1){
                    setToFalse($element);
                }

                //minimo y maximo de caracteres
                if(tagName == 'input' && ((elementValue.length < $element.data('min')) || (elementValue.length > $element.data('max')))){
                    setToFalse($element);
                }

                //minimo y maximo
                if(tagName == 'input' && ((elementValue.split('.').join("") < $element.data('min-value')) || (elementValue.split('.').join("") > $element.data('max-value')))){
                    setToFalse($element);
                }

                //Solo letras y numeros
                if(tagName == 'input' && $element.hasClass('numeros-letras')&& letrasyNumerosRegEx.test(elementValue) == false){

                    setToFalse($element);
                }

                //Confirmar clave
                if(tagName == 'input' && $element.hasClass('same-validation') && elementValue != "" && (elementValue != $('[name="clave-nueva"]').val())){
                    setToFalse($element);
                }
				
				//Reingreso de datos
				if(tagName == 'input' && $element.hasClass('same-validation-two') && elementValue != "" && (elementValue != $('[name="account-number"]').val())){
                    setToFalse($element);
                }
				
				if((tagName == 'input' && ($element.attr('type') == 'radio' || $element.attr('type') == 'checkbox'))  && event.type == 'submit'){
                    validateRadio($element);
                }

                if(tagName == 'select' && $element.hasClass('validate-date-range') && self.validateDateRange() == false && event.type == 'submit'){
                    isValid = false
                }


            });

            
            if(isValid && event.type == 'submit'){
                $form.off('submit');
                $form.submit();
            }else if(!isValid && !$form.data('no-scroll') && event.type == 'submit'){
                $('html, body').animate({
                    scrollTop: $(".invalid-input").offset().top - 120
                }, 300);
            }
        },
        getModal : function (event){
            event.preventDefault();
            var self = this;
            var $item = $(event.currentTarget);
            var target = $item.data('modal');
			var currentFondo = $item.data('current');

            if($item.data('modal-delegated') === true){
                return false;
            }

            var $cortina = self.setScreen();

            $cortina.append('<div class="la-ball-beat"><div></div><div></div><div></div></div>').addClass('loaded');


            $.ajax({url: 'partials/'+ target +'.html',dataType: "html", success: function(result){
                $('.la-ball-beat').remove();
                $cortina.append(result);

                $cortina.find('.lightbox').css('top', $document.scrollTop() + 30).addClass('animated bounceInDown');

                $cortina.one('click', function(event){
                    event.stopPropagation();
                    $cortina.removeClass('loaded').remove();
                });

                $cortina.find('.lightbox').on('click',function(event){
                    event.stopPropagation();
                });

                self.eventsHandler( $cortina.find('[data-func]') );

                var $cortinaForm = $cortina.find('form[data-validate]');

                if($cortinaForm){
                    $cortinaForm.on('submit', function (event) {
                        self.validateForms(event);
                    });
                    $cortinaForm.find('[required]').on('blur keyup change', function (event) {
                        self.validateForms(event);
                    });

                    $cortinaForm.find('input[type="text"], input[type="email"]').on('blur', function (event) {
                        self.showCorrector(event);
                    });

                    $cortinaForm.find('input[type="password"]').on('keyup', function (event) {
                        self.showPassViewer(event);
                    });
                }

                if( ! Modernizr.svg ) { self.svgFallback( $cortina.find('[data-svgfallback]') ); }
				
				//var fondoIndex = $('.lightbox .tab-control').data('index');
				
				function clearActive() {
					$('.lightbox').find('[data-index]').removeClass('active');
				}
				
				
			//	initialize_owl($('.owl-carousel-2'));
			//
			//function initialize_owl(el) {
			//	el.owlCarousel({
			//		stagePadding: 20,
			//		loop:false,
			//		nav:true,
			//		responsive:{
			//			0:{
			//				items:1,
			//				stagePadding: 30
			//			},
			//			600:{
			//				items:1,
			//				stagePadding: 30
			//			},
			//			1000:{
			//				items:1,
			//				stagePadding: 30
			//			}
			//		}
			//	});
			//}
				

            }});

        },
        setScreen : function(){
            var self = this;
            var cortina = '<div class="screen" data-func="closeModal"></div>';
            self.$body.append(cortina);

            var $cortina = $('.screen');
            $cortina.height($document.height());

            $cortina.addClass('on-screen');

            return $cortina;
        },
        //Retorna truo o false si el elemento está en pantalla
        isScrolledIntoView : function(elem){
            var $elem = $(elem);
            var $window = $(window);

            var docViewTop = $window.scrollTop();
            var docViewBottom = docViewTop + $window.height();

            var elemTop = $elem.offset().top;
            var elemBottom = elemTop + $elem.height();

            return ((elemBottom <= docViewBottom) && (elemTop >= docViewTop));
        },
        //Pone la clase 'animated' y la de animacion cuando aparezca el elemento en pantalla
        animateOnView : function($elements){
            var self = this;
            $.each($elements, function(index, element){
                var $element = $(element);
                if(self.isScrolledIntoView($element)){
                    $element.addClass('animated ' + $element.data('animate'));
                }
            });
        },
        animateOnDelay : function($elements){
            var self = this;
            $.each($elements, function(index, element){
                var $element = $(element);
                if(self.isScrolledIntoView($element)){
                    $element.addClass('show-element');
                }
            });
        },
        /////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////// DELEGACIONES
        closeModal : function(event){
            event.preventDefault();
            var self = this;
            var $item = $(event.currentTarget);
            $('.screen').find('.lightbox').removeClass('bounceInDown').addClass('bounceOutUp');

            setTimeout(function(){
                $('.screen').removeClass('on-screen');
            }, 600);
            
            setTimeout(function(){
                $('.screen').remove();
            }, 900);

            if($item.data('sesion-close')){
                self.idleTime = 0;
                self.startIdleTime();
            }
        },
        selectFondo : function(event){
            var $item = $(event.currentTarget);
            var url = $item.find('option:selected').val();

            window.location.href = url;
        },
        changeValueForm : function(event){
             event.preventDefault();
             var $item = $(event.currentTarget);
             var itemValue = $item.data('value');
             var target = $item.data('input-name');

             $item.addClass('active').siblings().removeClass('active');

             $('[name="'+ target +'"]').val(itemValue);
        },
        tabsClick : function(event){
            var $item = $(event.currentTarget);
            var index = $item.data('index');
            var $parentContainer = $item.parent();
            var $tabsContainer = $parentContainer.next();

            $('.hidded.flying').removeClass('active');

            $item.addClass('active').siblings().removeClass('active');
            $tabsContainer.find('[data-index="'+ index +'"]').addClass('active').siblings().removeClass('active');

            //Mueve el indicador
            var $indicador = $parentContainer.find('.tab-line');
            var positionTarget = $item.position().left;


            $indicador.css('left', positionTarget);
        },
        tabsClickBlock : function(event){
            var $item = $(event.currentTarget);
            var index = $item.data('index');
            var $parentContainer = $item.parent();
            var $tabsContainer = $parentContainer.next();
            var $targetElement = $tabsContainer.find('[data-index="'+ index +'"]');

            $item.toggleClass('active');
            $('[data-func="tabsClickBlock"]').not($item).removeClass('active');

            $targetElement.toggleClass('active');
            $('.block-tab-holder').find('[data-index]').not($targetElement).removeClass('active');

        },
        showSemiModal : function(event){
            event.preventDefault();
            var $item = $(event.currentTarget);
            var target = $item.data('target');
            var $targetElement = $('[data-show-element="'+ target +'"]');
            var $hiddingInputs = $targetElement.find('[data-validate-on-show]');
            var $detonated = $('[data-detonating-element]');
            var action = $item.data('action');

            if(action != 'hide'){
                $targetElement.addClass('active').find('[data-validate-on-show]');
                $hiddingInputs.attr('data-validate-on-show','true');
                $detonated.addClass('to-the-front');
            }else{
                $targetElement.removeClass('active');
                $detonated.removeClass('to-the-front');
                $hiddingInputs.attr('data-validate-on-show','false');
            }
        },
        toogleFieldset : function(event){
            var $item = $(event.currentTarget);
            var target = $item.data('target');
            var $targetElement = $('[data-show-element="'+ target +'"]');

            $targetElement.toggleClass('active');
            if($targetElement.hasClass('active')){
                $targetElement.find('[data-validate-on-show]').attr('data-validate-on-show', true);
            }else{
                $targetElement.find('[data-validate-on-show]').attr('data-validate-on-show', false);
            }

            $.each($('[data-func="toogleFieldset"]'), function(index, element){
                var $element = $(element);
				//console.log($element);
                if($element.prop('checked') == true){
                    $('.btn').removeClass('hidden');
					$('.disclaimer-fondos').removeClass('hidden');
                    return false;
                }else{
                    $('.btn').addClass('hidden');
					$('.disclaimer-fondos').addClass('hidden');
                }
            });
            
        },
        showSemiModalEmail : function(event){
			event.preventDefault();
			event.stopPropagation();

			var isValid = true; 
			var $item = $(event.currentTarget);
			var $form = $item.parents('form');
			var $input = $form.find('.rut-input');
			var elementValue = $input.val();

			if( $input.hasClass('rut-input') && $.Rut.validar(elementValue) == false){

			    var customMessage = $input.data('custom-message'); //Mensaje customizado
			    var $parentHolder = $input.parent(); //Elemento padre
			    var type = $input.attr('type'); //Tipo de input
			    isValid = false; //flag

			    $input.addClass('invalid-input').removeClass('valid-input'); //Agrega la clase de inválido y quita la clase de válido

			    if(!$input.next().is('.error-message') && event.type == 'submit' && customMessage && !$input.hasClass('in-text')){
			        $input.after('<p class="error-message">'+ customMessage +'</p>'); //Agrega mensaje de error si es que este no existe
			    }

			    if(($parentHolder.find('.error-img').length == 0) && !$input.hasClass('in-text')){
			        $parentHolder.find('.valid-img').remove();
			        $parentHolder.append('<span class="icon-element error-img"></span>'); 
			    }

			}else{
		            var $item = $(event.currentTarget);

		            $item.prev().removeClass('hidden-field');
					$item.next().fadeIn();
		            $item.html('Enviar');	

		            //$item.off('click');
		            $item.on('click',function(event){
		                $item.parents('form').submit();
		            });
			}

        },
        removeValue : function(event){
            var $item = $(event.currentTarget);

            $item.parent().find('input').not('.btn').val("");
            $item.remove();
			
			$('.result-box').removeClass('active');
			
        },
        showPass : function(event){
            var $item = $(event.currentTarget);
            var $input = $item.parent().find('input');
            var type = $input.attr('type');
            var v = getInternetExplorerVersion();

            $item.toggleClass('active');

            if(type == "password"){
                $input.attr('type', 'text');
            }else{
                $input.attr('type', 'password');
            }
        },
        showElement : function(event){
            var $item = $(event.currentTarget);
            var target = $item.find('option:selected').val();
            var closeTarget = $item.find('option:selected').data('close');

			//console.log($('[data-element="'+ target +'"]').length);
			
            $('[data-element="'+ target +'"]').addClass('active').siblings().removeClass('active');   
            $('.snoopy-img.five').hide();

            if(target == 'personalizado'){
                $('[data-element="'+ target +'"]').find('[data-validate-on-show]').attr('data-validate-on-show','true');
            }$('[data-element="'+ target +'"]')

            if(closeTarget){
                $('[data-element="'+ closeTarget +'"]').removeClass('active'); 
                $('[data-element="'+ closeTarget +'"]').find('[data-validate-on-show]').attr('data-validate-on-show','false');
            }
			
			//console.log(target);

            if($('[data-element="'+ target +'"]').find('[data-validate-on-show]')){
                $('[data-element="'+ target +'"]').find('[data-validate-on-show]').attr('data-validate-on-show','true');
            }
            
        },
        showElementSubfields : function(event){
            var $item = $(event.currentTarget);
            var target = $item.find('option:selected').val();
            var level = $item.data('hide');
            var closeTarget = $item.find('option:selected').data('close');

            $('[data-element="'+ target +'"]').addClass('active').siblings().removeClass('active');
            if(level){
                $('[data-level-wrapp="'+ level +'"]').find('.element-wrapp').removeClass('active');
            }

            
        },
        showElementEmpleador : function(event){
			var $item = $(event.currentTarget);
            var target = $item.find('option:selected').val();
            if( (target == 'afiliacion') || (target == 'cotizaciones') ){
            	$(".busqueda-trabajador").removeClass('hidden-element');
            }
            
        },
        showElements : function(event){
            var $item = $(event.currentTarget);
            var target = $item.find('option:selected').val();
            $(".type-uno").addClass('hidded');
            $(".type-dos").addClass('hidded');
            if( (target == 'Dependiente Antiguo') || (target == 'Dependiente Nuevo') ){
            	$(".hidded.type-uno").removeClass('hidded');
            }

            if( (target == 'Dependiente') || (target == 'Independiente') || (target == 'Voluntario') ){
            	$(".hidded.type-uno").removeClass('hidded');
            	$(".hidded.type-dos").removeClass('hidded');
            }

        },
        showMenuMobile : function(event){
            event.preventDefault();
            //$('.main-navigation-mobile-holder').toggleClass('current');
        },
		justNumbers : function(e){
			var keynum = (e.which) ? e.which : event.keyCode;
			   if (keynum == 8 || keynum == 46 || keynum == 37 || keynum == 39)
			   return true;
				
			   if (keynum>95 && keynum<106)
			   return true;
				
			   return /\d/.test(String.fromCharCode(keynum));
		},
        transformToNumber : function(event){
        	event.preventDefault();
        	 var self = this;
            var $item = $(event.currentTarget);
            var value = $item.val().split('.').join('');
			
			value = value.replace(/\D/g,'');
			$item.val(value);

            if(!value=='' && !$.isNumeric(value)){
				value = value.match(/\d+/g).join('0');
				$item.val(value);
            }

			value = '' + parseInt( value) ;
            var finalValue = self.currency(value);
            $item.val(finalValue);
			
			
//			if($item.data('limit-number') === 10){
//				alert('test');
//	            if($item.val().length > 10){
//	            	var value_new = $item.val().split('.').join('');
//	            	var value_new = value_new.substring(0, 7);
//		            var finalValue = self.currency(value_new);
//		            $item.val(finalValue);
//	            }
//            }
			
        },
        transformToNumberFc : function(valor){
        	 var self = this;
            var value = valor.toString().split('.').join('');
			
			value = value.replace(/\D/g,'');
			//$item.val(value);

            if(!value=='' && !$.isNumeric(value)){
				value = value.match(/\d+/g).join('0');
				//$item.val(value);
            }

			value = '' + parseInt( value) ;
            var finalValue = self.currency(value);
            return(finalValue);
        },
        accordeaonHandler : function(event){
            var $item = $(event.currentTarget);

            $item.toggleClass('active').next().toggleClass('active');
        },
        callInfo : function(event){
            var $item = $(event.currentTarget);
            var target = $item.data('index');

            $item.addClass('current').siblings().removeClass('current');
            $item.parent().next().find('.info-holder[data-index="'+ target +'"]').addClass('active').siblings().removeClass('active');

        },
        showForm : function(event){
            event.preventDefault();
            var $item = $(event.currentTarget);
            var $target = $item.parents('.dashed-box').find('.hidded').not('.active');

            $target.addClass('active').siblings().removeClass('active');

            
        },
        changeChart : function(event){
            var $item = $(event.currentTarget);

            $item.parents('.tab').find('img[data-num="'+ $item.data('num') +'"]').fadeIn().siblings('img').hide();
        },
        closeDisclaimer : function(event){
            var $item = $(event.currentTarget);
            $item.parent().addClass('hide');
            setTimeout(function(){
                $item.parent().remove();
				
				if ($('.disclaimer-box').length == 0) {
					$('.empty-disclaimer').fadeIn();
				}
				
            },350);
        },
        setTooltipText : function($table){
            var $tooltips = $table.find('.delete-tooltip');

            $.each($tooltips, function(index, element){
                var $element = $(element);
                var $tooltipParagraf = $element.find('p');

                var targetText = $element.attr('data-text'); //Necesitas 2 fondos para poder eliminar 1
                var currentText = $tooltipParagraf.text();

                $element.attr('data-text', currentText);
                $tooltipParagraf.text(targetText); 

            });

        },
        setInputsValues : function($table){
            var self = this;
            var selects = $table.find('select');
            var inputs = $table.find('input');

            //Setea los selects
            self.setupSelectors(selects);
            self.setupInputPercent($table.find('[name="porcentaje-1"]'), $table.find('[name="porcentaje-2"]'));
            self.setupInputPercent($table.find('[name="recaudacion-1"]'), $table.find('[name="recaudacion-2"]'));
    
        },
        setupSelectors : function(selects){
            var FondoIsSelected = false;

            selects.find('option').show();

            $.each(selects,function(index, element){
                var $element = $(element);

                if($element.find('option:selected').val()){
                    FondoIsSelected = true;
                }
            });

            if(FondoIsSelected){
                var targetOption = $('[name="destino-1"]').find('option:selected').val();
                $('[name="destino-2"]').find('option[value="'+ targetOption +'"]').hide();
            }
        },
        showMessage : function(event){
            var self = this;
            var $item = $(event.currentTarget);

            $item.toggleClass('active');
            $item.next().toggleClass('active');
        },
		
		showMessage2 : function(event){
            var self = this;
            var $item = $(event.currentTarget);

            $item.parent().toggleClass('active');
            $item.parent().next().toggleClass('active');
			//$item.find('.more-holder').toggleClass('active');
			
        },

    };
	
	
	function tabsToAccordion() {
		if( $(window).width() >= 640 ){
				
				//////////////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////////////
				//Tabs
				if( $('.acc-tab-button').hasClass('acc-mobile-button') ){
					$('.acc-tab-button').removeClass('acc-mobile-button'); // Remueve la clase mobile en los botones
				}
			
				if( $('.acc-tab-button').hasClass('active') ){ // Reviso si hay algun boton activo desde mobile
					var item = $('.acc-tab-button.active').data('index'); // Obtengo su index
					$('.deploy-'+item).addClass('active'); //Activo el content
				}else{ // Si no vienen ningun boton activo desde mobile, activo el primer elemento
					 var $item = $('.acc-tab-button').eq(0);
					 var itex = $item.data('index');
					 $item.addClass('active');
					 $('.deploy-'+itex).addClass('active');
				}
	
				// Revisa si es que existe el contenedor de los tab content
				if( $('.acc-tabs-holder').length == 0 ){
					// Si no existe lo crea
					$('.acc-tab-control-holder').after('<div class="acc-tabs-holder"></div>');
		
					//Clona los DIV Content si es que no existen
					var items = $('.acc-tab-control-holder button');
					items.each(function (i, obj) {
						var $index = $(obj).data('index');
						if( $('.deploy-'+$index).parent().hasClass('acc-tab-control-holder') ){
							$('.deploy-'+$index).clone(true).appendTo('.acc-tabs-holder');
						}
					});
		
					//Elimina los tab content que estan despues de los botones
					$('.acc-tab-control-holder').find('.acc-tab').remove();
				}
				
	
			}else{
				
				//Redimensionar a mobile
				$('.acc-tab-button').addClass('acc-mobile-button');
				
				//Clonación de divs bajo los botones
				var items = $('.acc-tab-control-holder button');
				items.each(function (i, obj) {
					var $index = $(obj).data('index');
					if( $('.deploy-'+$index).parent().hasClass('acc-tabs-holder') ){ // si no existe clona
						$('.deploy-'+$index).clone(true).insertAfter(obj);
					}
				});
				// Remove el contenedor con los content tabs
				$('.acc-tabs-holder').remove();
	
			}
	}

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////// COMIENZO
    
    
    var Main = new window.handler(); //Se genera un nuevo objeto para almacenar las funciones
    $document.ready(function(){Main.onReadySetup();}); //Se inicializan las funcionalidades en el document.ready
    $window.load(function(){ Main.onLoadSetup(); }); //Se inicializan las funcionalidades en el window.ready

    //Se inicializan las funcionalidades los eventos scroll y resize
    $window.on({
        'scroll' : function(){Main.onScrollSetup();},
        'resize' : function(){Main.onResizeSetup();}
    });
    
	//checkbox after created
	$(document).on('click', '.p-d-created', function() {
		if($(this).is(":checked")) {
			$(this).parent().find('.inline-element').toggleClass('visible');
		} else {
			$(this).parent().find('.inline-element').toggleClass('visible');
		}
	});
  
  
} (this, document, jQuery));



