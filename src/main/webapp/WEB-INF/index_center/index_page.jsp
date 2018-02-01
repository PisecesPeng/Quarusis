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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引用本地资源 -->
    <link rel="stylesheet"
          href="res/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script
            src="res/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
    <script
            src="res/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <style>
        ::-webkit-scrollbar {
            width: 0px;
            height:0px;
        }
        body {
            position: relative;
        }
    </style>

</head>
<body>
<div class="container">
<div style="width: 100%; height: 70px;"></div>

<div>
    <b>${uin}</b>
    <b>${name}</b>
    <a style="float: right" href="<%= basePath %>homepage"><b>用户主页</b></a>
</div>

<br>
<c:forEach items="${allPageList}" var="page">
    <div class="well">
        <h3><font color= #0f0f0f>
                <a href="<%=basePath %>page/${page.id}">&nbsp;&nbsp;<b>#${page.topic}#</b> ${page.title}</a>
                <%-- 关闭按钮 <a style="float:right;" id="removePage" href="<%=basePath %>removeAction.do?hid=${action.hid}"><span class="glyphicon glyphicon-remove-circle"></span></a>--%>
        </font></h3>
    </div>
</c:forEach>

</div>
<!-- jQuery -->
<script src="res/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="res/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="res/bower_components/metisMenu/dist/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="res/dist/js/sb-admin-2.js"></script>
<!-- Background -->

</body>
</html>