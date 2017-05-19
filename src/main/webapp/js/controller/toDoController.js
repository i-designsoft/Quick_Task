angular.module("todoApp").controller("toDoCtrl",function($scope,$state,$window,toDoService,ModalService, $filter, $http)
{	
      
	$scope.user={};
	 $scope.todos = [];
		console.log("todo Controller...");
		 $scope.isList = true;
		 $scope.active = true;
	        $scope.active1 = true;
	        
	    
	        $(this).parent().find('ul').mouseleave(function(){  
	            var thisUI = $(this);
	            $('html').click(function(){
	              thisUI.hide();
	              $('html').unbind('click');
	            });
	          });
	       
	        
	        
	       
	     
	        
	        
	        /*$scope.changeCardColor=function(color,e){
	      	  console.log();
	  		for(var i=0;i<e.target.length;i++)
	  		{
	  			console.log(i);

	  			if(e.path[i].classList.contains('card'))
	  			{
	  				// Here color CHnage dome
	  				var backgroundColor="#fff";
	  				switch(color){
	  					case "Red":
	  						e.path[i].style.backgroundColor="#FF8A80";
	  						break;
	  					case "White":
	  						e.path[i].style.backgroundColor="#fff";
	  						break;
	  					case "Gray":
	  						e.path[i].style.backgroundColor="#CFD8DC";
	  						break;
	  					case "Blue":
	  						e.path[i].style.backgroundColor="#80D8FF";
	  						break;

	  					case "Green":
	  						e.path[i].style.backgroundColor="#CCFF90";
	  						break;

	  					case "Orange":
	  						e.path[i].style.backgroundColor="#FFD180";
	  						break;

	  					default:
	  						e.path[i].style.backgroundColor="#fff";
	  					break;
	  				}
	  			}

	  		}
	       }*/
	        
	        $scope.changeCardColor=function(color,e){
	        	console.log(color);
	        	console.log(e);
	        	$(e.target).closest( ".card" ).css( "background-color", color);
	        }
	        
	       this.doReminder= function(id,index,day) {
	    	   if(day=="Today")
				{
	    		   console.log(id);
					var today=new Date();
					var obj=$scope.todos[index];
					obj.reminder=today;
					/*console.log($scope.todos[index].reminder);
					console.log(day);
					console.log(index);*/
					this.update(id,index,obj);
					console.log(id);
				console.log("updated");
					
				}
				
	    	   else if(day=="Tomorrow")
				{
	    		   console.log(day);
					var tomorrow=new Date();
					var obj=$scope.todos[index];
					obj.reminder= tomorrow.getDate()+ 1;
					
					
					console.log(obj.reminder);
					this.update(id,index,obj);
					console.log(" tommorow updated");
				}
	    	   else if(day=="Next-Week")
				{
					var next=new Date();
					var obj=$scope.todos[index];
					obj.reminder= next.getDate()+7;
					console.log($scope.todos[index].reminder);
					
				
					console.log(index);
					this.update(id,index,obj);
					console.log(id);
					console.log(" next weekupdated");
				}
			
		}
	       
	  
	       
	       $(document).ready(function () {

	           // ANIMATEDLY DISPLAY THE NOTIFICATION COUNTER.
	           $('#noti_Counter')
	               .css({ opacity: 0 })
	               .text('7')              // ADD DYNAMIC VALUE (YOU CAN
											// EXTRACT DATA FROM DATABASE OR
											// XML).
	               .css({ top: '-10px' })
	               .animate({ top: '-2px', opacity: 1 }, 500);

	           $('#noti_Button').click(function () {

	               // TOGGLE (SHOW OR HIDE) NOTIFICATION WINDOW.
	               $('#notifications').fadeToggle('fast', 'linear', function () {
	                   if ($('#notifications').is(':hidden')) {
	                       $('#noti_Button').css('background-color', '#FFF');
	                   }
	                   else $('#noti_Button').css('background-color', '#FFF');        // CHANGE
																						// BACKGROUND
																						// COLOR
																						// OF
																						// THE
																						// BUTTON.
	               });

	               $('#noti_Counter').fadeOut('slow');                 // HIDE
																		// THE
																		// COUNTER.

	               return false;
	           });

	           // HIDE NOTIFICATIONS WHEN CLICKED ANYWHERE ON THE PAGE.
	           $(document).click(function () {
	               $('#notifications').hide();

	               // CHECK IF NOTIFICATION COUNTER IS HIDDEN.
	               if ($('#noti_Counter').is(':hidden')) {
	                   // CHANGE BACKGROUND COLOR OF THE BUTTON.
	                   $('#noti_Button').css('background-color', '#FFF');
	               }
	           });

	           $('#notifications').click(function () {
	               return false;       // DO NOTHING WHEN CONTAINER IS CLICKED.
	           });
	       });
	        
	    /*
		 * var view =$scope.isList;
		 * 
		 * if(view!=undefined || view!=null) { setCookie("ToDoView",view); }
		 * if('true'==view) { $scope.isList=true; } else { $scope.isList=false; }
		 * 
		 * $scope.viewFunction=function() { $scope.isList=getCookie("ToDoView");
		 * console.log($scope.isList); console.log("View List/Grid"); } function
		 * setCookie(cname,cvalue) { var d = new Date(); d.setTime(d.getTime() +
		 * (24*60*60*1000)); var expires = "expires=" + d.toGMTString();
		 * document.cookie = cname+"="+cvalue+"; "+expires; }
		 * 
		 * function getCookie(cname) { var name = cname + "="; var ca =
		 * document.cookie.split(';'); for(var i=0; i<ca.length; i++) { var c =
		 * ca[i]; while (c.charAt(0)==' ') c = c.substring(1); if
		 * (c.indexOf(name) == 0) { return c.substring(name.length, c.length); } }
		 * return ""; }
		 */
	       
	        $(".slides").sortable({
	            placeholder: 'slide-placeholder',
	           axis: "z",
	           revert: 1000,
	           
	           start: function(e, ui){
	               
	               placeholderHeight = ui.item.outerHeight();
	               ui.placeholder.height(placeholderHeight + 15);
	               $('<div class="slide-placeholder-animator" data-height="' + placeholderHeight + '"></div>').insertAfter(ui.placeholder);
	           
	           },
	           change: function(event, ui) {
	               
	               ui.placeholder.stop().height(0).animate({
	                   height: ui.item.outerHeight() + 15
	               }, 300);
	               
	               placeholderAnimatorHeight = parseInt($(".slide-placeholder-animator").attr("data-height"));
	               
	               $(".slide-placeholder-animator").stop().height(placeholderAnimatorHeight + 15).animate({
	                   height: 0
	               }, 300, function() {
	                   $(this).remove();
	                   placeholderHeight = ui.item.outerHeight();
	                   $('<div class="slide-placeholder-animator" data-height="' + placeholderHeight + '"></div>').insertAfter(ui.placeholder);
	               });
	               
	           },
	           stop: function(e, ui) {
	               
	               $(".slide-placeholder-animator").remove();
	               
	           },
	       });
	          
	       
	        	 
	        	  $("#cart").on("click", function() {
	        	    $(".shopping-cart").fadeToggle( "fast");
	        	  });
	        	  
	        
	        
	        
	        
	        
	        
	        
	        
	        
	       
	      /*
			 * $('.gridly').gridly({ base: 60, // px gutter: 20, // px columns:
			 * 12 });
			 */

	        $scope.openPopup=function(obj,index){
	        	
	         	// Just provide a template url, a controller and call
				// 'showModal'.
	            ModalService.showModal({
	              templateUrl: "template/popup.html",
	              controller: function($scope,close){
	            	
	            	  $scope.todo=obj;
	            	  $scope.indx=index;
	            	  $scope.close = function(result) {
	            	 	  close(result, 500); // close, but give 500ms for
												// bootstrap to animate
	            	  };
	              },
	              controllerAs:"$modalCtrl"
	            }).then(function(modal) {
	            	
	                modal.element.modal();
	                modal.close.then(function(result) {
	                  $scope.yesNoResult = result ? "You said Yes" : "You said No";
	                });
	              });
	        };
	        
	        
	        $("#menu-toggle").click(function(e) {
	            e.preventDefault();
	            $("#wrapper").toggleClass("toggled");
	        });

		 /* $scope.range = _.range(1, 5); */
	        
	       /*
			 * $("#menu-toggle").click(function(e) { e.preventDefault();
			 * $("#wrapper").toggleClass("toggled"); });
			 * 
			 * $('#open-popup').on('click', function(){
			 * $('.overlay').fadeIn('fast'); $('#popup').show('fast'); });
			 * 
			 * $('.lightbox-close').on('click', function(){
			 * $(this).closest('.lightbox').hide('fast');
			 * $('.overlay').fadeOut('fast'); });
			 */
	        	
	        	
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
		 $interval($scope.reload, 1000);
	}
	
	
	this.deleteTask=function(id){
		console.log(id);
		var httpObj=toDoService.deleteTodo(id).then(function(data){
			console.log(data);
			$state.reload();
			
		})
		
	};

	
	this.update=function(id,index,item)
	{
		
		console.log(index);
		console.log(item);
		var title = $($('#title'+id).html()).html();
	    var toDoItemDescription = $($('#description'+id).html()).html() ;
		var obj=$scope.todos[index];
		obj.id=id;
		obj.title=title;
		obj.toDoItemDescription=toDoItemDescription;
		
		
		console.log(toDoItemDescription);
		console.log(obj);
		var httpobj = toDoService.updateToDoItem(id,{title:title,toDoItemDescription:toDoItemDescription,reminder}).then(function(data){
			console.log(data);
			if(data.status==200){
				
				$state.reload();
			}
		});
	}


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
	
	
	
	
	toDoService.getUser().then(function(data)
	{
		if(data.status === 200)
		{	
			$scope.user = data.data;
			
			
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
	this.getUser= function(){ 
		return $http({url:"http://localhost:8090/toDoApp/getUser",method:"get"});
	}
	
	this.deleteTodo=function(id){
		return $http({url:"http://localhost:8090/toDoApp/toDoItem/"+id, method:"delete"});
	}
	this.logoutUser=function(){
		return $http({url:"http://localhost:8090/toDoApp/logout",method:"get"});
	}
	this.updateToDoItem=function(id, todo){
		return $http({url:"http://localhost:8090/toDoApp/updateToDoItem/"+id, method:"post",data:todo});
	}
	
});

