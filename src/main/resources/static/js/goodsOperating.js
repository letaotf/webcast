/**
 * 查询网络节目内容
 */
$(function() {
    init();
    //搜索事件
    buttonEvent();
    getGoodsOperatingAjax({
        pageNo: 1,
    }, function (data) {
        renderPager(data.totalPage, data.pageNo);
        bindPagerEvent();
        initRecord();
    });
    //添加商品
    addGoods();
    //处理商品在线状态
    dealGoodsOnlineStatus();
    //删除商品
    deleteGoods();
    //更新商品
    updateGoodsPage();

    function init() {
        $(".mulu").hide();
    }

    /**
     * 查询出投诉详情
     * @param queryParm
     * @param callback
     */
    function getGoodsOperatingAjax(queryParams, callback) {
        $(".goodsOperating-table table tbody").html("");
        $.ajax({
            method: "GET",
            url: "/goods/queryAllListGoods.json",
            data: $.extend({}, {
                pageSize: 10
            }, queryParams)
        }).done(function (response) {
            var htmlStr = "";
            if (Array.isArray(response.data.result)) {
                response.data.result.forEach(function (item) {
                    htmlStr += `<tr>
                    <td>${item.goodsNo}</td>
                    <td>${item.goodsName}</td>
                    <td>${item.goodsPrice}</td>
                    <td>${item.gmtModified}</td>               
                    <td>
                        ${distinguish(item)}&nbsp;&nbsp;&nbsp;
                        <a class="update-goods" data-goods-id=${item.goodsId}
                                                data-goods-name=${item.goodsName}
                                                data-goods-price=${item.goodsPrice}
                                                data-img-path=${item.imgPath}>修改</a>&nbsp;&nbsp;&nbsp;
                        <a class="delete-goods" data-goods-id=${item.goodsId}>删除</a>&nbsp;&nbsp;&nbsp;
                    </td>
                  </tr>`;
                });
                $(".goodsOperating-table table tbody").html(htmlStr);
                if (typeof callback === 'function') {
                    callback(response.data);
                }
            }
        });
    }
    
    function distinguish(data) {
        var parmHtml = "";
        if(data.goodsOnlineStatus === 1){
            parmHtml = `<a class="goods-soldAOut" data-goods-id=${data.goodsId}>下架</a>`;
        }else{
            parmHtml = `<a class="goods-putAway" data-goods-id=${data.goodsId}>上架</a>`;
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
                var goodsName = $(".goods-goodName").val();
                var goodsNo = $(".goods-goodNo").val();
                var date = $(".goods-time").val();
                var underPrice = $(".goods-underPrice").val();
                var upPrice = $(".goods-upPrice").val();
                getGoodsOperatingAjax({
                    pageNo: currentPage,
                    goodsName: goodsName === '' ? null : goodsName,
                    goodsNo: goodsNo === '' ? null : goodsNo,
                    underPrice: underPrice === '' ? null : underPrice,
                    date: date === '' ? null : date,
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
            var goodsName = $(".goods-goodName").val();
            var goodsNo = $(".goods-goodNo").val();
            var date = $(".goods-time").val();
            var underPrice = $(".goods-underPrice").val();
            var upPrice = $(".goods-upPrice").val();
            getGoodsOperatingAjax({
                pageNo: currentPage,
                goodsName: goodsName === '' ? null : goodsName,
                goodsNo: goodsNo === '' ? null : goodsNo,
                underPrice: underPrice === '' ? null : underPrice,
                date: date === '' ? null : date,
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
            var goodsName = $(".goods-goodName").val();
            var goodsNo = $(".goods-goodNo").val();
            var date = $(".goods-time").val();
            var underPrice = $(".goods-underPrice").val();
            var upPrice = $(".goods-upPrice").val();
            getGoodsOperatingAjax({
                pageNo: currentPage,
                goodsName: goodsName === '' ? null : goodsName,
                goodsNo: goodsNo === '' ? null : goodsNo,
                underPrice: underPrice === '' ? null : underPrice,
                date: date === '' ? null : date,
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
        $(".goodsOperating-button").html(htmlStr);
    }

    /**
     * 搜索按钮事件
     */
    function buttonEvent() {
        $(".search-goodsOperating").on("click", function () {
            var goodsName = $(".goods-goodName").val();
            var goodsNo = $(".goods-goodNo").val();
            var date = $(".goods-time").val();
            var underPrice = $(".goods-underPrice").val();
            var upPrice = $(".goods-upPrice").val();
            var queryParams = {
                pageNo: 1,
                goodsName: goodsName === '' ? null : goodsName,
                goodsNo: goodsNo === '' ? null : goodsNo,
                underPrice: underPrice === '' ? null : underPrice,
                date: date === '' ? null : date,
                upPrice: upPrice === '' ? null : upPrice,
            };
            getGoodsOperatingAjax(queryParams, function (data) {
                renderPager(data.totalPage, data.pageNo);
                bindPagerEvent();
            });
        });
    }

    /**
     * 初始化查询条件
     */
    function initRecord() {
        $(".goods-goodName").val('');
        $(".goods-goodNo").val('');
        $(".goods-time").val('');
        $(".goods-underPrice").val('');
        $(".goods-upPrice").val('');
    }

    /**
     * 添加节目类型
     */
    function addGoods() {
        $(document).on("click",".addGoods-success",function () {
            var goodsName = $(".addGoods-name-input").val();
            var goodsPrice = $(".addGoods-price-input").val();
            var imgPath =  $(".addGoods-img-textarea").val();
            getAddGoodsAjax({
                goodsName: goodsName === ''?null:goodsName,
                goodsPrice: goodsPrice ===''?null:goodsPrice,
                imgPath: imgPath===''?null:imgPath
            });
        });
    }

    /**
     * 添加节目post请求
     * @param queryParams
     */
    function getAddGoodsAjax(queryParams) {
        $.ajax({
            url:"/goods/addGoods.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("商品添加失败");
            }
        }).done(function (result) {
            if(result.data == true){
                alert("商品添加成功");
                //添加成功后,重新刷新页面
                var goodsName = $(".goods-goodName").val();
                var goodsNo = $(".goods-goodNo").val();
                var date = $(".goods-time").val();
                var underPrice = $(".goods-underPrice").val();
                var upPrice = $(".goods-upPrice").val();
                getGoodsOperatingAjax({
                    pageNo:1,
                    goodsName: goodsName === '' ? null : goodsName,
                    goodsNo: goodsNo === '' ? null : goodsNo,
                    underPrice: underPrice === '' ? null : underPrice,
                    date: date === '' ? null : date,
                    upPrice: upPrice === '' ? null : upPrice,
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
    function dealGoodsOnlineStatus() {
        $(document).on("click",".goods-putAway",function () {
            var goodsId = this.dataset.goodsId;
            getDealGoodsOnlineStatusAjax({
                goodsId:goodsId,
                goodsOnlineStatus:1,
            });
        });

        $(document).on("click",".goods-soldAOut",function () {
            var goodsId = this.dataset.goodsId;
            getDealGoodsOnlineStatusAjax({
                goodsId:goodsId,
                goodsOnlineStatus:0,
            });
        });
    }

    /**
     * 处理商品状态post请求
     * @param queryParams
     */
    function getDealGoodsOnlineStatusAjax(queryParams) {
        $.ajax({
            url:"/goods/dealGoodsOnlineStatus.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("处理失败");
            }
        }).done(function (result) {
            if(result.data == true){
                alert("处理成功");
                //添加成功后,重新刷新页面
                var goodsName = $(".goods-goodName").val();
                var goodsNo = $(".goods-goodNo").val();
                var date = $(".goods-time").val();
                var underPrice = $(".goods-underPrice").val();
                var upPrice = $(".goods-upPrice").val();
                getGoodsOperatingAjax({
                    pageNo:1,
                    goodsName: goodsName === '' ? null : goodsName,
                    goodsNo: goodsNo === '' ? null : goodsNo,
                    underPrice: underPrice === '' ? null : underPrice,
                    date: date === '' ? null : date,
                    upPrice: upPrice === '' ? null : upPrice,
                },function(data){
                    renderPager(data.totalPage,data.pageNo);
                    bindPagerEvent();
                });
            }
        });
    }

    /**
     * 删除商品
     */
    function deleteGoods() {
        $(document).on("click",".delete-goods",function () {
            var goodsId = this.dataset.goodsId;
            getDeleteGoodsAjax({
                goodsId:goodsId,
            });
        })
    }

    /**
     * 删除商品post请求
     * @param queryParams
     */
    function getDeleteGoodsAjax(queryParams) {
        $.ajax({
            url:"/goods/deleteGoods.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("处理失败");
            }
        }).done(function (result) {
            if(result.data == true){
                alert("处理成功");
                //添加成功后,重新刷新页面
                var goodsName = $(".goods-goodName").val();
                var goodsNo = $(".goods-goodNo").val();
                var date = $(".goods-time").val();
                var underPrice = $(".goods-underPrice").val();
                var upPrice = $(".goods-upPrice").val();
                getGoodsOperatingAjax({
                    pageNo:1,
                    goodsName: goodsName === '' ? null : goodsName,
                    goodsNo: goodsNo === '' ? null : goodsNo,
                    underPrice: underPrice === '' ? null : underPrice,
                    date: date === '' ? null : date,
                    upPrice: upPrice === '' ? null : upPrice,
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
    function updateGoodsPage() {
        $(document).on("click",".update-goods",function () {
            var goodsName = this.dataset.goodsName;
            var goodsPrice = this.dataset.goodsPrice;
            var imgPath = this.dataset.imgPath;
            var goodsId = this.dataset.goodsId;
            var  e = document.querySelector(".updateGoods-main");
            e.dataset.id = goodsId;
            $(".updateGoods-name-input").val(goodsName);
            $(".updateGoods-price-input").val(goodsPrice);
            $(".updateGoods-img-textarea").val(imgPath);
            $( ".updateGoods-main" ).dialog("open");
            updateGoods();
        });
    }

    /**
     *更新页面
     */
    function updateGoods() {
        $(document).on("click",".updateGoods-success",function () {
            var  e = document.querySelector(".updateGoods-main");
            var goodsId = e.dataset.id;
            var goodsName = $(".updateGoods-name-input").val();
            var goodsPrice = $(".updateGoods-price-input").val();
            var imgPath = $(".updateGoods-img-textarea").val();
            getUpdateGoodsAjax({
                goodsId:goodsId,
                goodsName:goodsName,
                goodsPrice:goodsPrice,
                imgPath:imgPath
            });
        });
    }

    /**
     * 删除商品post请求
     * @param queryParams
     */
    function getUpdateGoodsAjax(queryParams) {
        $.ajax({
            url:"/goods/updateGoods.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("更新商品失败");
            }
        }).done(function (result) {
            if(result.data == true){
                alert("更新商品成功");
                //添加成功后,重新刷新页面
                var goodsName = $(".goods-goodName").val();
                var goodsNo = $(".goods-goodNo").val();
                var date = $(".goods-time").val();
                var underPrice = $(".goods-underPrice").val();
                var upPrice = $(".goods-upPrice").val();
                getGoodsOperatingAjax({
                    pageNo:1,
                    goodsName: goodsName === '' ? null : goodsName,
                    goodsNo: goodsNo === '' ? null : goodsNo,
                    underPrice: underPrice === '' ? null : underPrice,
                    date: date === '' ? null : date,
                    upPrice: upPrice === '' ? null : upPrice,
                },function(data){
                    renderPager(data.totalPage,data.pageNo);
                    bindPagerEvent();
                });
            }
        });
    }

});
