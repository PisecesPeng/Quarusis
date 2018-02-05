/**
 * 隐藏输入评论框
 */
window.onload = function() {
    $("#commentForm").hide();
};

/**
 * 折叠/显示输入评论框
 */
$(function(){
    $("#addComment").click(function(){
        $("#commentForm").slideToggle("slow");
    });
});

/**
 * 检查'评论'是否符合格式
 */
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

/**
 * 确认删除page弹窗
 */
$(function(){
    $("#removePage").click(function(){
        if(!confirm("确定要删除此Page吗？")) {
            window.event.returnValue = false;
        }
    });
});