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
          href="http://localhost:8080/Quarusis/res/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script
            src="http://localhost:8080/Quarusis/res/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
    <script
            src="http://localhost:8080/Quarusis/res/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <style>
        ::-webkit-scrollbar {
            width: 0px;
            height:0px;
        }
        body {
            position: relative;
        }
        a{
            color:#3f3f3f;
        }
        a:hover{
            color:#3f3f3f;
            text-decoration: none;
        }
    </style>
    <script type="text/javascript">
        window.onload = function() {
            $("#commentForm").hide();
        };
        $(function(){
            $("#addComment").click(function(){
                $("#commentForm").slideToggle("slow");
            });
        });
        //检查'评论'是否符合格式
        function checkCommentForm(r) {
            if(!(/^[A-Za-z0-9\u4e00-\u9fa5,，.。；;‘’''？?！!：:、“”""\~\`\-_=+@#$%^&*\[\]{}()]{1,70}$/.test(r.pageWord.value))) {
                $("#checkComment").html('请务必控制输入1~70个规范字符之间.');
                r.comment.focus();
                return false;
            } else {
                $("#checkComment").html('<br>');
            }
            return true;
        }
        function plusCommentHeat(cid){
            var map = '{"cid":"' + cid + '"}';
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=utf-8',
                url : "/Quarusis/page/${page.id}/plusCommentHeat.do",
                processData : false,
                dataType : 'json',
                data : map,
                success : function(data) {
                    if(data == 1) {
                        ;
                    } else {
                        alert("您已heat过此评论，请勿重复操作")
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
<div style="width: 100%; height: 70px;"></div>

    <div style="height: 500px;">
        <h2  class="text-center"><font color="black">
            <b>#${page.topic}#</b> ${page.title}
        </font></h2><br><br>
        <div class="row">
            <div class="col-lg-7">
                <p style="width: 600px;height: 100%;" class="thumbnail">
                    <!-- 这里是调用另一个tomcat的虚拟路径，访问图片库 -->
                    <img src="http://localhost:8081/upload/${page.url}" class="img-rounded">
                </p>
            </div>
            <div class="col-lg-5">
                <br><h3>
                ${page.text}
                </h3><br>
            </div>
        </div>
    </div>

    <c:if test="${page.whetherComment == 1}">
    <div>
        <div class="row">
        <div class="col-lg-2"></div>
        <div class="col-lg-8">
            <hr align="left" width="15%">
            <div id="addComment" style="color: #0f0f0f; float: left;">
                <font size="3"><b> + NewComment</b></font>
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
                                <p class="glyphicon glyphicon-fire" style="color: rgb(255, 32, 0);" onclick="this.innerHTML=this.innerHTML*1+1">${comment.heat}</p>
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