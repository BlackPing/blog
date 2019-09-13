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
<link rel="stylesheet" type="text/css" href="/res/lib/ckeditor/contents.css">

<!-- <link rel="stylesheet" media="only screen and (max-width: 768px)" href="../m5/css/index_768.css">
<link rel="stylesheet" media="only screen and (max-width: 576px)" href="../m5/css/index_576.css"> -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-route.js"></script>
<script src="/res/js/directive.js"></script> -->
<script src="/res/js/jquery-3.4.1.min.js"></script>
<script src="/res/js/Main.js"></script>
<script type="text/javascript">
</script>
</head>
<body data-ng-app="app">
	<nav>
		<input type="checkbox" id="category" class="disable">
		<label for="category"><div class="cate_btn">CATEGORY</div></label>
		<div class="category">
			<div>
				<form class="search">
					<label for="category"><img src="/res/icon/search.png" class="icon"></label>
					<input type="text" name="search">
				</form>
				<label for="category"><img src="/res/icon/close-cross.png" class="icon cancel"></label>
			</div>
			<form action="/" method="get">
				<% if(request.getSession().getAttribute("SPRING_SECURITY_CONTEXT") != null) { %>
	            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<button type="submit" formaction="/write" formmethod="post" class="btn btn-primary">글쓰기</button>
					<button type="submit" formaction="/logout" formmethod="post" class="btn btn-danger">로그아웃</button>
				<% } %>
			</form>
			<div style="clear: both;">
				<p>전체 게시글</p>
				<p>테스트</p>
				<p>인간</p>
			</div>
		</div>
	</nav>
	<jsp:include page="/resources/commons/header.jsp" flush="true" />
	<div class="contain">
		<c:if test="${empty data.result}">
			<jsp:include page="/resources/commons/searchfail.jsp" flush="true" />
		</c:if>
		<c:if test="${not empty data.result}">
		<c:forEach var="item" items="${data.result}" varStatus="status">
			<div class="document">
				<div>
					<div class="document_tit">
						<div class="document_title">${item.TITLE}</div> 
						<div class="document_tc">|</div>
						<div class="document_category">${item.CATEGORY_NAME}</div>
						<div class="document_time">${item.TIME}</div>
					</div>
				</div>
				<div class="document_text">
					${item.TEXT}
				</div>
			</div>
		</c:forEach>
		</c:if>
	</div>
	
	<jsp:include page="/resources/commons/footer.jsp" flush="true" />
</body>
</html>