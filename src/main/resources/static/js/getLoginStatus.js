$.ajax({
    type: "get",
    url: "/user/getCurrentUser.do",
    dataType: "json",
    success: function (result) {
        if (result.success) {
            $("#userInfo").append(
                "<li><a href=\"#about\">"+result.data.username+"</a></li>\n" +
                "<li><a onclick='window.location.href=\"/logout.do\"'>注销</a></li>"
            )
        }else if (!result.success) {
            $("#userInfo").append(
                "<li><a href=\"login.html\" >登录</a></li>\n" +
                "<li><a href=\"register.html\" >注册</a></li>"
            )
        }
    }
})