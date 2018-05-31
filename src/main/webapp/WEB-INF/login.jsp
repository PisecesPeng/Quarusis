<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String hostname = request.getScheme()+"://"+request.getServerName();
%>
<html>
<head>
    <title>Quarusis</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引用本地资源 -->
    <link rel="stylesheet"
          href="<%= hostname %>:8080/Quarusis/res/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script
            src="<%= hostname %>:8080/Quarusis/res/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
    <script
            src="<%= hostname %>:8080/Quarusis/res/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

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
    $(function () {
        var hostname = window.location.protocol+"//"+window.location.hostname;
        $("#login").click(function () {
            $("#login").fadeOut(500);            
            var timeStr = "$($(\"#login\").html('<img src=\"" + hostname + ":8081/QR.png\">')).fadeIn(1000)";
            setTimeout(timeStr,500)
            $.ajax({
                type : 'GET',
                contentType : 'application/json;charset=utf-8',
                url : "/Quarusis/loginQRcode",
                success : function(data) {
                    if(data == 1) {
                        self.location= '<%= hostname %>:8080/Quarusis/indexpage';
                    } else if(data == 0) {
                        $("#setName").html('<font color="#5B5B5B">检测到您是首次登录，请先设置您的Quarusis名称</font>');
                    } else {
                        alert("抱歉，验证系统繁忙，请稍后登录");
                    }
                },
                error : function() {
                    alert("抱歉，验证系统繁忙，请稍后登录");
                }
            });
        })
    })

    /**
     * 检查'注册名称'是否符合要求
     */
    var flag = false;
    function checkName(name){
        var map = '{"name":"' + name + '"}';
        $("#checkName").html('正在验证...');
        if(/^[A-Za-z0-9\u4e00-\u9fa5]{1,9}$/.test(name)) {
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=utf-8',
                url : "/Quarusis/checkName",
                processData : false,
                dataType : 'json',
                data : map,
                success : function(data) {
                    if(data == 1) {
                        flag = true;
                        $("#checkName").html('<font color="#00AEAE">非常不错的名称，这个名称还未被使用.</font>');
                    } else if(data == 0) {
                        flag = false;
                        $("#checkName").html('<font color="#FF5151">抱歉，该名称已被占用!</font>');
                    } else {
                        flag = false;
                        $("#checkName").html('<font color="#FF5151">抱歉，该名称已被占用!</font>');
                    }
                },
                error : function() {
                    flag = false;
                    $("#checkName").html('<font color="#FF5151">验证系统繁忙!</font>');
                }
            });
        } else {
            flag = false;
            $("#checkName").html('<font color="#F75000">名字是你的标识，所以请务必简短，但不要随意输入</font>');
        }
    }
    //根据ajax的结果，判断返回return的结果
    function checkNameForm() {
        if(flag){
            return true;
        }
        return false;
    }
    </script>

</head>
<body>

<div style="width: 100%; height: 70px;"></div>
<div align="center">
    <a id="login" class="btn btn-default btn-lg" role="button">登录</a>
</div>
<br><br>
<div align="center">
    <a id="setName" data-toggle="modal" data-target="#setNameModal" ><br></a>
</div>

<!-- 弹出框，注册操作 -->
<div id="setNameModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h3 align="center" class="modal-title" id="myModalLabel">
                    该名称将是其他用户认识您的唯一标识
                </h3>
            </div>
            <div class="modal-body">
                <br>
                <form action="<%= basePath %>setName.do" method="post" onsubmit="return checkNameForm()">
                    <div class="form-group">
                        <input class="form-control input-lg" placeholder="Quarusis Name"
                               name="name" type="text" value="${name}" onblur="checkName(this.value)">
                        <p id="checkName" align="center"><br></p>
                    </div>
                    <br>
                    <div align="center">
                        <input type="submit" value="Login" class="btn btn-info btn-lg input-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
