angular.module("todoApp").controller("toDoCtrl",function($scope,toDoService){
	 $scope.todos = [];
	$scope.addToDo=function(){
		console.log($scope.title);
		console.log($scope.toDoItemDescription);
		
		if($scope.title && $scope.toDoItemDescription){
	          
	          var toDoItem = {
	  				title :$scope.title,
	  				toDoItemDescription : $scope.toDoItemDescription
	  				
	  		};
	          /*$scope.todos.push({title:$scope.title,description:$scope.toDoItemDescription, done:false});*/
	          $scope.title= '';
	          $scope.toDoItemDescription= '';
	      }
		
		
		var httpObje = toDoService.addingToDo(toDoItem);
		
	}
	toDoService.getAllTask().then(function(data){
		console.log(data);
		if(data.status == 200)
		{
			$scope.todos = data.data.list;
			console.log(todos.size());
		}
		else{
		}
	}).catch(function(){});
	
	
	
}).service('toDoService',function($http){
	this.addingToDo = function(toDoItem){
		console.log(toDoItem);
		return $http({
			url:"http://localhost:8090/toDoApp/toDoItem",
			method:"post",
			data:toDoItem
		});
	}

	this.getAllTask = function(){ 
		return $http({url:"http://localhost:8090/toDoApp/toDoList"});
	}
});

