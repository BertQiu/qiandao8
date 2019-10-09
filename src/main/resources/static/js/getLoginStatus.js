$.ajax({
    type: "get",
    url: "/user/getCurrentUser.do",
    dataType: "json",
    success: function (result) {
        if (result.status === 0) {
            $("#userInfo").append(
                "<li><a href=\"#about\">"+result.data.username+"</a></li>\n" +
                "<li><a href=\"/logout.do\">注销</a></li>"
            )
        }else if (result.status === 1) {
            $("#userInfo").append(
                "<li><a href=\"login.html\" >登录</a></li>\n" +
                "<li><a href=\"/about/\" >注册</a></li>"
            )
        }
    }
})