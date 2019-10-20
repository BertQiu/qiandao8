var attendanceData;
var activityData;
var prefix = "qid_"; //因为id不能用数字开头，所以拼上一个前缀 签到id
var nowPage = 1;
var pageNum = 20;// 总共的页数
var pageSize = 10;//一页显示多少条数据
var buttonNum = 7; //要展示的页码数量
var pageTotal;// 总共有多少条数据
var aid = getUrlParam("aid");
var effectAttendPeople=0;

function initData() {
    $.ajax({
        type: "get",
        url: "/activity/getActivity.do/" + aid,
        dataType: "json",
        success: function (result) {
            activityData = result.data;
        },
        async: false
    });
    $.ajax({
        type: "get",
        url: "/attend/listAttendanceInfo.do",
        data: {
            aid: aid,
            currentPage: nowPage,
            pageSize: pageSize
        },
        dataType: "json",
        success: function (result) {
            if (!result.success) {
                window.location.href = "/error/errorPage.html";
            }
            attendanceData = result.data;
            pageTotal = attendanceData.total;
            pageNum = attendanceData.pages;
            effectAttendPeople = pageTotal;
        },
        async: false
    });
}

function render() {
    renderHead();
    renderTable();
}

function renderHead() {
    // 渲染头
    $("#qdTitle").text(activityData.activityName);
    $("#qdStartTime").text(dateFormat(activityData.startTime));
    $("#qdNum").text(pageTotal);
}

function renderTable() {
    var rows = 1;//1是签到时间
    // 渲染表格
    var appendedHtmlCode="";
    var tableEl = $("#activitiesInfo");
    tableEl.empty();
    appendedHtmlCode += "<div class=\"table-responsive\">\n" +
        "<table class=\"table  table-hover \">\n" +
        "<thead>\n" +
        "<tr>";
    //迭代选项
    $.each(JSON.parse(activityData.basicSelc), function (i, v) {
        appendedHtmlCode += "<td>" + v.title + "</td>";
        rows++;
    });
    $.each(JSON.parse(activityData.listSelc), function (i, v) {
        appendedHtmlCode += "<td>" + v.title + "</td>";
        rows++;
    });
    appendedHtmlCode +="<td>签到时间</td>";
    appendedHtmlCode += "</tr>\n" +
        "</thead>";
    //没人签到
    if (pageTotal === 0) {
        appendedHtmlCode += "</table>\n" +
            "<p class=\"emptyInfo\">暂无人员签到</p>\n" +
            "</div>";
    } else {
        appendedHtmlCode+="<tbody>";
        $.each(attendanceData.list,function (i, v) {
            var basicItem="";
            var listItem="";
            try{
                basicItem = JSON.parse(v.basicSelcInfo);
                listItem = JSON.parse(v.listSelcInfo);
            }catch(e){
            }
            if (basicItem == "" && listItem == "") {
                effectAttendPeople--;
                return true;
            }
            var _perfix = prefix + v.id;
            appendedHtmlCode += "<tr id=\"" + _perfix + "\">";
            $.each(basicItem, function (i,v) {
                appendedHtmlCode+="<td>"+v.content+"</td>"
            });
            $.each(listItem, function (i,v) {
                appendedHtmlCode+="<td>"+v.selectedOption+"</td>"
            });
            appendedHtmlCode += "<td>" + dateFormat(v.checkInTime)+ "</td>";
            appendedHtmlCode += "</tr>";
        });
        appendedHtmlCode+="<tr><td colspan=\""+rows+"\">\n" +
            "<p class=\"countAll\">共有<span id=\"pageTotal\">"+effectAttendPeople+"</span>条有效数据</p>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "<div id=\"page\"></div>\n" +
            "</div>"
    }
    tableEl.append(appendedHtmlCode);
}
function refresh() {
    initData();
    renderTable();
    paging();
}

function paging() {
    $("#page").paging({
        nowPage: nowPage, // 当前页码
        pageNum: pageNum, // 总页码
        buttonNum: buttonNum, //要展示的页码数量
        canJump: 1,// 是否能跳转。0=不显示（默认），1=显示
        showOne: 0,//只有一页时，是否显示。0=不显示,1=显示（默认）
        callback: function (num) { //回调函数
            nowPage = num;
            refresh();
        }
    });
}

function deleteActivity(aid) {
    $.ajax({
        type: "get",
        url: "/activity/deleteActivities.do",
        data: {
            aid : aid
        },
        dataType: "json",
        success: function (result) {
            if (result.success) {
                alert(result.msg);
                window.location.href="viewMyActivities.html"
            } else {
                alert(result.msg);
            }
        },
        async: false
    });
}

function downloadExcel() {
    var aid = aid;
}
$(function () {
    initData();
    render();
    paging();
    $("#deleteActionBtn").click(function () {
        if (confirm("是否要删除本次活动？")) {
            deleteActivity(aid);
        }
    });
});
