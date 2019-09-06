(function ($) {
    $.fn.initServiceList = function (vo) {
        $('#datalist').empty();

        $('#datalist').append($(
            "<tr>" +
                '<th class="width50">业务ID</th>' +
                '<th class="width70">账务账号ID</th>' +
                '<th class="width150">身份证</th>' +
                '<th class="width70">姓名</th>' +
                '<th>OS 账号</th>' +
                '<th class="width50">状态</th>' +
                '<th class="width100">服务器 IP</th>' +
                '<th class="width100">资费</th>' +
                '<th class="width200"></th>' +
            "</tr>")
        )

        for (column of vo.serviceColumns) {
            var service = column.service;
            var account = column.account;
            var cost = column.cost;

            var domString;
            if(service.serviceStatus == "2")
                domString = "";
            else{
                if (service.serviceStatus == "0")
                    domString = '<input type="button" value="暂停" class="btn_pause" />';
                else if (service.serviceStatus == "1" && account.accountStatus == "0")
                    domString = '<input type="button" value="开通" class="btn_start" />';
                else if (service.serviceStatus == "1" && account.accountStatus != "0")
                    domString = '<input type="button" style="visibility:hidden;" value="开通" class="btn_start" />';
                domString += '<input type="button" value="修改" class="btn_modify"/> <input type="button" value="删除" class="btn_delete"/>'
            }
            
            $('#datalist').append($(
                "<tr>" +
                    '<td><a class="btn_detail" href="service_detail.html" title="查看明细">'+ service.serviceId +'</a></td>' +
                    '<td>' + service.accountId + '</td>' +
                    '<td>' + account.accountIdcardNo + '</td>' +
                    '<td>' + account.accountRealName + '</td>' +
                    '<td>' + service.osUsername + '</td>' +
                    '<td>' + (service.serviceStatus == 0 ? "开通" : (service.serviceStatus == 1 ? "暂停" : "删除")) + '</td>' +
                    '<td>' + service.unixHost + '</td>' +
                    '<td>' + 
                        '<a class="summary" onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">'+ cost.costName + '</a>' + 
                        '<div class="detail_info">' + cost.costDescr + '</div>' +
                    '</td>' +
                    '<td>' + domString + '</td>' +
                "</tr>"
                ));
        }

        //详情
        $('.btn_detail').click(function (e) {
            sessionStorage.setItem("detailServiceId", e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText);
            location.href = 'service_detail.html';
        });

        //启用
        $('.btn_start').click(function (e) {
            var r = window.confirm("确定要开通此业务账号吗？");
            if (r) {
                var serviceId = e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText;
                console.log('serviceId:' + serviceId);
                global_post("/service/startUsing", { 'serviceId': serviceId }, function (result) {
                    if (result.code == 200) {
                        global_operate_result(true, "开通成功！");
                    } else if(result.code == 130){
                        global_operate_result(false, "该业务账号所属账务账号未开通，无法开通");
                    } else if (result.code == 500) {
                        global_operate_result(false, "开通失败，数据并发错误");
                    }
                });
            }
        });

        //暂停
        $('.btn_pause').click(function (e) {
            var r = window.confirm("确定要暂停此业务账号吗？");
            if (r) {
                var serviceId = e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText;
                console.log('serviceId:' + serviceId);
                global_post("/service/pauseUsing", { 'serviceId': serviceId }, function (result) {
                    if (result.code == 200) {
                        global_operate_result(true, "暂停成功！");
                    } else if (result.code == 500) {
                        global_operate_result(false, "暂停失败，数据并发错误");
                    }
                });
            }
        });

        //修改
        $('.btn_modify').click(function (e) {
            sessionStorage.setItem("modiServiceId",e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText);
            location.href = 'service_modi.html';
        });

        //删除
        $('.btn_delete').click(function (e) {
            var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
            if (r) {
                var serviceId = e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText;
                console.log('serviceId:' + serviceId);
                global_post("/service/delete", { 'serviceId': serviceId }, function (result) {
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