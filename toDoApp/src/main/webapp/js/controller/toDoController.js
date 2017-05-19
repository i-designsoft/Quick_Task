angular.module("todoApp").controller("toDoCtrl",function($scope,$state,$window,toDoService)
{
	 $scope.todos = [];
		console.log("todo Controller...");
		 $scope.isList = true;
		 $scope.active = true;
	        $scope.active1 = true;
		 /* $scope.range = _.range(1, 5);*/
	        
	        $("#menu-toggle").click(function(e) {
	            e.preventDefault();
	            $("#wrapper").toggleClass("toggled");
	        });
		 
	        $state.reload=function(){console.log("dfgfbhghnbvnb");$state.reload();}
	        $scope.reloadPage = function(){$window.location.reload();}

		 
		 this.logout=function(){
			console.log('logout');
			var httpobj=toDoService.logoutUser().then(function(data){
				if(data.status===200){
					console.log("User Logout..")
					$state.go("login");
				}
			})
		}
	
	$scope.addToDo=function()
	{
		console.log($scope.reminder);
		console.log($scope.toDoItemDescription);
		
		if($scope.title && $scope.toDoItemDescription)
		{
	          
	          var toDoItem = 
	        {		
	  				title :$scope.title,
	  				reminder:$scope.reminder,
	  				toDoItemDescription : $scope.toDoItemDescription
	  				
	  		};
	          /*
				 * $scope.todos.push({title:$scope.title,description:$scope.toDoItemDescription,
				 * done:false});
				 */
	          $scope.title= '';
	          $scope.toDoItemDescription= '';
	          $scope.reminder='';
	      }
		
		
		var httpObje = toDoService.addingToDo(toDoItem);
		$state.reload();
		
	}
	
	
	this.deleteTask=function(id){
		console.log(id);
		var httpObj=toDoService.deleteTodo(id).then(function(data){
			console.log(data);
			$state.reload();
			
		})
		
	};
	
	
	this.updateEnable=function(index){
		$scope.todos=$scope.todos.map(function(ret){
			ret.update=false
			return ret;
		}); // Reset To all update
		
		$scope.todos[index].update=true; // set only one field
	}
	
this.updateToDo=function(index , id){
		
		$scope.todos[index].update=true;
		
		var obj = $scope.todos[index];
		console.log(obj);
		var httpobj = toDoService.updateToDo(id, obj).then(function(data){
			console.log(data);
			if(data.status==200){
				console.log(data.message);
				$state.reload();
			}
		})
	};

	
	toDoService.listAllToDo().then(function(data)
	{
		if(data.status === 200)
		{	
			$scope.todos = data.data;
			console.log($scope.todos.length);
			
		}
		else
		{
			console.log("else..")
		}
	}).catch(function(err){
		console.log(err);
	});
	
	
	

	
	
}).service('toDoService',function($http){
	this.addingToDo = function(toDoItem){
		return $http({
			url:"http://localhost:8090/toDoApp/toDoItem",
			method:"post",
			data:toDoItem
		});
	}

	this.listAllToDo = function(){ 
		return $http({url:"http://localhost:8090/toDoApp/toDoList",method:"get"});
	}
	
	this.deleteTodo=function(id){
		return $http({url:"http://localhost:8090/toDoApp/toDoItem/"+id, method:"delete"});
	}
	this.logoutUser=function(){
		return $http({url:"http://localhost:8090/toDoApp/logout",method:"get"});
	}
	this.updateToDo=function(id, todo){
		return $http({url:"http://localhost:8090/toDoApp/toDoUpdate/"+id, method:"post",data:todo});
	}
	
});

