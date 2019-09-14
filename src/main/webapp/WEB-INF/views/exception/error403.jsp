<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Developer b</title>

<link rel="stylesheet" type="text/css" href="/res/css/commons.css">
<link rel="stylesheet" type="text/css" href="/res/css/Main.css">
<!-- <link rel="stylesheet" media="only screen and (max-width: 768px)" href="../m5/css/index_768.css">
<link rel="stylesheet" media="only screen and (max-width: 576px)" href="../m5/css/index_576.css"> -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
	<jsp:include page="/resources/commons/header.jsp" flush="true" />
	<div id="searchfail">
		권한이 없습니다.
	</div>
	<jsp:include page="/resources/commons/footer.jsp" flush="true" />
</body>
</html>