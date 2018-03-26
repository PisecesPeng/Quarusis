/**
 * 检查'重命名'是否符合要求
 */
var flag = false;
function checkName(newname){
    var map = '{"name":"' + newname + '"}';
    $("#checkName").html('正在验证...');
    if(/^[A-Za-z0-9\u4e00-\u9fa5]{1,9}$/.test(newname)) {
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