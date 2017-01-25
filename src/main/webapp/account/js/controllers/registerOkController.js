var app = angular.module('StarterApp')
app.controller('regOkCtrl', function($scope, $http, $window, $q, deviceDetector, CONSTANTS, $mdDialog, $cookies) {
	clearFlagsLocalStorage();
	$scope.callCenterProvida = CONSTANTS.CALL_CENTER_PROVIDA;
	$scope.URL = $cookies.get("URI_COOKIE");
	$scope.mail = $window.localStorage.getItem('mail');
	var emailLastChars = $scope.mail.substring(4,$scope.mail.length ).replace(/.(?=)/g, "X");
	$scope.mail = $scope.mail.substring(0,4) + emailLastChars;
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() + 1);
	expireDate.setMinutes(expireDate.getMinutes() + 1);
  	$cookies.put("URI_COOKIE", "" ,  {'path': '/', 'domain':CONSTANTS.DOMAIN, 'expires': expireDate});


$scope.isKnowDomain = function(){
	var domain = getDomainOfEmail($scope.mail);
	if(getDomainOfEmail($scope.mail) == "GMAIL.COM" || getDomainOfEmail($scope.mail) == "HOTMAIL.COM" || getDomainOfEmail($scope.mail) == "LIVE.CL"){
		return true;
	}
}

$scope.getUrlMailClient = function(){
	return  "http://www." + getDomainOfEmail($scope.mail).toLowerCase();
}

function getDomainOfEmail(mail){
	var domain = mail.substring(mail.lastIndexOf("@") +1);
	return domain.toUpperCase();
}

function clearFlagsLocalStorage(){ //Limpia flags para identificar flujos de alta y delegados
	  $window.localStorage.setItem('isInvitation', false);
	  $window.localStorage.setItem('isToRegisterProvidaBox', false);
}



})

