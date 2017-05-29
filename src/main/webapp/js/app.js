var myApp = angular.module('todoApp', ['ui.router','angularModalService', 'ngSanitize','ui.bootstrap','ngAnimate','ui.bootstrap.datetimepicker'])
.config(function ($stateProvider, $urlRouterProvider) {
  $stateProvider
  .state("home",{
    url:"/home",
    templateUrl:"template/home.html"
    // controller:""
  })
  .state("about",{
    url:"/about",
    templateUrl:"template/about.html"
  })
  .state("login",{
    url:"/login",
    templateUrl:"template/login.html",
    controller:"loginController"
  })
  .state("toDoItem",{
    url:"/toDoItem",
    templateUrl:"template/ToDoItem.html",
   
  })  
  .state("signUp",{
    url:"/create",
    templateUrl:"template/signUp.html",
    controller:"signupController"
  });
  $urlRouterProvider.otherwise('/login');

});