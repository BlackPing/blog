<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="res/css/common.css" type="text/css">
        <link rel="stylesheet" href="res/css/login.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Mukta&display=swap" rel="stylesheet">

        <title>Login</title>
    </head>

    <body>
        <section class="contain">
            <input type="checkbox" id="box_check" class="none">
            <label for="box_check" class="card card-top position">
                <h1>Project</h1>
            </label>
            <form action="/ajax/dbtest" class="position" method="POST">
                <p style="margin-top: 20px">User</p>
                <input type="text" name="id" id="id" class="input">
                <p>Password</p>
                <input type="password" name="pwd" id="pwd" class="input">
                <input type="submit" name="login" id="login" value="Sign in" class="button button-green">
                <p style="margin-top: 20px;">New to immotal? <a href="#">Create an account.</a></p>
            </form>
            <label for="box_check" class="card card-bottom position"></div>
        </section>
    </body>
</html>