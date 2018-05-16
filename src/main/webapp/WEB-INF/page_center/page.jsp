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
            src="http://localhost:8080/Quarusis/res/quarusis/page_center/page.js"></script>

    <script type="text/javascript">
        function plusCommentHeat(cid){
            var map = '{"cid":"' + cid + '"}';
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=utf-8',
                url : "/Quarusis/page/${page.id}/operatCommentHeat.do",
                processData : false,
                dataType : 'json',
                data : map,
                success : function(data) {
                    if(data == 1) {
                        var i = $("#commentHeat").text()
                        $("#checkPageTitle").text(i+1)
                    } else if (data == 0) {
                        var i = $("#commentHeat").text()
                        $("#checkPageTitle").text(i-1)
                        alert("您已取消heat此评论")
                    } else {
                        alert("抱歉，评论服务繁忙")
                    }
                },
                error : function() {
                    alert("抱歉，评论服务繁忙")
                }
            });
        }
    </script>

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
<div style="height: 10px;"></div>
    <div>
        <c:if test="${page.uin == uin}">
            <a style="float:right;" id="removePage" href="<%=basePath %>removePage.do?pid=${pid}"><span class="glyphicon glyphicon-remove-circle"></span></a>
        </c:if>
        <h2  class="text-center"><font color="black">
            <b>#${page.topic}#</b> ${page.title}
        </font></h2><br><br>
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg-5">
                <p style="width: 550px;height: 100%;" class="thumbnail">
                    <img src="http://localhost:8081/upload/${page.url}" class="img-rounded">
                </p>
            </div>
            <div class="col-lg-1"></div>
            <div class="col-lg-4">
                <br><h3>
                ${page.text}
                </h3><br>
            </div>
            <div class="col-lg-1"></div>
        </div>
    </div>

    <c:if test="${page.whetherComment == 1}">
    <div>
        <div class="row">
        <div class="col-lg-2"></div>
        <div class="col-lg-8">
            <br>
            <div id="addComment" style="color: #0f0f0f; float: left;">
                <font size="3" color="#3f3f3f"><b> + NewComment</b></font>
            </div><br><br>
            <form id="commentForm" action="<%= basePath %>page/${page.id}/uploadComment.do" method="post" onsubmit="return checkCommentForm()">
                <div align="center" class="form-group">
                    <textarea id="comment" class="form-control" rows="3" id="pageWord" placeholder="暂不支持回车与不常用字符"
                              name="comment" type="text" value="${comment}"></textarea>
                    <p id="checkComment" align="center"><br></p>
                    <input type="submit" value="Comment" class="btn btn-info input-control">
                </div>
            </form>
            <hr align="left" width="15%">
            <c:forEach items="${heatCommentList}" var="comment">
                <div class="panel panel-default"><div class="panel-body">
                    <font>
                        <b>${comment.name} : </b><br>
                        <div style="display: inline">
                            <font color="black" size="3">${comment.text}</font>
                            <div style="float:right;" onclick="plusCommentHeat(${comment.id})">
                                <p id="commentHeat" class="glyphicon glyphicon-fire" style="color: rgb(255, 32, 0);">${comment.heat}</p>
                            </div>
                        </div>
                    </font>
                </div></div>
            </c:forEach>
            <hr align="left" width="15%">
            <c:forEach items="${commentList}" var="comment">
                <div class="panel panel-default"><div class="panel-body">
                    <font>
                        <b>${comment.name} : </b><br>
                        <div style="display: inline">
                            <font color="black" size="3">${comment.text}</font>
                            <div style="float:right;" onclick="plusCommentHeat(${comment.id})">
                                <p id="heat" class="glyphicon glyphicon-fire" style="color: rgb(255, 32, 0);" onclick="this.innerHTML=this.innerHTML*1+1">${comment.heat}</p>
                            </div>
                        </div>
                    </font>
                </div></div>
            </c:forEach>
        </div>
        <div class="col-lg-2"></div>
        </div>
        </div>
    </div>
    </c:if>

</div>

</body>
</html>