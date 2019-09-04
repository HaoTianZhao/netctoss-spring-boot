function gloal_save_result(success, text) {
    if (success) {
        $('#save_result_info').text(text);
        $('#save_result_info').removeClass('save_fail');
        $('#save_result_info').addClass('save_success');
    }
    else {
        $('#save_result_info').text(text);
        $('#save_result_info').removeClass('save_success');
        $('#save_result_info').addClass('save_fail');
    }
    $('#save_result_info').show('slow');
    setTimeout(() => {
        $('#save_result_info').hide('slow');
    }, 3000);
}

function global_operate_result(success, text) {
    if (success) {
        $('#operate_result_info p')[0].innerText = text;
        $('#operate_result_info').removeClass('operate_fail');
        $('#operate_result_info').addClass('operate_success');
    }
    else {
        $('#operate_result_info p')[0].innerText = text;
        $('#operate_result_info').removeClass('operate_success');
        $('#operate_result_info').addClass('operate_fail');
    }
    $('#operate_result_info').show();
}

function global_post(url, data, callback) {
    $.post({
        url, data,
        success: function (result) {
            if (result.code == 0) {
                location.href = "/page/login.html";
            } else if (result.code == 30) {
                alert("请输入合法参数");
            } else if (result.code == 100) {
                location.href = "/page/nopower.html";
            }
            callback(result);
        },
        error: function () {
            location.href = "/page/error.html";
        }
    });
}

function g_isNull(object, yes, no) {
    if (object == null || object == undefined || object == "undefined")
        return yes;
    return eval(no);
}

var g_fillForm = function ($form, json) {
    var jsonObj = json;
    if (typeof json === 'string') {
        jsonObj = $.parseJSON(json);
    }
    for (var key in jsonObj) { // 遍历json字符串
        var objtype = jsonObjType(jsonObj[key]); // 获取值类型
        if (objtype === "array") { // 如果是数组，一般都是数据库中多对多关系
            var obj1 = jsonObj[key];
            for (var arraykey in obj1) {
                // alert(arraykey + jsonObj[arraykey]);
                var arrayobj = obj1[arraykey];
                for (var smallkey in arrayobj) {
                    setCkb(key, arrayobj[smallkey]);
                    break;
                }
            }
        } else if (objtype === "object") { // 如果是对象，啥都不错，大多数情况下，会有 xxxId
            // 这样的字段作为外键表的id

        } else if (objtype === "string") { // 如果是字符串
            var str = jsonObj[key];
            var date = new Date(str);
            if (str.length >= 8 && date.getDay()) { // 这种判断日期是本人懒，不想写代码了，大家慎用。
                $("[name=" + key + "]", $form).val(str);
                continue;
            }

            var tagobjs = $("[name=" + key + "]", $form);
            if ($(tagobjs[0]).attr("type") == "radio") {// 如果是radio控件
                $.each(tagobjs, function (keyobj, value) {
                    if ($(value).attr("value") == jsonObj[key]) {
                        value.checked = true;
                    }
                });
                continue;
            }
            $("[name=" + key + "]", $form).val(jsonObj[key]);
        } else { // 其他的直接赋值
            $("[name=" + key + "]", $form).val(jsonObj[key]);
        }

    }
}

var setCkb = function (name, value) {
    // alert(name + " " + value);
    // $("[name=" + name + "][value=" + value + "]").attr("checked", "checked");
    // 不知为何找不到具体标签;
    $("[name='" + name + "'][val='" + value + "']").attr("checked", "checked");
}

var fillckb = function (name, json) {
    var jsonObj = json;
    if (typeof json === 'string') {
        jsonObj = $.parseJSON(json);
    }
    var str = jsonObj[name];
    if (typeof str === "string") {
        var array = str.split(",");
        $.each(array, function (key, value) {
            setCkb(name, value);
        });
    }
}

var jsonObjType = function (obj) {
    if (typeof obj === "object") {
        var teststr = JSON.stringify(obj);
        if (teststr[0] == '{' && teststr[teststr.length - 1] == '}')
            return "class";
        if (teststr[0] == '[' && teststr[teststr.length - 1] == ']')
            return "array";
    }
    return typeof obj;
}