/**
 * Created by houcunlu on 2017/8/31.
 */

/**
 * 页面加载
 */
$(function(){
    // token
    var  token = sessionStorage.getItem("token");
    if(token == null || token == "" ){
        hint("请重新登陆！");
        setTimeout(function(){
            location.href = "../../../index.html";
        } , 3000);
        return;
    }

    // 初始化table
    var table = $("#table");
    var url = ysscaleBackUrl + ysscaleBack_findAlluserPage+"?token="+token;
    table.bootstrapTable("refresh", {url: url});

    // 关闭侧滑
    slider();
})





/**
 *  图片
 **/
picture = function (value, row, index) {
    var img = row.icon;
    var imgUrl = "";
    if (img != null && img != '') {
        imgUrl = File_Download_Url + img;
    }
    return [
        '<img src=' + imgUrl + ' style="height:50px;width: 50px;" />'
    ].join('');
}






operateFormatter = function (value, row, index) {
    return [
        '<a class="edit" href="javascript:void(0)" title="编辑" data-toggle="slider" data-target="#upUserDiv" ><i class="glyphicon glyphicon-pencil" ></i></a>' +
        '&nbsp;&nbsp;&nbsp;'+
        '<a class="delete" href="javascript:void(0)" title="删除用户" data-target="#delUserModal" data-to="modal" ><i class="glyphicon glyphicon-remove-sign"></i></a>' +
        '&nbsp;&nbsp;&nbsp;'+
        '<a class="list" href="javascript:void(0)" title="电子秤列表" data-toggle="slider" data-target="#tableDiv" ><i class="glyphicon glyphicon-hdd"></i></a>' +
        '&nbsp;&nbsp;&nbsp;'

    ].join('');

}


window.operateEvents = {
    'click .edit': function (e, value, row, index) {
        $("#upUserInfoDiv").toggleClass('slide-open');
        setRowToFrm("upUserInfoForm",row);
        var url = File_Download_Url + row.icon;
        $("#upUserPhoto").attr("src","");
        $("#upUserPhoto").attr("img-path","");
        $("#upUserPhoto").attr("src",url);
        $("#upUserPhoto").attr("img-path",row.icon);

        loadUpStore(row.storeId);
    }, 'click .delete': function (e, value, row, index) {
        $("#delId").val(row.id);
    }, 'click .list': function (e, value, row, index) {


        var token = comment.getToken();
        // 初始化table
        var table = $("#balanceTable");
        var url = ysscaleBackUrl + ysscaleBack_findUserBalance+"?token="+token+"&userId="+row.id;
        table.bootstrapTable("refresh", {url: url});


    }
};



function  loadUpStore( storeId ){
// token
    var token =   comment.getToken();
    var url = ysscaleBackUrl + ysscaleBack_findListStore+"?token="+token;
    var  params = {
    }
    comment.post( url  , params , '加载中。。。', function (data) {
        var option = $("#upSelect");
        option.empty();
        if (data.state && data.res.code == 40000) {
            var objData =  JSON.parse(data.res.data);
            for(var i = 0; i< objData.length ; i++){
                var obj = objData[i];
                if(obj.id == storeId){
                    option.append('<option selected="selected"  value="' + obj.id + '">' + obj.name + '</option>');
                }else{
                    option.append('<option  value="' + obj.id + '">' + obj.name + '</option>');
                }
            }
        } else {
            option.append('<option  value="">店铺加载失败!</option>');
        }
    });


}












/**
 * 添加用户
 */
