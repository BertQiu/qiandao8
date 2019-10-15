var activitiesData;
var prefix = "aid_"; //因为id不能用数字开头，所以拼上一个前缀
var nowPage = 1;
var pageNum ;// 总共的页数
var pageSize = 10;//一页显示多少条数据
var buttonNum = 7; //要展示的页码数量
var pageTotal;// 总共有多少条数据
// init
initData();
//init end
function initData() {
    $.ajax({
        type: "get",
        url: "/activity/listAllActivities.do",
        data: {
            currentPage: nowPage,
            pageSize: pageSize
        },
        dataType: "json",
        success: function (result) {
            if (!result.success) {
                window.location.href = "/error/errorPage.html";
            }
            activitiesData = result.data;
            pageTotal = activitiesData.total;
            pageNum=activitiesData.pages;
        },
        async: false
    });
}

function render(activityInfoEl) {
    var appendedHtmlCode="";
    activityInfoEl.empty();
    //判断是否创建了活动
    if (pageTotal == 0) {
        appendedHtmlCode = "<div class=\"empty\">\n" +
            "<img src=\"images/empty.svg\" alt=\"\">\n" +
            "<p> 亲～您还没有创建活动哦，去<a href=\"createActivity.html\">创建新活动</a>吧！</p>\n" +
            "</div>";
    } else {
        //头
        appendedHtmlCode = "<div class=\"table-responsive\">\n" +
            "<table class=\"table  table-hover \">\n" +
            "<thead>\n" +
            "<tr>\n" +
            "<td>活动名称</td>\n" +
            "<td>活动时间</td>\n" +
            "<td>状态</td>\n" +
            "<td>签到人数</td>\n" +
            "<td>操作</td>\n" +
            "</tr>\n" +
            "</thead>\n" +
            "<tbody>";
        //内容
        $.each(activitiesData.list, function (i, v) {
            var itemId = prefix+v.id;
            appendedHtmlCode+="<tr id=\"aid_"+itemId+"\">\n" +
                "<td>"+v.activityName+"</td>\n" +
                "<td>" +dateFormat(v.startTime)+" ——"+dateFormat(v.endTime)+ "</td>";
            //判断状态：进行中或者已结束
            if (v.status === 1) {
                //已结束
                appendedHtmlCode+="<td>已结束</td>";
            } else {
                appendedHtmlCode+="<td class=\"blue\">进行中</td>";
            }
            appendedHtmlCode+=" <td><span>"+v.participantsNums+"</span>人</td> "
            appendedHtmlCode += "<td>\n" +
                "<a href=\"signinBoard.html?aid="+v.id+"\">互动墙</a>\n" +
                "<a href=\"viewActivityDetail.html?aid="+v.id+"\">活动详情</a>\n" +
                "<a  onclick='deleteActivity("+v.id+")'>删除</a>\n" +
                "</td>\n" +
                "</tr>";
        });
        //尾
        appendedHtmlCode += "<tr>\n" +
            "<td colspan=\"5\">\n" +
            "<p class=\"countAll\">共有<span id=\"pageTotal\">"+pageTotal+"</span>条数据</p>\n" +
            "</td>\n" +
            "</tr>";

        appendedHtmlCode+="</tbody>\n" +
            "</table>\n" +
            "</div>\n" +
            "<div id=\"page\">\n" +
            "</div>"
    }
    activityInfoEl.append(appendedHtmlCode);
}

function refresh() {
    initData();
    render($("#activitiesInfo"));
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
                refresh();
            } else {
                alert(result.msg);
            }
        },
        async: false
    });
}

$(function () {
    render($("#activitiesInfo"));
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
});
