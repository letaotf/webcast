$(function () {
    hideDetail();
    //鼠标事件
    mouseEvent();
    //获取用户信息
    getMyUserInfo();
    //退出登录
    logoutMyButton();
    //查询公告信息
    queryNotice();
});

/**
 * 将ul中的列隐藏起来
 */
function hideDetail() {
    $(".detail").hide();
}

/**
 * 将样式进行转变
 */
function mouseEvent() {

    $(".title_1").on("mouseenter",function () {
        $(".wrapper-body .left ul .title_1").css("height",60);
        $(".active_1").show();
    });
    $(".title_1").on("mouseleave",function () {
        $(".active_1").hide();
        $(".wrapper-body .left ul .title_1").css("height",42);
    });

    $(".title_2").on("mouseenter",function () {
        $(".wrapper-body .left ul .title_2").css("height",60);
        $(".active_2").show();
    });
    $(".title_2").on("mouseleave",function () {
        $(".active_2").hide();
        $(".wrapper-body .left ul .title_2").css("height",42);
    });

    $(".title_3").on("mouseenter",function () {
        $(".wrapper-body .left ul .title_3").css("height",60);
        $(".active_3").show();
    });
    $(".title_3").on("mouseleave",function () {
        $(".active_3").hide();
        $(".wrapper-body .left ul .title_3").css("height",42);
    });

    $(".title_4").on("mouseenter",function () {
        $(".wrapper-body .left ul .title_4").css("height",120);
        $(".active_4").show();
    });
    $(".title_4").on("mouseleave",function () {
        $(".active_4").hide();
        $(".wrapper-body .left ul .title_4").css("height",42);
    });

    $(".title_5").on("mouseenter",function () {
        $(".wrapper-body .left ul .title_5").css("height",60);
        $(".active_5").show();
    });
    $(".title_5").on("mouseleave",function () {
        $(".active_5").hide();
        $(".wrapper-body .left ul .title_5").css("height",42);
    });

    $(".title_6").on("mouseenter",function () {
        $(".wrapper-body .left ul .title_6").css("height",60);
        $(".active_6").show();
    });
    $(".title_6").on("mouseleave",function () {
        $(".active_6").hide();
        $(".wrapper-body .left ul .title_6").css("height",42);
    });

    $(".title_7").on("mouseenter",function () {
        $(".wrapper-body .left ul .title_7").css("height",60);
        $(".active_7").show();
    });
    $(".title_7").on("mouseleave",function () {
        $(".active_7").hide();
        $(".wrapper-body .left ul .title_7").css("height",42);
    });

    $(".title_8").on("mouseenter",function () {
        $(".wrapper-body .left ul .title_8").css("height",120);
        $(".active_8").show();
    });
    $(".title_8").on("mouseleave",function () {
        $(".active_8").hide();
        $(".wrapper-body .left ul .title_8").css("height",42);
    });

    $(".title_9").on("mouseenter",function () {
        $(".wrapper-body .left ul .title_9").css("height",120);
        $(".active_9").show();
    });
    $(".title_9").on("mouseleave",function () {
        $(".active_9").hide();
        $(".wrapper-body .left ul .title_9").css("height",42);
    });

    $(".title_10").on("mouseenter",function () {
        $(".wrapper-body .left ul .title_10").css("height",120);
        $(".active_10").show();
    });
    $(".title_10").on("mouseleave",function () {
        $(".active_10").hide();
        $(".wrapper-body .left ul .title_10").css("height",42);
    });
}

/**
 * 获取用户信息
 */
function getMyUserInfo() {
    $.ajax({
        method:"GET",
        url:"/user/queryLoginUser.json",
        data:$.extend({},{
        })
    }).done(function(response){
        $(".wrapper-title .my-userName").val(response.data.LOGIN_NAME);
    });
}

/**
 * 退出登录按钮
 */
function logoutMyButton() {
    $(".my-logout").on("click",function () {
        logout();
    });
}

/**
 * 退出登录
 */
function logout() {
    $.ajax({
        method:"GET",
        url:"/user/logout.json",
        data:$.extend({},{
        })
    }).done(function(response){
        if(response.success == true){
            alert("退出登录成功");
            window.location.href="common.htm";
        }
    });
}

function queryNotice() {
    $.ajax({
        method:"GET",
        url:"/notice/queryNoticeInfo.json",
        data:$.extend({},{
        })
    }).done(function(response){
        $(".notice ul").html("");
        var htmlStr = "";
        if (Array.isArray(response.data)) {
            response.data.forEach(function (item) {
                htmlStr += `<li>
                               <div>
                                   <span>标题:</span>
                                   <span>${item.title}</span>
                                   </br>
                                   <span>内容:</span>
                                   <span>${item.content}</span>
                               </div>
                           </li>`;
            });
            $(".notice ul").html(htmlStr);
        }
    });
}



