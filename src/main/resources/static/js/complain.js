/**
 * 查询网络节目内容
 */
$(function() {
    //搜索事件
    buttonEvent();
    getComplainAjax({
        pageNo: 1,
    }, function (data) {
        renderPager(data.totalPage, data.pageNo);
        bindPagerEvent();
        initRecord();
    });
    //添加投诉
    addComplain();
    //处理投诉
    dealComplainStatus();
    //更新投诉
    updateComplainPage();
    init();

    function init() {
        $(".mulu").hide();
    }

    /**
     * 查询出投诉详情
     * @param queryParm
     * @param callback
     */
    function getComplainAjax(queryParams, callback) {
        $(".complain-table table tbody").html("");
        $.ajax({
            method: "GET",
            url: "/complain/queryComplainList.json",
            data: $.extend({}, {
                pageSize: 10
            }, queryParams)
        }).done(function (response) {
            var htmlStr = "";
            if (Array.isArray(response.data.result)) {
                response.data.result.forEach(function (item) {
                    htmlStr += `<tr>
                    <td>${item.userNo}</td>
                    <td>${item.userName}</td>
                    <td>${item.userTel}</td>
                    <td>${item.defendantUserNo}</td>
                    <td>${item.defendantUserName}</td>
                    <td>${item.defendantUserTel}</td>
                    <td>${item.complainDate}</td>
                    <td>${item.reason}</td>
                    <td>${item.complainRankDesc}</td>
                    <td>
                       ${distinguish(item)}
                       &nbsp;&nbsp;&nbsp;<a class="update-complain" data-complain-id=${item.complainId}
                                                                    data-complain-rank=${item.complainRank}
                                                                    data-reason-detail=${item.reason}>添加等级</a>
                    </td>
                  </tr>`;
                });
                $(".complain-table table tbody").html(htmlStr);
                if (typeof callback === 'function') {
                    callback(response.data);
                }
            }
        });
    }

    /**
     * 区分通过与不通过按钮
     * @param result
     */
    function distinguish(data) {
        var parmHtml = "";
        if(data.operatorType === 1){
            parmHtml = `<a class="noPass" data-complain-id=${data.complainId}>不通过</a>`;
        }else{
            parmHtml = `<a class="pass" data-complain-id=${data.complainId}>通过</a>`;
        }
        return parmHtml;
    }

    /**
     * 做分页查询
     */
    function bindPagerEvent() {
        var currentPage = 1;
        $(".pager .page").each(function () {
            $(this).on("click", function (e) {
                var page = e.target.dataset.page;
                currentPage = page * 1;
                getAssetAjax({
                    pageNo: currentPage,
                }, function (data) {
                    renderPager(data.totalPage, currentPage);
                    bindPagerEvent();
                });
            });
        });
        /**
         * 上一页
         */
        $(".pager .prev").on("click", function () {
            if (currentPage != 1) {
                currentPage--;
            }
            getComplainAjax({
                pageNo: currentPage,
            }, function (data) {
                renderPager(data.totalPage, currentPage);
                bindPagerEvent();
            });
        });

        /**
         * 下一页
         */
        $(".pager .next").on("click", function () {
            currentPage++;
            getComplainAjax({
                pageNo: currentPage,
            }, function (data) {
                renderPager(data.totalPage, currentPage);
                bindPagerEvent();
            });
        });
    }

    /**
     * 画出分页按钮
     * @param totalPage
     * @param pageSize
     * @param pageNo
     */
    function renderPager(totalPage, pageNo) {
        var pageHtml = "";
        for (var i = 0; i < totalPage; i++) {
            pageHtml += `
               <li><a href="javascript:;" class="page ${i + 1 === pageNo ? "active" : ""}" data-page="${i + 1}">${i + 1}</a></li>
           `;
        }
        var htmlStr = `
         <nav class="pager" aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="javascript:;" class="prev" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        ${pageHtml}
                        <li>
                            <a href="javascript:;" class="next" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
         </nav>
       `;
        $(".complain-button").html(htmlStr);
    }

    /**
     * 搜索按钮事件
     */
    function buttonEvent() {
        $(".search-complain").on("click", function () {
            var userNo = $(".complain-userNO").val();
            var defendantUserNo = $(".complain-defendantUserNo").val();
            var complainDate = $(".complain-complainDate").val();
            var queryParams = {
                pageNo: 1,
                userNo: userNo ,
                defendantUserNo: defendantUserNo,
                complainDate: complainDate ,
            };
            getComplainAjax(queryParams, function (data) {
                renderPager(data.totalPage, currentPage);
                bindPagerEvent();
            });
        });
    }

    /**
     * 初始化查询条件
     */
    function initRecord() {
        $(".complain-userNO").val('');
        $(".complain-defendantUserNo").val('');
        $(".complain-complainDate").val('');
    }

    /**
     * 增加投诉类型
     */
    function addComplain() {
        $(document).on("click",".addComplain-success-button",function () {
            var userName = $(".addComplain-name-input").val();
            var defendantUserName = $(".addComplain-defendantUserName-input").val();
            var reason =  $(".addGoods-content-textarea").val();
            getAddComplainAjax({
                userName: userName === ''?null:userName,
                defendantUserName: defendantUserName ===''?null:defendantUserName,
                reason: reason===''?null:reason
            });
            $( ".addComplain-main" ).dialog("close");
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
                //添加成功后,重新刷新页面
                var userNo = $(".complain-userNO").val();
                var defendantUserNo = $(".complain-defendantUserNo").val();
                var complainDate = $(".complain-complainDate").val();
                getComplainAjax({
                    pageNo:1,
                    userNo: userNo === '' ? null : userNo,
                    defendantUserNo: defendantUserNo === '' ? null : defendantUserNo,
                    complainDate: complainDate === '' ? null : complainDate,
                },function(data){
                    renderPager(data.totalPage,data.pageNo);
                    bindPagerEvent();
                });
            }
        });
    }

    /**
     * 处理商品在线状态
     */
    function dealComplainStatus() {
        $(document).on("click",".noPass",function () {
            var complainId = this.dataset.complainId;
            getDealComplainStatusAjax({
                complainId:complainId,
                operatorType:0,
            });
        });

        $(document).on("click",".pass",function () {
            var complainId = this.dataset.complainId;
            getDealComplainStatusAjax({
                complainId:complainId,
                operatorType:1,
            });
        });
    }

    /**
     * 处理商品状态post请求
     * @param queryParams
     */
    function getDealComplainStatusAjax(queryParams) {
        $.ajax({
            url:"/complain/dealComplainRank.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("处理投诉失败");
            }
        }).done(function (result) {
            if(result.data == true){
                alert("处理投诉成功");
                //添加成功后,重新刷新页面
                var userNo = $(".complain-userNO").val();
                var defendantUserNo = $(".complain-defendantUserNo").val();
                var complainDate = $(".complain-complainDate").val();
                getComplainAjax({
                    pageNo:1,
                    userNo: userNo === '' ? null : userNo,
                    defendantUserNo: defendantUserNo === '' ? null : defendantUserNo,
                    complainDate: complainDate === '' ? null : complainDate,
                },function(data){
                    renderPager(data.totalPage,data.pageNo);
                    bindPagerEvent();
                });
            }
        });
    }

    /**
     * 更新
     */
    function updateComplainPage() {
        $(document).on("click",".update-complain",function () {
            var complainRank = this.dataset.complainRank;
            var reason = this.dataset.reasonDetail;
            var complainId = this.dataset.complainId;
            var  e = document.querySelector(".complain-rank");
            e.dataset.id = complainId;
            $(".rank-select").val(complainRank);
            $(".complain-rank-textarea").val(reason);
            $( ".complain-rank" ).dialog("open");
            updateComplain();
        });
    }

    /**
     *更新页面
     */
    function updateComplain() {
        $(document).on("click",".complain-rank-button",function () {
            var  e = document.querySelector(".complain-rank");
            var complainId = e.dataset.id;
            var complainRank = $(".rank-select").val();
            var reason = $(".complain-rank-textarea").val();
            getUpdateComplainAjax({
                complainId:complainId,
                complainRank:complainRank,
                reason:reason,
            });
            $( ".complain-rank" ).dialog("close");
        });
    }

    /**
     * 删除商品post请求
     * @param queryParams
     */
    function getUpdateComplainAjax(queryParams) {
        $.ajax({
            url:"/complain/updateComplain.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("处理投诉失败");
            }
        }).done(function (result) {
            if(result.data == true){
                alert("处理投诉成功");
                //添加成功后,重新刷新页面
                var userNo = $(".complain-userNO").val();
                var defendantUserNo = $(".complain-defendantUserNo").val();
                var complainDate = $(".complain-complainDate").val();
                getComplainAjax({
                    pageNo:1,
                    userNo: userNo === '' ? null : userNo,
                    defendantUserNo: defendantUserNo === '' ? null : defendantUserNo,
                    complainDate: complainDate === '' ? null : complainDate,
                },function(data){
                    renderPager(data.totalPage,data.pageNo);
                    bindPagerEvent();
                });
            }
        });
    }

});