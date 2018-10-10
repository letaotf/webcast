/**
 * 用于在另一个div中加入页面
 */
$.post("/",{},function(d){
    $("#main-div").html(d);
});

/**
 * 显示和隐藏的切换
 */
$(document).ready(function () {
   $("#audit-li").click(function(){
       $("#audit-div").toggle();
   });
});

/**
 * 前后端的数据交互
 */
$(document).ready(function(){
    $("#login-button").click(function() {
        $.ajax({
            url : "/user/login",
            type : "POST",
            dataType:"json",
            contentType : "application/json;charset=UTF-8",
            <!-- 向后端传输的数据 -->
            data : JSON.stringify({
                userId : $("#user_id").val(),
                password : $("#password").val(),
                userType: getJob()
            }),
            success:function(result) {
                <!-- 处理后端返回的数据 -->
                alert("注册成功");
                window.location.href="/main.htm";
            },
            error:function(result){
                alert("登录失败");
            }
        });
    });
});


/**
 * 获取性别
 * @returns {string}
 */
function getJob() {
    var value = "";
    var radioValue = document.getElementsByName("sex");
    for(var i=0;i<radioValue.length;i++){
        if(radioValue[i].checked == true){
            value = radioValue[i].value;
            break;
        }
    }
    return value;
}


