$(function () {

    //初始化页面元素
    init();
    //获取用户信息
    getUserInfo();
    //退出登录
    logoutButton();
    //页面切换
    pageSwitchover();
    //添加投诉
    addComplain();

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

        getAssetDetailAjax({
            userId:response.data.USER_ID,
        });
        //查询出用户信息详情
        getUserInfoAjax({
            userId:response.data.USER_ID,
            userType:response.data.USER_TYPE,
        },function (data) {
            buttonEvent(data);
            saveUserInfo(response.data.USER_ID,response.data.USER_TYPE);
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
 * 查询出投诉详情
 * @param queryParm
 * @param callback
 */
function getUserInfoAjax(queryParams,callback) {
    $(".anchor-main").html("");
    $.ajax({
        method: "GET",
        url: "/user/queryUserInfoDetail.json",
        data: $.extend({}, {

        }, queryParams)
    }).done(function (response) {
        var htmlStr = "";
        if (response.success == true) {
            htmlStr += `<div class="anchor-info-head">
                            <div class="compile-button">
                                <div class="compile">
                                    <button class="layui-btn" style="width: 100px;height: 40px">编辑</button>
                                </div>
                                <button class="layui-btn save" style="width: 100px;height: 40px">保存</button>
                            </div>
                            <div class="anchor-info-img inputText">
                                <div class="head">
                                    <span style="font-size: 15px">头像:</span>
                                </div>
                                <div class="head-img">
                                    <div class="user-img">
                                      <img src="${response.data.userHeadImg}">
                                    </div>
                                    <div class="head-button">
                                        <button class="layui-btn" style="width: 100px;height: 40px">上传照片</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                           <h1>用户信息</h1>
                        </div>
                        <div class="anchor-info">
                            <div class="anchor-info-div">
                                <div class="anchor-info-list">
                                    <div class="inputText userName-div">
                                        <span style="font-size: 15px">用户名:</span>
                                        <input id="test1" class="layui-input userName" type="text" style="width: 400px;height: 40px" disabled="disabled" value="${response.data.userName}"/>
                                    </div>
                                    <div class="inputText userNo-div">
                                        <span style="font-size: 15px">用户编号:</span>
                                        <input  class="layui-input userNo" type="text" style="width: 400px;height: 40px" disabled="disabled" value="${response.data.userNo}"/>
                                    </div>
                                    <div class="inputText tel-div">
                                        <span style="font-size: 15px">联系方式:</span>
                                        <input id="test2" class="layui-input tel" type="text" style="width: 400px;height: 40px" disabled="disabled" value="${response.data.userTel}"/>
                                    </div>
                                    <div class="inputText sex-div">
                                        <span style="font-size: 15px">性别:</span>
                                        ${setSex(response.data.sexDesc)}
                                     </div>
                                </div>
                            </div>
                            <div class="anchor-info-div2">
                                <div class="inputText card-div">
                                    <span style="font-size: 15px">身份证号:</span>
                                    <input  class="layui-input cardID" type="text" style="width: 400px;height: 40px" disabled="disabled" value="${response.data.identityCard}"/>
                                </div>
                                <div class="inputText network-type-div">
                                   ${setNetworkType()}
                                </div>
                                <div class="inputText network-name-div">
                                    <span style="font-size: 15px">节目名称:</span>
                                    <input  class="layui-input networkName" type="text" style="width: 400px;height: 40px" disabled="disabled" value="${response.data.networkBroadcastTypeName}"/>
                                </div>
                            </div>
                        </div>`;
            $(".anchor-main").html(htmlStr);
            if (typeof callback === 'function') {
                callback(response.data);
            }
        }
    });
}

function buttonEvent(data) {
    $(".save").hide();
    document.getElementById("test1").disabled  = "disabled";
    document.getElementById("test2").disabled  = "disabled";
    var obj=document.getElementById('broadcast-type-select');
    var index=data.networkBroadcastTypeDetailId; //序号，取当前选中选项的序号
    obj.options[index].selected = "selected";
    $(".compile").on("click",function () {
        document.getElementById("test1").disabled  = "";
        document.getElementById("test2").disabled  = "";
        $(".save").show();
    });
}

/**
 * 保存用户信息
 */
function saveUserInfo(userId,userType) {

    $(".save").on("click",function () {
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
        document.getElementById("test3").disabled  = "disabled";
        document.getElementById("test4").disabled  = "disabled";
    });
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

function setNetworkType() {
    var htmlString = `<div class="form_item broadcastType">
                                       <span>节目类型：</span>
                                           <select id="broadcast-type-select" class="broadcast-type" style="width: 150px;height: 25px">
                                               <option value="" >请选择节目类型</option>
                                               <option value="1">网游竞技</option>
                                               <option value="2">手游休闲</option>
                                               <option value="3">休闲娱乐</option>
                                               <option value="4">单机热门</option>
                                               <option value="5">科学教育</option>
                                           </select>
                        </div>`;
    return htmlString;
}

/**
 * 初始化页面元素
 */
function init() {
    $(".anchor-templet").hide();
    $(".asset-info-templet").hide();
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
        $(".anchor-templet").show();
        $(".mulu").hide();
    });

    //资产详情页面
    $(".asset-info").on("click",function () {
        init();
        $(".asset-info-templet").show();
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
            if(response.data.length == 0){
                htmlStr = `<tr>
                             <td colspan="10" style="text-align: center;height: 30px">暂无数据</td>
                           </tr>`;
            }
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
                    <td style="text-align: center;height: 30px">${item.deductValue}</td>
                    <td style="text-align: center;height: 30px">${item.reason}</td>
                    <td style="text-align: center;height: 30px">${item.time}</td>
                  </tr>`;
                });
                if(response.data.complainRecordDTOS.length == 0){
                    htmlStr = `<tr>
                             <td colspan="3" style="height: 30px">暂无数据</td>
                           </tr>`;
                }
                $(".intergity-record-table table tbody").html(htmlStr);
            }
        }
    });
}

/**
 * 查询出网络节目内容详情
 * @param queryParm
 * @param callback
 */
function getAssetDetailAjax(queryParams) {
    $(".assetDetail-table table tbody").html("");
    $.ajax({
        method: "GET",
        url: "/assetManagement/queryAssetDetailBy.json",
        data: $.extend({}, {
        }, queryParams)
    }).done(function (response) {
        var htmlStr = "";
        if (Array.isArray(response.data)) {
            response.data.forEach(function (item) {
                htmlStr += `<tr>
                    <td style="text-align: center;height: 30px">${item.yearDescription}</td>
                    <td style="text-align: center;height: 30px">${item.monthDescription}</td>
                    <td style="text-align: center;height: 30px">${item.dayDescription}</td>
                    <td style="text-align: center;height: 30px" >${item.fishBallMoney}</td>
                    <td style="text-align: center;height: 30px">${item.flowerMoney}</td>
                    <td style="text-align: center;height: 30px">${item.rocketMoney}</td>
                    <td style="text-align: center;height: 30px">${item.allMoney}</td>
                  </tr>`;
            });
            if(response.data.length == 0){
                htmlStr = `<tr>
                             <td colspan="7" style="text-align: center;height: 30px">暂无数据</td>
                           </tr>`;
            }
            $(".assetDetail-table table tbody").html(htmlStr);
        }
    });
}

