myApp.controller("loginController",function ($scope,  $state, loginService ) {	
	this.login = function () {
		var user = {};
		user.email = $scope.email;
		user.password = $scope.password;
		var httpObje = loginService.login(user);
		
		httpObje.then(function (data) {
			console.log(data);
			var resp = data.data;
            if( resp.status == 1)
            {
            	$state.go("toDoItem");
            }
			else
			{
				$scope.emailError = data.data.emailError;
				
				var message = data.data.message;
				$scope.errorMessage = message;
			}
		})
		.catch( function(error){
			console.log(error);
		});
	}	
});


myApp.service("loginService",function ($http) {
	this.login = function(user){ 
		return $http({
			url:"http://localhost:8090/toDoApp/login",
			method:"post",
			data:user
		});
	}
});