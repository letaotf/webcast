$(function(){
    //ajax方式提交时，加入随机数
    ajaxRandom();
    //A标签加入随机数
    aRandom();
    //form加入随机数
    formRandom();
});

function formRandom(){
    $("form").submit(function(e){
        rand=getRandom(15,62);
        action=$(this).attr("action");
        if(action==null || action.length==0||action.indexOf("javascript")>-1||action.indexOf("#")>-1){
        }else if(action.indexOf("?")>-1){
            $(this).attr("action",action+"?"+rand);
        }else{
            $(this).attr("action",action+"?"+rand);
        }
    });
}
function aRandom(){
    $("a").each(function(){
        rand=getRandom(15,62);
        href=$(this).attr("href");
        if(href==null||href.length==0||href.indexOf("javascript")>-1||href.indexOf("#")>-1){
            return;
        }else if(href.indexOf("?")>-1){
            $(this).attr("href",href+"?"+rand);
        }else{
            $(this).attr("href",href+"?"+rand);
        }
    });
}
var temp_ajax=$.ajax;
function ajaxRandom(){
    $.ajax=function(args){
        rand=getRandom(15,62);
        var randomURL='';
        if(args.url.indexOf("?")>-1){
            randomURL=args.url+"?"+rand;
        }else{
            randomURL=args.url+"?"+rand;
        }
        args.url=randomURL;
        temp_ajax(args);
    };
}

//生成随机数 len是长度 radix是chars的随机范围。如果radix为2就是前两位字符范围类随机 .如果radix为10，就是前10位内随机，
//例: len=5 radix=10 (0~9)   radix=16(0~F) radix=62(0~z)
function getRandom(len, radix) {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = [], i;
    //如果不填radix就是chars的长度
    radix = radix || chars.length;
    if (len) {
        for (i = 0; i < len; i++){
            uuid[i] = chars[0 | Math.random()*radix];
        }
    } else {
        var r;
        uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
        uuid[14] = '4';
        for (i = 0; i < 36; i++) {
            if (!uuid[i]) {
                r = 0 | Math.random()*16;
                uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
            }
        }
    }
    return uuid.join('');
}

function redirect(url){
    rand=getRandom(15,62);
    if(url.indexOf("?")>-1){
        url=url+"?"+rand;
    }else{
        url=url+"?"+rand;
    }
    window.location.href=url;
}


