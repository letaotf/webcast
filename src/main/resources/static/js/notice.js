$(function () {
    //添加公告
    addBroadcastType();
    //
    init();

    function init() {
        $(".mulu").hide();
    }

    /**
     * 添加节目类型
     */
    function addBroadcastType() {
        $(document).on("click",".notice-success-button",function () {
            var title = $(".notice-title-input").val();
            var content = $(".notice-content-textarea").val();
            getAddNoticeAjax({
                title: title ===''?null:title,
                content: content===''?null:content,
            });
        });
    }

    /**
     * 添加节目post请求
     * @param queryParams
     */
    function getAddNoticeAjax(queryParams) {
        $.ajax({
            url:"/notice/addNotice.json",
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(queryParams),
            error:function(result){
                alert("公告添加失败");
            }
        }).done(function (result) {
            if(result.data == true) {
                alert("公告添加成功");
            }
        });
    }





});