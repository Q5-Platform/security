/**
 * Created by houcunlu on 2017/8/30.
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
    var url = security + findRolePage+"?token="+token;
    table.bootstrapTable("refresh", {url: url});

    // 关闭侧滑
    slider();

})




operateFormatter = function(value,row,index){
    return[
        '<a class="look" href="javascript:void(0)" title="查看用户"><i class="glyphicon glyphicon-arrow-right"></i></a>' +
        '&nbsp;&nbsp;&nbsp;&nbsp;'
    ].join('');
}

userOperateFormatter = function(value,row,index){
    return[
        '<a class="remove" href="javascript:void(0)" title="删除用户" data-target="#deleteRoleModal" data-to="modal" ><i class="glyphicon glyphicon-remove-sign"></i></a>' +
        '&nbsp;&nbsp;&nbsp;&nbsp;'
    ].join('');
}


window.operateEvents = {
    'click .look': function (e, value, row, index) {
        $("#rid").val(row.id);
        // token
        var token =   comment.getToken();
        var url = security + getUserByRoleIdPage+"?token="+token+"&rid="+row.id;
        var table = $("#userTable");
        table.bootstrapTable("refresh",{url:url});
    },
    'click .remove': function (e, value, row, index) {
        $("#deleteUserId").val(row.aid);
    }
}



/**
 * 添加 用户权限
 */
$("#addUserRole").on('click',  function() {
    var rid = $("#rid").val();
    if(rid==null||rid==''){
        hint("请先选择权限");
    }else{
        $("#s-searchinfo").toggleClass('slide-open');

        // token
        var token =   comment.getToken();

        var table = $("#userAllTable");
        var url = security + loadUserNoHaveUserPage+"?token="+token+"&rid="+rid;
        table.bootstrapTable("refresh", {url: url});
    }
})




/**
 * 添加 用户权限
 */
$("#save-searchinfo-btn").on('click',  function() {
    // token
    var token =   comment.getToken();

    var table = $("#userAllTable");
    var json = table.bootstrapTable('getSelections');
    if(json.length==0){
        hint('请选中数据');
        return false;
    }
    var ids='';
    for(var i=0;i<json.length;i++){
        ids+=json[i].aid+','
    }
    if(ids==''){
        hint("请选择数据");
        return false;
    }
    var rid = $("#rid").val();

    var url = security + addUserRole+"?token="+token;
    var  params = {
        rid:rid ,
        ids:ids
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                hint("添加成功！")
                $("#s-searchinfo").toggleClass('slide-open');
                $("#userTable").bootstrapTable('refresh');
            }
        }
    });
})




/**
 * 删除 用户
 */
$("#deleteUser").on('click',  function() {
    // token
    var token =   comment.getToken();
    var uid = $("#deleteUserId").val();
    var rid =$("#rid").val();

    var url = security + deleteUser+"?token="+token;
    var  params = {
        aid:uid ,
        rid:rid
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                hint("删除成功！")
                modal.modal('hide');
                $("#userTable").bootstrapTable('refresh');
            }
        }
    });

})