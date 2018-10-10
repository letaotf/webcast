/**
 * 查询网络节目内容
 */
$(function(){
    //搜索事件
    buttonEvent();
    //初始化条件
    initRecord();
    getNetworkBroadcastTypeAjax({
        pageNo:1,
    },function(data){
        renderPager(data.totalPage,data.pageNo);
        bindPagerEvent();
        //节目详情
        //broadcastTypeDetail();
    });
    //删除节目列表
    deleteBroadcastType();
    //添加节目
    addBroadcastType();
    //更新节目
    updateBroadcastPage();

    init();

    function init() {
        $(".mulu").hide();
    }

    /**
     * 查询出网络节目内容详情
     * @param queryParm
     * @param callback
     */
    function getNetworkBroadcastTypeAjax(queryParams,callback){
        $(".broadcastType-table table tbody").html("");
        $.ajax({
            method:"GET",
            url:"/networkBroadcastType/queryNetworkBroadcastType.json",
            data:$.extend({},{
                pageSize: 10
            },queryParams)
        }).done(function(response){
            var htmlStr = "";
            if(Array.isArray(response.data.result)){
                response.data.result.forEach(function(item){
                    htmlStr +=`<tr>
                    <td><span class="network-type-no" data-network-id=${item.networkBroadcastTypeDetailId}>${item.networkTypeNo}</span></td>
                    <td><span>${item.typeDetailName}</span> </td>
                    <td><span>${item.briefIntroduction}</span></td>
                    <td>${item.gmtCreate}</td>
                    <td>
                       <a class="update-broadcastType" data-network-id=${item.networkBroadcastTypeDetailId} 
                                                       data-type-id=${item.typeId} 
                                                       data-type-detail-name=${item.typeDetailName} 
                                                       data-brief-introduction=${item.briefIntroduction}>修改</a>&nbsp;&nbsp;&nbsp;
                       <a class="delete-broadcastType" data-network-id=${item.networkBroadcastTypeDetailId}>删除</a>
                    </td>
                  </tr>`;
                });
                $(".broadcastType-table table tbody").html(htmlStr);
                broadcastTypeDetail()
                if(typeof callback === 'function'){
                    callback(response.data);
                }
            }
        });
    }

    /**
     * 初始化查询条件
     */
    function initRecord() {
        $(".broadcastType").val("");
    }

    /**
     * 做分页查询
     */
    function bindPagerEvent(){
        var currentPage = 1;
        $(".pager .page").each(function(){
            $(this).on("click",function(e){
                var page=e.target.dataset.page;
                currentPage=page*1;
                var broadcastType = $(".broadcastType").val();
                getNetworkBroadcastTypeAjax({
                    pageNo:currentPage,
                    typeId:broadcastType ===0?null:broadcastType
                },function(data){
                    renderPager(data.totalPage,currentPage);
                    bindPagerEvent();
                });
            });
        });
        /**
         * 上一页
         */
        $(".pager .prev").on("click",function() {
            if (currentPage != 1) {
                currentPage--;
            }
            var broadcastType = $(".broadcastType").val();
            getNetworkBroadcastTypeAjax({
                pageNo:currentPage,
                typeId:broadcastType ===0?null:broadcastType
            },function(data){
                renderPager(data.totalPage,currentPage);
                bindPagerEvent();
            });
        });

        /**
         * 下一页
         */
        $(".pager .next").on("click",function(){
            currentPage++;
            var broadcastType = $(".broadcastType").val();
            getNetworkBroadcastTypeAjax({
                pageNo:currentPage,
                typeId:broadcastType ===0?null:broadcastType
            },function(data){
                renderPager(data.totalPage,currentPage);
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
    function renderPager(totalPage,pageNo){
        var pageHtml="";
        for(var i=0;i<totalPage;i++){
            pageHtml+=`
               <li><a href="javascript:;" class="page ${i+1 === pageNo?"active":""}" data-page="${i+1}">${i+1}</a></li>
           `;
        }
        var htmlStr= `
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
        $(".broadcastType-button").html(htmlStr);
    }

    /**
     * 搜索按钮事件
     */
    function buttonEvent() {

        $(".search-broadcastType").on("click",function(){
            var broadcastType = $(".broadcastType").val();
            broadcastType = broadcastType*1;
            var queryParams = {
                pageNo:1,
                typeId:broadcastType ===0?null:broadcastType
            };
            getNetworkBroadcastTypeAjax(queryParams,function(data){
                renderPager(data.totalPage,data.pageNo);
                bindPagerEvent();
            });
        });
    }

    /**
     * 添加节目类型
     */
    function addBroadcastType() {
        $(document).on("click",".saveBroadcastType",function () {
            var typeId = $(".select-type").val();
            var typeDetailName = $(".typeDetailName").val();
            var briefIntroduction = $(".briefIntroduction").val();
            getAddBroadcastAjax({
                typeId: typeId === ''?null:typeId,
                typeDetailName: typeDetailName ===''?null:typeDetailName,
                briefIntroduction: briefIntroduction===''?null:briefIntroduction
            });
            $( ".addBroadcastType-main" ).dialog("close");
        });
    }

    /**
     * 添加节目post请求
     * @param queryParams
     */
    function getAddBroadcastAjax(queryParams) {
        $.ajax({
            url:"/networkBroadcastType/addNetworkBroadcastType.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("节目添加失败");
            }
        }).done(function (result) {
            if(result.data == true){
                alert("节目添加成功");
                //添加成功后,重新刷新页面
                var broadcastType = $(".broadcastType").val();
                getNetworkBroadcastTypeAjax({
                    pageNo:1,
                    typeId:broadcastType ===0?null:broadcastType
                },function(data){
                    renderPager(data.totalPage,data.pageNo);
                    bindPagerEvent();
                });
            }
        });
    }

    /**
     * 删除一条节目类型
     */
    function deleteBroadcastType() {
        $(document).on("click",".delete-broadcastType",function () {
            var networkBroadcastTypeDetailId = this.dataset.networkId;
            getDeleteBroadcastAjax({
                networkBroadcastTypeDetailId:networkBroadcastTypeDetailId,
            });
        });
        getNetworkBroadcastTypeAjax({
            pageNo:1,
        },function (data) {
            renderPager(data.totalPage,data.pageNo);
            bindPagerEvent();
        });
    }

    /**
     * 删除节目post请求
     * @param queryParams
     */
    function getDeleteBroadcastAjax(queryParams) {
        $.ajax({
            url:"/networkBroadcastType/deleteNetworkBroadcastType.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("删除节目失败");
            }
        }).done(function (result) {
            if(result.data == true){
                alert("删除节目成功");
                //添加成功后,重新刷新页面
                var broadcastType = $(".broadcastType").val();
                getNetworkBroadcastTypeAjax({
                    pageNo:1,
                    typeId:broadcastType ===0?null:broadcastType
                },function(data){
                    renderPager(data.totalPage,currentPage);
                    bindPagerEvent();
                });
            }
        });
    }

    /**
     * 更新节目页面
     */
    function updateBroadcastPage() {
        $(document).on("click",".update-broadcastType",function () {
            var typeDetailName = this.dataset.typeDetailName;
            var briefIntroduction = this.dataset.briefIntroduction;
            var typeId = this.dataset.typeId;
            var networkBroadcastTypeDetailId = this.dataset.networkId;
            var  e = document.querySelector(".updateBroadcastType");
            e.dataset.id = networkBroadcastTypeDetailId;
            $(".update-select-type").val(typeId);
            $(".updateTypeDetailName").val(typeDetailName);
            $(".updatebriefIntroduction").val(briefIntroduction);
            $(".updateBroadcastType-main").dialog("open");
            updateBroadcastType();
        });
    }

    /**
     * 更新一条记录
     */
    function updateBroadcastType() {
        $(document).on("click",".updateBroadcastType",function () {
            var  e = document.querySelector(".updateBroadcastType");
            var networkBroadcastTypeDetailId = e.dataset.id;
            var typeId = $(".update-select-type").val();
            var typeDetailName = $(".updateTypeDetailName").val();
            var briefIntroduction = $(".updatebriefIntroduction").val();
            getUpdateBroadcastAjax({
                networkBroadcastTypeDetailId:networkBroadcastTypeDetailId,
                typeId:typeId,
                typeDetailName:typeDetailName,
                briefIntroduction:briefIntroduction
            });
            $(".updateBroadcastType-main").dialog("close");
        });
    }



    /**
     * 删除节目post请求
     * @param queryParams
     */
    function getUpdateBroadcastAjax(queryParams) {
        $.ajax({
            url:"/networkBroadcastType/updateNetworkBroadcastType.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("更新节目失败");
            }
        }).done(function (result) {
            if(result.data == true){
                alert("更新节目成功");
                //添加成功后,重新刷新页面
                var broadcastType = $(".broadcastType").val();
                getNetworkBroadcastTypeAjax({
                    pageNo:1,
                    typeId:broadcastType ===0?null:broadcastType
                },function(data){
                    renderPager(data.totalPage,data.pageNo);
                    bindPagerEvent();
                });
            }
        });
    }

    var flag = ''
    /**
     * 节目详情
     */
    function broadcastTypeDetail() {
        //hover

        $(".network-type-no").on("mouseenter",function () {
            var networkBroadcastTypeDetailId = this.dataset.networkId;
            if(networkBroadcastTypeDetailId===flag){
                return
            }
            flag = networkBroadcastTypeDetailId
            getNetworkBroadcastTypeDetailAjax({
                networkBroadcastTypeDetailId:networkBroadcastTypeDetailId,
            });
            $(".broadcastTypeDetail-main").dialog("open");
        });
        $(".network-type-no").on("mouseleave",function () {

            $(".broadcastTypeDetail-main").dialog("close");
        });

        // $(".network-type-no").hover(function () {
        //
        //     console.log(123)
        //
        //     var networkBroadcastTypeDetailId = this.dataset.networkId;
        //     if(networkBroadcastTypeDetailId===flag){
        //         return
        //     }
        //     flag = networkBroadcastTypeDetailId
        //     getNetworkBroadcastTypeDetailAjax({
        //         networkBroadcastTypeDetailId:networkBroadcastTypeDetailId,
        //     });
        //     $(".broadcastTypeDetail-main").dialog("open");
        // },function () {
        //     $(".broadcastTypeDetail-main").dialog("close");
        // });

    }

    /**
     * 查询出网络节目内容详情
     * @param queryParm
     * @param callback
     */
    function getNetworkBroadcastTypeDetailAjax(queryParams){
        $.ajax({
            method:"GET",
            url:"/networkBroadcastType/queryNetworkBroadcastTypeDetail.json",
            async:false,
            data:$.extend({},{
            },queryParams)
        }).done(function(result){
            if(result.data != null){
                $(".network-no").val(result.data.networkTypeNo);
                $(".select-type-detail").val(result.data.typeId);
                $(".updateTypeDetailName").val(result.data.typeDetailName);
                $(".briefIntroductionDetail").val(result.data.briefIntroduction);
                $(".broadcastType-detail-time").val(result.data.gmtCreate);
            }
        })
    }

});