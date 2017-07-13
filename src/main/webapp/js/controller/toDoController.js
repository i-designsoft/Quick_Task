angular.module("todoApp")
.filter('pinnedFilter', function() {

	  // Create the return function and set the required parameter name to **input**
	  return function(todos) {

	    var filterTodos = [];

	    // Using the angular.forEach method, go through the array of data and perform the operation of figuring out if the language is statically or dynamically typed.
	    angular.forEach(todos, function(todo) {
	    	console.log(todo.isPinned);
	      if (todo.isPinned === "false" || !todo.isPinned) {
	    	  filterTodos.push(todo)
	      }
	      
	    })

	    return filterTodos;
	  }

	})
.controller("toDoCtrl", function($scope, $interval,$state,$window, toDoService, ModalService, $filter, $http) {

    $scope.user = {};
    $scope.archivedTask = [];
    $scope.todos = [];
    $scope.pinnedTask=[];
    console.log("todo Controller..."); 
    $scope.isList = true;
    $scope.active = true;
    $scope.active1 = true;
    $scope.notifications1=[];
    $scope.isPin=0;
    
    
  
    this.note=function(){
    	console.log("redirect to another page as ToDoItem ...");
    	$state.go('toDoItem');
    }
    
    this.archive=function(){
    	console.log("redirect to another page as Archive ...");
    	$state.go('archiveItem');
    }

     $scope.shareViaFB = function(todo) {
        console.log("Inside Fb Share function");
        FB.init({
            appId      : '455615694791739',
            status     : true,
            xfbml      : true
        });
        
      /*  
        var fbShare = function() {
            FB.ui({
                method: "feed",
                display: "iframe",
                link: "http://example.com/",
                caption: "Example.com",
                description: "Here is the text I want to share.",
                picture: "http://example.com/image.png"
            });
        };
        $("#fb-publish").click(function() {
            FB.login(function(response) {
                if (response.authResponse) {
                    fbShare();
               }
            }, {scope: 'publish_stream'});
        });*/
        
        FB.ui({
            method: 'share_open_graph',
            action_type: 'og.shares',
            action_properties: JSON.stringify({
                object : {
                   // your url to share
                   'og:title': todo.title,
                   'og:description': todo.toDoItemDescription,
                   /*'og:image': 'http://example.com/link/to/your/image.jpg'*/
                }
            })
            },
            // callback
            function(response) {
            if (response && !response.error_message) {
                // then get post content
                alert('successfully posted. Status id : '+response.post_id);
            } else {
                alert('Something went error.');
            }
        });
        
};

    
 $scope.notifications=function(id){
	 var todayDate =new Date().getTime();
	
	 var obj;
	 var futureDate;
	 console.log(todayDate);
	 
	 	for (i = 0; i < $scope.todos.length; i++)
    	{
        	if($scope.todos[i].id==id)
        	{
        		obj=$scope.todos[i];
        	}
        	
    	}
	 	futureDate=obj.reminder;
	 	console.log(futureDate);
	 	
	 	console.log(futureDate==todayDate);
	 	
	 	
	//selected dueDate is in the future
	
	if (futureDate == todayDate) {
	   alert('Task Due date is due today!');
	}
 };
  
  
  
  
  
  
    $scope.hoverIn=function()
    {
    	this.display=true;
    };
    $scope.hoverOut=function(){
    	this.display=false;
    };
    
    
    $(this).parent().find('ul').mouseleave(function() {
        var thisUI = $(this);
        $('html').click(function() {
            thisUI.hide();
            $('html').unbind('click');
        });
    });

    

	this.shareViaFb=function(){
		console.log("Todo Item Successfully Shared on Your FB Wall")
	};

   
    // Make a Copy of Existing ToDo Item
    $scope.copyItem = function(todo) {
        console.log(todo);
        var toDoItem = {
            title: todo.title,
            reminder: todo.reminder,
            toDoItemDescription: todo.toDoItemDescription,
            color:todo.color       
        };
        console.log(toDoItem);
        toDoService.addingToDo(toDoItem);
        $state.reload();

    }

    // Change Color of ToDo Item Card
    $scope.changePinnedCardColor = function(color, e,index,id) {
        
      
        var obj;
        
        for (i = 0; i < $scope.pinnedTask.length; i++)
        	{
            	if($scope.pinnedTask[i].id==id)
            	{
            		obj=$scope.pinnedTask[i];
            	}
        	}
       console.log(obj);
       obj.color=color;
       obj.isPinned="true";
       
       console.log(obj);
       toDoService.updateToDoItem(id,obj);
        /*console.log($scope.todos[index].reminder);
        console.log(day);
        console.log(index);*/
        /*toDoService.updateToDoItem(id,obj);*/
   /*     toDoService.updateToDoItem(id,obj);*/
        console.log("color changed");
        $(e.target).closest(".card").css("background-color", color);
    };
    
    
    
    $scope.changePinnedPopUpCardColor = function(color, e,id) {
        
        console.log(id);
        var obj;
        
        for (i = 0; i < $scope.pinnedTask.length; i++)
        	{
            	if($scope.pinnedTask[i].id==id)
            	{
            		obj=$scope.pinnedTask[i];
            	}
        	}
	    console.log(obj);
	    obj.color=color;
	    toDoService.updateToDoItem(id,obj);
        console.log("color changed");
        $(e.target).closest(".card").css("background-color", color);
    };

    
    
    
    // Change Color of ToDo Item Card
    $scope.changeCardColor = function(color, e,index,id) {
        
      
        var obj;
        
        for (i = 0; i < $scope.todos.length; i++)
        	{
            	if($scope.todos[i].id==id)
            	{
            		obj=$scope.todos[i];
            	}
        	}
        
       console.log(obj);
       obj.color=color;
       
       
       
       toDoService.updateToDoItem(id,obj);
        /*console.log($scope.todos[index].reminder);
        console.log(day);
        console.log(index);*/
        /*toDoService.updateToDoItem(id,obj);*/
   /*     toDoService.updateToDoItem(id,obj);*/
        console.log("color changed");
        $(e.target).closest(".card").css("background-color", color);
    };
    
    
    $scope.changePopUpCardColor = function(color, e,id) {
        
        console.log(id);
        var obj;
        
        for (i = 0; i < $scope.todos.length; i++)
        	{
            	if($scope.todos[i].id==id)
            	{
            		obj=$scope.todos[i];
            	}
        	}
       console.log(obj);
       obj.color=color;
       
       
       
       toDoService.updateToDoItem(id,obj);
        /*console.log($scope.todos[index].reminder);
        console.log(day);
        console.log(index);*/
        /*toDoService.updateToDoItem(id,obj);*/
   /*     toDoService.updateToDoItem(id,obj);*/
        console.log("color changed");
        $(e.target).closest(".card").css("background-color", color);
    };
    
    
this.deleteReminder=function(id,index){
	var obj;
	
    for (i = 0; i < $scope.todos.length; i++)
    	{
        	if($scope.todos[i].id==id)
        	{
        		obj=$scope.todos[i];
        	}
    	}
    obj.reminder = null;
    console.log(obj.id);
    toDoService.updateToDoItem(id,obj);
    console.log("reminder Deletted");
};
    
$scope.setNotification=function(id){
	 var obj;
	 var today = new Date();
	 for (i = 0; i < $scope.todos.length; i++)
    	{
        	if($scope.todos[i].id==id)
        	{
        		obj=$scope.todos[i];
        	}
    	}
   if(obj.reminder==new Date())
	   {
	   console.log("reminder set");
	   }
}
    //------------------------ Set Reminder of a ToDoItem---------------------------------

    this.doReminder = function(id, index, day) {
        console.log(id, index);
        if (day == "Today") {
            console.log(id);
            var today = new Date();
            var obj;
            for (i = 0; i < $scope.todos.length; i++)
            	{
	            	if($scope.todos[i].id==id)
	            	{
	            		obj=$scope.todos[i];
	            	}
            	}
            obj.reminder = today;
            console.log(obj.id);
            toDoService.updateToDoItem(id,obj);
            console.log("updated");

        } else if (day == "Tomorrow") {
            console.log(day);
            var tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);
            var obj;
            for (i = 0; i < $scope.todos.length; i++)
            	{
	            	if($scope.todos[i].id==id)
	            	{
	            		obj=$scope.todos[i];
	            	}
            	}
            obj.reminder = tomorrow;
            toDoService.updateToDoItem(id,obj);
            console.log(" tommorow updated");
        } else if (day == "Next-Week") {
            var next = new Date();
            next.setDate(next.getDate() + 7);
            var obj;
            for (i = 0; i < $scope.todos.length; i++)
            	{
	            	if($scope.todos[i].id==id)
	            	{
	            		obj=$scope.todos[i];
	            	}
            	}
            obj.reminder = next;
            toDoService.updateToDoItem(id,obj);
            console.log(" next weekupdated");
        }
    }

    
    // Set List or grid view in cookie to open same view when reopen browser
    // read from cookie
    /*$scope.isList = false;
    var cView = readCookie('view');
    // console.log('view',cView)
    if ('true' == cView) {
        // console.log('if');
        $scope.isList = true;
    } else { // console.log('else');
        $scope.isList = false;
    }
    // console.log('is list ', $scope.isList);

    this.changeView = function() {
        // store in cookie
        // isList = !isList;
        // console.log('change view', $scope.isList);
        writeCookie('view', $scope.isList);
    }
*/
    function writeCookie(cname, cvalue) {
        var d = new Date();
        d.setTime(d.getTime() + (30 * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires;
    }

    function readCookie(name) {
        if (document.cookie.indexOf(name) > -1) {
            return document.cookie.split(name)[1].split("; ")[0].substr(1);
        } else {
            return "";
        }
    }




    $(document).ready(function() {

        // ANIMATEDLY DISPLAY THE NOTIFICATION COUNTER.
        $('#noti_Counter')
            .css({
                opacity: 0
            })
            .text($scope.todos.length) // ADD DYNAMIC VALUE (YOU CAN
            // EXTRACT DATA FROM DATABASE OR
            // XML).
            .css({
                top: '-10px'
            })
            .animate({
                top: '-2px',
                opacity: 1
            }, 500);

        $('#noti_Button').click(function() {

            // TOGGLE (SHOW OR HIDE) NOTIFICATION WINDOW.
            $('#notifications').fadeToggle('fast', 'linear', function() {
                if ($('#notifications').is(':hidden')) {
                    $('#noti_Button').css('background-color', '#FFF');
                } else $('#noti_Button').css('background-color', '#FFF');
            });

            $('#noti_Counter').fadeOut('slow');

            return false;
        });

        // HIDE NOTIFICATIONS WHEN CLICKED ANYWHERE ON THE PAGE.
        $(document).click(function() {
            $('#notifications').hide();

            // CHECK IF NOTIFICATION COUNTER IS HIDDEN.
            if ($('#noti_Counter').is(':hidden')) {
                // CHANGE BACKGROUND COLOR OF THE BUTTON.
                $('#noti_Button').css('background-color', '#FFF');
            }
        });

        $('#notifications').click(function() {
            return false; // DO NOTHING WHEN CONTAINER IS CLICKED.
        });
    });




    //-----------------------Make a toDoItem Card as Sortable --------------------------------
    
    $(".slides").sortable({
        placeholder: 'slide-placeholder',
        axis: "z",
        revert: 1000,
        
        start: function(e, ui) {
        	ui.item.startPos = ui.item.index();
            placeholderHeight = ui.item.outerHeight();
            ui.placeholder.height(placeholderHeight );
            $('<div class="slide-placeholder-animator" data-height="' + placeholderHeight + '"></div>').insertAfter(ui.placeholder);
           
        },
        change: function(event, ui) {
            ui.placeholder.stop().height(0).animate({
                height: ui.item.outerHeight() 
            }, 300);

            placeholderAnimatorHeight = parseInt($(".slide-placeholder-animator").attr("data-height"));

            $(".slide-placeholder-animator").stop().height(placeholderAnimatorHeight).animate({
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
        update:function(e,ui){
        	console.log(e);
        	var itemIndex=[];
        	
        	item='.sortable';
        	console.log(ui.item.startPos);
        	console.log(ui.item.attr("my-uid"));
   
        	
        	var temp = $(e.currentTarget);
        	
        	console.log(temp);
        	console.log(ui.item.index());
        	/*var httpObje=toDoService.saveItemPosition();*/
        }
    });




    // ------------------Open Popup Function to edit or Update Particular ToDoItem. ------------------
    
    $scope.openPopup = function(obj, index) {
        console.log(obj);
        console.log(index);
        ModalService.showModal({
            templateUrl: "template/popup.html",
            controller: function($scope, close) {
                $scope.todo = obj;
                $scope.index = index;
                $scope.close = function(result) {
                    close(result, 500); // close, but give 500ms for
                    // bootstrap to animate
                };
            },
            controllerAs: "$modalCtrl"
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
            $scope.yesNoResult = result ? "You said Yes" : "You said No";
            });
        });
    };



    // -----------------Open SideBar Menu ---------------------------

    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });




   

    //-----------------Logout Function-------------------------------
    
    this.logout = function() {
        console.log('logout');
        var httpobj = toDoService.logoutUser().then(function(data) {
            if (data.status === 200) {
                console.log("User Logout..")
                $state.go("login");
            }
        })
    }




    // ---------------Creating New Task -------------------------------
    
    $scope.addToDo = function() {
        var title1 = $('#title').html();
        var rem=document.getElementById("reminder").value;
        var toDoItemDescription1 = $('#description').html();
        var rem1=(new Date(rem)).getTime();
       
        console.log(rem1);
        var toDoItem = {
                title: title1,
                reminder: rem1,
                toDoItemDescription: toDoItemDescription1,
                color: "#fff",
            };
        console.log(toDoItem);
        var httpObje = toDoService.addingToDo(toDoItem).then(function(){
        	$state.reload();
        })        
    }

    //--------------Delete Function to Delete a Task By ID--------------
    
    this.deleteTask = function(id) {
        console.log(id);
        var httpObj = toDoService.deleteTodo(id).then(function(data) {
            console.log(data);
            $state.reload();
        })
    };
    
    //---------------------Archive Function-------------------------------
    
    this.archiveTask = function(id , index){
    	var obj;
    	console.log("inside  dnfvvnfkjvf");
    	for (i = 0; i < $scope.todos.length; i++)
       	{
           	if($scope.todos[i].id==id)
           	{
           		obj=$scope.todos[i];
           	}
       	}
       obj.isArchive="true";
       console.log(obj);
       toDoService.makeArchiveTask(id,obj);
       $state.reload();
    }
    
    
    //----------------Pinned Function ---------------------------------
    
   this.pinned=function(id,index){
	   var obj;
       
       for (i = 0; i < $scope.todos.length; i++)
       	{
           	if($scope.todos[i].id==id)
           	{
           		obj=$scope.todos[i];
           	}
       	}
       $scope.isPin++;
       obj.isPinned="true";
      
       console.log(obj);
       console.log( $scope.isPin);
       toDoService.makePinnedTask(id,obj);
      //$state.reload();
   }

   //---------------Make Pinned Task to Unpinned---------------------------
   
   this.unPinned=function(id,index){
	   var obj;
       
       for (i = 0; i < $scope.todos.length; i++)
       	{
           	if($scope.todos[i].id==id)
           	{
           		obj=$scope.todos[i];
           	}
       	}
       $scope.isPin--; 	
       obj.isPinned="false";
       console.log(obj);
       console.log("gnfkbnk", $scope.isPin);
       toDoService.makeUnPinnedTask(id,obj);
      // $state.reload();
   }
   
   
   $scope.hidePinned = function(){
	   return $scope.todos.some(function(someData){
		   return someData.isPinned==='true';
	   });
   }
   $scope.hideOther = function(){
	   return $scope.todos.some(function(someData){
		   return someData.isPinned==='false';
	   });
   }
   

   //-------------------Update Function for Task Modification----------------
   
    this.update = function(id, index, objj) {
        var title = $($('#title' + id).html()).html();
        var toDoItemDescription = $($('#description' + id).html()).html();
        var obj;
       
        for (i = 0; i < $scope.todos.length; i++)
        	{
            	if($scope.todos[i].id==id)
            	{
            		obj=$scope.todos[i];
            	}
        	}
       obj.title=title;
       obj.toDoItemDescription=toDoItemDescription;
       console.log(obj);
       toDoService.updateToDoItem(id,obj).then(function(data) {
           if (data.status == 200) {
               $state.reload();
           }
       });
    }


    //------------------Get All Archived Task-------------------------
    
    toDoService.listAllArchivedTask().then(function(data){
    	if(data.status === 200){
    		console.log(data.data);
    		$scope.archivedTask=data.data;
    	}else{
    		console.log("Nothing Archived..")
    	}
    }).catch(function(err) {
        console.log(err);
    });
    
    
    //------------------Get All Pinned Task-------------------------
   /* 
    toDoService.listAllPinnedTask().then(function(data){
    	if(data.status === 200){
    		console.log(data.data);
    		$scope.pinnedTask=data.data;
    	}else{
    		console.log("Nothing Pinned..")
    	}
    }).catch(function(err) {
        console.log(err);
    });*/
    
    
    //---------------Get All Task-----------------------------------
    toDoService.listAllToDo().then(function(data) {
        if (data.status === 200) {
        	console.log(data.data);
            $scope.todos = data.data;
        } else {
            console.log("else..");
        }
    }).catch(function(err) {
        console.log(err);
    });



    //---------------Get Current Logged in User---------------------
    
    toDoService.getUser().then(function(data) {
        if (data.status === 200) {
            $scope.user = data.data;
            console.log("User Found.");            
        } else {
            console.log("Session Timeout or User Not Found..");
            $state.go("login");
        }
    })
    
    

})
.service('toDoService', function($http) {
    this.addingToDo = function(toDoItem) {
        return $http({
            url: "/toDoApp/toDoItem",
            method: "post",
            data: toDoItem
        });
    }
    this.listAllArchivedTask = function() {
        return $http({
            url: "/toDoApp/archivedTaskList",
            method: "get"
        });
    }

  /*  this.listAllPinnedTask = function() {
        return $http({
            url: "/toDoApp/pinnedTaskList",
            method: "get"
        });
    }*/
    this.listAllToDo = function() {
        return $http({
            url: "/toDoApp/toDoList",
            method: "get"
        });
    }
    this.getUser = function() {
        return $http({
            url: "/toDoApp/getUser",
            method: "get"
        });
    }

    this.deleteTodo = function(id) {
        return $http({
            url: "/toDoApp/toDoItem/" + id,
            method: "delete"
        });
    }
    this.logoutUser = function() {
        return $http({
            url: "/toDoApp/logout",
            method: "get"
        });
    }
    this.updateToDoItem = function(id,todo) {
        return $http({
            url: "/toDoApp/updateToDoItem/" + id,
            method: "post",
            data: todo
        });
    }
    this.makePinnedTask= function(id,todo){
    	return $http({
    		url: "/toDoApp/pinnedTask/" + id,
            method: "post",
            data: todo
    	});
    }
    this.makeUnPinnedTask= function(id,todo){
    	return $http({
    		url: "/toDoApp/unPinnedTask/" + id,
            method: "post",
            data: todo
    	});
    }
    this.makeArchiveTask = function(id,todo){
    	return $http({
    		url: "/toDoApp/archiveTask/" + id,
    		method: "post",
    		data: todo
    	});
    }

});