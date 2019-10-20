var activityObj;
var basicComponent;
var listComponent;
$.ajax({
    type: "get",
    url: "/attend/getAttendActivityInfo.do",
    dataType: "json",
    success: function (result) {
        console.log(result);
        if (!result.success) {
            window.location.href = "/error/errorPage.html";
        }
        activityObj = result.data;
        basicComponent = JSON.parse(activityObj.basicSelc);
        listComponent = JSON.parse(activityObj.listSelc);
    },
    async: false
});