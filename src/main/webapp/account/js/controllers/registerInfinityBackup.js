var app = angular.module('StarterApp')

app.controller('regInfinityCtrl', function($scope, $http, $window, $q, deviceDetector, CONSTANTS, $mdDialog, $filter) {
  if(checkInvitationFag()==false)
    //$window.location.href = "index.html";

  $scope.isLoading = true; //se muestra loader para proceder a cargar el mail actual del usuario
  $scope.resquestState = "Espera mientras se cargan tus datos"; //seteo el estado del loader
  $scope.reg = {};
  $scope.reg.rut = $window.localStorage.getItem('rut'); //rut desde pantalla anterior
  $scope.reg.rut = "169456135" //rut test
  if (!$scope.reg.rut) { //Se comprueba que existe el rut del usuario en el local storage
    $scope.error = "Hubo un problema al identificarte, inténtalo nuevamente.";
    $scope.isLoading = false;
    return false;
  }


  $scope.reg.terms_and_conditions = false; // no se han leído términos y condiciones
  $scope.llMQObj = '';
  $scope.ll2MQObj = '';
  $scope.llCMHObj = '';
  $scope.emailPattern = CONSTANTS.EMAIL_PATTERN;
  var claiden = prepareRut($scope.reg.rut.substring(0, $scope.reg.rut.length - 1));
  var rutDigito = $scope.reg.rut.substring($scope.reg.rut.length - 1, $scope.reg.rut.length);
  var actualEmail = ""; //en caso de cambio de correo la trx kn31 solicita el mail actual y el mail de cambio
  $scope.emailFlag = false;
  $scope.isLoading = true;

  //Se carga el mail asociado al rut del local Storage
  $http.get(CONSTANTS.SERVICES_URL + "/ApiMQProvida/llamadaMQProvida?TRX=PE27&MAPA=PEM270S&OPCION=1&CLAIDEN=" + claiden + "&TIPERSO=&CODIDEN=01&NOMBRE=&PRIAPE=&SEGAPE=&CLAVEPA=")
  .success(function(response){ 
      //console.log(response)
      var x2js = new X2JS();
      $scope.llMQObj = x2js.xml_str2json(response);
      //console.log($scope.llMQObj)
      if ($scope.llMQObj.llamadaMQ.Salida.Estado == "OK") {
              $http.get(CONSTANTS.SERVICES_URL + "/ApiMQProvida/llamadaMQProvida?TRX=KN31&MAPA=KNM310S&CLAIDEN=" + claiden + "&DIGIDEN=" + rutDigito +"&NUMCLIE="+$scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE +"&OPCION=C&EMAIL=&EMAIL1=&EMAIL2=")
              .success(function(response){ 
                  //console.log(response)
                  var x2js = new X2JS();
                  $scope.ll2MQObj = x2js.xml_str2json(response);
                  $scope.mail = $scope.ll2MQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.EMAILSA;
                  actualEmail = $scope.mail;
                  $scope.isLoading = false;
              }).error(function(data, status, headers, config){  
                  $scope.error = CONSTANTS.SERVER_ERROR;
                  $scope.isLoading = false;
              })
      }else{
            $scope.error = $scope.llMQObj.llamadaMQ.Salida.ER.Descripcion;
            $scope.isLoading = false;
      }
  }).error(function(data, status, headers, config){  
        $scope.error = CONSTANTS.SERVER_ERROR;
        $scope.isLoading = false;
  })



// $scope.isLoading = false;



function checkInvitationFag(){
  var isInvitation = $window.localStorage.getItem('isToRegisterProvidaBox');
  if (isInvitation == "true") {
    return true;
  }else{
    return false;
  }

}


$scope.emailModify = function(toDo) {
  $scope.error = "";
  //console.log("nuevo mail ", $scope.mail)
  if (toDo == "setEmail") {
    $scope.emailFlag = false;
    $scope.isLoading = true;
    $scope.resquestState = "Registrando nuevo correo";
     if (actualEmail=="" || actualEmail == undefined) {
                var optionsRequest = "&OPCION=I&EMAIL=" + $scope.mail+ "&EMAIL1=" + $scope.mail + "&EMAIL2=" + $scope.mail;
      }else{
                var optionsRequest = "&OPCION=M&EMAIL="+actualEmail+"&EMAIL1=" + $scope.mail + "&EMAIL2=" + $scope.mail;
      }
            $http.get(CONSTANTS.SERVICES_URL + "/ApiMQProvida/llamadaMQProvida?TRX=KN31&MAPA=KNM310S&CLAIDEN=" + claiden + "&DIGIDEN=" + rutDigito +"&NUMCLIE="+$scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE + optionsRequest)
            .success(function(response){ 
                //console.log(response)
                var x2js = new X2JS();
                var llMailMQObj = x2js.xml_str2json(response);
                //console.log(llMailMQObj)

                 if (llMailMQObj.llamadaMQ.Salida.Estado == "OK") {
                     $scope.isLoading = false;
                     $scope.resquestState = "";
                 }else{
                      $scope.error = "Ocurrio un problema al actualizar tu correo";
                      $scope.isLoading = false;
                      $scope.resquestState = "";
                 }

            }).error(function(data, status, headers, config){  
                          $scope.error = CONSTANTS.SERVER_ERROR;
                          $scope.isLoading = false;
                          $scope.resquestState = "";
            })

  }else{
   $scope.emailFlag = true;
   $scope.mail = "";
  }
}






function checkEmail(email){
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

$scope.register = function(){
  $scope.error = "";
  if ($scope.reg.terms_and_conditions == false || $scope.reg.terms_and_conditions == undefined) {
    $mdDialog.show(
      $mdDialog.alert()
        .parent(angular.element(document.querySelector('#popupContainer')))
        .clickOutsideToClose(true)
        .title('Términos y Condiciones')
        .textContent('Debes Aceptar los Términos y Condiciones.')
        .ariaLabel('Términos y Condiciones')
        .ok('Aceptar')
        //.targetEvent(ev)
    );
  }else{

     

      $scope.isLoading = true;
      $scope.resquestState = "Conectando con el servidor";
      // window.location.href = "registro-infinity-exitoso.html";
                  console.log($scope.ll2MQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.EMAILSA)

                           $http.post(CONSTANTS.SERVICES_URL + "/Provida/AccountCreate", {"email":$scope.mail, "firstName":$scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NOMBRE , "lastName": $scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.PRIAPE}) //ACC CREATE
                                    .success(function(response){ 
                                      console.log("response ACC CREATE")
                                      console.log(response)
                                          if (response.Id != undefined && response.Id != "") {
                                                console.log(response.Id)
                                                var id_infinity = response.Id;
                                                 $http.get(CONSTANTS.SERVICES_URL + "/ApiMQProvida/llamadaMQProvida?TRX=KNZ2&MAPA=KNMCZ21&OPCION=E&NUMCLIE="+ $scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE +"&NRODOCU=&DV=&EMAIL=" + $scope.mail + "&FAX=&CELULAR=&AGENCIA=&CANAL=INF&IDINFIN="+id_infinity+"&TIPOSER=INF&ESTADO=A") //ACC CREATE
                                                  .success(function(response){ 
                                                        console.log("response KNz2")
                                                        console.log(response)
                                                          var x2js = new X2JS();
                                                          var ll3MQObj = x2js.xml_str2json(response);
                                                          console.log(ll3MQObj)

                                                        if (ll3MQObj.llamadaMQ.Salida.Estado == "OK") {
                                                              
                                                                    $scope.resquestState = "Configurando cuenta Provida Box";
                                                                    $http({
                                                                        url: CONSTANTS.SERVICES_URL + "/Provida/Confirm?id=" + id_infinity,
                                                                        method: 'GET',
                                                                        transformResponse: [function (data) {
                                                                            // Do whatever you want!
                                                                            console.log(data);
                                                                            if (data.trim() == "{'status':'OK'}") {
                                                                                console.log("registro: ok")
                                                                                console.log(data);
                                                                                $scope.resquestState = "";
                                                                                $window.localStorage.setItem('mail',$scope.mail);
                                                                                //$window.location.href = CONSTANTS.OK_GET_PASS_LINK;


                                                                                        // Consulta CMH
                                                                                        $http.get(CONSTANTS.SERVICES_URL + "/ApiMQProvida/llamadaMQProvida?TRX=KNIF&MAPA=KNIF01S&NUMCLIE="+$scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE+"&OPCION=2")
                                                                                        .success(function(response){ 
                                                                                             
                                                                                                var x2js = new X2JS();
                                                                                                $scope.llCMHObj = x2js.xml_str2json(response);
                                                                                                console.log($scope.llCMHObj)  
                                                                                                //console.log("CMH? " + $scope.llCMHObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.RETORNO)
                                                                                                if(!$scope.llCMHObj.llamadaMQ.Salida.RESPUESTAS){
                                                                                                        console.log("CMH: NO")
                                                                                                        //$window.location.href = CONSTANTS.OK_GET_PASS_LINK;
                                                                                                }
                                                                                                if($scope.llCMHObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.RETORNO != undefined && $scope.llCMHObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.RETORNO == "SI"){


                                                                                                            // Certificado de Afiliaciones (CAF)
                                                                                                            //$http.get(CONSTANTS.SERVICES_URL + "/packBienvenida/GeneraPDF?email="+$scope.mail+"&id="+id_infinity+"&reporte=CAF&cliente="+$scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE+"&rut="+claiden+"&dv="+rutDigito)
                                                                                                            console.log("http://10.172.130.40:8081/packBienvenida/GeneraPDF?email="+$scope.mail+"&id="+id_infinity+"&reporte=CAF&cliente="+$scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE+"&rut="+claiden+"&dv="+rutDigito)
                                                                                                            $http.get(CONSTANTS.SERVICES_URL + "/packBienvenida/GeneraPDF?email="+$scope.mail+"&id="+id_infinity+"&reporte=CAF&cliente="+$scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE+"&rut="+claiden+"&dv="+rutDigito)
                                                                                                            .success(function(response){ 
                                                                                                                console.log(response)   
                                                                                                                
                                                                                                                
                                                                                                                $http.get("http://10.172.130.40:8081/packBienvenida/GeneraPDF?email="+$scope.mail+"&id="+id_infinity+"&reporte=CCO&cliente="+$scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE+"&rut="+claiden+"&dv="+rutDigito)
                                                                                                                .success(function(response){ 
                                                                                                                    console.log(response)   
                                                                                                                        $http.get("http://10.172.130.40:8081/packBienvenida/GeneraPDF?email="+$scope.mail+"&id="+id_infinity+"&reporte=CVP&cliente="+$scope.llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE+"&rut="+claiden+"&dv="+rutDigito)
                                                                                                                        .success(function(response){ 
                                                                                                                            console.log(response)   
                                                                                                                            $window.location.href = CONSTANTS.OK_GET_PASS_LINK;
                                                                                                                                          
                                                                                                              
                                                                                                                        }).error(function(data, status, headers, config){  
                                                                                                                                              $scope.error = CONSTANTS.SERVER_ERROR;
                                                                                                                                              $scope.isLoading = false;
                                                                                                                                               console.log(data)  
                                                                                                                                               console.log(status)  
                                                                                                                        })
                                                                                                                    
                                                                                                                                  
                                                                                                      
                                                                                                                }).error(function(data, status, headers, config){  
                                                                                                                                      $scope.error = CONSTANTS.SERVER_ERROR;
                                                                                                                                      $scope.isLoading = false;
                                                                                                                                       console.log(data)  
                                                                                                                                       console.log(status)  
                                                                                                                })



                                                                                                  
                                                                                                            }).error(function(data, status, headers, config){  
                                                                                                                                  $scope.error = CONSTANTS.SERVER_ERROR;
                                                                                                                                  $scope.isLoading = false;
                                                                                                            })



                                                                                                }else{
                                                                                                  //redirijir, no tiene CMH
                                                                                                  console.log("sin CMH");
                                                                                                  //$window.location.href = CONSTANTS.OK_GET_PASS_LINK;
                                                                                                }
                                                                                                


                                                                                        }).error(function(data, status, headers, config){  
                                                                                                              $scope.error = CONSTANTS.SERVER_ERROR;
                                                                                                              $scope.isLoading = false;
                                                                                        })


                                                                            }else{
                                                                              $scope.error = CONSTANTS.ACC_CONFIRM_ERROR;
                                                                              $scope.isLoading = false;
                                                                              $scope.resquestState = "";
                                                                            }
                                                                            
                                                                        }]
                                                                      });



                                                        }else{
                                                            $scope.error = CONSTANTS.KNZ2_ERROR;
                                                            $scope.isLoading = false;
                                                            $scope.resquestState = "";
                                                        }
                                                 
                                                 
                                                  }).error(function(data, status, headers, config){  
                                                        $scope.error = CONSTANTS.SERVER_ERROR;
                                                        $scope.isLoading = false;
                                                        $scope.resquestState = "";
                                                  })








                                          }else{
                                              $scope.error = CONSTANTS.ACC_CREATE_ERROR;
                                              $scope.isLoading = false;
                                              $scope.resquestState = "";
                                          }
                                   
                                   
                                    }).error(function(data, status, headers, config){  
                                          $scope.error = CONSTANTS.SERVER_ERROR;
                                          $scope.isLoading = false;
                                          $scope.resquestState = "";
                                    })



















                    // $http.post(CONSTANTS.SERVICES_URL + "/Provida/AccountCreate")
                    // .success(function(response){ 
                    // //console.log(response)
                    // var x2js = new X2JS();
                    // var $scope.llMQObj = x2js.xml_str2json(response);
                    //     if ($scope.llMQObj.llamadaMQ.Salida.Estado == "OK") {
                    //       $window.location.href = "registro-infinity-exitoso.html";
                    //       $scope.isLoading = false;
                    //     }else{
                    //       $scope.error = $scope.llMQObj.llamadaMQ.Salida.ER.Descripcion;
                    //       $scope.isLoading = false;
                    //     }
                    // }).error(function(data, status, headers, config){  
                    //       $scope.error = "Ocurrio un problema al procesar tu solicitud, intenta en otro momento";
                    //       $scope.isLoading = false;
                    // })
  }
    
}  
function setTerms(){
  $scope.reg.terms_and_conditions = true;
}  

$scope.status = '  ';
$scope.customFullscreen = false;



  $scope.showAdvanced = function(ev) {
    $mdDialog.show({
      controller: DialogController,
      templateUrl: 'terms-and-conditions.html',
      parent: angular.element(document.body),
      targetEvent: ev,
      clickOutsideToClose:true,
      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
    })
    .then(function(answer) {
      $scope.status = 'You said the information was "' + answer + '".';
    }, function() {
      $scope.status = 'You cancelled the dialog.';
    });
  };

function prepareRut(rut){
  //console.log()
  var zerosToConcat = 20 - rut.length;
  for (var i = 0; i < zerosToConcat; i++) {
    rut = '0' + rut;
  }
   return rut;
}

 function DialogController($scope, $mdDialog) {
    $scope.termsTitle =CONSTANTS.TITLE_TERMS;
    
    $scope.termsContent = CONSTANTS.TERMS;

    $scope.boxTermsTitle = CONSTANTS.TITLE_BOX_TERMS;
    
    $scope.hide = function() {
      $mdDialog.hide();
    };

    $scope.cancel = function() {
      $mdDialog.cancel();
    };
    $scope.print = function(){
      var docDefinition = { content: [
    // using a { text: '...' } object lets you set styling properties
    { text: $scope.termsTitle, fontSize: 15, margin: [ 5, 2, 10, 20 ]},

    // if you set the value of text to an array instead of a string, you'll be able
    // to style any part individually
    {
      text: [
        $scope.termsContent
      
      ]
    }
  ]};
      pdfMake.createPdf(docDefinition).open();
    }
    $scope.answer = function(answer) {
      $mdDialog.hide(answer);
      if (answer=="accepted"){
        setTerms();
      }
    };
  }
})

