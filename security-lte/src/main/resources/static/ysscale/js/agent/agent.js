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
    var url = ysscaleBackUrl + ysscaleBack_findUserPage+"?token="+token;
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



/**
 * 显示状态
 */
state = function(value,row,index){
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
    var url = aiMouBackUrl + aiMouBack_updateUserState+"?token="+token;
    var  params = {
        userId:id ,
        state:enable
    }

    comment.post( url , JSON.stringify(params) ,"操作中。。。",function(data){
        if(data.res.code==40000){
            $("#table").bootstrapTable('refresh');
        }else{
            hint("操作失败");
        }
    });

}



operateFormatter = function (value, row, index) {
    return [
        '<a class="details" href="javascript:void(0)" title="查看详情"><i class="glyphicon glyphicon-search" ></i></a>' +
        '&nbsp;&nbsp;&nbsp;' +
        '<a class="edit" href="javascript:void(0)" title="编辑" data-toggle="slider" data-target="#upUserDiv" ><i class="glyphicon glyphicon-pencil" ></i></a>' +
        '&nbsp;&nbsp;&nbsp;' +
        '<a class="upPwd" href="javascript:void(0)" title="重置密码" data-target="#upUserPwdModal" data-to="modal"  ><i class="fa fa-history"></i></a>' +
        '&nbsp;&nbsp;&nbsp;'+
        '<a class="projectList" href="javascript:void(0)" title="删除用户" data-target="#deleteUserModal" data-to="modal" ><i class="glyphicon glyphicon-remove-sign"></i></a>' +
        '&nbsp;&nbsp;&nbsp;'+
        '<a class="findCommissionList" href="javascript:void(0)" title="查看佣金记录"   ><i class="glyphicon glyphicon-th-list"></i></a>' +
        '&nbsp;&nbsp;&nbsp;'+
        '<a class="addCommission" href="javascript:void(0)" title="添加佣金" data-toggle="slider" data-target="#addCommissionDiv"   ><i class="glyphicon glyphicon-jpy"></i></a>' +
        '&nbsp;&nbsp;&nbsp;'

    ].join('');

}


window.operateEvents = {
    'click .details': function (e, value, row, index) {
        $("#detailDiv").toggleClass('slide-open');
        setRowToFrm("userForm",row);
        //  用户状态
        $("#userPhoto").attr("src",File_Download_Url+row.icon);
    },
    'click .edit': function (e, value, row, index) {
        setRowToFrm("upUserForm",row);
        if(row.icon == "" || row.icon == null){
            $("#upUserPhoto").attr("src","../../../icon/img_add.png");
            $("#upUserPhoto").attr("img-path","");
        }else{
            var url = File_Download_Url + row.icon;
            $("#upUserPhoto").attr("src","");
            $("#upUserPhoto").attr("img-path","");
            $("#upUserPhoto").attr("src",url);
            $("#upUserPhoto").attr("img-path",row.icon);
        }
    },
    'click .upPwd': function (e, value, row, index) {
        $("#upUserPwdId").val(row.id);
    },
    'click .projectList': function (e, value, row, index) {
        $("#deleteUserId").val(row.id);

    },
    'click .findCommissionList': function (e, value, row, index) {
        $("#commissionListDIV").toggleClass('slide-open');
        // token
        var token =   comment.getToken();
        var table = $("#commissionListTable");
        var url = ysscaleBackUrl + ysscaleBack_findCommissionList+"?token="+token+"&agentId="+row.id;
        table.bootstrapTable("refresh", { url: url} );
    },
    'click .addCommission': function (e, value, row, index) {
       var  kid =   randomString(16);
        $("#addCommissionKid").val(kid);
        $("#addMoney").val("");
        $("#addRemarks").val("");
        $("#userId").val(row.id);
    }
};









/**
 * 添加用户
 */
$("#addAdmin").on('click',  function() {
    $("#addUserDiv").toggleClass('slide-open');

    $("#addUserForm").find("input[name=account]").val("");
    $("#addUserForm").find("input[name=userPwd]").val("");
    $("#addUserForm").find("input[name=userName]").val("");
    $("#addUserForm").find("input[name=mobile]").val("");

    $("#addUserPhoto").attr("src","../../../icon/img_add.png");
    $("#addUserPhoto").attr("img-url","");
    $("#addUserPhoto").attr("img-path","");

})


