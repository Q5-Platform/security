/**
 * Created by houcunlu on 2017/8/24.
 */


/**
 * 登陆
 */
$("#submit").click(function (){
    // 账号
    var userName =   $.trim($("input[name=userName]").val());
    // 密码
    var userPwd =   $.trim($("input[name=userPwd]").val());

    if(isNull(userName)){
        hint("账号不可为空！");
        return;
    }

    if(isNull(userPwd)){
        hint("密码不可为空！");
        return;
    }

    // 参数
    var  params = {
        userName:userName ,
        userPwd:userPwd
    }
    // 登陆
    var loginUrl = security + securityLogin;
    comment.post( loginUrl  , JSON.stringify(params)  , "登陆中，，，", function (res) {
        if (res.res.code == 40000) {
            var obj = res.res.data;
            sessionStorage.setItem("token" ,obj.toKen );
            sessionStorage.setItem("userId" ,obj.userId );
            // 跳转首页
            location.href = "security/html/index.html";
        }
    });
})
