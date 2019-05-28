$(function () {
        var lname = window.localStorage.getItem("lname");
        var lastTime = window.localStorage.getItem("lastTime");
        var roleType = window.localStorage.getItem("roleType");
        if (lname != null && lastTime != null) {
            if (roleType != "学生") {
                window.location.href= "/online-service/404.html";
            } else {
                $("#name").text(lname);
                $("#time").text(lastTime);
            }
        } else {
            $("#helloTitle").html("Hi,您已退出系统，请重新登录!<br/>如果您忘记密码或没有账号请您联系学校管理员并申请账号再进行访问。");
            $("#helloContents").text("联系方式：四川轻化工大学计算机学院 Lina老师 18161089646");
            alert("你已退出系统！请重新登录！！");
            window.location.reload();
            setTimeout(window.location.href = "/online-service/login.html", 5);
        }
    }
);
now = new Date(), hour = now.getHours();
if (hour < 6) {
    $("#hello").text("凌晨好！");
}
else if (hour < 9) {
    $("#hello").text("早上好！");
}
else if (hour < 12) {
    $("#hello").text("上午好！");
}
else if (hour < 14) {
    $("#hello").text("中午好！");
}
else if (hour < 18) {
    $("#hello").text("下午好！");
}
else {
    $("#hello").text("晚上好！");
}