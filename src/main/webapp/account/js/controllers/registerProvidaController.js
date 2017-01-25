var app = angular.module('StarterApp')

app.controller('regProvidaCtrl', function($scope, $http, $window, $q, deviceDetector, CONSTANTS, $mdDialog, $filter) {
  $scope.reg = {};
  //$window.localStorage.setItem('isInvitation', true);
  if(checkInvitationFag()==false) // Comprobando que la flag indique que es un usuario con ivnitación valida
    $window.location.href = "index.html";
  

//sin json

                                                                    // $http({
                                                                    //     url: CONSTANTS.SERVICES_URL + "/Provida/Confirm?id=asdas",
                                                                    //     method: 'GET',
                                                                    //     transformResponse: [function (data) {
                                                                    //         // Do whatever you want!
                                                                    //         console.log(data);
                                                                    //         if (data == "{status:'OK'}") {
                                                                    //             console.log("registro: ok")
                                                                    //             console.log(data);
                                                                    //             $window.location.href = "registro-infinity-exitoso.html";
                                                                    //         }
                                                                    //         if (data.trim() == "{'status':'ERROR'}") {
                                                                    //             console.log("registro: no")
                                                                    //             console.log(data);
                                                                    //             //$window.location.href = "registro-infinity-exitoso.html";
                                                                    //         }
                                                                            
                                                                    //         //$window.location.href = "registro-infinity-exitoso.html";
                                                                    //     }]
                                                                    //   });


//con jsonn

 // $http.get(CONSTANTS.SERVICES_URL + "/Provida/Confirm?id=sadas") //ACC CONFIG
 //                                                                    .success(function(response){ 
 //                                                                          console.log("response ACC ONFIG")
 //                                                                          console.log(response)
 //                                                                          if (response == "{status:'OK'}") {
 //                                                                            console.log("tofo biuen")
 //                                                                          }

                                                                   
                                                                   
 //                                                                    }).error(function(data, status, headers, config){  
 //                                                                          $scope.error = "Ocurrio un problema al procesar tu solicitud, intenta en otro momento (Conexión ACC Config)";
 //                                                                          $scope.isLoading = false;
 //                                                                    })


// var test = "<llamadaMQ><Parametros><Trx>PEA3</Trx><OPCION>A</OPCION><CODIDEN>01</CODIDEN><CLAIDEN>00000000000015933879</CLAIDEN><DIGIDEN>7</DIGIDEN><IDENPRI>S</IDENPRI><FECEXPD>0001-01-01</FECEXPD><CIUDAD></CIUDAD><ESTADO></ESTADO><PAIS></PAIS><TIPDCAP></TIPDCAP><NDOCAPR></NDOCAPR><FOLIO></FOLIO><EXTENSI></EXTENSI><NUMCLIE></NUMCLIE><TIPOPER>15</TIPOPER><ESTCLIE></ESTCLIE><NOMBRE>FRANCISCO</NOMBRE><SEGNOM>ANDRES</SEGNOM><PRIAPE>COVARRUBIAS</PRIAPE><SEGAPE>SOTO</SEGAPE><TERAPE></TERAPE><ALIAS></ALIAS><FENACIM>1995-04-29</FENACIM><FEDEFUN>0001-01-01</FEDEFUN><LUNACIM></LUNACIM><PANACIM></PANACIM><PARECID></PARECID><PENACIO></PENACIO><FENACIO>0001-01-01</FENACIO><FEINCCN>0001-01-01</FEINCCN><SEXO>M</SEXO><ESTCIVI>S</ESTCIVI><SUJGRUP></SUJGRUP><SUJSUB></SUJSUB><ACTECON></ACTECON><NIVELES></NIVELES><GRADINV></GRADINV><FEDECIN>0001-01-01</FEDECIN><INDEMBA></INDEMBA><FECEMBA>0001-01-01</FECEMBA><CANALIN>0006</CANALIN><UNIDGES>1712</UNIDGES><FEINIRE>0001-01-01</FEINIRE><FEINACT>0001-01-01</FEINACT><CONTACT></CONTACT><TECONTA></TECONTA><CACONTA></CACONTA><INDAFIL>N</INDAFIL><INDRELB></INDRELB><INDIFON></INDIFON><INDNOUB></INDNOUB><INDLEY5></INDLEY5><FEACLEY>0001-01-01</FEACLEY><SECDOPR>00</SECDOPR><CODOBSO></CODOBSO><CODCOCO></CODCOCO><INDEMPL>N</INDEMPL><INFO2></INFO2><FEMATRI>0001-01-01</FEMATRI><LUMATRI></LUMATRI><ESTUDIA>N</ESTUDIA><SERVMIL>N</SERVMIL><INCOLPU>N</INCOLPU><FNOTLPU>0001-01-01</FNOTLPU><FCONFPU>0001-01-01</FCONFPU><FINCLPU>0001-01-01</FINCLPU><SNOTLPU>N</SNOTLPU><AREAREP></AREAREP><CODBENE></CODBENE><MapaSalida>PEM11CS</MapaSalida></Parametros><Salida><Estado>ERROR</Estado><ER><Codigo>PEE0011</Codigo><Descripcion>PERSONA YA EXISTE CON EL MISMO ROL</Descripcion></ER></Salida></llamadaMQ>";
// var test = "<llamadaMQ><Parametros><Trx>PEA3</Trx><OPCION>A</OPCION><CODIDEN>01</CODIDEN><CLAIDEN>00000000000015933879</CLAIDEN><DIGIDEN>7</DIGIDEN><IDENPRI>S</IDENPRI><FECEXPD>0001-01-01</FECEXPD><CIUDAD></CIUDAD><ESTADO></ESTADO><PAIS></PAIS><TIPDCAP></TIPDCAP><NDOCAPR></NDOCAPR><FOLIO></FOLIO><EXTENSI></EXTENSI><NUMCLIE></NUMCLIE><TIPOPER>15</TIPOPER><ESTCLIE></ESTCLIE><NOMBRE>FRANCISCO</NOMBRE><SEGNOM>ANDRES</SEGNOM><PRIAPE>COVARRUBIAS</PRIAPE><SEGAPE>SOTO</SEGAPE><TERAPE></TERAPE><ALIAS></ALIAS><FENACIM>1995-04-29</FENACIM><FEDEFUN>0001-01-01</FEDEFUN><LUNACIM></LUNACIM><PANACIM></PANACIM><PARECID></PARECID><PENACIO></PENACIO><FENACIO>0001-01-01</FENACIO><FEINCCN>0001-01-01</FEINCCN><SEXO>M</SEXO><ESTCIVI>S</ESTCIVI><SUJGRUP></SUJGRUP><SUJSUB></SUJSUB><ACTECON></ACTECON><NIVELES></NIVELES><GRADINV></GRADINV><FEDECIN>0001-01-01</FEDECIN><INDEMBA></INDEMBA><FECEMBA>0001-01-01</FECEMBA><CANALIN>0006</CANALIN><UNIDGES>1712</UNIDGES><FEINIRE>0001-01-01</FEINIRE><FEINACT>0001-01-01</FEINACT><CONTACT></CONTACT><TECONTA></TECONTA><CACONTA></CACONTA><INDAFIL>N</INDAFIL><INDRELB></INDRELB><INDIFON></INDIFON><INDNOUB></INDNOUB><INDLEY5></INDLEY5><FEACLEY>0001-01-01</FEACLEY><SECDOPR>00</SECDOPR><CODOBSO></CODOBSO><CODCOCO></CODCOCO><INDEMPL>N</INDEMPL><INFO2></INFO2><FEMATRI>0001-01-01</FEMATRI><LUMATRI></LUMATRI><ESTUDIA>N</ESTUDIA><SERVMIL>N</SERVMIL><INCOLPU>N</INCOLPU><FNOTLPU>0001-01-01</FNOTLPU><FCONFPU>0001-01-01</FCONFPU><FINCLPU>0001-01-01</FINCLPU><SNOTLPU>N</SNOTLPU><AREAREP></AREAREP><CODBENE></CODBENE><MapaSalida>PEM11CS</MapaSalida></Parametros><Salida><Estado>OK</Estado><AV><Codigo>PEA0007</Codigo><Descripcion>OK. ALTA EFECTUADA</Descripcion></AV><RESPUESTAS><RESPUESTA><NUMCLIE>76175937</NUMCLIE></RESPUESTA></RESPUESTAS></Salida></llamadaMQ>"
// $scope.isLoading = true;

$scope.reg.terms_and_conditions = false; // no se han leído términos y condiciones
$scope.reg.rut = $window.localStorage.getItem('rut'); //rut desde pantalla anterior



function checkInvitationFag(){
  var isInvitation = $window.localStorage.getItem('isInvitation');
  if (isInvitation == "true") {
    return true;
  }else{
    return false;
  }

}
$scope.test = function(){
  // $scope.error = 'Debes rellenar los campos: ';
  // // if ($scope.reg.second_name == undefined || $scope.reg.second_name == "") {
  // //       $scope.reg.second_name = "lo modifique";
  // //   }
  // // console.log("nac" , $scope.reg.birthday_year+"-"+$scope.reg.birthday_month+"-"+$scope.reg.birthday_day)
  // // console.log("rut" , $scope.reg.rut)
  // // console.log("nombre" , $scope.reg.first_name)
  // // console.log("seungo nombre" , $scope.reg.second_name)
  // // console.log("aplleido" , $scope.reg.last_name)
  // // console.log("segundo apellido" , $scope.reg.second_last_name)
  // // console.log("telefono" , $scope.reg.phone)
  // // console.log("genero" , $scope.reg.gender)
  // console.log($scope.myFormRegister.$error.required)
  // if($scope.myFormRegister.$invalid){
  //   console.log($scope.myFormRegister.$invalid)
  //   console.log("corrige los errore")
  // }

  //   console.log($scope.myFormRegister.$error.required.forEach(function(data){
  //     $scope.error = $scope.error + " " + data.$name;
  //     console.log(data.$name)
  //   }))
  
}


function inputsValidation(){
  // console.log($scope.myFormRegister)
  if ($scope.myFormRegister.$invalid) {
      $scope.error = CONSTANTS.FORM_ERROR;
      return false;
  }
  return true;
}

function checkInputs(){
    if(!$scope.myFormRegister.$error.required){
      $scope.error = "";
      return true;
    }
    $scope.error = CONSTANTS.MISSING_FIELDS_ERROR;
    for (var i = $scope.myFormRegister.$error.required.length - 1; i >= 0; i--) {
      // console.log($scope.myFormRegister.$error.required[i].$name)
      $scope.error = $scope.error + $scope.myFormRegister.$error.required[i].$name;
        if (i!=0) {
          $scope.error=$scope.error + ", ";
        }

    }
    return false;

}


$scope.register = function(){
  
    if (!checkInputs())
      return false;
    if (!inputsValidation())
      return false;

    if ($scope.reg.second_name == undefined || $scope.reg.second_name == "") {
        $scope.reg.second_name = " ";
    }
    $scope.error="";
    var claiden = prepareRut($scope.reg.rut.substring(0, $scope.reg.rut.length - 1));
    var rutDigito = $scope.reg.rut.substring($scope.reg.rut.length - 1, $scope.reg.rut.length);
    // var dateBirthday = moment($scope.reg.birthday).format('YYYY-MM-DD')
    var dateBirthday = $scope.reg.birthday_year+"-"+$scope.reg.birthday_month+"-"+$scope.reg.birthday_day;
      if ($scope.reg.terms_and_conditions == false || $scope.reg.terms_and_conditions == undefined) {
        $mdDialog.show(
          $mdDialog.alert()
            .parent(angular.element(document.querySelector('#popupContainer')))
            .clickOutsideToClose(true)
            .title('Términos y Condiciones')
            .textContent('Debes Aceptar los Términos y Condiciones.')
            .ariaLabel('Términos y Condiciones')
            .ok('Aceptar')
        );
      }else{
        $scope.resquestState = "Conectando con el servidor";
          $scope.isLoading = true;
          
          $http.get(CONSTANTS.SERVICES_URL + "/ApiMQProvida/llamadaMQProvida?TRX=PEA3&MAPA=PEM11CS" +
            "&OPCION=A&CODIDEN=01&CLAIDEN=" + claiden + "&DIGIDEN="+rutDigito+"&IDENPRI=S&FECEXPD=0001-01-01&CIUDAD=&ESTADO=&PAIS=&TIPDCAP=&NDOCAPR=&FOLIO=&EXTENSI=&NUMCLIE=&TIPOPER=15&ESTCLIE=&NOMBRE=" + 
            $scope.reg.first_name + "&SEGNOM=" + $scope.reg.second_name + "&PRIAPE=" + $scope.reg.last_name + "&SEGAPE=" + $scope.reg.second_last_name + 
            "&TERAPE=&ALIAS=&FENACIM=" + dateBirthday + "&FEDEFUN=0001-01-01&LUNACIM=&PANACIM=&PARECID=&PENACIO=&FENACIO=0001-01-01&FEINCCN=0001-01-01&SEXO=" + $scope.reg.gender + 
            "&ESTCIVI=S&SUJGRUP=&SUJSUB=&ACTECON=&NIVELES=&GRADINV=&FEDECIN=0001-01-01&INDEMBA=&FECEMBA=0001-01-01&CANALIN=0006&UNIDGES=1712&FEINIRE=0001-01-01&FEINACT=0001-01-01&CONTACT=&TECONTA=" + 
            "&CACONTA=&INDAFIL=N&INDRELB=&INDIFON=&INDNOUB=&INDLEY5=&FEACLEY=0001-01-01&SECDOPR=00&CODOBSO=&CODCOCO=&INDEMPL=N&INFO2=&FEMATRI=0001-01-01&LUMATRI=&ESTUDIA=N&SERVMIL=N&INCOLPU=N"+
            "&FNOTLPU=0001-01-01&FCONFPU=0001-01-01&FINCLPU=0001-01-01&SNOTLPU=N&AREAREP=&CODBENE=")
                        .success(function(response){ 
                        // console.log(response)
                        var x2js = new X2JS();
                        var llMQObj = x2js.xml_str2json(response);
                        // console.log("respuesta PEA3")
                        // console.log(llMQObj)
                        if (llMQObj.llamadaMQ.Salida.Estado == "OK") {
                          //$window.location.href = "registro-infinity-exitoso.html";
                          //$scope.isLoading = false;
                    $scope.resquestState = "Creando cuenta";
                    $http.get(CONSTANTS.SERVICES_URL + "/ApiMQProvida/llamadaMQProvida?TRX=KN31&MAPA=KNM310S&CLAIDEN=" + claiden + "&DIGIDEN=" + rutDigito +"&NUMCLIE="+llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE +"&OPCION=I&EMAIL=" + $scope.reg.mail + "&EMAIL1=" + $scope.reg.mail + "&EMAIL2=" + $scope.reg.mail)
                            .success(function(response){ 
                              // console.log("respuesta KN31")
                              //console.log(response)
                              var x2js = new X2JS();
                              var ll2MQObj = x2js.xml_str2json(response);
                              // console.log(ll2MQObj)
                              //console.log(ll2MQObj.llamadaMQ.Salida.Estado)
                              if (llMQObj.llamadaMQ.Salida.Estado == "OK") {




                                  $scope.resquestState = "Configurando cuenta";
                                  //$window.location.href = "registro-infinity-exitoso.html";
                                   $http.post(CONSTANTS.SERVICES_URL + "/Provida/AccountCreate", {"email":$scope.reg.mail , "firstName":$scope.reg.first_name , "lastName":$scope.reg.last_name,"IsReadOnly":true}) //ACC CREATE
                                    .success(function(response){ 
                                      // console.log("response ACC CREATE")
                                      // console.log(response)
                                          if (response.Id != undefined && response.Id != "") {
                                                // console.log("id infinity generado" + response.Id)
                                                var id_infinity = response.Id;



                                                 $http.get(CONSTANTS.SERVICES_URL + "/ApiMQProvida/llamadaMQProvida?TRX=KNZ2&MAPA=KNMCZ21&OPCION=E&NUMCLIE="+ llMQObj.llamadaMQ.Salida.RESPUESTAS.RESPUESTA.NUMCLIE +"&NRODOCU=&DV=&EMAIL=" + $scope.reg.mail + "&FAX=&CELULAR=&AGENCIA=&CANAL=INF&IDINFIN="+id_infinity+"&TIPOSER=INF&ESTADO=A") //ACC CREATE
                                                  .success(function(response){ 
                                                        // console.log("response KNz2")
                                                        //console.log(response)
                                                          var x2js = new X2JS();
                                                          var ll3MQObj = x2js.xml_str2json(response);
                                                          // console.log(ll3MQObj)

                                                        if (ll3MQObj.llamadaMQ.Salida.Estado == "OK") {
                                                                    $scope.resquestState = "Conectando cuenta Provida Box";
                                                                    $http({
                                                                        url: CONSTANTS.SERVICES_URL + "/Provida/Confirm?id=" + id_infinity,
                                                                        method: 'GET',
                                                                        transformResponse: [function (data) {
                                                                            // Do whatever you want!
                                                                            // console.log(data);
                                                                            $scope.resquestState = "Terminando";
                                                                            if (data.trim() == "{'status':'OK'}") {
                                                                                // console.log("registro: ok")
                                                                                // console.log(data);
                                                                                $window.localStorage.setItem('mail',$scope.reg.mail);
                                                                                $window.location.href = CONSTANTS.OK_GET_PASS_LINK;
                                                                                $scope.resquestState = "";
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







                              }else if(llMQObj.llamadaMQ.Salida.Estado == "ERROR"){
                                  $scope.error = CONSTANTS.KN31_ERROR;
                                  $scope.isLoading = false;
                                  $scope.resquestState = "";

                              }else{
                                  $scope.error = CONSTANTS.KN31_ERROR;
                                  $scope.isLoading = false;
                                  $scope.resquestState = "";

                              }




                            }).error(function(data, status, headers, config){  
                              $scope.error = CONSTANTS.SERVER_ERROR;
                              $scope.isLoading = false;
                              $scope.resquestState = "";
                            })

                        }else{
                         $scope.error = llMQObj.llamadaMQ.Salida.ER.Descripcion;
                         $scope.isLoading = false;
                         $scope.resquestState = "";
                        }
          }).error(function(data, status, headers, config){  
            $scope.error = CONSTANTS.SERVER_ERROR;
            $scope.isLoading = false;
            $scope.resquestState = "";
          })
      }
        
}  
function setTerms(){
  $scope.reg.terms_and_conditions = true;
}  
function prepareRut(rut){
  //console.log()
  var zerosToConcat = 20 - rut.length;
  for (var i = 0; i < zerosToConcat; i++) {
    rut = '0' + rut;
  }
   return rut;
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

