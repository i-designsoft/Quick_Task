<!DOCTYPE html>
<html>
  <head>
    <script src="bower_components/angular/angular.min.js" charset="utf-8"></script>
    <script src="bower_components/angular-ui-router/release/angular-ui-router.min.js" charset="utf-8"></script>
    <script src="bower_components/jquery/dist/jquery.min.js" charset="utf-8"></script>
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js" charset="utf-8"></script>
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  </head>
<body data-ng-app="todoApp">

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">ToDo Application</a>
        </div>
        <div class="col-xs-6" style="width:85%;">
				<div class="row">
					<div class="col-xs-7" style="margin-top:8px;">
						<div class="input-group">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
							</span>
							<input type="search" class="form-control" placeholder="Search" ng-model="todoSearch">
						</div>
					</div>
					</div>
				</div>
        </div>
        
        <!-- <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
           
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Other Links <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li></li>
                <li class="active"></li>
                <li></li>
              </ul>
            </li>
            <li class="dropdown">
            		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Share <b class="caret"></b></a>
            		<ul class="dropdown-menu">
            		<li><a href="https://twitter.com/">Twitter</a></li>
            		<li><a href="https://facebook.com/">Facebook</a></li>
           		<li><a href="https://plus.google.com/">Google+</a></li>
            		</ul>
            </li>
          </ul>
        </div>
      </div> -->
     <!--  <div class="col-xs-6">
				<div class="row">
					<div class="col-xs-7">
						<div class="input-group">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
							</span>
							<input type="search" class="form-control" placeholder="Search" ng-model="todoSearch">
						</div>
					</div>
					</div>
				</div>
				     -->
    </div>
<div class="container" style="margin-top:80px;">
	<ui-view></ui-view>
</div>
</body>
<script src="js/app.js" charset="utf-8"></script>
<script src="js/controller/loginController.js" charset="utf-8"></script>
<script src="js/controller/signupController.js" charset="utf-8"></script>
<script src="js/controller/toDoController.js" charset="utf-8"></script>
<script src="js/controller/navController.js" charset="utf-8"></script>

</html>