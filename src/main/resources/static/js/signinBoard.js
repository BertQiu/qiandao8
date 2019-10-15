$(function () {
    var refreshTime = 3000;//刷新时间
    var aid = getUrlParam("aid");
    var actInfoUrl;//请求活动信息的url地址

    if (aid == null) {
        //如果aid为null，则调用无参接口
        actInfoUrl = "/activity/getActivity.do";
    } else {
        actInfoUrl = "/activity/getActivity.do/" + aid;
    }
    // 获取此次活动信息
    $.ajax({
        type: "get",
        url: actInfoUrl,
        dataType: "json",
        success: function (result) {
            if (!result.success) {
                alert("查询活动失败！" + result.msg);
                window.location.href = "index.html";
            }
            $("#actTitle").text(result.data.activityName);
            $("#createTime").text(dateFormat(result.data.createTime));
            $("#endTime").text(dateFormat(result.data.endTime));
            if (result.data.status === 0) {
                aid = result.data.id;//现在就有了id
                $("#left").append(
                    "<div class=\"title\">\n" +
                    "<p>请扫描二维码完成签到</p>\n" +
                    "</div>\n" +
                    "<div  class=\"qrCode\">\n" +
                    "<div id='qrcode' class=\"img\"></div>\n" +
                    "<a id=\"testA\" href=\"\" style=\"text-align: center;font-size: 24px;\"></a>\n" +
                    "</div>\n"
                )
            } else if (result.data.status === 1) {
                // 活动已经结束
                $("#left").append(
                    "<div class=\"title\">\n" +
                    "<p>活动已结束</p>\n" +
                    "</div>\n" +
                    "<div class=\"img\">\n" +
                    "<img src=\"images/image-over.png\" alt=\"\">\n" +
                    "</div>"
                )
            }
        },
        async: false
    });
    // 定义一些需要用到的函数
    function getJoinNumFun() {
        if (aid == null) {
            return;
        }
        $.ajax({
            type: "get",
            url: "/activity/getParticipantNumbers.do?aid=" + aid,
            dataType: "json",
            success: function (result) {
                var joinBoard = $("#joinBoard");
                if (result.status === 0) {
                    if (result.data === 0) {
                        //没人签到
                        joinBoard.empty();
                        joinBoard.append("<img src=\"images/img-nomessage.png\" height=\"280\"/><p class=\"noPeople\">暂无人员签到</p>\n");
                    } else {
                        joinBoard.empty();
                        joinBoard.append(
                            "<p>当前共有<span id=\"joinNum\">" + result.data + "</span>人签到！</p>"
                        )
                    }
                }
            }
        });
    };

    function getQRcodeFun() {
        if (aid == null) {
            return;
        }
        $.ajax({
            type: "get",
            url: "/attend/getCheckInUrl.do?aid=" + aid,
            dataType: "json",
            success: function (result) {
                if (result.success) {
                    $("#qrcode").empty();
                    var location = new Const().ProjectDomain + "attend/getAttendAccess.do?aid=" + aid + "&token=" + result.data;
                    console.log(location);
                    var qrcode = new QRCode(document.getElementById("qrcode"), {
                        width : 400,
                        height : 400
                    });
                    qrcode.makeCode(location);
                }
            }
        });
    };

    getJoinNumFun();
    getQRcodeFun();
    //3秒请求一次
    setInterval(function () {
        //请求当前签到人数
        getJoinNumFun();
        // 获取签到二维码
        getQRcodeFun();
    }, refreshTime);

})
