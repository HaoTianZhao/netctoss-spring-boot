(function ($) {
    $.fn.initAdminList = function (vo) {
        $('#datalist').empty();

        $('#datalist').append($(
            "<tr>" +
                '<th class="th_select_all">' +
                    '<input type="checkbox" onclick="selectAdmins(this);" />' +
                    '<span>全选</span>' +
                '</th>' +
                "<th>管理员ID</th>" +
                "<th>姓名</th>" +
                "<th>登录名</th>" +
                "<th>电话</th>" +
                "<th>电子邮件</th>" +
                "<th>授权日期</th>" +
                "<th class='width100'>拥有角色</th>" +
                "<th class='td_modi'></th>" +
            "</tr>")
        )

        for (admin of vo.adminColumns) {
            $('#datalist').append($(
                "<tr>" +
                    "<td><input type='checkbox' /></td>" +
                    "<td>" + admin.adminInfo.adminId + "</td>" +
                    "<td>" + admin.adminInfo.adminName + "</td>" +
                    "<td>" + admin.adminInfo.adminCode + "</td>" +
                    "<td>" + admin.adminInfo.adminTelephone + "</td>" +
                    "<td>" + admin.adminInfo.adminEmail + "</td>" +
                    "<td>" + admin.adminInfo.adminEnrolldate.split(" ")[0] + "</td>" +
                    "<td>" +
                        "<a class='summary' onmouseover='showDetail(true,this);' onmouseout='showDetail(false,this);'>" + admin.roleNames[0] + "</a>" +
                        "<div class='detail_info'>" + admin.roleNames + "</div>" +
                    "</td>" +
                    "<td class='td_modi'>" +
                        "<input type='button' value='修改' class='btn_modify' />" +
                        "<input type='button' value='删除' class='btn_delete' />" +
                    "</td>" +
                "</tr>"));
        }

        //修改
        sessionStorage.setItem("allRoles",JSON.stringify(vo.roles));
        $('.btn_modify').click(function (e) {
            var tds = e.target.parentNode.parentNode.getElementsByTagName('td');
            sessionStorage.setItem("modiAdminId", tds[1].innerText);
            sessionStorage.setItem("modiAdminName", tds[2].innerText);
            sessionStorage.setItem("modiAdminCode", tds[3].innerText);
            sessionStorage.setItem("modiAdminTelephone", tds[4].innerText);
            sessionStorage.setItem("modiAdminEmail", tds[5].innerText);
            sessionStorage.setItem("modiAdminEnrolldate", tds[6].innerText);
            sessionStorage.setItem("modiAdminRoles", tds[7].getElementsByClassName("detail_info")[0].innerText);
            location.href = 'admin_modi.html';
        });

        //删除
        $('.btn_delete').click(function (e) {
            var r = window.confirm("确定要删除此角色吗？");
            if (r) {
                var adminId = e.target.parentNode.parentNode.getElementsByTagName('td')[1].innerText;
                console.log('adminId:' + adminId);
                global_post("/admin/delete", { 'adminId': adminId }, function (result) {
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