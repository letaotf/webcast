/**
 * 查询网络节目内容
 */
$(function() {
    //搜索事件
    buttonEvent();
    getIntergityAjax({
        pageNo: 1,
    }, function (data) {
        renderPager(data.totalPage, data.pageNo);
        bindPagerEvent();
        initRecord();
    });
    //更新数据
    updateIntergityValuesPage();
    //
    init();

    function init() {
        $(".mulu").hide();
    }

    /**
     * 查询出网络节目内容详情
     * @param queryParm
     * @param callback
     */
    function getIntergityAjax(queryParams, callback) {
        $(".intergityValues-table table tbody").html("");
        $.ajax({
            method: "GET",
            url: "/intergitValues/queryIntergitValuesList.json",
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
                    <td>${item.userTypeDesc}</td>
                    <td>${item.userTel}</td>
                    <td>${item.value}</td>
                    <td><a class="update-intergity" data-intergity-id=${item.intergityId} data-value=${item.value}>修改</a></td>
                  </tr>`;
                });
                $(".intergityValues-table table tbody").html(htmlStr);
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
                var userNO = $(".intergityValues-userNO").val();
                var userType = $(".intergityValues-select").val();
                var userName = $(".intergityValues-userName").val();
                var upValue = $(".intergityValues-upValue").val();
                var underValue = $(".intergityValues-underValue").val();
                getIntergityAjax({
                    pageNo: currentPage,
                    userNO: userNO === '' ? null : userNO,
                    userName: userName === '' ? null : userName,
                    userType: userType === '' ? null : userType,
                    upValue: upValue === '' ? null : upValue,
                    underValue: underValue === '' ? null : underValue,
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
            var userNO = $(".intergityValues-userNO").val();
            var userType = $(".intergityValues-select").val();
            var userName = $(".intergityValues-userName").val();
            var upValue = $(".intergityValues-upValue").val();
            var underValue = $(".intergityValues-underValue").val();
            getIntergityAjax({
                pageNo: currentPage,
                userNO: userNO === '' ? null : userNO,
                userName: userName === '' ? null : userName,
                userType: userType === '' ? null : userType,
                upValue: upValue === '' ? null : upValue,
                underValue: underValue === '' ? null : underValue,
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
            var userNO = $(".intergityValues-userNO").val();
            var userType = $(".intergityValues-select").val();
            var userName = $(".intergityValues-userName").val();
            var upValue = $(".intergityValues-upValue").val();
            var underValue = $(".intergityValues-underValue").val();
            getIntergityAjax({
                pageNo: currentPage,
                userNO: userNO === '' ? null : userNO,
                userName: userName === '' ? null : userName,
                userType: userType === '' ? null : userType,
                upValue: upValue === '' ? null : upValue,
                underValue: underValue === '' ? null : underValue,
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
        $(".intergityValues-button").html(htmlStr);
    }

    /**
     * 搜索按钮事件
     */
    function buttonEvent() {
        $(".search-intergityValues").on("click", function () {
            var userNO = $(".intergityValues-userNO").val();
            var userType = $(".intergityValues-select").val();
            var userName = $(".intergityValues-userName").val();
            var upValue = $(".intergityValues-upValue").val();
            var underValue = $(".intergityValues-underValue").val();
            var queryParams = {
                pageNo: 1,
                userNO: userNO === '' ? null : userNO,
                userName: userName === '' ? null : userName,
                userType: userType === '' ? null : userType,
                upValue: upValue === '' ? null : upValue,
                underValue: underValue === '' ? null : underValue,
            };
            getIntergityAjax(queryParams, function (data) {
                renderPager(data.totalPage, data.pageNo);
                bindPagerEvent();
            });
        });
    }

    /**
     * 初始化查询条件
     */
    function initRecord() {
       $(".intergityValues-userNO").val('');
       $(".intergityValues-select").val('');
       $(".intergityValues-userName").val('');
       $(".intergityValues-upValue").val('');
       $(".intergityValues-underValue").val('');
    }

    /**
     * 更新诚信值
     */
    function updateIntergityValuesPage() {
        $(document).on("click",".update-intergity",function () {
            var intergityId = this.dataset.intergityId;
            var value = this.dataset.value;
            var  e = document.querySelector(".update-intergityValues");
            e.dataset.id = intergityId;
            $(".update-intergityValues-input").val(value);
            $( ".update-intergityValues" ).dialog("open");
            updateIntergityValues();
        });
    }

    /**
     *更新页面
     */
    function updateIntergityValues() {
        $(document).on("click",".update-intergityValues-button",function () {
            var  e = document.querySelector(".update-intergityValues");
            var intergityId = e.dataset.id;
            var value = $(".update-intergityValues-input").val();
            getUpdateIntergityAjax({
                intergityId:intergityId,
                value:value,
            });
            $(".update-intergityValues").dialog("close");
        });
    }

    /**
     * 更新诚信值
     * @param queryParams
     */
    function getUpdateIntergityAjax(queryParams) {
        $.ajax({
            url:"/intergitValues/updateIntergitValues.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("更新诚信值失败");
            }
        }).done(function (result) {
            if(result.data == true){
                alert("更新诚信值成功");
                //添加成功后,重新刷新页面
                var userNO = $(".intergityValues-userNO").val();
                var userType = $(".intergityValues-select").val();
                var userName = $(".intergityValues-userName").val();
                var upValue = $(".intergityValues-upValue").val();
                var underValue = $(".intergityValues-underValue").val();
                getIntergityAjax({
                    pageNo:1,
                    userNO: userNO === '' ? null : userNO,
                    userName: userName === '' ? null : userName,
                    userType: userType === '' ? null : userType,
                    upValue: upValue === '' ? null : upValue,
                    underValue: underValue === '' ? null : underValue,
                },function(data){
                    renderPager(data.totalPage,data.pageNo);
                    bindPagerEvent();
                });
            }
        });
    }

});