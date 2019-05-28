//学习考试系统js
var ipAddress = 'http://' + location.host;

var projectNmae = "/online-service/";
var token = "";
var baseUrl = ipAddress + projectNmae;


//增删改查功能实现
function baseCtrl($scope, $http) {
    $scope.conf = "";
    $scope.pageData = {
        sumCount: 0,
        currentPage: 1,
        sumPage: 1,
        pageSize: 10,
        param1: '',
        param2: ''
    };
    $scope.getSelect = function () {
        $http({
            method: "POST",
            url: baseUrl + "sys/select"
        }).then(function successCallback(response) {
            $scope.selectData = response.data.list;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.query = function (path) {
        $http({
            method: "POST",
            url: baseUrl + path + "/query",
            data: $scope.pageData
        }).then(function successCallback(response) {
            $scope.infos = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.query1 = function (path) {
        $http({
            method: "POST",
            url: baseUrl + path + "/query",
            data: $scope.pageData
        }).then(function successCallback(response) {
            $scope.sjinfo = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.query2 = function (Count, path) {
        $scope.conf = $scope.infos[Count];
        $scope.pageData.param1 = $scope.conf.id;
        $http({
            method: "POST",
            url: baseUrl + path + "/query",
            data: $scope.pageData
        }).then(function successCallback(response) {
            $scope.sjinfo = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    //上一页
    $scope.previous = function (path) {
        $scope.pageData.currentPage = $scope.currentPage - 1;
        $scope.query(path);
    };
    //下一页
    $scope.next = function (path) {
        $scope.pageData.currentPage = $scope.currentPage + 1;
        $scope.query(path);
    };
    //获取选择的那列数据
    $scope.getModel = function (Count) {
        $scope.conf = $scope.infos[Count];
        $scope.getSelect();
    };
    //获取选择的那列数据
    $scope.getModelMore = function (Count, path) {
        $scope.conf = $scope.infos[Count];
        $scope.pageData.param1 = $scope.conf.id;
        $scope.query1(path);
    };
    $scope.getModelMore1 = function (Count, path) {
        $scope.conf = $scope.infos[Count];
        $scope.pageData.param1 = $scope.conf.id;
        $scope.add();
    };
    $scope.getSJDetail = function (Count, path) {
        $scope.pageData.param1 = $scope.infos[Count].id;
        $scope.query1(path);
    };
    //学生点击开始答题，获取该试题的内容显示在model上
    $scope.ksdt = function (Count, path) {
        if (confirm("你确定要进行考试吗？如果是请点击“是”。请各位同学按照考试要求进行答题，否则将按照学校规定来进行处理！！") == true) {
            $('#modal-table2').modal('show');
            $scope.conf = $scope.infos[Count];
            $scope.addRecord(path);
        }
    };
    $scope.update = function (path) {
        alert("试卷提交成功，请前往【我的答题】查看成绩详情！")
        var obj1 = $scope.sjinfo;
        angular.forEach(obj1, function (obj, index) {
            $scope.conf = $scope.sjinfo[index];
            $scope.add2(path);
        })
        alert("自动阅卷完毕！！")
    };

    $scope.updatePass = function () {
        var pass1 = $scope.newpass1;
        var pass2 = $scope.newpass2;
        if (pass1 != pass2) {
            alert("两次输入密码不一致！！");
        } else if (pass1 == null) {
            alert("请输入新密码！！");
        } else if (pass2 == null) {
            alert("请再次输入新密码！！");
        } else {
            $http({
                method: "POST",
                url: baseUrl + "user/updatePass",
                data: pass1
            }).then(function successCallback(response) {
                if ('0000' == response.data.status) {
                    alert("修改成功！");
                } else {
                    alert("登陆失效，重新登陆！");
                    window.location = projectNmae + "login.html";

                }
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }
    };
    $scope.add2 = function (path) {
        $http({
            method: "POST",
            url: baseUrl + path + "/save",
            data: $scope.conf
        }).then(function successCallback(response) {
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.addRecord = function (path) {
        $http({
            method: "POST",
            url: baseUrl + path + "/save",
            data: $scope.conf
        }).then(function successCallback(response) {
            if ('0000' == response.data.status) {
                $scope.pageData.param1 = response.data.id;
                $scope.query1("recordDetail");

                $('#modal-table1').modal('hide');
            } else {
                alert(response.data.status);
            }
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.add = function (path) {
        $http({
            method: "POST",
            url: baseUrl + path + "/save",
            data: $scope.conf
        }).then(function successCallback(response) {
            if ('0000' == response.data.status) {
                $scope.query(path);
                $scope.pageData.param1 = $scope.conf.id;
                $scope.query1("recordDetail");

                $('#modal-table1').modal('hide');
            } else {
                alert(response.data.status);
            }
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });

    };
    //教师处理试卷得分，备注实现
    $scope.addMemo = function (path) {
        if ($scope.conf.memo == null) {
            alert("内容不能为空，请重新输入！！")
        } else {
            $http({
                method: "POST",
                url: baseUrl + path + "/update",
                data: $scope.conf
            }).then(function successCallback(response) {
                if ('0000' == response.data.status) {
                    $scope.query(path);
                    $scope.pageData.param1 = $scope.conf.id;
                    $scope.query1("recordDetail");
                    $('#modal-table1').modal('hide');
                } else {
                    alert(response.data.status);
                }
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }

    };
    $scope.del = function (Count, path) {
        if (confirm("确定删除吗？") == true) {
            $scope.conf = $scope.infos[Count];
            $http({
                method: "POST",
                url: baseUrl + path + "/del",
                data: $scope.conf
            }).then(function successCallback(response) {
                $scope.query(path);
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }
    }


}

//登陆实现
function loginCtrl($scope, $http) {
    $scope.message = '';
    $scope.name = '';
    $scope.lastTime = '';
    $scope.loginData = {
        username: '',
        userpass: '',
        sysType: '',
        token: '',
        sysStatus: ''
    };
    $scope.login = function () {
        $http({
            method: "POST",
            url: baseUrl + "app/login",
            data: $scope.loginData
        }).then(function successCallback(response) {
            $scope.loginData = response.data;
            $scope.name = response.data.name;
            $scope.lastTime = response.data.lastTime;

            var lastTime = $scope.lastTime;
            var lname = $scope.name;
            var status1 = response.data.status;
            var roleType = response.data.sysType;
            token = response.data.token;

            window.localStorage.setItem("lname", lname);
            window.localStorage.setItem("lastTime", lastTime);
            window.localStorage.setItem("roleType", roleType);

            if (status1 == "0000") {
                if ("管理员" == roleType) {
                    window.location = projectNmae + "systemIndex.html";

                } else if ("教师" == roleType) {
                    window.location = projectNmae + "teachIndex.html";
                } else if ("学生" || "用户" == roleType) {
                    window.location = projectNmae + "stuIndex.html";
                } else {
                    alert('系统错误');
                    window.location.reload();

                }
            } else {
                alert(status1);
                window.location.reload();
                $scope.message = status1;
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            $scope.message = '系统错误';
        });
    }
}

//类别功能实现
function sysTypeCtrl($scope, $http) {
    $scope.conf = "";
    $scope.pageData = {
        sumCount: 0,
        currentPage: 1,
        sumPage: 1,
        pageSize: 10,
        titile: ''
    };
    $scope.query = function () {
        $http({
            method: "POST",
            url: baseUrl + "sys/query",
            data: $scope.pageData
        }).then(function successCallback(response) {
            $scope.infos = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.query();
    //上一页
    $scope.previous = function () {
        $scope.pageData.currentPage = $scope.pageData.currentPage - 1;
        $scope.query();
    };
    //下一页
    $scope.next = function () {
        $scope.pageData.currentPage = $scope.pageData.currentPage + 1;
        $scope.query();
    };
    //获取选择的那列数据
    $scope.getModel = function (Count) {
        $scope.conf = $scope.infos[Count];
    };

    $scope.add = function () {
        $http({
            method: "POST",
            url: baseUrl + "sys/save",
            data: $scope.conf
        }).then(function successCallback(response) {
            $('#modal-table1').modal('hide');
            $scope.query();

        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.del = function (Count) {
        if (confirm("确定删除吗？") == true) {
            $scope.conf = $scope.infos[Count];
            $http({
                method: "POST",
                url: baseUrl + "sys/del",
                data: $scope.conf
            }).then(function successCallback(response) {
                $scope.query();
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }
    }
}


//用户信息功能实现
function sysUserCtrl($scope, $http) {
    $scope.conf = "";
    $scope.request = {
        sumCount: 0,
        currentPage: 1,
        sumPage: 1,
        pageSize: 10,
        usertype: 10,
        username: ''
    };
    $scope.query = function () {
        $scope.request.usertype = "学生";
        $http({
            method: "POST",
            url: baseUrl + "user/query",
            data: $scope.request
        }).then(function successCallback(response) {
            $scope.infos = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.query();
    $scope.query1 = function () {
        $scope.request.usertype = "教师";
        $http({
            method: "POST",
            url: baseUrl + "user/query",
            data: $scope.request
        }).then(function successCallback(response) {
            $scope.infos1 = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.query1();
    //上一页
    $scope.previous = function () {
        $scope.request.currentPage = $scope.request.currentPage - 1;
        $scope.query();
        $scope.query1();
    };
    //下一页
    $scope.next = function () {
        $scope.request.currentPage = $scope.request.currentPage + 1;
        $scope.query();
        $scope.query1();
    };
    //获取选择的那列数据
    $scope.getModel = function (Count) {
        $scope.conf = $scope.infos[Count];
    };
    $scope.getModel1 = function (Count) {
        $scope.conf = $scope.infos1[Count];
    };
    //json 数据
    $scope.school = [
        {
            "institute": "计算机学院",
            "major": ["软件工程1班", "软件工程2班", "软件工程3班"]
        },
        {
            "institute": "电器工程学院",
            "major": ["电器制造1班", "电器制造2班", "电器制造3班",]
        }
        ,
        {
            "institute": "生物工程学院",
            "major": ["生物研究1班", "生物研究1班", "生物研究1班",]
        }
    ];

    $scope.banji = $scope.school[0].major;
    $scope.twoChange = function (xy) {
        $scope.banji = xy.major;
    };
    var userReg = /^([\u4e00-\u9fa5]){2,7}$/;
    var phoneReg = /^1(3|4|5|7|8)\d{9}$/;
    var passReg = /^\d{6,}$/;
    $scope.add = function () {
        $scope.conf.usertype = "学生";
        $scope.conf.spare3 = $scope.xueyuan.institute;
        $scope.conf.usergroup = $scope.ban;
        if ($scope.conf.username == null) {
            alert("请输入学生姓名！！");
        } else if (!userReg.test($scope.conf.username)) {
            alert("请输入正确的学生姓名！！");
        } else if ($scope.conf.userphone == null) {
            alert("请输入手机号码！！");
        } else if (!phoneReg.test($scope.conf.userphone)) {
            alert("请输入正确的手机号码！！");
        } else if ($scope.conf.userpass == null) {
            alert("请输入密码！！");
        } else if (!passReg.test($scope.conf.userpass)) {
            alert("请输入至少6位数密码！！");
        } else if ($scope.conf.useraddr == null) {
            alert("请输入学生宿舍地址！！");
        } else {
            $http({
                method: "POST",
                url: baseUrl + "user/save",
                data: $scope.conf
            }).then(function successCallback(response) {
                $scope.query();
                $scope.conf = "";
                $('#modal-table1').modal('hide');
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }
    };

    $scope.add1 = function () {
        $scope.conf.usertype = "教师";
        $http({
            method: "POST",
            url: baseUrl + "user/save",
            data: $scope.conf
        }).then(function successCallback(response) {
            $scope.query1();
            $('#modal-table1').modal('hide');
            $scope.conf = "";
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.del = function (Count) {
        if (confirm("确定删除吗？") == true) {
            $scope.conf = $scope.infos[Count];
            $http({
                method: "POST",
                url: baseUrl + "user/del",
                data: $scope.conf
            }).then(function successCallback(response) {
                $scope.query();
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }
    };
    $scope.delTeacher = function (Count) {
        $scope.conf = $scope.infos1[Count];
        if (confirm("确定删除吗？") == true) {
            $http({
                method: "POST",
                url: baseUrl + "user/del",
                data: $scope.conf
            }).then(function successCallback(response) {
                $scope.query1();
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        } else {
            $scope.query1();
        }
    }
    $scope.uploadExcel = function () {
        var form = new FormData();
        var file = document.getElementById("fileUpload").files[0];
        if (file == null) {
            alert("请选择你要导入的Excel学生信息表！！");
        } else {
            if (confirm("确定导入题库？") == true) {
                console.log(file);
                form.append('file', file);
                $http({
                    method: 'POST',
                    url: 'http://' + location.host + '/online-service/uploadvideo/uploadExcel',
                    data: form,
                    headers: {'Content-Type': undefined},
                    transformRequest: angular.identity
                }).then(function successCallback(response) {
                    $scope.status = response.data.status;
                    if ($scope.status == "0000") {
                        alert("导入成功！！")
                    } else {
                        alert("导入失败，请重新选择。如果还是不行，请联系管理员！！");
                        window.location.reload()
                    }
                    console.log(response.data.path)
                    // window.location.reload();
                }, function errorCallback(response) {
                });
            }
        }
    }
}

//我的关注
function mycareCtrl($scope, $http) {
    $scope.conf = "";
    $scope.request = {
        sumCount: 0,
        currentPage: 1,
        sumPage: 1,
        pageSize: 10,
        usertype: 10,
        username: ''
    };
    $scope.query = function () {
        $http({
            method: "POST",
            url: baseUrl + "user/mycare",
            data: $scope.request
        }).then(function successCallback(response) {
            $scope.infos = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.query();
    $scope.recordRequest = {
        oprid: ''
    };
    $scope.getNow = function (Count) {
        $scope.recordRequest.oprid = $scope.infos[Count].id;
        $http({
            method: "POST",
            url: baseUrl + "Tbrecord/query",
            data: $scope.recordRequest
        }).then(function successCallback(response) {
            $scope.recordinfos = response.data.list;

        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    //上一页
    $scope.previous = function () {
        $scope.request.currentPage = $scope.request.currentPage - 1;
        $scope.query();

    };
    //下一页
    $scope.next = function () {
        $scope.request.currentPage = $scope.request.currentPage + 1;
        $scope.query();

    };

    $scope.addcareRequest = {
        careid: '',
        type: ''
    };
    $scope.notcare = function (Count) {
        $scope.conf = $scope.infos[Count];
        $scope.addcareRequest.careid = $scope.conf.id;
        $scope.addcareRequest.type = '关注';
        if (confirm("确定取消关注吗？") == true) {
            $http({
                method: "POST",
                url: baseUrl + "Mycare/del",
                data: $scope.addcareRequest
            }).then(function successCallback(response) {

                alert('取消关注成功！');
                $scope.query();

            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }
    }
}

//学习资源功能实现
function tachDataCtrl($scope, $http) {
    $scope.conf = "";
    $scope.videoRequest = {
        sumCount: 0,
        currentPage: 1,
        sumPage: 1,
        pageSize: 10,
        titile: ''
    };
    $scope.pageData = {
        sumCount: 0,
        currentPage: 1,
        sumPage: 1,
        pageSize: 10,
        titile: ''
    };
    $scope.query = function () {
        $http({
            method: "POST",
            url: baseUrl + "video/query",
            data: $scope.pageData
        }).then(function successCallback(response) {
            $scope.infos = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.query();
    //上一页
    $scope.previous = function () {
        $scope.pageData.currentPage = $scope.pageData.currentPage - 1;
        $scope.query();
    };
    //下一页
    $scope.next = function () {
        $scope.pageData.currentPage = $scope.pageData.currentPage + 1;
        $scope.query();
    };
    //获取选择的那列数据
    $scope.getModel = function (Count) {
        $scope.conf = $scope.infos[Count];
        $scope.getSelect();
    };
    $scope.getSelect = function () {
        $http({
            method: "POST",
            url: baseUrl + "sys/select"
        }).then(function successCallback(response) {
            $scope.selectData = response.data.list;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.add = function () {
        $http({
            method: "POST",
            url: baseUrl + "video/save",
            data: $scope.conf
        }).then(function successCallback(response) {
            $scope.query();
            $('#modal-table1').modal('hide');
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.del = function (Count) {
        if (confirm("确定删除吗？") == true) {
            $scope.conf = $scope.infos[Count];
            $http({
                method: "POST",
                url: baseUrl + "video/del",
                data: $scope.conf
            }).then(function successCallback(response) {
                $scope.query();
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }
    };
    $scope.uploadVideo = function () {
        var form = new FormData();
        var videoTitle = $scope.conf.titile;
        var videoType = $scope.conf.type;
        var file = document.getElementById("fileUpload").files[0];
        if (videoTitle == null) {
            alert("请输入视频标题！！");
        } else if (videoType == null) {
            alert("请输入视频类型！！");
        } else if (file == null) {
            alert("请输入选择你要上传的视频！！");
        } else {
            if (confirm("确定上传视频？") == true) {
                console.log(file);
                form.append('videoTitle', videoTitle);
                form.append('videoType', videoType);
                form.append('file', file);
                $http({
                    method: 'POST',
                    url: 'http://' + location.host + '/online-service/uploadvideo/upload',
                    data: form,
                    headers: {'Content-Type': undefined},
                    transformRequest: angular.identity
                }).then(function successCallback(response) {
                    $scope.status = response.data.status;
                    if ($scope.status == "0000") {
                        alert("上传成功！！")
                        $('#modal-table1').modal('hide');
                    } else {
                        alert("上传失败，请重新上传。如果还是不行，请联系管理员！！");
                        window.location.reload()
                    }
                    console.log(response.data.path)
                    // window.location.reload();
                }, function errorCallback(response) {
                });
            }
        }
    }
}

//学习资源功能实现
function stockCtrl($scope, $http) {

    $scope.uploadExcelStock = function () {
        var form = new FormData();
        var file = document.getElementById("fileUpload2").files[0];
        if (file == null) {
            alert("请选择你要导入的Excel题库表！！");
        } else {
            if (confirm("确定导入题库？") == true) {
                console.log(file);
                form.append('file', file);
                $http({
                    method: 'POST',
                    url: 'http://' + location.host + '/online-service/stock/uploadExcel',
                    data: form,
                    headers: {'Content-Type': undefined},
                    transformRequest: angular.identity
                }).then(function successCallback(response) {
                    $scope.status = response.data.status;
                    if ($scope.status == "0000") {
                        alert("导入成功！！");
                        $scope.query();
                    } else {
                        alert("导入失败，请重新选择。如果还是不行，请联系管理员！！");
                        window.location.reload()
                    }
                    console.log(response.data.path)
                    // window.location.reload();
                }, function errorCallback(response) {
                });
            }
        }
    }
    $scope.conf = "";
    $scope.videoRequest = {
        sumCount: 0,
        currentPage: 1,
        sumPage: 1,
        pageSize: 10,
        titile: ''
    };
    $scope.ExamTypeRequest = {
        sumCount: 0,
        currentPage: 1,
        sumPage: 1,
        pageSize: 10,
        titile: ''
    };
    $scope.query = function () {
        $http({
            method: "POST",
            url: baseUrl + "stock/query",
            data: $scope.ExamTypeRequest
        }).then(function successCallback(response) {
            $scope.infos = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.query();
    //上一页
    $scope.previous = function () {
        $scope.ExamTypeRequest.currentPage = $scope.ExamTypeRequest.currentPage - 1;
        $scope.query();
    };
    //下一页
    $scope.next = function () {
        $scope.ExamTypeRequest.currentPage = $scope.ExamTypeRequest.currentPage + 1;
        $scope.query();
    };
    //获取选择的那列数据
    $scope.getModel = function (Count) {
        $scope.conf = $scope.infos[Count];
        $scope.getSelect();
    };
    $scope.getSelect = function () {
        $http({
            method: "POST",
            url: baseUrl + "sys/select"
        }).then(function successCallback(response) {
            $scope.selectData = response.data.list;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.add = function () {
        var stockpoint = $scope.conf.point;
        var stocktitle = $scope.conf.titile;
        var stockanswer = $scope.conf.answer;
        if (stockpoint == null) {
            alert("请输入分值！！")
        } else if (stocktitle == null) {
            alert("请输入试题内容！！")
        } else if (stockanswer == null) {
            alert("请输入试题答案！！")
        } else {
            $http({
                method: "POST",
                url: baseUrl + "stock/save",
                data: $scope.conf
            }).then(function successCallback(response) {
                $scope.conf = "";
                $scope.query();
                $('#modal-table1').modal('hide');
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }
        ;
    }
    $scope.del = function (Count) {
        if (confirm("确定删除吗？") == true) {
            $scope.conf = $scope.infos[Count];
            $http({
                method: "POST",
                url: baseUrl + "stock/del",
                data: $scope.conf
            }).then(function successCallback(response) {
                $scope.query();
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }
    }

}

//学习资源功能实现
function mytachDataCtrl($scope, $http) {
    $scope.conf = "";
    $scope.videoRequest = {
        sumCount: 0,
        currentPage: 1,
        sumPage: 1,
        pageSize: 10,
        titile: ''
    };
    $scope.pageData = {
        sumCount: 0,
        currentPage: 1,
        sumPage: 1,
        pageSize: 10,
        titile: ''
    };
    $scope.query = function () {
        $http({
            method: "POST",
            url: baseUrl + "video/myquery",
            data: $scope.pageData
        }).then(function successCallback(response) {
            $scope.infos = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.query();
    //上一页
    $scope.previous = function () {
        $scope.pageData.currentPage = $scope.pageData.currentPage - 1;
        $scope.query();
    };
    //下一页
    $scope.next = function () {
        $scope.pageData.currentPage = $scope.pageData.currentPage + 1;
        $scope.query();
    };
    //获取选择的那列数据
    $scope.getModel = function (Count) {
        $scope.conf = $scope.infos[Count];
        $scope.getSelect();
    };
    $scope.getSelect = function () {
        $http({
            method: "POST",
            url: baseUrl + "sys/select"
        }).then(function successCallback(response) {
            $scope.selectData = response.data.list;
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.add = function () {
        $http({
            method: "POST",
            url: baseUrl + "video/save",
            data: $scope.conf
        }).then(function successCallback(response) {
            $scope.query();
            $('#modal-table1').modal('hide');
        }, function errorCallback(response) {
            $scope.message = "系统异常"
        });
    };
    $scope.del = function (Count) {
        if (confirm("确定删除吗？") == true) {
            $scope.conf = $scope.infos[Count];
            $http({
                method: "POST",
                url: baseUrl + "video/del",
                data: $scope.conf
            }).then(function successCallback(response) {
                $scope.query();
            }, function errorCallback(response) {
                $scope.message = "系统异常"
            });
        }
    }
}