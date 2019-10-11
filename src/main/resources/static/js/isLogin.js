$.ajax({
    type: "get",
    url: "/user/getCurrentUser.do",
    dataType: "json",
    success: function (result) {
        if (result.status === 1) {
            window.location.href="/login.html";
        }
    },
    async:false
});