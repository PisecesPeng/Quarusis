<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Quarusis</title>

    <!-- 引用本地资源 -->
    <link rel="stylesheet"
          href="res/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script
            src="res/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
    <script
            src="res/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<script type="text/javascript">
    var websocket = null;
    // 判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/Quarusis/pagelist");
    }
    else {
        alert('当前浏览器 Not support websocket')
    }
    //websocket开始
    websocket.onopen = function () {
        $("#startweb").html('websocket开始');
        //websocket发送
        setInterval(
            function() {
                websocket.send(1);
            }, 10000);
    }
    //websocket发送
    websocket.onmessage = function (event) {

        var str= "";
        str += '<c:forEach items="${newpageList}" var="page">';
        str += '<div class="well"><h3><font color= #0f0f0f>';
        str += '<c:if test="${page.whetherRead == 1 }" ><span class="glyphicon glyphicon-asterisk" style="color: rgb(255, 47, 0);" /></c:if> <a href="<%=basePath %>page/${page.id}">&nbsp;&nbsp;<b>#${page.topic}#</b> ${page.title}</a>';
        str += '</font></h3></div>';
        str += '</c:forEach>';
        $("#pageList").html(str);

        $("#test").click();

    }
</script>

</head>
<body>
<div id="test" onclick="this.innerHTML=this.innerHTML*1+1">1</div>
    <div id="pageList">
        <c:forEach items="${newpageList}" var="page">
            <div class="panel panel-default"><div class="panel-body">
                <h3><font color= #0f0f0f>
                    <c:if test="${page.whetherRead == 1 }" ><span class="glyphicon glyphicon-asterisk" style="color: rgb(255, 47, 0);" /></c:if> <a href="<%=basePath %>page/${page.id}">&nbsp;&nbsp;<b>#${page.topic}#</b> ${page.title}</a>
                </font></h3>
            </div></div>
        </c:forEach>
    </div>

</body>
</html>
