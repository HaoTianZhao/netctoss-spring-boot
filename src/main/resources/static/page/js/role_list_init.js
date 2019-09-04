(function ($) {
    $.fn.initRoleList = function (vo) {
        $('#datalist').empty();

        $('#datalist').append($(
            "<tr>" +
                "<th>角色 ID</th>" +
                "<th>角色名称</th>" +
                "<th class='width600'>拥有的权限</th>" +
                "<th class='td_modi'></th>" +
            "</tr>")
        )

        for (role of vo.roleColumns) {
            $('#datalist').append($(
                "<tr>" +
                    "<td>" + role.roleId + "</td>" +
                    "<td>" + role.roleName + "</td>" +
                    "<td>" + role.privilegeName + "</td>" +
                    "<td>" +
                        "<input type='button' value='修改' class='btn_modify' />" +
                        "<input type='button' value='删除' class='btn_delete' />" +
                    "</td>" +
                "</tr>"));
        }

        $('.btn_modify').click(function (e) {
            var tr = e.target.parentNode.parentNode;
            sessionStorage.setItem("modiRoleId", tr.getElementsByTagName('td')[0].innerText);
            sessionStorage.setItem("modiRoleName", tr.getElementsByTagName('td')[1].innerText);
            sessionStorage.setItem("modiRolePrivilege", tr.getElementsByTagName('td')[2].innerText);
            location.href = 'role_modi.html';
        })

        $('.btn_delete').click(function (e) {
            var r = window.confirm("确定要删除此角色吗？");
            if (r) {
                var roleId = e.target.parentNode.parentNode.getElementsByTagName('td')[0].innerText;
                console.log('roleId:' + roleId);
                global_post("/role/delete", { 'roleId': roleId }, function (result) {
                    if (result.code == 200) {
                        global_operate_result(true, "删除成功！");
                        e.target.parentNode.parentNode.remove();
                    } else if (result.code == 500) {
                        global_operate_result(false, result.msg);
                    }
                });
            }
        })
    }
})(jQuery);