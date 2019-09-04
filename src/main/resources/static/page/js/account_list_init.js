(function ($) {
    $.fn.initAccountList = function (vo) {
        $('#datalist').empty();

        $('#datalist').append($(
            "<tr>" +
                '<th>账号ID</th>' +
                '<th>姓名</th>' +
                '<th class="width150">身份证</th>' +
                '<th>登录名</th>' +
                '<th>状态</th>' +
                '<th class="width100">创建日期</th>' +
                '<th class="width150">上次登录日期</th>' +
                '<th class="width200"></th>' +
            "</tr>")
        )

        for (account of vo.accountColumns) {
            $('#datalist').append($(
                "<tr>" +
                    "<td>" + g_isNull(account.accountId, "-", "account.accountId") + "</td>" +
                    "<td><a href='#' class='btn_detail'>" + g_isNull(account.accountRealName, "-", "account.accountRealName") + "</a></td>" +
                    "<td>" + g_isNull(account.accountIdcardNo, "-", "account.accountIdcardNo") + "</td>" +

                    "<td>" + g_isNull(account.accountLoginName, "-", "account.accountLoginName") + "</td>" +
                    "<td>" + (account.accountStatus == 0 ? "开通" : (account.accountStatus == 1 ? "暂停" : "删除")) + "</td>" +
                    "<td>" + g_isNull(account.accountCreateDate, "-", "account.accountCreateDate.split(' ')[0]") + "</td>" +

                    "<td>" + g_isNull(account.accountLastLoginDate, "-", "account.accountLastLoginDate") + "</td>" +
                    "<td>" +
                        (account.accountStatus == 2 ? "" : (
                        (account.accountStatus == 0 ? "<input type='button' value='暂停' class='btn_pause' />":"<input type='button' value='开通' class='btn_start' />") +
                        "<input type='button' value='修改' class='btn_modify' />" +
                        "<input type='button' value='删除' class='btn_delete' />")
                        ) +
                    "</td>" +
                "</tr>"
                ));
        }

        //详情
        $('.btn_detail').click(function (e) {
            sessionStorage.setItem("detailAccountId", e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText);
            location.href = 'account_detail.html';
        });

        //启用
        $('.btn_start').click(function (e) {
            var r = window.confirm("确定要开通此账务账号吗？");
            if (r) {
                var accountId = e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText;
                console.log('accountId:' + accountId);
                global_post("/account/startUsing", { 'accountId': accountId }, function (result) {
                    if (result.code == 200) {
                        global_operate_result(true, "开通成功！");
                    } else if (result.code == 500) {
                        global_operate_result(false, "开通失败，数据并发错误");
                    }
                });
            }
        });

        //暂停
        $('.btn_pause').click(function (e) {
            var r = window.confirm("确定要暂停此账务账号吗？");
            if (r) {
                var accountId = e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText;
                console.log('accountId:' + accountId);
                global_post("/account/pauseUsing", { 'accountId': accountId }, function (result) {
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
            sessionStorage.setItem("modiAccountId",e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText);
            location.href = 'account_modi.html';
        });

        //删除
        $('.btn_delete').click(function (e) {
            var r = window.confirm("确定要删除此账务账号吗？\r\n删除后将不能恢复，且会删除其下属的所有业务账号。");
            if (r) {
                var accountId = e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText;
                console.log('accountId:' + accountId);
                global_post("/account/delete", { 'accountId': accountId }, function (result) {
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