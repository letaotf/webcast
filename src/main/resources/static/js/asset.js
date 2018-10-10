/**
 * 查询网络节目内容
 */
$(function() {

    init();
    //搜索事件
    buttonEvent();
    getUserInfo();
    getAssetAjax({
        pageNo: 1,
    }, function (data) {
        renderPager(data.totalPage, data.pageNo);
        bindPagerEvent();
        initRecord();
        //查询资产详情
        queryAssetDetails();
    });
    function init() {
        $(".assetDetail-main").hide();
        $(".mulu").hide();
    }

    function getUserInfo() {
        $.ajax({
            method:"GET",
            url:"/user/queryLoginUser.json",
            data:$.extend({},{
            })
        }).done(function(response){
            $(".assetDetail-catalogue-userName").val(response.data.LOGIN_NAME);
        });
    }

    /**
     * 查询出网络节目内容详情
     * @param queryParm
     * @param callback
     */
    function getAssetAjax(queryParams, callback) {
        $(".asset-table table tbody").html("");
        $.ajax({
            method: "GET",
            url: "/assetManagement/queryAssetList.json",
            data: $.extend({}, {
                pageSize: 10
            }, queryParams)
        }).done(function (response) {
            var htmlStr = "";
            if (Array.isArray(response.data.result)) {
                response.data.result.forEach(function (item) {
                    htmlStr += `<tr>
                    <td><a class="assetNo-data" data-asset-id=${item.assetId} data-user-no=${item.userNo} data-asset-no=${item.assetNo}>${item.assetNo}</a></td>
                    <td>${item.userNo}</td>
                    <td>${item.fishBallNum}</td>
                    <td>${item.fishBallValue}</td>
                    <td>${item.flowerNum}</td>
                    <td>${item.flowerValue}</td>
                    <td>${item.rocketNum}</td>
                    <td>${item.rocketValue}</td>
                  </tr>`;
                });
                $(".asset-table table tbody").html(htmlStr);
                if (typeof callback === 'function') {
                    callback(response.data);
                }
            }
        });
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
                    queryAssetDetails();
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
            getAssetAjax({
                pageNo: currentPage,
            }, function (data) {
                renderPager(data.totalPage, currentPage);
                bindPagerEvent();
                queryAssetDetails();
            });
        });

        /**
         * 下一页
         */
        $(".pager .next").on("click", function () {
            currentPage++;
            getAssetAjax({
                pageNo: currentPage,
            }, function (data) {
                renderPager(data.totalPage, currentPage);
                bindPagerEvent();
                queryAssetDetails();
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
        $(".asset-button").html(htmlStr);
    }

    /**
     * 搜索按钮事件
     */
    function buttonEvent() {
        $(".search-asset").on("click", function () {
            var assetNo = $(".asset-assetNo").val();
            var userNo = $(".asset-userNo").val();
            var queryParams = {
                pageNo: 1,
                assetNo: assetNo === '' ? null : assetNo,
                userNo:userNo === '' ? null :userNo,
            };
            getAssetAjax(queryParams, function (data) {
                renderPager(data.totalPage, data.pageNo);
                bindPagerEvent();
                queryAssetDetails();
            });
        });
    }

    /**
     * 搜索按钮事件
     */
    function exportButton(assetId) {
        $(".export").on("click", function () {
            var yearId = $(".year").val();
            var monthId = $(".month").val();
            var dayId = $(".day").val();
            var queryParams = {
                pageNo: 1,
                assetId:assetId,
                yearId: yearId ,
                monthId: monthId ,
                dayId:dayId ,
            };
            getExportAjax(queryParams);
        });
    }

    /**
     * 查询出网络节目内容详情
     * @param queryParm
     * @param callback
     */
    function getExportAjax(queryParams) {
        $.ajax({
            method: "GET",
            url: "/assetManagement/exportALLFeeDetail.json",
            data: $.extend({}, {
                pageSize: 10
            }, queryParams)
        }).done(function (response) {
            var e = document.getElementsByClassName("export");
            e.href = response.data;
        });
    }

    /**
     * 初始化查询条件
     */
    function initRecord() {
        $(".asset-assetNo").val('');
        $(".asset-userNo").val('');
    }

    /**
     * 搜索按钮事件
     */
    function buttonEventDetail(assetId,userNo,assetNo) {
        $(".search-detail").on("click", function () {
            // var assetId =  $(".asset-detail").dataset.assetId;
            // var userNo =  $(".asset-detail").dataset.userNo;
            // var assetNo =  $(".asset-detail").dataset.assetNo;
            var yearId = $(".year").val();
            var monthId = $(".month").val();
            var dayId = $(".day").val();
            var queryParams = {
                pageNo: 1,
                assetId:assetId,
                yearId: yearId ,
                monthId: monthId ,
                dayId:dayId ,
            };
            getAssetDetailAjax(queryParams,userNo,assetNo,assetId,function(data){
                renderPagerDetail(data.totalPage, data.pageNo);
                bindPagerEventDetail(assetId);
            });
        });
    }


    /**
     * 查询订单详情
     */
    function queryAssetDetails() {
        $(".assetNo-data").on("click",function () {
            $(".asset-main").hide();
            $(".assetDetail-main").show();
            var assetId = this.dataset.assetId;
            var userNo = this.dataset.userNo;
            var assetNo = this.dataset.assetNo;
            $(".assetDetail-catalogue-assetNO").val(assetNo);
            $(".assetDetail-catalogue-userNO").val(userNo);
            $(".assetDetail-catalogue-userName").val();
            var queryParams={
                assetId:assetId,
                pageNo:1,
            };
            getAssetDetailAjax(queryParams,userNo,assetNo,function(data){
                renderPagerDetail(data.totalPage, data.pageNo);
                bindPagerEventDetail(assetId);
                buttonEventDetail(assetId,userNo,assetNo);
                exportButton(assetId);
            });
        });
    }

    /**
     * 查询出网络节目内容详情
     * @param queryParm
     * @param callback
     */
    function getAssetDetailAjax(queryParams,userNo,assetNo,callback) {
        $(".assetDetail-table table tbody").html("");
        $.ajax({
            method: "GET",
            url: "/assetManagement/queryAssetListDetails.json",
            data: $.extend({}, {
                pageSize: 10
            }, queryParams)
        }).done(function (response) {
            var htmlStr = "";
            if(response.success) {
                if (Array.isArray(response.data.result)) {
                    response.data.result.forEach(function (item) {
                        htmlStr += `<tr>
                                        <td class="asset-detail" data-asset-id=${item.assetId} data-user-no=${assetNo}  data-asset-no=${userNo}>${assetNo}</td>
                                        <td>${userNo}</td>
                                        <td>${item.yearDescription}</td>
                                        <td>${item.monthDescription}</td>
                                        <td>${item.dayDescription}</td>
                                        <td>${item.fishBallMoney}</td>
                                        <td>${item.flowerMoney}</td>
                                        <td>${item.rocketMoney}</td>
                                        <td>${item.allMoney}</td>
                                    </tr>`;
                    });
                    $(".assetDetail-table table tbody").html(htmlStr);
                    if (typeof callback === 'function') {
                        callback(response.data);
                    }
                }
            }
        });
    }

    /**
     * 做分页查询
     */
    function bindPagerEventDetail(assetId,userNo,assetNo) {
        var currentPage = 1;

        $(".pageDetail .page-detail").each(function () {
            $(this).on("click", function (e) {
                var page = e.target.dataset.page;
                currentPage = page * 1;
                getAssetDetailAjax({
                    pageNo: currentPage,
                    assetId:assetId
                },userNo,assetNo,assetId, function (data) {
                    renderPagerDetail(data.totalPage, currentPage);
                    bindPagerEventDetail(assetId);
                });
            });
        });
        /**
         * 上一页
         */
        $(".pageDetail .prev-detail").on("click", function () {
            if (currentPage != 1) {
                currentPage--;
            }
            getAssetDetailAjax({
                pageNo: currentPage,
                assetId:assetId
            },userNo,assetNo,assetId, function (data) {
                renderPagerDetail(data.totalPage, currentPage);
                bindPagerEventDetail(assetId,userNo,assetNo);
            });
        });

        /**
         * 下一页
         */
        $(".pageDetail .next-detail").on("click", function () {
            currentPage++;
            getAssetDetailAjax({
                pageNo: currentPage,
                assetId:assetId
            },userNo,assetNo,assetId, function (data) {
                renderPagerDetail(data.totalPage, currentPage);
                bindPagerEventDetail(assetId,userNo,assetNo);
            });
        });
    }

    /**
     * 画出分页按钮
     * @param totalPage
     * @param pageSize
     * @param pageNo
     */
    function renderPagerDetail(totalPage, pageNo) {
        var pageHtml = "";
        for (var i = 0; i < totalPage; i++) {
            pageHtml += `
               <li><a href="javascript:;" class="page-detail ${i + 1 === pageNo ? "active" : ""}" data-page="${i + 1}">${i + 1}</a></li>
           `;
        }
        var htmlStr = `
         <nav class="pageDetail" aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="javascript:;" class="prev-detail" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        ${pageHtml}
                        <li>
                            <a href="javascript:;" class="next-detail" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
         </nav>
       `;
        $(".assetDetail-button").html(htmlStr);
    }

});