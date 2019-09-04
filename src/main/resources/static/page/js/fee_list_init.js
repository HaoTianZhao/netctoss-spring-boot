(function ($) {
    $.fn.initCostList = function (vo) {
        $('#datalist').empty();

        $('#datalist').append($(
            "<tr>" +
                '<th>资费ID</th>' +
                '<th class="width100">资费名称</th>' +
                '<th>基本时长</th>' +
                '<th>基本费用</th>' +
                '<th>单位费用</th>' +
                '<th>创建时间</th>' +
                '<th>开通时间</th>' +
                '<th class="width50">状态</th>' +
                '<th class="width200"></th>' +
            "</tr>")
        )

        for (cost of vo.costColumns) {
            $('#datalist').append($(
                "<tr>" +
                    "<td>" + g_isNull(cost.costId, "-"," cost.costId") + "</td>" +
                    "<td><a href='#' class='btn_detail'>" + g_isNull(cost.costName, "-", "cost.costName") + "</a></td>" +
                    "<td>" + g_isNull(cost.costBaseDuration, "-", "cost.costBaseDuration + '小时'") + "</td>" +
                    "<td>" + g_isNull(cost.costBaseCost, "-", "cost.costBaseCost + '元'") + "</td>" +
                    "<td>" + g_isNull(cost.costUnitCost, "-", "cost.costUnitCost + '元/小时'") + "</td>" +
                    "<td>" + g_isNull(cost.costCreattime, "-", "cost.costCreattime") + "</td>" +
                    "<td>" + g_isNull(cost.costStarttime, "-", "cost.costStarttime") + "</td>" +
                    "<td>" + (cost.costStatus == 0 ? "开通" : "暂停") + "</td>" +
                    "<td>" +
                        (cost.costStatus == 0 ? "" : 
                        ("<input type='button' value='启用' class='btn_start' />" +
                        "<input type='button' value='修改' class='btn_modify' />" +
                        "<input type='button' value='删除' class='btn_delete' />")) +
                    "</td>" +
                    "<td hidden name='costType'>" + g_isNull(cost.costType, "-", "cost.costType") + "</td>" +
                    "<td hidden name='costDescr'>" + g_isNull(cost.costDescr, "-", "cost.costDescr") + "</td>" +
                "</tr>"
                ));
        }

        //详情
        $('.btn_detail').click(function (e) {
            var tds = e.target.parentNode.parentNode.getElementsByTagName('td');
            sessionStorage.setItem("modiCostId", tds[0].innerText);
            sessionStorage.setItem("modiCostName", tds[1].innerText);
            sessionStorage.setItem("modiCostStatus", tds[7].innerText);
            sessionStorage.setItem("modiCostType", $(e.target.parentNode.parentNode).find("[name='costType']").text());
            sessionStorage.setItem("modiCostBaseDuration", tds[2].innerText);
            sessionStorage.setItem("modiCostBaseCost", tds[3].innerText);
            sessionStorage.setItem("modiCostUnitCost", tds[4].innerText);
            sessionStorage.setItem("modiCostCreattime", tds[5].innerText);
            sessionStorage.setItem("modiCostStarttime", tds[6].innerText);
            sessionStorage.setItem("modiCostDescr", $(e.target.parentNode.parentNode).find("[name='costDescr']").text());
            location.href = 'fee_detail.html';
        });

        //启用
        $('.btn_start').click(function (e) {
            var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
            if (r) {
                var costId = e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText;
                console.log('costId:' + costId);
                global_post("/cost/startUsing", { 'costId': costId }, function (result) {
                    if (result.code == 200) {
                        global_operate_result(true, "启用成功！");
                    } else if (result.code == 500) {
                        global_operate_result(false, "启用失败，数据并发错误");
                    }
                });
            }
        });

        //修改
        $('.btn_modify').click(function (e) {
            var tds = e.target.parentNode.parentNode.getElementsByTagName('td');
            sessionStorage.setItem("modiCostId", tds[0].innerText);
            sessionStorage.setItem("modiCostName", tds[1].innerText);
            sessionStorage.setItem("modiCostType", $(e.target.parentNode.parentNode).find("[name='costType']").text());
            sessionStorage.setItem("modiCostBaseDuration", tds[2].innerText);
            sessionStorage.setItem("modiCostBaseCost", tds[3].innerText);
            sessionStorage.setItem("modiCostUnitCost", tds[4].innerText);
            sessionStorage.setItem("modiCostDescr", $(e.target.parentNode.parentNode).find("[name='costDescr']").text());
            location.href = 'fee_modi.html';
        });

        //删除
        $('.btn_delete').click(function (e) {
            var r = window.confirm("确定要删除此资费吗？");
            if (r) {
                var costId = e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText;
                console.log('costId:' + costId);
                global_post("/cost/delete", { 'costId': costId }, function (result) {
                    if (result.code == 200) {
                        global_operate_result(true, "删除成功！");
                    } else if (result.code == 500) {
                        global_operate_result(false, "删除失败，数据并发错误");
                    }
                });
            }
        });

    }
})(jQuery);