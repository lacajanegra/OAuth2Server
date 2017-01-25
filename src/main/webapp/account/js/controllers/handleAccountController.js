var app = angular.module('StarterApp')
app.controller('handler', function($scope, $http, $window, $q, CONSTANTS, $cookies) {
  $scope.isLoading = true;
  var expireDate = new Date();
  expireDate.setDate(expireDate.getDate() + 1);
  expireDate.setMinutes(expireDate.getMinutes() + 1);
  $cookies.put("URI_COOKIE", "http://prov-inf-qa.provida.cl:8181/oauth2/rest/auth?response_type=code&client_id=test&redirect_uri=http://localhost:8888/testprovida/&state=xyz&token=de750f1a-0de8-472a-9b99-6713b05b30ab" ,  {'path': '/', 'domain':'localhost', 'expires': expireDate});

  var cookie = $cookies.get("URI_COOKIE");
  
})