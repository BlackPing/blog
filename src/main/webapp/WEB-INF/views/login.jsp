<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="res/css/login.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Mukta&display=swap" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-cookies.js"></script>
		<script type="text/javascript">
			let app = angular.module('app', ['ngCookies']);
			app.controller('appctl', ($scope, $http, $window, $cookies) => {
				if($cookies.get('ASID') != undefined) $window.location.href = '/board';
				$scope.login = () => {
					$http({method: "POST", url: "/login", params: {id: $scope.id, pw: $scope.pw}}).then((res) => {
						$scope.node = res.data;
						console.log($scope.node);
						if($scope.node.state) {
							$window.location.href = '/';
						}
					});
				}
			});
		</script>

        <title>Login</title>
    </head>

    <body data-ng-app="app" data-ng-controller="appctl">
        <div class="contain">
            <input type="checkbox" id="box_check" class="none">
            <label for="box_check" class="card card-top position">
                <h1>Login Click Me</h1>
            </label>
            <form class="position" method="POST">
                <p style="margin-top: 20px">User <span style="color: red;">{{ node.errors.id }}</span></p>
                <input type="text" name="id" id="id" class="input" data-ng-model="id">
                <p>Password <span style="color: red;">{{ node.errors.pw }}</span></p>
                <input type="password" name="pw" id="pw" class="input" data-ng-model="pw">
                <input type="button" name="login" id="login" value="Sign in" class="button button-green" data-ng-click="login()">
            </form>
            <label for="box_check" class="card card-bottom position"></label>
         </div>
    </body>
</html>