$("#addAdmin").on('click',  function() {
    $("#addUserDiv").toggleClass('slide-open');

    $("#addUserName").val("");
    $("#addUserPwd").val("");
    $("#addName").val("");
    $("#addMobile").val("");

    $("#addIcon").attr("src","../../../icon/img_add.png");
    $("#addIcon").attr("img-url","");
    $("#addIcon").attr("img-path","");




    // token
    var token =   comment.getToken();
    var url = ysscaleBackUrl + ysscaleBack_findListStore+"?token="+token;
    var  params = {
    }
    comment.post( url  , params , '加载中。。。', function (data) {
        var option = $("#AddiconSelect");
        option.empty();
        option.append('<option  selected="selected"  value="">请选择店铺</option>');
        if (data.state && data.res.code == 40000) {
            var objData =  JSON.parse(data.res.data);
            for(var i = 0; i< objData.length ; i++){
                var obj = objData[i];
                option.append('<option  value="' + obj.id + '">' + obj.name + '</option>');
            }
        } else {
            option.append('<option  value="">店铺加载失败!</option>');
        }
    });




})


/**
 * 添加用户
 */
$("#addUser-btn").on('click',  function() {

    var picture =   $("#addIcon").attr("img-path");

    if(picture == undefined || picture.length == 0 ){
        hint("头像不可为空！");
        return;
    }

    var account =   $("#addUserName").val();
    if(account.length != 11){
        hint("账户请输入正确的手机号！");
        return;
    }

    var userPwd =   $("#addUserPwd").val();
    if(userPwd == "" || userPwd.length == 0 ){
        hint("密码不可为空！");
        return;
    }

    var name =   $("#addName").val();
    if(name == "" || name.length == 0 ){
        hint("用户名称不可为空！");
        return;
    }

    var mobile =   $("#addMobile").val();
    if(mobile == "" || mobile.length == 0 ){
        hint("联系电话不可为空！");
        return;
    }

    if(mobile.length != 11){
        hint("手机号尾数不正确！");
        return;
    }

    var storeId =  $("#AddiconSelect").val();
    if(storeId =="" || storeId == "0"){
        hint("请选择店铺！");
        return;
    }

    // token
    var token =   comment.getToken();
    var url = ysscaleBackUrl + ysscaleBack_addUser+"?token="+token;
    var  params = {
        userName:account ,
        userPwd:userPwd ,
        name:name ,
        icon:picture ,
        mobile:mobile,
        storeId:storeId

    }

    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == "true" ){
                $("#addUserDiv").toggleClass('slide-open');
                hint("添加成功！")
                $("#table").bootstrapTable('refresh');
            }else{
                hint("添加失败！")
            }
        }
    });

})





/**
 * 修改用户
 */
$("#upUser-btn").on('click',  function() {

    var picture =   $("#upUserPhoto").attr("img-path");
    if(picture == undefined || picture.length == 0 ){
        hint("头像不可为空！");
        return;
    }

    var account =   $("#upUserName").val();
    if(account.length != 11){
        hint("账户请输入正确的手机号！");
        return;
    }

    var name =   $("#upName").val();
    if(name == "" || name.length == 0 ){
        hint("用户昵称不可为空！");
        return;
    }

    var mobile =   $("#upMobile").val();
    if(mobile == "" || mobile.length == 0 ){
        hint("联系电话不可为空！");
        return;
    }

    if(mobile.length != 11){
        hint("手机号尾数不正确！");
        return;
    }

    var storeId =  $("#upSelect").val();

    var id =  $("#id").val();

    // token
    var token =   comment.getToken();
    var url = ysscaleBackUrl + ysscaleBack_modifyUser+"?token="+token;
    var  params = {
        id:id,
        userName:account ,
        name:name ,
        icon:picture ,
        mobile:mobile,
        storeId:storeId
    }

    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == "true" ){
                $("#upUserInfoDiv").toggleClass('slide-open');
                hint("修改成功！")
                $("#table").bootstrapTable('refresh');
            }else{
                hint("修改失败！")
            }
        }
    });
})




/**
 * 删除用户
 */
$("#delUser-btn").on('click',  function() {

    var id =  $("#delId").val();
    // token
    var token =   comment.getToken();
    var url = ysscaleBackUrl + ysscaleBack_removeUser+"?token="+token;
    var  params = {
        id:id
    }

    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == "true" ){
                modal.modal('hide')
                hint("删除成功！")
                $("#table").bootstrapTable('refresh');
            }else{
                hint("删除失败！")
            }
        }
    });
})


