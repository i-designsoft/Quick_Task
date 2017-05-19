<!DOCTYPE html>
<html>
<head>
<script src="bower_components/angular/angular.min.js" charset="utf-8"></script>
<script
	src="bower_components/angular-ui-router/release/angular-ui-router.min.js"
	charset="utf-8"></script>
<script src="bower_components/jquery/dist/jquery.min.js" charset="utf-8"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"
	charset="utf-8"></script>
<link rel="stylesheet"
	href="bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body data-ng-app="todoApp">
	<div class="container" style="margin-top: 80px;">
		<ui-view></ui-view>
	</div>
</body>
<script src="js/app.js" charset="utf-8"></script>
<script src="js/controller/loginController.js" charset="utf-8"></script>
<script src="js/controller/signupController.js" charset="utf-8"></script>
<script src="js/controller/toDoController.js" charset="utf-8"></script>
<script src="js/controller/navController.js" charset="utf-8"></script>
<script src="js/controller/newController.js" charset="utf-8"></script>
</html>