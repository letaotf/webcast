$(function () {
    //初始化页面元素
    init();
    //获取用户信息
    getUserInfo();
    //页面切换
    pageSwitchover();
    //添加投诉
    addComplain();
    //退出登录按钮
    logoutButton();
});

/**
 * 获取用户信息
 */
function getUserInfo() {
    $.ajax({
        method:"GET",
        url:"/user/queryLoginUser.json",
        data:$.extend({},{
        })
    }).done(function(response){

        $(".wrapper-title .userName").val(response.data.LOGIN_NAME);
        //获取商品信息
        getGoodsDetailAjax({
            pageNo: 1,
        },function () {
            buyGoodsButton(response.data.USER_ID);
        });
        //查询出用户信息详情
        getUserInfoAjax({
            userId:response.data.USER_ID,
            userType:response.data.USER_TYPE,
        },function () {
            buttonEvent();
            saveUserInfo(response.data.USER_ID,response.data.USER_TYPE);
        });
        //查询用户购买记录
        getBuyGoodsAjax({
            userId:response.data.USER_ID,
        });
        //查询用户的投诉记录
        getComplainDetailAjax({
            userId:response.data.USER_ID,
            complainStatus:0,
        });
        //查询诚信值记录
        getIntergityDetailAjax({
            userId:response.data.USER_ID,
        });
    });
}

/**
 * 退出登录按钮
 */
