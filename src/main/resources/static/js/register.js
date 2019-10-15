$(function () {
    // 校验
    $("#loginForm").validate({
        rules: {
            username: "required",
            password: "required",
            password2:{
                equalTo:"#inputPassword"
            },
            nickName: "required"

        },
        messages: {
            username: "请输入用户名",
            password: "请输入密码",
            password2: "两次密码不一致",
            nickName: "请输入昵称"
        }
    });
    // 登录
    $("#login").click(function () {
        $.ajax({
            type: "post",
            url: "/register.do",
            data: {
                username: $("input[name=username]").val(),
                password:$("input[name=password]").val(),
                nickName: $("input[name=nickName]").val()
            },
            dataType: "json",
            beforeSend: function () {
                return $("#loginForm").valid();
            },
            success: function (result) {
                if (result.success) {
                    alert('注册成功！');
                    window.location.href = "/index.html";
                }
                if (!result.success) {
                    alert(+result.msg);
                }
            }
        });
    });
});
