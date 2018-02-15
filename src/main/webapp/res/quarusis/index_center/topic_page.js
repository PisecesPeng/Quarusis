/**
 * 从指定Topicpage中搜索指定page
 */
$(function () {
    $("#searchInput").change(function () {
        if (!($("#searchInput").val().length == 0)) {
            var map = '{"search":"' + $("#searchInput").val() + '"}';
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=utf-8',
                url : "/Quarusis/searchTopicpage",
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

/**
 * 追加topicpage的pagelist
 */
var beginpage = 9;
function appendIndexpage() {
    var map = '{"beginpage":"' + beginpage + '"}';
    beginpage += 9;
    $.ajax({
        type : 'POST',
        contentType : 'application/json;charset=utf-8',
        url : "/Quarusis/appendTopicpage",
        processData : false,
        dataType : 'json',
        data : map,
        success : function(data) {
            var pages = eval(data);
            var str = "";
            $.each(pages, function (i, page) {
                str += '<div class="panel panel-default"><div class="panel-body"><h3><font color= #0f0f0f>';
                str += '<a href="page/' + page.id + '">&nbsp;&nbsp;<b>#' + page.topic + '#</b> ' + page.title + '</a>';
                str += '</font></h3></div></div>';
            });
            $("#pagelist").append(str);
        }
    });
}
var timeoutInt;   // 要保证最后要运行一次
window.onscroll = function () {
    setTimeout(function () {
        if (timeoutInt != undefined) {
            window.clearTimeout(timeoutInt);
        }
        timeoutInt = window.setTimeout(function () {
            //监听事件内容
            if(getScrollHeight() - getDocumentTop() - getWindowHeight() <= 5) {
                //触发内容,请求数据
                appendIndexpage();
            }
        }, 105);
    }, 100);
}

//文档高度
function getDocumentTop() {
    var scrollTop =  0, bodyScrollTop = 0, documentScrollTop = 0;
    if (document.body) {
        bodyScrollTop = document.body.scrollTop;
    }
    if (document.documentElement) {
        documentScrollTop = document.documentElement.scrollTop;
    }
    scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
    console.log("DocumentTop:"+scrollTop);
    return scrollTop;
}

//可视窗口高度
function getWindowHeight() {
    var windowHeight = 0;
    if (document.compatMode == "CSS1Compat") {
        windowHeight = document.documentElement.clientHeight;
    } else {
        windowHeight = document.body.clientHeight;
    }
    console.log("windowHeight:"+windowHeight);
    return windowHeight;
}

//滚动条滚动高度
function getScrollHeight() {
    var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
    if (document.body) {
        bodyScrollHeight = document.body.scrollHeight;
    }
    if (document.documentElement) {
        documentScrollHeight = document.documentElement.scrollHeight;
    }
    scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
    console.log("scrollHeight:"+scrollHeight);
    return scrollHeight;
}

