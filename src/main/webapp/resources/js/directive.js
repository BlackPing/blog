var app = angular.module('app', ['ngRoute']);

app.config(['$routeProvider', '$compileProvider', ($routeProvider) => {
	/* 
	router 화면 전환용
	when = switch case
	otherwise default
	*/
	$routeProvider.when("/a", {template : "<div data-write></div>", controller: "appCTL1"})
					.otherwise({redirectTo: "/a"});
}]);
app.controller('appCTL1', function($scope, $routeParams) {

})

app.directive('write', ($compile) => {
	var linked = (scope, element, atttrs) => {
		scope.$watch('data', () => {
			var html = 'test';
			element.html(html);
			$compile(element.contents())(scope);
		});
	}
	
	return {
		restrict : "A",
		link : linked
		// template : "<h1>directive 화면</h1>"
	};
});