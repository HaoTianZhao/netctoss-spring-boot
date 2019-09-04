(function ($) {
    $.fn.pagination = function (url, callback, currentPage, pageSize, count) {
        var o = {
            'url': url,
            'callback': callback,
            'currentPage': currentPage,
            'pageMaxNumber': 2,
            'pageSize': pageSize,
            'totalCount': count,
            'totalPage': count / pageSize + (count % pageSize == 0 ? 0 : 1),
            'havePrePage': currentPage > 1,
            'haveNextPage': currentPage < Math.floor(count / pageSize) + (count % pageSize == 0 ? 0 : 1)
        };

        var ajaxJsonData = "";
        if (arguments.length > 5) {
            ajaxJsonData += "{";
            for (var i = 5; i < arguments.length; i += 2) {
                ajaxJsonData += '"' + arguments[i] + '"' + ":" + '"' + arguments[i + 1] + '",';
            }
            ajaxJsonData = ajaxJsonData.substr(0, ajaxJsonData.length - 1);
            ajaxJsonData += "}"
        }

        initPagination();

        function initPagination() {
            $('#pages').empty();

            $('#pages').append("<a name=" + (o.havePrePage ? o.currentPage - 1 : o.currentPage) + " id='pre_page' href='#'>上一页</a>");
            if (o.currentPage - o.pageMaxNumber > 1)
                $('#pages').append("<span>...</a>");

            for (var i = Math.max(1, o.currentPage - o.pageMaxNumber); i < o.currentPage; i++)
                $('#pages').append("<a name=" + i + " href='#'>" + i + "</a>")

            $('#pages').append("<a name=" + o.currentPage + " href='#' class='current_page'>" + o.currentPage + "</a>");

            for (var i = o.currentPage + 1; i <= Math.min(o.totalPage, o.currentPage + o.pageMaxNumber); i++)
                $('#pages').append("<a name=" + i + " href='#'>" + i + "</a>")

            if (o.currentPage + o.pageMaxNumber < o.totalPage)
                $('#pages').append("<a>...</a>");
            $('#pages').append("<a name=" + (o.haveNextPage ? o.currentPage + 1 : o.currentPage) + " id='next_page' href='#'>下一页</a>");


            $('#pages a[name]').click(function (e) {
                global_post(o.url,
                    JSON.parse((JSON.stringify({ 'currentPage': e.target.name, 'pageSize': o.pageSize }) + ajaxJsonData).replace(/}{/, ',')),
                    function (result) {
                        if (result.code == 200) {
                            o.callback(result.data);
                            o.currentPage = Number.parseInt(e.target.name);
                            o.totalCount = result.data.totalCount;
                            o.totalPage = Math.floor(result.data.totalCount / o.pageSize) + (o.totalCount % o.pageSize == 0 ? 0 : 1);
                            o.havePrePage = o.currentPage > 1;
                            o.haveNextPage = o.currentPage < o.totalPage;
                            initPagination();
                        } else if (result.code == 500) {
                            gloal_save_result(false, "服务器故障，无法查看角色信息。");
                        }
                    });
            })
        };
    }
})(jQuery);