/**
 * 添加用户
 */
$("#addUser-btn").on('click',  function() {
    var account =   $("#addUserName").val();
    var userPwd =   $("#addUserPwd").val();
    var name =   $("#addName").val();
    var mobile =   $("#addMobile").val();

    var picture =   $("#addIcon").attr("img-path");

    if(account == "" || account.length == 0 ){
        hint("账户不可为空！");
        return;
    }

    if(account.length != 11){
        hint("账户请输入正确的手机号！");
        return;
    }

    if(userPwd == "" || userPwd.length == 0 ){
        hint("密码不可为空！");
        return;
    }

    if(name == "" || name.length == 0 ){
        hint("用户名称不可为空！");
        return;
    }

    if(mobile == "" || mobile.length == 0 ){
        hint("手机号不可为空！");
        return;
    }

    if(mobile.length != 11){
        hint("手机号尾数不正确！");
        return;
    }

    if(picture == undefined || picture.length == 0 ){
        hint("图片不可为空！");
        return;
    }

    // token
    var token =   comment.getToken();
    var url = ysscaleBackUrl + ysscaleBack_addAgent+"?token="+token;
    var  params = {
        userName:account ,
        userPwd:userPwd ,
        mobile:mobile ,
        name:name ,
        icon:picture

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
 * 修改 用户
 */
$("#upUser-btn").on('click',  function() {

    var upUserName =   $("#upUserName").val();
    var upName =   $("#upName").val();
    var upMobile =   $("#upMobile").val();
    var upUserPhoto =   $("#upUserPhoto").attr("img-path");

    if(upUserName == "" || upUserName.length == 0 ){
        hint("账户不可为空！");
        return;
    }

    if(upUserName.length != 11){
        hint("账户请输入正确的手机号！");
        return;
    }

    if(upName == "" || upName.length == 0 ){
        hint("用户名称不可为空！");
        return;
    }

    if(upMobile == "" || upMobile.length == 0 ){
        hint("联系电话不可为空！");
        return;
    }

    if(upUserName.length != 11){
        hint("联系电话输入正确的手机号！");
        return;
    }

    if(upUserPhoto == "" || upUserPhoto.length == 0 ){
        hint("图片不可为空！")
        return;
    }
    var id =   $("#id").val();

    // token
    var token =   comment.getToken();
    var url = ysscaleBackUrl + ysscaleBack_modifyAgentInfo+"?token="+token;
    var  params = {
        id:id ,
        userName:upUserName ,
        name:upName ,
        mobile:upMobile ,
        icon:upUserPhoto
    }

    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == "true" ){
                $("#upUserDiv").toggleClass('slide-open');
                hint("修改成功！");
                $("#table").bootstrapTable('refresh');
            }else{
                hint("修改失败！");
            }
        }
    });

})





/**
 * 重置 用户 密码
 */
$("#upUserPwd-btn").on('click',  function() {

    var upUserId =   $("#upUserPwdId").val();

    // token
    var token =   comment.getToken();
    var url = ysscaleBackUrl + ysscaleBack_modifyAgentUserPwd+"?token="+token;
    var  params = {
        id:upUserId
    }

    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == "true" ){
                modal.modal('hide')
                hint("修改成功！");
            }else{
                hint("修改失败！")
            }
        }
    });

})








/**
 * 删除 用户
 */
