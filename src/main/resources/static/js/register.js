/**
 * 前后端数据交互ajax请求
 */
$(document).ready(function(){
    $("#register-button").click(function() {
        $.ajax({
            url:"/user/register",
            type:"POST",
            dataType:"json",
            contentType : "application/json;charset=UTF-8",
            <!--向后端传递数据-->
            data : JSON.stringify({
                userId:$("#register-userId").val(),
                userName:$("#register-userName").val(),
                password:$("#register-password").val(),
                userTel:$("#register-tel").val(),
            }),
            <!--后端传递给前端的数据-->
            success:function (result) {
                alert("注册成功");
            },
            error:function (result) {
                alert("注册失败");
            }
        });
    });
});

/**
 * 获取性别
 * @returns {string}
 */
function getSex() {
    var value = "";
    var radioValue = document.getElementsByName("sex");
    for(var i=0;i<radioValue.length;i++){
        if(radioValue[i].checked == true){
            value = radioValue[i].value;
        }
        break;
    }
    return value;
}