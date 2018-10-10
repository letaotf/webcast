
$(function() {
    init();
    //登录页面
    loginPage();
    //登录按钮
    loginButton();
    //注册页面
    registerPage();
    //注册按钮
    registerButton();
    //根据角色选择注册页面
    singleRadio();
    //忘记密码页面
    openForgetDiv();
    //页面点击事件
    forgetPasswordButton();
});

function loginPage() {
    $('#login_icon').on('click', function(){
        // layui.use('layer', function(){
        //     var layer = layui.layer;
        //     layer.open({
        //         type: 2,
        //         title: '登录',
        //         maxmin: true,
        //         shadeClose: true, //点击遮罩关闭层
        //         area : ['500px' , '400px'],
        //         content: 'login.htm'
        //     });
        // });
        $(".login-form").show();
        $(".login-main").hide();
        $(".register").hide();
        $(".forget-password-div").hide();
    });
}

function init() {
    $(".register").hide();
    $(".content-message").hide();
    $(".content-admin").hide();
    $(".login-form").hide();
    $(".radio-type").attr("checked",false);
    $(".forget-password-div").hide();
}

function setUserName() {
    var userName = $(".content .form_text").val();
    $(".content-message .form_item #email").val(userName);
}

/**
 * 注册页面
 */
function registerPage() {
    $("#user_app").on("click",function () {
        $(".register").show();
        $(".login-main").hide();
        $(".login-form").hide();
        $(".forget-password-div").hide();
    });
}

/**
 * 获取用户类型
 * @returns {string}
 */
function getRole() {
    var value = "";
    var radioValue = document.getElementsByName("role");
    for(var i=0;i<radioValue.length;i++){
        if(radioValue[i].checked == true){
            value = radioValue[i].value;
            break;
        }
    }
    return value;
}


/**
 * radio的选择,每一次只能选择一种
 */
function singleRadio() {
    $(".radio-type").click(function () {
        var d = null;
        var obj=document.getElementsByName("user-type");
        for (var i=0;i<obj.length;i++){ //遍历Radio
            d = obj[i].value;
            if(obj[i].checked){
                if(obj[i].value == 0 || obj[i].value == 2){
                    $(".content-message").show();
                    $(".content-admin").hide();
                }else{
                    $(".content-message").hide();
                    $(".content-admin").show();
                }
            }
        }
    });
}


function disp(url){
    if(url.indexOf("app")>0){
        if('true'){
            $("#login_bj").show();
            $("#login_main").show();
        return;
    }
    }
    redirect(url);
}

/**
 * 登录按钮
 */
function loginButton(){
    //登录按钮
    $(".login").on("click",function(){
        var userName = $(".login-form .user-name").val();
        var password = $(".login-form .password").val();
        var role = getRole();
        loginPostAjax({
            userName:userName,
            password:password,
            userType:role,
        });
    });
}

function loginPostAjax(queryParams) {
    $.ajax({
        url:"/user/login.jsonHtml",
        type:"POST",
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(queryParams),
        error:function(result){
            alert("登陆失败");
        }
    }).done(function (result) {
        if(result.success == true){
            alert("登录成功");
            window.location.href=result.data;
        }else{
            alert(result.message);
        }
    });
}

/**
 * 登录按钮
 */
function registerButton(){
    //登录按钮
    $(".button1").on("click",function(){
        var userName = $(".content .username").val();
        var password = $(".content .short").val();
        var userSex = getSex();
        var userType = getUserType();
        var userTel = null;
        if(userType == 1){
            userTel = $(".telAnchor").val();
        }else{
            userTel = $(".tel").val();
        }
        var idCard = $(".idCard").val();
        var idCardImg = $(".idCard-img").val();
        var typeId = $(".broadcast-type").val();
        var typeDetailName = $(".type-detail-name").val();
        registerPostAjax({
            userName:userName,
            password:password,
            userType:userType,
            userTel:userTel,
            userSex:userSex,
            identityCard:idCard,
            cardImgPath:idCardImg,
            typeId:typeId,
            typeDetailName:typeDetailName,
        });
    });
}

function registerPostAjax(queryParams) {
    $.ajax({
        url:"/user/register.jsonHtml",
        type:"POST",
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(queryParams),
        error:function(result){
            alert("注册失败");
        }
    }).done(function (result) {
        if(result.success == true){
            alert("注册成功");
            window.location.href=result.data;
        }else{
            alert(result.message);
        }
    });
}

function getUserType() {
    var value = "";
    var radioValue = document.getElementsByName("user-type");
    for(var i=0;i<radioValue.length;i++){
        if(radioValue[i].checked == true){
            value = radioValue[i].value;
            break;
        }
    }
    return value;
}

function getSex() {
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

function openForgetDiv() {
    $(".forget-password").on("click",function () {
        $(".forget-password-div").show();
        $(".register").hide();
        $(".login-main").hide();
        $(".login-form").hide();
    });

}

function forgetPasswordButton() {
    //登录按钮
    $(".forget").on("click",function(){
        var tel = $(".forget-password-div .user-info-tel").val();
        var password = $(".forget-password-div .new-password").val();
        forgetGetAjax({
            tel:tel,
            password:password,
        });
    });
}

function forgetGetAjax(queryParams) {
    $.ajax({
        url:"/user/updatePassword.jsonHtml",
        type:"POST",
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(queryParams),
        error:function(result){
            alert("更新用户密码失败");
        }
    }).done(function (result) {
        if(result.success == true){
            alert("登录成功");
            window.location.href="/common.htm";
        }else{
            alert(result.message);
        }
    });
}




