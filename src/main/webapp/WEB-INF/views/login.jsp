<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="res/css/login.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Mukta&display=swap" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
		<script type="text/javascript">
			let app = angular.module('login', []);
			app.controller('form', ($scope, $http) => {
				$scope.login = () => {
					console.log("login function ", $scope.id, $scope.pwd);
					$http({method: "POST", url: "/login", params: {id: $scope.id, pw: $scope.pw}}).then((res) => {
						console.log(res);
					});
				}
			});
		</script>

        <title>Login</title>
    </head>

    <body>
        <section class="contain">
            <input type="checkbox" id="box_check" class="none">
            <label for="box_check" class="card card-top position">
                <h1>Login Click Me</h1>
            </label>
            <form action="/ajax/dbtest" class="position" method="POST" data-ng-app="login" data-ng-controller="form">
                <p style="margin-top: 20px">User</p>
                <input type="text" name="id" id="id" class="input" data-ng-model="id">
                <p>Password</p>
                <input type="password" name="pw" id="pw" class="input" data-ng-model="pw">
                <input type="button" name="login" id="login" value="Sign in" class="button button-green" data-ng-click="login()">
                <p style="margin-top: 20px;">New to ID? <a href="#">Create an account.</a></p>
            </form>
            <label for="box_check" class="card card-bottom position"></div>
        </section>
    </body>
</html>