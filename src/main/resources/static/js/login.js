$(function () {
    // 校验
    $("#loginForm").validate({
        rules: {
            username: "required",
            password: "required"
        },
        messages: {
            username: "请输入用户名",
            password: "请输入密码"
        }
    });
    // 登录
    $("#login").click(function () {
        $.ajax({
            type: "post",
            url: "/login.do",
            data: {
                username: $("input[name=username]").val(),
                password: $("input[name=password]").val()
            },
            dataType: "json",
            beforeSend: function () {
                console.log("sb");
                return $("#loginForm").valid();
            },
            success: function (result) {
                console.log(result);
                if (result.status === 0) {
                    window.location.href = "/index.html";
                }
                if (result.status === 1) {
                    alert('账户或密码错误');
                }
            }
        });
    })
});