function logoutButton() {
    $(".logout").on("click",function () {
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

/**
 * 初始化页面元素
 */
function init() {
    $(".user-templet").hide();
    $(".goods-info-templet").hide();
    $(".goods-record-templet").hide();
    $(".complain-info-templet").hide();
    $(".complain-record-templet").hide();
    $(".intergity-record-templet").hide();
}

/**
 * 页面展示和页面的切换
 */
function pageSwitchover() {

    //个人信息详情页面
    $(".user-info").on("click",function () {
        init();
        $(".user-templet").show();
        $(".mulu").hide();
    });

    //网上商店页面
    $(".goods-info").on("click",function () {
        init();
        $(".goods-info-templet").show();
        $(".mulu").hide();
    });

    //购买记录页面
    $(".goods-record").on("click",function () {
        init();
        $(".goods-record-templet").show();
        $(".mulu").hide();
    });

    //发起投诉页面
    $(".complain-info").on("click",function () {
        init();
        $(".complain-info-templet").show();
        $(".mulu").hide();
    });

    //查看投诉页面
    $(".complain-record").on("click",function () {
        init();
        $(".complain-record-templet").show();
        $(".mulu").hide();
    });

    //诚信值记录页面
    $(".intergity-record").on("click",function () {
        init();
        $(".intergity-record-templet").show();
        $(".mulu").hide();
    });
}

/**
 * 查询出投诉详情
 * @param queryParm
 * @param callback
 */
function getGoodsDetailAjax(queryParams,callback) {
    $(".goodsDetail-list").html("");
    $.ajax({
        method: "GET",
        url: "/goods/queryOnlineListGoods.json",
        data: $.extend({}, {
            pageSize: 10
        }, queryParams)
    }).done(function (response) {
        var htmlStr = "";
        if (Array.isArray(response.data.result)) {
            response.data.result.forEach(function (item) {
                htmlStr += `<div id="goodsDetail-list-id"  class="goodsDetail-list-div">
                                <div class="goodsDetail-list-img">
                                    <img src="img/rocket.jpg" width="224" height="200"/>
                                </div>
                                <div class="goodsDetail-list-goodsName" id="good-id-div" >
                                    <span>商品名称:</span>
                                    <input value=${item.goodsName} disabled="disabled" />
                                </div>
                                <div class="goodsDetail-list-goodsPrice" id="goods-price-div" >
                                    <span>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格:</span>
                                    <input value=${item.goodsPrice}￥ disabled="disabled" width="150px" />
                                </div>
                                <div class="goodsDetail-list-gmtModified">
                                    <span>上架时间:</span>
                                    <input value=${item.gmtModified} disabled="disabled" width="150px" data-gmt-modified="${item.gmtModified}"/>
                                </div>
                                <div class="goodsDetail-list-button" data-goods-id=${item.goodsId}  data-goods-price=${item.goodsPrice}>
                                    <button class="buy-goods layui-btn">购买</button>
                                </div>
                            </div>`;
            });
            $(".goodsDetail-list").html(htmlStr);
            if (typeof callback === 'function') {
                callback();
            }
        }
    });
}

/**
 * 购买商品按钮
 */
function buyGoodsButton(userId) {

    $(".buy-goods").on("click",function () {
         var  e = document.getElementsByClassName("goodsDetail-list-button");
         var a = $(".buy-goods").index(this);
         var goodsId = e[a].dataset.goodsId;
         var goodsPrice = e[a].dataset.goodsPrice;
         $.ajax({
            url:"/goods/addBuyGoods.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify({
                goodsId:goodsId,
                payPrice:goodsPrice,
                userId:userId,
            }),
            error:function(result){
                alert("购买商品失败");
            }
         }).done(function (result) {
            if(result.success == true){
                alert("购买商品成功");
                //查询用户购买记录
                getBuyGoodsAjax({
                    userId:userId,
                });
            }else{
                alert(result.message);
            }
        });
    });
}




/**
 * 增加投诉类型
 */
function addComplain() {
    $(".addComplain-button").on("click",function () {
        var userName = $(".addComplain-name-input").val();
        var defendantUserName = $(".addComplain-defendantUserName-input").val();
        var reason =  $(".addGoods-content-textarea").val();
        getAddComplainAjax({
            userName: userName === ''?null:userName,
            defendantUserName: defendantUserName ===''?null:defendantUserName,
            reason: reason===''?null:reason
        });
    });
}

/**
 * 添加投诉post请求
 * @param queryParams
 */
function getAddComplainAjax(queryParams) {
    $.ajax({
        url:"/complain/addComplain.json",
        type:"POST",
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(queryParams),
        error:function(result){
            alert("投诉添加失败");
        }
    }).done(function (result) {
        if(result.data == true){
            alert("投诉添加成功");
        }
    });
}

/**
 * 查询出用户信息详情
 * @param queryParm
 * @param callback
 */
function getUserInfoAjax(queryParams,callback) {
    $(".user-main").html("");
    $.ajax({
        method: "GET",
        url: "/user/queryUserInfoDetail.json",
        data: $.extend({}, {

        }, queryParams)
    }).done(function (response) {
        var htmlStr = "";
        if (response.success == true) {
            htmlStr += `<div class="compile-button">
                            <button class="layui-btn compile" style="width: 100px;height: 40px">编辑</button>
                        </div>
                        <div class="save-button">
                            <button class="layui-btn save" style="width: 100px;height: 40px">保存</button>
                        </div>
                        <div class="user-info">
                             <div class="user-info-list">
                                <div class="user-info-div">
                                 <div class="userName-div">
                                      <span style="font-size: 15px">用户名:</span>
                                      <input id="test1" class="layui-input userName" type="text" style="width: 400px;height: 40px"  value="${response.data.userName}"/>
                                 </div>
                                 <div class="userNo-div">
                                      <span style="font-size: 15px">用户编号:</span>
                                      <input  class="layui-input userNo" type="text" style="width: 400px;height: 40px" disabled="disabled" value="${response.data.userNo}"/>
                                 </div>
                                 <div class="tel-div">
                                     <span style="font-size: 15px">联系方式:</span>
                                     <input id="test2" class="layui-input tel" type="text" style="width: 400px;height: 40px"  value="${response.data.userTel}"/>
                                 </div>
                                 <div class="sex-div">
                                     <span style="font-size: 15px">性别:</span>
                                     ${setSex(response.data.sexDesc)}
                                 </div>
                               </div>
                             </div>
                             <div class="user-info-img inputText">
                                   <img src="${response.data.userHeadImg}">
                             </div>
                        </div>`;
            $(".user-main").html(htmlStr);
            if (typeof callback === 'function') {
                callback();
            }
        }
    });
}

function setSex(result) {
    var htmlString = null;
    if(result == '男'){
        htmlString = ` <div class="type-block">
                           <input type="radio" name="sex" value="0" checked="checked" class="test3"/>
                           <span style="white-space:pre;">男</span>
                           <input type="radio" name="sex" value="1" class="test4"/>
                           <span style="white-space:pre;">女</span>
                       </div>`;
    } else {
        htmlString = ` <div class="type-block">
                           <input type="radio" name="sex" value="0" class="test3"/>
                           <span style="white-space:pre;">男</span>
                           <input type="radio" name="sex" value="1" checked="checked" class="test4"/>
                           <span style="white-space:pre;">女</span>
                       </div>`;
    }
    return htmlString;
}


function buttonEvent() {
    $(".save-button .save").hide();
    document.getElementById("test1").disabled  = "disabled";
    document.getElementById("test2").disabled  = "disabled";
    $(".compile-button .compile").on("click",function () {
        document.getElementById("test1").disabled  = "";
        document.getElementById("test2").disabled  = "";
        $(".save-button .save").show();
    });
}

/**
 * 保存用户信息
 */
function saveUserInfo(userId,userType) {

    $(".save-button .save").on("click",function () {
        //添加成功后,重新刷新页面
        var userName = $(".userName").val();
        var tel = $(".tel").val();
        var sex = getSex();
        saveUserInfoAjax({
            userType:userType,
            userId:userId,
            userName:userName,
            userHeadImg:12,
            userTel:tel,
            userSex:sex,
        });
        document.getElementById("test1").disabled  = "disabled";
        document.getElementById("test2").disabled  = "disabled";
    });
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

/**
 * 保存用户信息
 * @param queryParams
 */
function saveUserInfoAjax(queryParams) {
    $.ajax({
        url:"/user/updateUserInfoDetail.json",
        type:"POST",
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(queryParams),
        error:function(result){
            alert("保存用户信息失败");
        }
    }).done(function (result) {
        if(result.data == true){
            alert("保存用户信息成功");
        }else{
            alert("保存用户信息成功");
        }
    });
}

/**
 * 查询用户的购买记录
 * @param queryParams
 * @param callback
 */
function getBuyGoodsAjax(queryParams) {
    $(".goods-record-table table tbody").html("");
    $.ajax({
        method: "GET",
        url: "/goods/queryBuyGoodsRecord.json",
        data: $.extend({}, {
        }, queryParams)
    }).done(function (response) {
        var htmlStr = "";
        if (Array.isArray(response.data)) {
            response.data.forEach(function (item) {
                htmlStr += `<tr>
                    <td style="text-align: center;height: 30px">${item.goodsName}</td>
                    <td style="text-align: center">${item.payPrice}</td>
                    <td style="text-align: center">${item.gmtCreate}</td>               
                    <td style="text-align: center">${item.employStatusDes}</td>
                  </tr>`;
            });
            $(".goods-record-table table tbody").html(htmlStr);
        }else{
            htmlStr=`
               <td>暂无数据<td>
            `;
            $(".goods-record-table table tbody").html(htmlStr);
        }
    });
}

/**
 * 查询出投诉详情
 * @param queryParm
 * @param callback
 */
function getComplainDetailAjax(queryParams) {
    $(".complain-record-table table tbody").html("");
    $.ajax({
        method: "GET",
        url: "/complain/queryComplainDetailBy.json",
        data: $.extend({}, {

        }, queryParams)
    }).done(function (response) {
        var htmlStr = "";
        if (Array.isArray(response.data)) {
            response.data.forEach(function (item) {
                htmlStr += `<tr>
                    <td style="text-align: center;height: 30px">${item.userNo}</td>
                    <td style="text-align: center">${item.userName}</td>
                    <td style="text-align: center">${item.userTel}</td>
                    <td style="text-align: center">${item.defendantUserNo}</td>
                    <td style="text-align: center">${item.defendantUserName}</td>
                    <td style="text-align: center">${item.defendantUserTel}</td>
                    <td style="text-align: center">${item.complainDate}</td>
                    <td style="text-align: center">${item.reason}</td>
                    <td style="text-align: center">${item.complainRankDesc}</td>
                  </tr>`;
            });
            $(".complain-record-table table tbody").html(htmlStr);
        }
    });
}

/**
 * 查询出网络节目内容详情
 * @param queryParm
 * @param callback
 */
function getIntergityDetailAjax(queryParams) {
    $(".intergity-record-table table tbody").html("");
    $.ajax({
        method: "GET",
        url: "/intergitValues/queryIntergitValuesRecord.json",
        data: $.extend({}, {
        }, queryParams)
    }).done(function (response) {
        var htmlStr = "";
        if(response.success == true){
            $(".intergity-value .intergity-record-value").val(response.data.values);
            if (Array.isArray(response.data.complainRecordDTOS)) {
                response.data.complainRecordDTOS.forEach(function (item) {
                    htmlStr += `<tr>
                    <td style="height: 30px">${item.deductValue}</td>
                    <td style="height: 30px">${item.reason}</td>
                    <td style="height: 30px">${item.time}</td>
                  </tr>`;
                });
                if(response.data.complainRecordDTOS.length == 0){
                    htmlStr = `<tr>
                             <td colspan="3" style="height: 30px">暂无数据</td>
                           </tr>`;
                }
                $(".intergity-record-table table tbody").html(htmlStr);
            }s
        }
    });
}
