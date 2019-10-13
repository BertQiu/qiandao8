// ls1 下拉框1 ls1o 下拉框1选项 ls1x 下拉框1效果
function addOption(ev) {
    var id = "#"+ev.parentNode.id;
    var newOpt = $(id + "o").val().trim();
    var selectEl = $(id + "x");
    var optionEl = $(id + "o");
    if (newOpt === "") {
        alert("请输入一个选项");
        return;
    }
    $(id + "x").append(
        " <option value='"+newOpt+"'>"+newOpt+"</option>"
    );
    // 清空
    optionEl.val("");
    //让select选择
    selectEl.val(newOpt);
}

function deleteOption(ev) {
    var id = "#"+ev.parentNode.id;
    var selectVal = $(id + "x").val();
    $(id + "x option[value='" + selectVal + "']").remove();
}


function deleteItem(ev) {
    var id = ev.parentNode.id;
    var idNum = parseInt(ev.parentNode.id.substring(2));
    // 判断是bi元素还是ls元素
    if (id.substring(0, 2) === "bi") {
        // 先从数组中挪去这个元素
        basicItems.remove(idNum);
        // 再从页面删除元素
        ev.parentNode.parentNode.removeChild(ev.parentNode);
    }else if (id.substring(0, 2) === "ls") {
        listItems.remove(idNum);
        ev.parentNode.parentNode.removeChild(ev.parentNode);
    }
}


function getIncreasedValByLastIdx(arr) {
    return arr.length === 0 ? 1 : arr[arr.length - 1]+1;
}

// <!--id：bi1 = 普通框1 bi1t = 普通框1标题 bi1r =普通框1备注
// opt:id: ls1+opt1
// basicComponents=姓名-aaa,手机,呵呵-123&listComponents=性别:男,女-年龄:20,30,40
// -->
function ComponentUtil() {
    this.getBasicComponentsString = function () {
        var failure = false;// 本次执行失败，返回空
        var ret="";
        if (basicItems.length===0){
            return "";
        }
        for (var i = 0; i < basicItems.length; i++) {
            var objEl = $("#bi" + basicItems[i]);
            var titleEl = $("#bi" + basicItems[i] + "t");
            var remarkEl = $("#bi" + basicItems[i] + "r");
            // 判断是否填写标题
            if (titleEl.val().trim() === "") {
                objEl.addClass("red");
                titleEl.attr("placeholder", "请输入活动名");
                failure = true;
            }
            // 判断是否有备注
            if (remarkEl.val().trim() === "") {
                ret += titleEl.val() + ",";
            } else {
                ret += titleEl.val() + "-" + remarkEl.val() + ",";
            }
        }
        return failure ? "" :  ret.substring(0, ret.length - 1);
    };

    // ls1 下拉框1 ls1t下拉框标题  ls1x 下拉框1效果
    // opt:id: ls1+opt1
    // listComponents=性别:男,女-年龄:20,30,40
    this.getListComponentsString = function () {
        var failure = false;// 本次执行失败，返回空
        var ret="";
        if (listItems.length===0){
            return "";
        }
        for (var i = 0; i < listItems.length; i++) {
            var objEl = $("#ls" + listItems[i]);
            var titleEl = $("#ls" + listItems[i] + "t");
            // 判断是否填写标题
            if (titleEl.val().trim() === "") {
                objEl.addClass("red");
                titleEl.attr("placeholder", "请输入活动名");
                failure = true;
            }
            // 迭代选项
            var options = $("#ls" + listItems[i] + "x option");
            if (options.size() === 0) {
                //当一个下拉选项没有任何选项，此条失败了
                continue;
            }
            // 添加名称
            ret += titleEl.val()+":";
            options.each(function(){ //遍历全部option
                var txt = $(this).text(); //获取option的内容
                ret += txt + ",";
            });
            ret = ret.substring(0, ret.length - 1) + "-";
        }
        return failure ? "" :  ret.substring(0, ret.length - 1);
    };

}