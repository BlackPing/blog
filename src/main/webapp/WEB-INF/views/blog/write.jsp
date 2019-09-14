<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Developer b</title>

<link rel="stylesheet" type="text/css" href="/res/css/commons.css">
<link rel="stylesheet" type="text/css" href="/res/css/Write.css">
<link rel="stylesheet" type="text/css" href="/res/lib/ckeditor/contents.css">

<script type="text/javascript" src="/res/lib/ckeditor/ckeditor.js"></script>
<!-- <link rel="stylesheet" media="only screen and (max-width: 768px)" href="../m5/css/index_768.css">
<link rel="stylesheet" media="only screen and (max-width: 576px)" href="../m5/css/index_576.css"> -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="/res/js/jquery-3.4.1.min.js"></script>
<script src="/res/js/Write.js"></script>
<script type="text/javascript"></script>
<script>
	$(document).ready(() => {
		let doc_width = $(document).width();
		
		start();
		function start() {
		    CKEDITOR.on('dialogDefinition', function( ev ){
		        var dialogName = ev.data.name;
		        var dialogDefinition = ev.data.definition;

		        switch (dialogName) {
		            case 'image': //Image Properties dialog
		            //dialogDefinition.removeContents('info');
		            dialogDefinition.removeContents('Link');
		            dialogDefinition.removeContents('advanced');
		            break;
		        }
		    });
		    
			CKEDITOR.replace( 'editor1', {
				resize_enabled: false,
				width: doc_width * 0.8,
				height: 800
			});
		}
	});
</script>

</head>
<body>
	<jsp:include page="/resources/commons/header.jsp" flush="true" />
	<div class="contain">
		<form id="write" action="/write/insert" method="post" enctype="multipart/form-data">
			<div class="title">
				<div class="title_name">제목</div> <input type="text" id="title" name="title" required="required">
				<div class="category_name">카테고리</div>
				<select id="category" name="category_name">
					<c:forEach var="item" items="${catedata.result}" varStatus="status">
						<c:if test="${'N' eq item.TYPE}">
							<option value="${item.NAME}">${item.NAME}</option>
						</c:if>
					</c:forEach>
				</select>
				<button type="submit" style="float: right;" class="btn btn-primary">작성완료</button>
			</div>
			<textarea name="text" id="editor1" rows="10" cols="80"></textarea>
			<div class="files">
				<input type="file" name="files" multiple="multiple" onchange="file_Event(this)">
			</div>
		</form>
	</div>
	<jsp:include page="/resources/commons/footer.jsp" flush="true" />
</body>
</html>