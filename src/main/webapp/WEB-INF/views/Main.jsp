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
<script type="text/javascript">
	var checkIndex = -1;

	var app = angular.module('app', []);
	app.controller('appCLT', ($rootScope, $scope, $http)=> {
		$rootScope.comment = () => {
			$http({
				method: 'POST',
				url: '/select',
				data: $.param({
			        key: 'test'
			    })
			}).then((response) => {
				$scope.data = response.data.result;
				console.log($scope.data);
			});
		}

		$scope.checked = (index) => {
			var ck = document.getElementsByName('checkbox');
			for(var i = 0; i < ck.length; i++) {
				ck[i].checked = false;
			}
			
			if(index != checkIndex) {
				ck[index].checked = true;
				var txt = document.getElementsByTagName("tr")[index + 1].getElementsByTagName("td")[2].textContent;
				document.getElementById("text").value = txt;
				document.getElementsByName("no")[0].value = index + 1;
				document.getElementById("update").classList.remove("disabled");
				document.getElementById("delete").classList.remove("disabled");
			} else {
				document.getElementById("text").value = "";
				document.getElementsByName("no")[0].value = "";
				document.getElementById("update").classList.add("disabled");
				document.getElementById("delete").classList.add("disabled");
			}
			
			if(ck[index].checked) checkIndex = index;
			else checkIndex = -1;
		}

		$rootScope.comment();
	});
	
	app.controller('appCRUD', ($rootScope, $scope, $http)=> {
		$scope.insert = () => {
			$http({
				method: 'POST',
				url: '/insert',
				params : {NO: document.getElementsByName("no")[0].value, TXT: document.getElementById("text").value},
			})
			.then((response) => {
				$rootScope.comment();
			});
		}
	});
	
</script>
</head>
<body data-ng-app="app">
<div class="container">
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
<div class="container" data-ng-controller="appCRUD">
	<h1 class="text-center">구디아카데미</h1>
	<form id="edit">
	  <div class="form-group row">
	    <div class="col-xs-2">
	    	<label for="text">한줄평  :</label>
	    </div>
	    <div class="col-xs-7">
	    	<input type="text" class="form-control" id="text" name="text" placeholder="입력하세요." autocomplete="off">
	    	<input type="hidden" name="no">
	    </div>
	    <div class="col-xs-1">
	    	<button type="submit" class="btn btn-primary" id="submit" data-ng-click="insert()">추가</button>
	    </div>
	    <div class="col-xs-1">
	    	<button type="button" class="btn btn-success disabled" id="update">수정</button>
	    </div>
	    <div class="col-xs-1">
	    	<button type="button" class="btn btn-danger disabled" id="delete">삭제</button>
	    </div>
	  </div>
	</form>
</div>
<div class="container" data-ng-controller="appCLT">
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
			<td><input type="checkbox" name="checkbox" data-ng-click="checked($index)"> </td>
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