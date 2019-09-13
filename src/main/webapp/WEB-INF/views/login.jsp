<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="../res/css/login.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Mukta&display=swap" rel="stylesheet">

        <title>Login</title>
    </head>

    <body data-ng-app="app" data-ng-controller="appctl">
        <div class="contain">
            <input type="checkbox" id="box_check" class="none">
            <label for="box_check" class="card card-top position">
                <h1>adminCode Check</h1>
            </label>
            <form class="position" action="../login" method="POST">
            	<br>
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            	<p>adminID</p>
                <input type="text" name="id" class="input" required>
                <p>adminCode</p>
                <input type="password" name="password" class="input" required>
                <input type="submit" value="Sign in" class="button button-green">
            </form>
            <label for="box_check" class="card card-bottom position"></label>
         </div>
    </body>
</html>