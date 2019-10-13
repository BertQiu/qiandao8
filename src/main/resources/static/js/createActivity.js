var basicItems = []; //basicComponents对象
var listItems = [];//listComponents对象
// type 0 basic 1 list
function addItem(ev,type,title) {
    // var itemContainer = $("#items");
    var _title;
    // 判断是否有标题
    if (title == null) {
        // 没使用模板添加
        _title = "";
    } else {
        _title=title
    }
    // 判断类型 0:bi 1:ls
    var idx = -1;
    if (type === 0) {
        // 先在数组中存放数据
        idx = getIncreasedValByLastIdx(basicItems);
        basicItems.push(idx);
        //生成dom
        $("#basicCreatedItems").append(
            " <div class=\"basicCreatedItem\" id=\"bi"+idx+"\">\n" +
            " <label for=\"bi"+idx+"t\">\n" +
            "     <span class=\"red\">*</span>名称:\n" +
            " </label>\n" +
            " <input type=\"text\" name=\"basicItemName\" class=\"form-control \" id=\"bi"+idx+"t\" value="+_title+">\n" +
            " <label for=\"bi"+idx+"r\" style=\"color: black;\">\n" +
            "     备注:\n" +
            " </label>\n" +
            " <input type=\"text\" name=\"basicItemRemark\" class=\"form-control \" id=\"bi"+idx+"r\">\n" +
            " <button type=\"button\" class=\"btn btn-default ml15\" aria-label=\"Left Align\" onclick=\"deleteItem(this)\">\n" +
            "     <span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span>\n" +
            " </button>\n" +
            "  </div>")
    }else if (type === 1) {
        idx = getIncreasedValByLastIdx(listItems);
        listItems.push(idx);
        $("#listCreatedItems").append(
            "<div class=\"listCreatedItem\" id=\"ls"+idx+"\">\n" +
            "<label for=\"ls"+idx+"t\">\n" +
            "<span class=\"red\">*</span>名称:\n" +
            "</label>\n" +
            "<input type=\"text\" name=\"basicItemName\" class=\"form-control \" id=\"ls"+idx+"t\" value="+_title+">\n" +
            "<label for=\"ls1o\" style=\"color: black;\">\n" +
            "    选项:\n" +
            "</label>\n" +
            "<input type=\"text\" name=\"basicItemName\" class=\"form-control \" id=\"ls"+idx+"o\" maxlength=\"10\">\n" +
            "<button type=\"button\" class=\"btn btn-default ml15\" aria-label=\"Left Align\" onclick=\"addOption(this)\">\n" +
            "<span class=\"glyphicon glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>\n" +
            "</button>\n" +
            "\n" +
            "<label for=\"ls"+idx+"x\" style=\"color: black;\">\n" +
            "    效果预览:\n" +
            "</label>\n" +
            "<select id=\"ls"+idx+"x\" style=\"color: black;\">\n" +
            "</select>\n" +
            "<button type=\"button\" class=\"btn btn-default\" aria-label=\"Left Align\" onclick=\"deleteOption(this)\">\n" +
            "<span class=\"glyphicon glyphicon-minus\" aria-hidden=\"true\"></span>\n" +
            "</button>\n" +
            "<button type=\"button\" class=\"btn btn-default\" aria-label=\"Left Align\" onclick=\"deleteItem(this)\">\n" +
            "<span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span>\n" +
            "</button>\n" +
            "</div>\n"
        )
    }
}

$(function () {
    $("#InfoForm").validate({
        rules: {
            activityName: "required",
            originator: "required",
            endTime: {
                required: true,
                date: true
            },

        },
        messages: {
            activityName: "请填写活动名",
            originator: "请填写发起者",
            endTime: "请填写正确的日期",
        }
    });


    $("#submit").click(function () {
        var componentUtil = new ComponentUtil();
        var basicComponentsStr = componentUtil.getBasicComponentsString();
        var listComponentsStr = componentUtil.getListComponentsString();
        $.ajax({
            type: "post",
            url: "/activity/createOneActivity.do",
            data: {
                activityName: $("input[name=activityName]").val(),
                originator: $("input[name=originator]").val(),
                startTime: dateFormat(new Date()),
                endTime: dateFormat($("input[name=endTime]").val()),
                basicComponents: basicComponentsStr,
                listComponents: listComponentsStr,
            },
            dataType: "json",
            beforeSend: function () {
                if (basicComponentsStr === "" && listComponentsStr === "") {
                    alert("请至少选择一项签到信息！");
                    return false;
                }
                return $("#InfoForm").valid();
            },
            success: function (result) {
                if (result.status === 0) {
                    window.location.href="signinBoard.html"
                } else if (result.status === 1) {
                    alert("提交失败！" + result.msg);
                }
            }
        });
    });
})