$("#deleteUser-btn").on('click',  function() {
    var id =   $("#deleteUserId").val();
    // token
    var token =   comment.getToken();
    var url = ysscaleBackUrl + ysscaleBack_removeAgent+"?token="+token;
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




/**
 * 添加 佣金
 */
$("#addCommission-btn").on('click',  function() {

    var addCommissionKid =   $("#addCommissionKid").val();
    if(addCommissionKid =="" || addCommissionKid.length == 0){
        hint("佣金记录标识不可为空！");
        return;
    }

    var addMoney =   $("#addMoney").val();
    if(addMoney =="" || addMoney.length == 0){
        hint("金额不可为空！");
        return;
    }

    if(!addMoney.match(/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/)){
        hint("金额请输入正确的数字！");
        return;
    }

    var remarks =   $("#addRemarks").val();

    var userId =   $("#userId").val();

    // token
    var token =   comment.getToken();
    var url = ysscaleBackUrl + ysscaleBack_addCommission+"?token="+token;
    var  params = {
        id:userId ,
        commissionKid:addCommissionKid ,
        money:addMoney ,
        remarks:remarks
    }

    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == "true" ){
                hint("添加成功！")
                $("#table").bootstrapTable('refresh');
            }else{
                hint("删除失败！")
            }
        }
    });

})




/**
 *  图片
 **/
projectIcon = function (value, row, index) {
    var img = row.projectIcon;
    var imgUrl = "";
    if (img != null && img != '') {
        imgUrl = File_Download_Url + img;
    }
    return [
        '<img src=' + imgUrl + ' style="height:50px;width: 50px;" />'
    ].join('');
}




ProjectOperateFormatter = function (value, row, index) {

    return [
        '<a class="projectDetails" href="javascript:void(0)" title="查看详情" data-target="#UserProjectModal" data-to="modal" ><i class="glyphicon glyphicon-search" ></i></a>' +
        '&nbsp;&nbsp;&nbsp;' +
        '<a class="projectEdit" href="javascript:void(0)" title="编辑" data-target="#UpUserProjectModal" data-to="modal" ><i class="glyphicon glyphicon-pencil" ></i></a>' +
        '&nbsp;&nbsp;&nbsp;'
    ].join('');

}



window.ProjectOperateEvents = {
    'click .projectDetails': function (e, value, row, index) {
        setRowToFrm("UserProjectForm" , row);
        var url = File_Download_Url+ row.projectIcon
        $("#UserProjectIcon").attr("src",url);
        $("#projectProfile").text(row.projectProfile)
    },
    'click .projectEdit': function (e, value, row, index) {
        setRowToFrm("UpUserProjectForm" , row);
        var url = File_Download_Url+ row.projectIcon;
        $("#UpUserProjectIcon").attr("src",url);
        $("#UpUserProjectIcon").attr("img-path",row.projectIcon);
        $("#UpProjectProfile").val(row.projectProfile)
    }
};


/**
 * 保存项目信息
 */
$("#upUserProject-btn").on('click',  function() {
   var img =  $("#UpUserProjectIcon",parent.document).attr("img-path");
    if(img == "" || img.length == 0){
        hint("图片不可为空！");
        return;
    }

    var ProjectName =  $("#UpProjectName",parent.document).val();
    if(ProjectName == "" || ProjectName.length == 0){
        hint("项目名称不可为空！");
        return;
    }
    console.log(window);

    var UpProjectProfile =  $("#UpProjectProfile",parent.document).val();

    if(UpProjectProfile == "" || UpProjectProfile.length == 0){
        hint("项目简介不可为空！");
        return;
    }

    var UpVersion =  $("#UpVersion",parent.document).val();
    if(UpVersion == "" || UpVersion.length == 0){
        hint("项目版本不可为空！");
        return;
    }

    var UpTypeName =  $("#UpTypeName",parent.document).val();
    if(UpTypeName == "" || UpTypeName.length == 0){
        hint("项目类型不可为空！");
        return;
    }

    var ProjectKid =  $("#UserProjectKid",parent.document).val();


    // token
    var token =   comment.getToken();
    var url = aiMouBackUrl + aiMouBack_updateProjectInfo+"?token="+token;
    var  params = {
        projectKid:ProjectKid ,
        userProjectIcon:img ,
        projectName:ProjectName ,
        projectProfile:UpProjectProfile ,
        version:UpVersion ,
        typeName:UpTypeName
    }

    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == "true" ){
                $("#userProjectTable").bootstrapTable('refresh');
                modal.modal('hide')
                hint("修改成功！")
            }
        }
    });



})




function randomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}
