/**
 * 隐藏显示略缩图框
 */
window.onload = function() {
    $("#showPreview").hide();
}

/**
 * 实时显示略缩图
 */
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

/**
 * 检查'上传动态'是否符合格式
 */
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

/**
 * 读取websocket.jsp中的数据
 */
window.onload = function() {
    setInterval(
        function() {
            $("#pageList").load("/Quarusis/pagelist #pageList");
        }, 5000);
}

// /**
//  * 从homepage中搜索指定page
//  */
// $(function () {
//     $("#searchInput").change(function () {
//         if (!($("#searchInput").val().length == 0)) {
//             var map = '{"search":"' + $("#searchInput").val() + '"}';
//             $.ajax({
//                 type : 'POST',
//                 contentType : 'application/json;charset=utf-8',
//                 url : "/Quarusis/searchHomepage",
//                 processData : false,
//                 dataType : 'json',
//                 data : map,
//                 success : function(data) {
//                     var pages = eval(data);
//                     var str = "";
//                     $.each(pages, function (i, page) {
//                         str += '<div class="panel panel-default"><div class="panel-body"><h3><font color= #0f0f0f>';
//                         str += '<a href="<%=basePath %>page/' + page.id + '">&nbsp;&nbsp;<b>#' + page.topic + '#</b> ' + page.title + '</a>';
//                         str += '</font></h3></div></div>';
//                     });
//                     $("#pagelist").html(str);
//                 }
//             });
//         } else {}
//     });
// });
