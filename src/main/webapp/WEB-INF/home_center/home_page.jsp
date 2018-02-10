<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
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
    <link rel="stylesheet"
          href="res/quarusis/public.css">
    <script
            src="res/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
    <script
            src="res/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script
            src="res/quarusis/public.js"></script>
    <script
            src="res/quarusis/home_center/home_page.js"></script>
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
                <input id="searchInput" type="text" class="form-control" placeholder="暂不支持Search" disabled/>
            </div>
            <li><a
                    data-container="body" data-toggle="popover" data-placement="bottom"
                    data-content='
                        <h5><a href="<%= basePath %>homepage">
                        <span class="glyphicon glyphicon-home"></span> Homepage
                        </a></h5>
                        <h5><a href="<%= basePath %>homepage">
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

<a style="color: #0f0f0f; float: left;" data-toggle="modal" data-target="#addPageModal">
    <font size="3" color="#3f3f3f"><b> + NewPage</b></font>
</a><br>
<hr align="left" width="15%">
    <div class="row" >
        <div class="col-lg-9"><div id="pageList">
            <c:forEach items="${pageList}" var="page">
                <div class="panel panel-default"><div class="panel-body">
                    <h3><font color= #0f0f0f>
                        <c:if test="${page.whetherRead == 1 }" ><span class="glyphicon glyphicon-asterisk" style="color: black;" /></c:if><a href="<%=basePath %>page/${page.id}">&nbsp;&nbsp;<b>#${page.topic}#</b> ${page.title}</a>
                    </font></h3>
                </div></div>
            </c:forEach>
        </div></div>
        <div class="col-lg-3">
            <div class="panel panel-default"><div class="panel-body">
                <h3><b>UserReviewsHistory</b></h3>
                <hr align="left" width="25%">
                <c:forEach items="${historyList}" var="page">
                        <h5><font color= #0f0f0f>
                            <a href="<%=basePath %>page/${page.id}">&nbsp;&nbsp;<b>#${page.topic}#</b> ${page.title}</a>
                        </font></h5>
                </c:forEach>
            </div></div>
        </div>
    </div>

</div>
<!-- 弹出框，用户新添动态 -->
<div id="addPageModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h3 align="center" class="modal-title" id="myModalLabel">
                    New Page
                </h3>
            </div>
            <div class="modal-body">
                <br>
                <form id="UploadPage" action="<%=basePath %>pageUpload.do " enctype="multipart/form-data" method="post" onsubmit="return checkPageForm(this)">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-lg-1"></div>
                            <div class="col-lg-3">
                                <select id="pageTopic" name="pageTopic" class="selectpicker form-control">
                                    <option value="生活">生活</option>
                                    <option value="产品">产品</option>
                                    <option value="科技">科技</option>
                                    <option value="职场">职场</option>
                                    <option value="国际">国际</option>
                                    <option value="兴趣">兴趣</option>
                                </select>
                            </div>
                            <div class="col-lg-7">
                                <input class="form-control input" id="pageTitle" placeholder="请输入内容标题"
                                       name="pageTitle" type="text" value="${pageTitle}">
                                <p id="checkPageTitle" align="center"><br></p>
                            </div>
                            <div class="col-lg-1"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" rows="3"	id="pageWord" placeholder="暂不支持回车与不常用字符"
                                  name="pageWord" type="text" value="${pageWord}"></textarea>
                        <p id="checkPageWord" align="center"><br></p>
                    </div>
                    <div class="form-group">
                        <input id="pagePicture" name="pagePicture" type="file" style="display: none;">
                        <p class="text-center">
                            <a class="btn" onclick="$('#pagePicture').click();">Browse</a>
                        </p>
                        <p id="checkPagePicture" align="center"><br></p>
                        <p id="showPreview" align="center">
                            <img id="preview" style="width: 400px; height: 200px" />
                        </p>
                    </div>
                    <div class="form-group text-center">
                        <input name="pageComment" type="checkbox" checked="checked" value="1"> 是否允许评论
                    </div>
                    <br>
                    <p class="text-center">
                        <input type="submit" value="Push Page" class="btn btn-info input-control">
                    </p>
                </form>
            </div>
        </div>
    </div>
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