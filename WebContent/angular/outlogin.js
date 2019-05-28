var baseDemoUrl = 'http://127.0.0.1:8080/online-service/demo/query';

function outloginCtrl($scope) {
    $scope.outlogin = function () {
        if (confirm("确定退出登录吗？") == true) {
            $.ajax({
                url: "/online-service/app/outlogin",
                type: "post",
                success: function () {
                    window.localStorage.clear();
                    window.location = "/online-service/login.html";
                }
            })
        }
        ;
    }
}
