var bookstore = angular.module("bookstore", []);
//Dependency Injection
//value factory  service provider  constant
 //Value 是一个简单的 javascript 对象，用于向控制器传递值（配置阶段）：
bookstore.value("defaultInput", 5); 
//constant(常量)用来在配置阶段传递数值，注意这个常量在配置阶段是不可用的。
mainApp.constant("configParam", "constant value");

//factory 是一个函数用于返回值。在 service 和 controller 需要时创建。
//mainApp.service('CalcService', function(MathFactory){
bookstore.factory('MathFactory', function() {
   var factory = {};
   
   factory.multiply = function(a, b) {
      return a * b
   }
   return factory;
}); 



bookstore.config(function($sceDelegateProvider) {
    $sceDelegateProvider.resourceUrlWhitelist([	
	//<div ng-include="'https://c.runoob.com/runoobtest/angular_include.php'"></div>
        'https://c.runoob.com/runoobtest/**'
    ]);
});
  bookstore.config(function($provide) {
            $provide.provider('MathService', function() {
               this.$get = function() {
                  var factory = {};
                  
                  factory.multiply = function(a, b) {
                     return a * b;
                  }
                  return factory;
               };
            });
         });
// ///AngularJS 内建了30 多个服务。
bookstore.service('hexafy', function(MathFactory){
    this.square = function(a) {
        return MathFactory.multiply(a,a);
    }
    this.myFunc = function (x) {
        return x.toString(16);
    }
});
bookstore.controller('validateCtrl', function($scope) {
    $scope.user = 'John Doe';
    $scope.email = 'john.doe@gmail.com';
});
bookstore.controller("BookAdd", function($scope, $http, $location, $interval, $timeout, hexafy) {
	$scope.loginFn=function(){
		$http({
			method:"GET",
			url:"http://datainfo.duapp.com/shopdata/userinfo.php",
			params:{
				status:"login",
				userID:$scope.username,
				password:$scope.password
			}	
		}).then(
			function success(resp){
				console.log("请求成功",resp);
				if(resp.data){
					console.log("登陆成功，跳转到首页");
				}else{
					console.log("登录失败");
				}
			},
			function error(resp){
				console.log("请求失败");
			}
		);
	}

	$scope.bookId = "1002020";
	$scope.bookName = "Fly with Wind g";
	//
    $scope.hex = hexafy.myFunc(255);
	$scope.fullName = function() {
		return $scope.bookId + ":" + $scope.bookName;
	};
	$scope.countries = [
	     {name:'Jani',country:'Norway'},
	     {name:'Hege',country:'Sweden'},
	     {name:'Kai',country:'Denmark'}
	  ];
	  
	$scope.master = {firstName: "John", lastName: "Doe"};
    $scope.reset = function() {
        $scope.user = angular.copy($scope.master);
    };
    $scope.reset();

	$http.get("resources/myscript.js")
//	      .success(function (response) {$scope.names = response.sites;});  //obsolote from AngularJS1.5
	   .then(function (response) { //From 1.5. simple version
	        $scope.myWelcome = response.data;
	    });
		////From 1.5. general version
	$http({  
		method: 'GET',
		url: 'resources/sites.json',
		params: {
			page:10
		}
	}).then(function successCallback(response) {
			$scope.sites = response.data.sites;
		}, function errorCallback(response) {
			// 请求失败执行代码
	});
	// window.setTimeout
	$timeout(function () {
	        $scope.myHeader = "How are you today?";
	    }, 2000);
	$scope.theTime = new Date().toLocaleTimeString();
	 // window.setInterval
	$interval(function () {
	        $scope.theTime = new Date().toLocaleTimeString();
	    }, 1000);	
});
//你可以通过以下方式来调用指令：
//元素名, 属性, 类名, 注释
bookstore.directive("bookstoreDirective", function() {
    return {
        template : "<h1>自定义指令!</h1>"
    };
});

//
// currency 格式化数字为货币格式。
// filter 从数组项中选择一个子集。
// lowercase 格式化字符串为小写。
// orderBy 根据某个表达式排列数组。
// uppercase 格式化字符串为大写。
bookstore.filter('reverse', function() { // 可以注入依赖
    return function(text) {
        return text.split("").reverse().join("");
    }
});
bookstore.filter('myFormat',['hexafy', function(hexafy) {
    return function(x) {
        return hexafy.myFunc(x);
    };
}]);