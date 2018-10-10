/**
 * 查询网络主播详情
 */
$(function(){
    init();
    //按钮事件
    buttonEvent();

    getApplyRecordAjax({
        pageNo:1
    },function(data){
        //画出按钮
        renderPager(data.totalPage,data.pageSize,data.pageNo);
        //分页绑定
        bindPagerEvent();
        //刷新页面的时候不保存input中的值
        setInitInput();
    });

    //审核请求post
    setOperatorStatus();

    /**
     * ajax 请求
     * @param queryParams
     * @param callback
     */
   function getApplyRecordAjax(queryParams,callback){
       $("#user-table table tbody").html("");
       $.ajax({
           method:"GET",
           url:"/anchor/queryAnchorApplyRecord.json",
           data:$.extend({},{
               pageSize: 20
           },queryParams)
       }).done(function(response){
           var htmlStr="";
          if(Array.isArray(response.data.result)){
              response.data.result.forEach(function (item) {
                 htmlStr+= `<tr>
                        <td>${item.userNo}</td>
                        <td>${item.userName}</td>
                        <td>${item.userSexDesc}</td>
                        <td>${item.typeDetailName}</td>
                        <td>${item.userTel}</td>
                        <td>${item.identityCard}</td>
                        <td>${item.gmtCreate}</td>
                        ${distinguish(item)}
                  </tr>`;
              });
              $("#user-table table tbody").html(htmlStr);
              if(typeof callback === 'function'){
                  callback(response.data);
              }
          }
       });
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
              var userName = $(".user-name").val();
              var userNo = $(".user-no").val();
              var applyTime = $(".user-time").val();
              var auditStatus = $(".status-select").val();
              getApplyRecordAjax({
                  pageNo:currentPage,
                  userName:userName ===''?null:userName,
                  userNO:userNo === ''?null: userNo,
                  gmtCreate:applyTime === '' ?null:applyTime,
                  auditStatus:auditStatus === '' ?null:auditStatus
              },function(data){
                  renderPager(data.totalPage,data.pageSize,currentPage);
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
           var userName = $(".user-name").val();
           var userNo = $(".user-no").val();
           var applyTime = $(".user-time").val();
           var auditStatus = $(".status-select").val();
           getApplyRecordAjax({
               pageNo:currentPage,
               userName:userName ===""?null:userName,
               userNO:userNo === ''?null: userNo,
               gmtCreate:applyTime === '' ?null:applyTime,
               auditStatus:auditStatus === '' ?null:auditStatus
           },function(data){
               renderPager(data.totalPage,data.pageSize,currentPage);
               bindPagerEvent();
           });
       });

        /**
         * 下一页
         */
        $(".pager .next").on("click",function(){
           currentPage++;
            var userName = $(".user-name").val();
            var userNo = $(".user-no").val();
            var applyTime = $(".user-time").val();
            var auditStatus = $(".status-select").val();
           getApplyRecordAjax({
               pageNo:currentPage,
               userName:userName ===''?null:userName,
               userNO:userNo === ''?null: userNo,
               gmtCreate:applyTime === '' ?null:applyTime,
               auditStatus:auditStatus === '' ?null:auditStatus
           },function(data){
               renderPager(data.totalPage,data.pageSize,currentPage);
               bindPagerEvent();
           });
       });

   }

    /**
     *  按钮事件
     */
   function buttonEvent(){

       $(".query").on("click",function(){
           var userName = $(".user-name").val();
           var userNo = $(".user-no").val();
           var applyTime = $(".user-time").val();
           var auditStatus = $(".status-select").val();
           var  queryParams={
               pageNo:1,
               userName:userName ===''?null:userName,
               userNO:userNo === ''?null: userNo,
               gmtCreate:applyTime === '' ?null:applyTime,
               auditStatus:auditStatus === '' ?null:auditStatus
           };
           getApplyRecordAjax(queryParams,function(data){
               renderPager(data.totalPage,data.pageSize,currentPage);
               bindPagerEvent();
           });

       });
   }

    /**
     * 区分通过与不通过按钮
     * @param result
     */
   function distinguish(data) {
        var parmHtml = "";
        if(data.auditStatus === 1){
            parmHtml = `<td><a class="noPass" data-user-id=${data.userId}>不通过</a></td>`;
        }else{
            parmHtml = `<td><a class="pass" data-user-id=${data.userId}>通过</a></td>`;
        }
        return parmHtml;
   }


    /**
     * 画出分页按钮
     * @param totalPage
     * @param pageSize
     * @param pageNo
     */
   function renderPager(totalPage,pageSize,pageNo){
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
       $("#pager-wrapper").html(htmlStr);
   }

    /**
     * 审核请求 post
     */
    function getPostAuditStatus(queryParams) {
        $.ajax({
            url:"/anchor/auditAnchor.json",
            type:"POST",
            contentType : "application/json;charset=UTF-8",
            <!--向后端传递数据-->
            data : JSON.stringify(queryParams),
            error:function (result) {
                alert("操作失败");
            }
        }).done(function(result){
            if(result.data === true){
                alert("操作成功");
                var userName = $(".user-name").val();
                var userNo = $(".user-no").val();
                var applyTime = $(".user-time").val();
                var auditStatus = $(".status-select").val();
                getApplyRecordAjax({
                    pageNo:1,
                    userName:userName ===''?null:userName,
                    userNO:userNo === ''?null: userNo,
                    gmtCreate:applyTime === '' ?null:applyTime,
                    auditStatus:auditStatus === '' ?null:auditStatus
                },function(data){
                    renderPager(data.totalPage,data.pageSize,data.pageNo);
                    bindPagerEvent();
                });
            }
        });
    }

    /**
     * 设置操作状态（通过或不通过）
     * @param result
     */
    function setOperatorStatus() {

        $(document).on("click",".pass",function () {
            var userId=this.dataset.userId;
            getPostAuditStatus({
                 auditStatus: 1,
                 userId:userId
            });
        });

        $(document).on("click",".noPass",function () {
            var userId=this.dataset.userId;
            getPostAuditStatus({
                auditStatus: 0,
                userId:userId
            });
        });
    }

    /**
     * 设置输入框的值为空
     */
    function setInitInput() {
        $(".user-name").val('');
        $(".user-no").val('');
        $(".user-time").val('');
        $(".status-select").val('');
    }

    function init() {
        $(".mulu").hide();
    }
});

