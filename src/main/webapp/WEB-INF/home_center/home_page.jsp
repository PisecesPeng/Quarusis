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

    <script type="text/javascript">
        window.onload = function() {
            $("#showPreview").hide();
        }
        $(function () {
            $("#pagePicture").change(function () {
                //当没选中图片时，清除预览
                if (this.files.length === 0) {
                    document.querySelector('#preview').src = '';
                    return;
                }
                //实例化一个FileReader
                var reader = new FileReader();
                reader.onload = function (e) {
                    //当reader加载时，把图片的内容赋值给
                    document.querySelector('#preview').src = e.target.result;
                };
                //读取选中的图片，并转换成dataURL格式
                reader.readAsDataURL(this.files[0]);
                $("#showPreview").show(1000);
            });
        });
        //检查'上传动态'是否符合格式
        function checkPageForm(r) {
            if(!(/^[A-Za-z0-9\u4e00-\u9fa5,，.。；;‘’''？?！!：:、“”""\~\`\-_=+@#$%^&*\[\]{}()]{1,20}$/.test(r.pageTitle.value))) {
                $("#checkPageTitle").html('请务必控制输入1~20个规范字符之间.');
                r.pageTitle.focus();
                return false;
            } else {
                $("#checkPageTitle").html('<br>');
            }
            if(!(/^[A-Za-z0-9\u4e00-\u9fa5,，.。；;‘’''？?！!：:、“”""\~\`\-_=+@#$%^&*\[\]{}()]{1,99}$/.test(r.pageWord.value))) {
                $("#checkPageWord").html('请务必控制输入1~99个规范字符之间.');
                r.pageWord.focus();
                return false;
            } else {
                $("#checkPageWord").html('<br>');
            }
            if(!(/\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([pP][nN][gG]){1}$| $/.test(r.pagePicture.value))) {
                $("#checkPagePicture").html('仅能上传jpg/jpeg/png格式的图片.');
                return false;
            } else {
                $("#checkPagePicture").html('<br>');
            }
            return true;
        }
    </script>
    <script type="text/javascript">
        window.onload = function() {
            setInterval(
                function() {
                    $("#pageList").load("/Quarusis/pagelist #pageList");
                }, 5000);
        }
    </script>

</head>
<body>
<div class="container">
<div style="width: 100%; height: 70px;"></div>

<a style="color: #0f0f0f; float: left;" data-toggle="modal" data-target="#addPageModal">
    <font size="3"><b> + NewPage</b></font>
</a><br>
<hr align="left" width="15%">
    <div class="row" >
        <div class="col-lg-9">
            <div id="pageList">
            <c:forEach items="${pageList}" var="page">
                <div class="well">
                    <h3><font color= #0f0f0f>
                        <c:if test="${page.whetherRead == 1 }" ><span class="glyphicon glyphicon-asterisk" /></c:if> <a href="<%=basePath %>page/${page.id}">&nbsp;&nbsp;<b>#${page.topic}#</b> ${page.title}</a>
                        <%-- 关闭按钮 <a style="float:right;" id="removePage" href="<%=basePath %>removeAction.do?hid=${action.hid}"><span class="glyphicon glyphicon-remove-circle"></span></a>--%>
                    </font></h3>
                </div>
            </c:forEach>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="well">
                预留历史评论page
            </div>
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
                        <p id="checkpagePicture" align="center"><br></p>
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