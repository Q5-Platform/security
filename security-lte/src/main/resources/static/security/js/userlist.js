/**
 * Created by houcunlu on 2017/8/26.
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
            location.href = "../../index.html";
        } , 3000);
        return;
    }

    // 初始化table
    var table = $("#table");
    var url = security + findUserPage+"?token="+token;
    table.bootstrapTable("refresh", {url: url});

    // 关闭侧滑
    slider();

})


operateFormatter = function (value, row, index) {
    if(row.id == 1){
        return '<span>超级管理不得操作</span>';
    }else{
        return [
            '<a class="edit" href="javascript:void(0)" title="编辑"><i class="glyphicon glyphicon-pencil" ></i></a>' +
            '&nbsp;&nbsp;&nbsp;' +
            '<a class="remove" href="javascript:void(0)" title="删除" data-target="#deleteUserModal" data-to="modal"  ><i class="fa fa-trash-o"></i></a>' +
            '&nbsp;&nbsp;&nbsp;'
        ].join('');
    }

}


window.operateEvents = {
    'click .edit': function (e, value, row, index) {
        $("#editinfo").toggleClass('slide-open');
        setRowToFrm("modifyUser",row);
        var url = File_Download_Url + row.photo;
        $("#UpUserPhoto").attr("src", "");
        $("#UpUserPhoto").attr("img-path", "");
        $("#UpUserPhoto").attr("src", url);
        $("#UpUserPhoto").attr("img-path", row.photo);
    },
    'click .remove': function (e, value, row, index) {
        $("#deleteUserId").val(row.id);
    }
};


/**
 * 保存用户信息
 */
$("#editinfo-btn").on('click',  function() {
    // token
    var token =   comment.getToken();

    var form = $("#modifyUser");
    var v = form.valid();
    if (v == false) {
        return false;
    }
    var userName  = $("#upUserName").val();
    if(userName == "" || userName == null){
        hint("账户不能为空");
        return false;
    }
    var photo = $("#UpUserPhoto").attr("img-path");
    if(photo == "" || photo == null){
        hint("头像不能为空");
        return false;
    }
    var userId = $("#upUserId").val();

    var url = security + updateUserById+"?token="+token;
    var  params = {
        userName:userName ,
        userId:userId ,
        photo:photo
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                $("#editinfo").toggleClass('slide-open');
                $("#table").bootstrapTable('refresh');
            }
        }
    });

})




/**
 *  图片
 **/
photo = function (value, row, index) {
    var img = row.photo;
    var imgUrl = "";
    if (img != null && img != '') {
        imgUrl = File_Download_Url + img;
    }
    return [
        '<img src=' + imgUrl + ' style="height:50px;width: 50px;" />'
    ].join('');
}



/**
 * 删除用户
 * deleteUser
 **/
$("#deleteUser").on('click',  function() {

   var userId =  $("#deleteUserId").val();
    // token
    var token =   comment.getToken();
    var url = security + deleteUserById+"?token="+token;
    var  params = {
        userId:userId ,
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                modal.modal('hide')
                hint("删除成功！")
                $("#table").bootstrapTable('refresh');
            }
        }
    });
});




/**
 * 显示状态
 */
showEnable = function(value,row,index){
    if(value==1){
        return [
            '<a href="javascript:void(0)" onclick="mergeEnable('+0+','+row.id+')"><span style="color: #01ff70">可用</span></a>'
        ].join('');
    }else{
        return [
            '<a href="javascript:void(0)" onclick="mergeEnable('+1+','+row.id+')"><span style="color: red">不可用</span></a>'
        ].join('');
    }
}

/**
 * 修改用户状态
 * @param enable
 * @param id
 */
var mergeEnable = function(enable,id){
    // token
    var token =   comment.getToken();
    var url = security + updateEnableByUserId+"?token="+token;
    var  params = {
        userId:id ,
        enable:enable
    }
    comment.post( url , JSON.stringify(params) ,"操作中。。。",function(data){
        if(data.res.code==40000){
            $("#table").bootstrapTable('refresh');
        }else{
            hint("操作失败");
        }
    });
}


/**
 * 添加用户
 *
 **/
$("#addAdmin").on('click',  function() {
    // 清空数据
    $("#addName").val("");
    $("#addPwd").val("");
    $("#addUserPhoto").attr("src","../../icon/img_add.png");
    $("#addUserPhoto").attr("img-url","");
    $("#addUserPhoto").attr("img-path","");


    $("#addAdmin-div").toggleClass('slide-open');

});


/**
 * 添加用户
 *
 **/
$("#submit-admin").on('click',  function() {
    // 账户
    var addName =  $.trim($("#addName").val());
    if(addName == "" || addName.length == 0){
        hint("账户不可为空！");
        return;
    }
    // 密码
    var addPwd =  $.trim($("#addPwd").val());
    if(addPwd == "" || addPwd.length == 0){
        hint("密码不可为空！");
        return;
    }
    // 头像
   var addUserPhoto =  $("#addUserPhoto").attr("img-path");
    if(addUserPhoto == ""){
        hint("头像不可为空！");
        return;
    }

    // token
    var token =   comment.getToken();
    var url = security + addUser+"?token="+token;
    var  params = {
        userName:addName ,
        userPwd:addPwd ,
        photo:addUserPhoto

    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                $("#addAdmin-div").toggleClass('slide-open');
                hint("添加成功！")
                $("#table").bootstrapTable('refresh');
            }
        }
    });



})