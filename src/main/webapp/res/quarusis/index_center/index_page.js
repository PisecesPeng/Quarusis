/**
 * 从所有page中搜索指定page
 */
$(function () {
    $("#searchInput").change(function () {
        if (!($("#searchInput").val().length == 0)) {
            var map = '{"search":"' + $("#searchInput").val() + '"}';
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=utf-8',
                url : "/Quarusis/searchAllpage",
                processData : false,
                dataType : 'json',
                data : map,
                success : function(data) {
                    var pages = eval(data);
                    var str = "";
                    $.each(pages, function (i, page) {
                        str += '<div class="panel panel-default"><div class="panel-body"><h3><font color= #0f0f0f>';
                        str += '<a href="<%=basePath %>page/' + page.id + '">&nbsp;&nbsp;<b>#' + page.topic + '#</b> ' + page.title + '</a>';
                        str += '</font></h3></div></div>';
                    });
                    $("#pagelist").html(str);
                }
            });
        } else {}
    });
});

