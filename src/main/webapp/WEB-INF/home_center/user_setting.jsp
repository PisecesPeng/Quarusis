<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
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
          href="http://localhost:8080/Quarusis/res/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="http://localhost:8080/Quarusis/res/quarusis/public.css">
    <script
            src="http://localhost:8080/Quarusis/res/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
    <script
            src="http://localhost:8080/Quarusis/res/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script
            src="http://localhost:8080/Quarusis/res/quarusis/public.js"></script>
    <script
            src="http://localhost:8080/Quarusis/res/quarusis/home_center/user_setting.js"></script>
</head>
<body>

<div class="container">
<div style="width: 100%; height: 20px;"></div>

<nav id="nav" class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header" style="border-bottom:1px solid #ffffff;">
            <span class="navbar-brand"><b>Quarusis</b></span>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="<%= basePath %>indexpage"><span class="glyphicon glyphicon-list-alt"></span> Index </a></li>
                <li><a
                        data-container="body" data-toggle="popover" data-placement="bottom"
                        data-content='
                        <a href="<%= basePath %>topicpage?topic=<%=URLEncoder.encode(URLEncoder.encode("生活")) %>"><h4># 生活 #</h4></a>
                        <a href="<%= basePath %>topicpage?topic=<%=URLEncoder.encode(URLEncoder.encode("产品")) %>"><h4># 产品 #</h4></a>
                        <a href="<%= basePath %>topicpage?topic=<%=URLEncoder.encode(URLEncoder.encode("科技")) %>"><h4># 科技 #</h4></a>
                        <a href="<%= basePath %>topicpage?topic=<%=URLEncoder.encode(URLEncoder.encode("职场")) %>"><h4># 职场 #</h4></a>
                        <a href="<%= basePath %>topicpage?topic=<%=URLEncoder.encode(URLEncoder.encode("国际")) %>"><h4># 国际 #</h4></a>
                        <a href="<%= basePath %>topicpage?topic=<%=URLEncoder.encode(URLEncoder.encode("兴趣")) %>"><h4># 兴趣 #</h4></a>
                        '>
                    <span class="glyphicon glyphicon-tags"></span> Topic
                    <div class="caret"></div>
                </a>
                </li>
                <li><a href="http://localhost:8081/Quarusis_InChat"><span class="glyphicon glyphicon-bullhorn"></span> InChat</a></li>
            </ul>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <div class="navbar-form navbar-left" role="search">
                <input id="searchInput" type="text" class="form-control" placeholder="Search" />
            </div>
            <li><a
                    data-container="body" data-toggle="popover" data-placement="bottom"
                    data-content='
                    <h5><a href="<%= basePath %>homepage">
                    <span class="glyphicon glyphicon-home"></span> Homepage
                    </a></h5>
                    <h5><a href="<%= basePath %>UserSetting">
                    <span class="glyphicon glyphicon-cog"></span> Setting
                    </a></h5>
                    <h5><a href="<%= basePath %>logout">
                    <span class="glyphicon glyphicon-log-out"></span> Logout
                    </a></h5>
                    '>
                <span class="glyphicon glyphicon-user"></span> ${name}
                <div class="caret"></div>
            </a></li>
        </ul>
    </div>
</nav>

<br>
<form action="<%= basePath %>updateName.do" method="post" onsubmit="return checkNameForm()">
    <div class="form-group">
        <input class="form-control input-lg" placeholder="Quarusis NewName"
               name="name" type="text" value="${newname}" onblur="checkName(this.value)">
        <p id="checkName" align="center"><br></p>
    </div>
    <br>
    <div align="center">
        <input type="submit" value="Rename" class="btn btn-info btn-lg input-control">
    </div>
</form>

</div>
</body>
</html>