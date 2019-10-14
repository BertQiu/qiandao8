$.ajax({
    type: "get",
    url: "/user/getCurrentUser.do",
    dataType: "json",
    success: function (result) {
        if (!result.success) {
            window.location.href="/login.html";
        }
    },
    async:false
});