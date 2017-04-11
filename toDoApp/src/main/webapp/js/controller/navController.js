angular.module('toolbarDemo2', ['ngMaterial'])

.controller('TitleController', function($scope) {
  $scope.title = 'ToDo Application';
})
.controller('AppCtrl', function($scope) {
  var imagePath = 'img/list/60.jpeg';

  
  
  $scope.todos = [];
  for (var i = 0; i < 5; i++) {
    $scope.todos.push({
      face: imagePath,
      what: "Brunch this weekend?",
      who: "Min Li Chan",
      notes: "I'll be in your neighborhood doing errands."
    });
  }
});
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
}