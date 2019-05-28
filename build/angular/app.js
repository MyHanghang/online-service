var baseDemoUrl='http://127.0.0.1:8080/online-service/demo/query';
var menuData;
function demoCtrl($scope, $http){
    $scope.pageData = {
    	sumCount:0,
    	currentPage:1,
    	sumPage:1,
    	pageSize:10
    };
    $scope.getDemo = function() {
        $http({
            method: "POST",
            url: baseDemoUrl,
            data: $scope.pageData
        }).then(function successCallback(response) {
            $scope.infos = response.data.demoList;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            // 请求失败执行代码
        });
    };
    //上一页
    $scope.previous = function() {
    	$scope.pageData.currentPage =$scope.pageData.currentPage-1;
   		$scope.getDemo();
    };
    //下一页
    $scope.next = function() {
    	$scope.pageData.currentPage = $scope.pageData.currentPage+1;
   		$scope.getDemo();
   };
    //获取选择的那列数据
    $scope.getModel = function(Count) {
    	$scope.conf =$scope.infos[Count];
    }
}
