$(function () {
    var basicComponentContainer = $("#basicComponentContainer");
    var listComponentContainer = $("#listComponentContainer");
    // 根据 basicComponent，listComponent对象生成此次表单
    // 基本组件id:bi_+${i} 列表组件id:ls_+${i}
    $.each(basicComponent, function (i, v) {
        var _title = v.title;
        var _remark = v.remarks===undefined?"":v.remarks;
        basicComponentContainer.append(
            "<div class=\"form-group\">\n" +
            "<label for=\"bi_"+i+"\" class=\"col-sm-2 control-label\">"+_title+"</label>\n" +
            "<div class=\"col-xs-12 col-sm-8 col-md-6 col-lg-4\">\n" +
            " <input type=\"text\" class=\"form-control\" id=\"bi_"+i+"\" placeholder=\""+_remark+"\">\n" +
            "</div>\n" +
            "</div> "
        )
    });
    $.each(listComponent, function (i, v) {
        var _title = v.title;
        var _options = v.options;
        listComponentContainer.append(
            "<div class=\"form-group\">\n" +
            "<label for=\"ls_" + i + "\" class=\"col-sm-2 control-label\">" + _title + "</label>\n" +
            "<div class=\"col-xs-12 col-sm-8 col-md-6 col-lg-4\">\n" +
            "<select name=\"\" id=\"ls_" + i + "\" class=\"form-control\">\n" +
            "</select>\n" +
            "</div>\n" +
            "</div>"
        );
        var _selectEl = $("#ls_" + i);
        for (var j = 0; j < _options.length; j++) {
            _selectEl.append(
                "<option value=\"" + _options[j] + "\">" + _options[j] + "</option>\n"
            );
        }
    });

    $("#submit").click(function () {
        // 对表格内容进行更新
        $.each(basicComponent, function (i, v) {
            // 获取元素
            var basicEl = $("#bi_" + i);
            v.content = basicEl.val();
        });
        $.each(listComponent, function (i, v) {
            // 获取元素
            var listEl = $("#ls_" + i);
            v.selectedOption = listEl.val();
        });
        $.ajax({
            type: "post",
            url: "/attend/attendActivity.do",
            data: {
                basicSelcInfo: JSON.stringify(basicComponent),
                listSelcInfo: JSON.stringify(listComponent)
            },
            dataType: "json",
            success: function (result) {
                if (result.success) {
                    alert(result.msg);
                    window.location.href = "/index.html";
                }
                if (!result.success) {
                    alert('签到失败!'+result.msg);
                }
            }
        });
    })
});
