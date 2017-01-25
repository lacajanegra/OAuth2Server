var app = angular.module('StarterApp')
app.controller('pass', function($scope, $http, $window, $q, CONSTANTS) {
// $scope.isLoading = true;
$scope.rut = $window.localStorage.getItem('rut');
$scope.recoveryPass = CONSTANTS.RECOVERY_PASS;
$scope.checkIfEnterKeyWasPressed = function($event){

    var keyCode = $event.which || $event.keyCode;
    // console.log(keyCode)
    if (keyCode === 13) {
        $scope.login();
    }

  }


$scope.login = function() {
  $scope.error = "";
  $scope.isLoading = true;
  $scope.redirect = "";
  var objLogin={user:$scope.rut, pass:$scope.pw};
  $http.post(CONSTANTS.SERVICES_URL + "/oauth2/rest/clientLogin",angular.toJson(objLogin))
                .success(function(response){ 
                  if (response.codResponse == 0) {
                    $scope.redirect = response.data;
                    //$window.location.href = response.data;
                      $http.get(CONSTANTS.SERVICES_URL + "/oauth2/rest/notif?client_id="+objLogin.user+"&timeout="+60*5)
                      .success(function(response){ 
                          if(response.status == "done"){
                            // console.log("redirect to : " +  $scope.redirect)
                            if($scope.redirect != null && $scope.redirect.length > 1 ){
                              $window.location.href = $scope.redirect;
                              //$scope.isLoading = false;
                            }else{
                              $window.location.href = CONSTANTS.WHITHOUT_URILINK_LINK;
                              //$scope.isLoading = false;
                            }
                          }else{
                            $scope.error = "Error al comprobar datos";
                            $scope.isLoading = false;
                          }
                      }).error(function(data, status, headers, config){  
                        $scope.error = CONSTANTS.SERVER_ERROR;
                          $scope.isLoading = false;
                      })
                       
                  }else if(response.codResponse == 98){
                    $window.localStorage.setItem('isToRegisterProvidaBox', true); //seteo flag para identificar flujo de alta provida Box 
                    $window.location.href = CONSTANTS.PROV_BOX_REG_LINK;
                    $scope.isLoading = false;
                  }else if(response.codResponse == 99){
                    $scope.error = CONSTANTS.PASS_ERROR;
                    $scope.isLoading = false;
                  }
                  else{
                    $scope.isLoading = false;
                    $scope.error = response.glosaResponse;
                    $scope.isLoading = false;
                  }
                }).error(function(data, status, headers, config){  
                  $scope.error = CONSTANTS.SERVER_ERROR;
                  $scope.isLoading = false;
                })
}

})