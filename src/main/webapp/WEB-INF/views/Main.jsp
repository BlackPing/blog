<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>문제</title>
<link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.png">
<style>
.modal-header, h4, .close {
  background-color: #5cb85c;
  color:white !important;
  text-align: center;
  font-size: 30px;
}
.modal-footer {
  background-color: #f9f9f9;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-cookies.js"></script>
<script type="text/javascript">
	let checkIndex = -1;

	var app = angular.module('app', ['ngCookies']);
	app.controller('appctl', ($scope, $http, $window, $cookies)=> {
		if(!$cookies.get('ASID')) $window.location.href = '/';
		
		$scope.logout = () => {
			$http({
				method: 'POST',
				url: '/logout',
			}).then((res) => {
				$window.location.href = '/';
			});
		}
		
		$scope.comment = () => {
			$http({
				method: 'POST',
				url: '/select',
			}).then((response) => {
				$scope.data = response.data.result;
			});
		}

		$scope.checked = (item) => {
			var update = angular.element(document.querySelector('#update'));
			var del = angular.element(document.querySelector('#delete'));
			if(item.checked) {
				checkIndex = item.NO;
				$scope.text = item.TXT;
				update.removeClass('disabled');
				del.removeClass('disabled');
			} else {
				checkIndex = -1;
				$scope.text = "";
				update.addClass('disabled');
				del.addClass('disabled');
			}
			angular.forEach($scope.data, (data) => {
				if(data.NO != item.NO) {
					if(data.checked) data.checked = false;
				}
			});
		}
		
		$scope.crud = (url) => {
			$http({
				"method": 'POST',
				"url": url,
				"params" : {NO: checkIndex, TXT: $scope.text},
			})
			.then((response) => {
				$scope.text = "";
				$scope.comment();
			});
		}
		
		$scope.comment();
	});
	
</script>
</head>
<body data-ng-app="app" data-ng-controller="appctl">
<div class="container">
<button style="margin-top: 10px;" type="button" class="btn btn-danger" id="logout" data-ng-click="logout()">Logout</button>
	<h3>웹 문제</h3>
	<p>
		위의 버튼 3개만 이용하여 추가, 수정, 삭제 이벤트를 구현 하시오.<br>
		1) 추가 : "입력하세요" 입력창을 이용하여 데이터 생성 할 것.<br>
		2) 수정 : 선택(체크박스)를 선택된 내용만 "입력하세요" 입력창으로 데이터를 가져온 후 수정 할 것.<br>
		3) 삭제 : 선택(체크박스)를 선택된 내용만 데이터를 삭제 할 것<br>
		4) 유저 : 추가 시 사용자가 누군지 myModal를 이용하여 정보를 받아서 데이터 처리 하시오.<br>
		5) 기능 : localStorage 에서 처리 하던 부분를 Spring (Controller)를 이용하여 처리하시오.<br>
		참조 : 체크박스 이벤트는 위에 있는 script의 이벤트를 같이 이용하여 처리 할것.
	</p>
</div>
<div class="container">
	<h1 class="text-center">구디아카데미</h1>
	<form id="edit">
	  <div class="form-group row">
	    <div class="col-xs-2">
	    	<label for="text">한줄평  :</label>
	    </div>
	    <div class="col-xs-7">
	    	<input type="text" class="form-control" id="text" name="text" placeholder="입력하세요." autocomplete="off" value="{{text}}" data-ng-model="text">
	    </div>
	    <div class="col-xs-1">
	    	<button type="button" class="btn btn-primary" id="insert" data-ng-click="crud('/insert')">추가</button>
	    </div>
	    <div class="col-xs-1">
	    	<button type="button" class="btn btn-success disabled" id="update" data-ng-click="crud('/update')">수정</button>
	    </div>
	    <div class="col-xs-1">
	    	<button type="button" class="btn btn-danger disabled" id="delete" data-ng-click="crud('/delete')">삭제</button>
	    </div>
	  </div>
	</form>
</div>
<div class="container">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th>선택</th>
	      <th>번호</th>
	      <th>한줄평</th>
	    </tr>
	  </thead>
	  <tbody>
		<tr data-ng-repeat="row in data">
			<td><input type="checkbox" name="checkbox" data-ng-model="row.checked" data-ng-click="checked(row)"> </td>
			<td>{{ row.NO }}</td>
			<td>{{ row.TXT }}</td>
		</tr>
	  </tbody>
	</table>
</div>


<!-- Modal -->
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
          <form role="form">
            <div class="form-group">
              <label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
              <input type="text" class="form-control" id="usrname" placeholder="Enter email" required="required" autocomplete="off">
            </div>
            <div class="form-group">
              <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
              <input type="text" class="form-control" id="psw" placeholder="Enter password" required="required" autocomplete="off">
            </div>
              <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
          </form>
        </div>
      </div>
      
    </div>
  </div> 
</div>
</body>
</html>