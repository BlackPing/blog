<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Developer b</title>

<link rel="stylesheet" type="text/css" href="/res/css/commons.css">
<link rel="stylesheet" type="text/css" href="/res/css/Main.css">
<link rel="stylesheet" type="text/css" href="/res/lib/ckeditor/contents.css">

<script type="text/javascript" src="/res/lib/ckeditor/ckeditor.js"></script>
<!-- <link rel="stylesheet" media="only screen and (max-width: 768px)" href="../m5/css/index_768.css">
<link rel="stylesheet" media="only screen and (max-width: 576px)" href="../m5/css/index_576.css"> -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script type="text/javascript"></script>
<script>
	function start() {
		CKEDITOR.replace( 'editor1', {
				resize_enabled: false,
				enterMode : CKEDITOR.ENTER_BR,
				width: 1000,
				height: 800
			} );
	}
</script>

</head>
<body onload="start()">
	<header class="bg-dark text-white text-center">
	  <div class="container">
	  	<div style="padding-top: 50px;">
		    <h1>Programming</h1>
		    <p class="lead">블로그</p>
	    </div>
	  </div>
	</header>
	<div class="contain">
		<form>
			<input type="text" name="title">
			<select>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
			</select>
			<textarea name="text" id="editor1" rows="10" cols="80"></textarea>
		</form>
	</div>
	<footer class="bg-dark text-white">
		<div class="text-center">
			<p style="padding-top: 40px;">Copyright © 2019 blackping.shop</p>
		</div>
	</footer>
</body>
</html>