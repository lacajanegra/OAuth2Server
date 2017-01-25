var app = angular.module('StarterApp')

app.controller('req', function($scope, $http, $window, $q, deviceDetector, CONSTANTS, $cookies) {
  // var expireDate = new Date();
  // expireDate.setDate(expireDate.getDate() + 1);
  // expireDate.setMinutes(expireDate.getMinutes() + 1);
  // $cookies.put("URI_COOKIE", "http://prov-inf-qa.provida.cl:8181/oauth2/rest/auth?response_type=code&client_id=test&redirect_uri=http://localhost:8888/testprovida/&state=xyz&token=de750f1a-0de8-472a-9b99-6713b05b30ab" ,  {'path': '/', 'domain':'localhost', 'expires': expireDate});



  //  var expireDate = new Date();
  // expireDate.setDate(expireDate.getDate() + 1);
  // expireDate.setMinutes(expireDate.getMinutes() + 1);
  // $cookies.put("URI_COOKIE", "http://prov-inf-qa.provida.cl:8181/oauth2/rest/auth?response_type=code&client_id=IxH9bacGGyXdQCv2vWbQ%2FrWbskPZphLTRfZXDYcXIy0%3D&redirect_uri=http%3A%2F%2Fmlinf-cl-beta.cloudapp.net%2Fsignin-provida" ,  {'path': '/', 'domain':'localhost', 'expires': expireDate});
  // // $scope.isLoading = true;


$scope.checkIfEnterKeyWasPressed = function($event){

    var keyCode = $event.which || $event.keyCode;
      //console.log(keyCode)
    if (keyCode === 13 && $scope.myForm2.$invalid==false) {
        $scope.goToPass();
    }

  }
$scope.goToPass = function(){
  $scope.isLoading = true;
  var rut = $scope.rut.toString();
  $scope.rut = (rut.replace(/\./g , "")).replace(/\-/g , "");
                    $http.get(CONSTANTS.SERVICES_URL + "/oauth2/rest/checkRut?rut=" + $scope.rut)
                    .success(function(response){ 
                      //console.log(response)
                      if (response.code == "99") {
                          // console.log(response.description);
                          $scope.isLoading = false;

                          var query = $cookies.get("URI_COOKIE");

                          if(query!=undefined && query!=""){
                             
                                var token =prepareTokenFromURLCookie(query);

                                if (token == false){
                                  $window.location.href = CONSTANTS.WITHOUT_INVITATION_LINK;
                                  $scope.isLoading = false;
                                }



                                $http.get(CONSTANTS.SERVICES_URL + "/Provida/actionsExternalSystem?accion=token&token=" + token)
                                .success(function(response){ 
                                    // console.log("respuesta token ", response)
                                    if (response.status) {
                                        //console.log("formulario de alta")
                                        $window.localStorage.setItem('rut', $scope.rut);
                                        $window.localStorage.setItem('isInvitation', true); //seteo flag para identificar flujo de alta delegado (cuando no es usuario provida)
                                        $window.location.href = CONSTANTS.PROV_REG_LINK;
                                    }else{
                                      $window.location.href = CONSTANTS.WITHOUT_INVITATION_LINK;
                                    }
                                }).error(function(data, status, headers, config){  
                                    $scope.error = CONSTANTS.SERVER_ERROR;
                                    $scope.isLoading = false;
                                })

                              }else{
                                //$scope.error = "Rut desconocido.";
                                $window.location.href = CONSTANTS.WITHOUT_INVITATION_LINK;
                                $scope.isLoading = false;
                              }

                        
                          
                      }else if(response.code == "0"){
                          // console.log(response.description);
                          $window.localStorage.setItem('rut', $scope.rut);
                          $window.location.href = CONSTANTS.PASS_LINK;
                          //$scope.isLoading = false;
                      }else{
                          $scope.error = CONSTANTS.UNKNOWN_ERROR;
                          $scope.isLoading = false;
                      }
                    }).error(function(data, status, headers, config){  
                        $scope.error = CONSTANTS.SERVER_ERROR;
                        $scope.isLoading = false;
                    })
}  

function prepareTokenFromURLCookie(query){
   var result = {};
  query.split("&").forEach(function(part) {
    var item = part.split("=");
    result[item[0]] = decodeURIComponent(item[1]);
  });
  // console.log( "token: " + result.token)
  if(result.token==undefined || result.token==""){
    return false;
  }else{
    return result.token.replace('"','');
  }

  
}

})

