$(function () {
    init();
    //搜索事件
    buttonEvent();
    getGoodsDetailAjax({
        pageNo: 1,
    }, function (data) {
        renderPager(data.totalPage, data.pageNo);
        bindPagerEvent();
        initRecord();
    });

    function init() {
        $(".mulu").hide();
    }

    /**
     * 查询出投诉详情
     * @param queryParm
     * @param callback
     */
    function getGoodsDetailAjax(queryParams, callback) {
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
                htmlStr += `<div class="goodsDetail-list-div">
                                <div class="goodsDetail-list-img">
                                    <img src="img/rocket.jpg" width="224" height="200"/>
                                </div>
                                <div class="goodsDetail-list-goodsName">
                                    <span>商品名称:</span>
                                    <input value=${item.goodsName} disabled="disabled" "/>
                                </div>
                                <div class="goodsDetail-list-goodsPrice">
                                    <span>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格:</span>
                                    <input value=${item.goodsPrice}￥ disabled="disabled" width="150px"/>
                                </div>
                                <div class="goodsDetail-list-gmtModified">
                                    <span>上架时间:</span>
                                    <input value=${item.gmtModified} disabled="disabled" width="150px"/>
                                </div>
                            </div>`;
                });
                $(".goodsDetail-list").html(htmlStr);
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
                var goodsName = $(".goodsDetail-name").val();
                var time = $(".goodsDetail-time").val();
                var underPrice = $(".goodsDetail-underPrice").val();
                var upPrice = $(".goodsDetail-upPrice").val();
                getGoodsDetailAjax({
                    pageNo: currentPage,
                    goodsName: goodsName === '' ? null : goodsName,
                    time: time === '' ? null : time,
                    underPrice: underPrice === '' ? null : underPrice,
                    upPrice: upPrice === '' ? null : upPrice,
                }, function (data) {
                    renderPager(data.totalPage, data.pageNo);
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
            var goodsName = $(".goodsDetail-name").val();
            var time = $(".goodsDetail-time").val();
            var underPrice = $(".goodsDetail-underPrice").val();
            var upPrice = $(".goodsDetail-upPrice").val();
            getGoodsDetailAjax({
                pageNo: currentPage,
                goodsName: goodsName === '' ? null : goodsName,
                underPrice: underPrice === '' ? null : underPrice,
                time: time === '' ? null : time,
                upPrice: upPrice === '' ? null : upPrice,
            }, function (data) {
                renderPager(data.totalPage, data.pageNo);
                bindPagerEvent();
            });
        });

        /**
         * 下一页
         */
        $(".pager .next").on("click", function () {
            currentPage++;
            var goodsName = $(".goodsDetail-name").val();
            var time = $(".goodsDetail-time").val();
            var underPrice = $(".goodsDetail-underPrice").val();
            var upPrice = $(".goodsDetail-upPrice").val();
            getGoodsDetailAjax({
                pageNo: currentPage,
                goodsName: goodsName === '' ? null : goodsName,
                underPrice: underPrice === '' ? null : underPrice,
                time: time === '' ? null : time,
                upPrice: upPrice === '' ? null : upPrice,
            }, function (data) {
                renderPager(data.totalPage, data.pageNo);
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
       // $(".goodsDetail-button").html(htmlStr);
    }

    /**
     * 搜索按钮事件
     */
    function buttonEvent() {
        $(".goodsDetail-search").on("click", function () {
            var goodsName = $(".goodsDetail-name").val();
            var time = $(".goodsDetail-time").val();
            var underPrice = $(".goodsDetail-underPrice").val();
            var upPrice = $(".goodsDetail-upPrice").val();
            var queryParams = {
                pageNo: 1,
                goodsName: goodsName === '' ? null : goodsName,
                underPrice: underPrice === '' ? null : underPrice,
                time: time === '' ? null : time,
                upPrice: upPrice === '' ? null : upPrice,
            };
            getGoodsDetailAjax(queryParams, function (data) {
                renderPager(data.totalPage, data.pageNo);
                bindPagerEvent();
            });
        });
    }

    /**
     * 初始化查询条件
     */
    function initRecord() {
       $(".goodsDetail-name").val('');
       $(".goodsDetail-time").val('');
       $(".goodsDetail-underPrice").val('');
       $(".goodsDetail-upPrice").val('');
    }

});