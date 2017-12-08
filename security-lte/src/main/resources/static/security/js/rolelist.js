/**
 * Created by houcunlu on 2017/8/29.
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


operateFormatter = function (value, row, index) {
    return [
        '<a class="edit" href="javascript:void(0)" title="编辑"><i class="glyphicon glyphicon-pencil" ></i></a>' +
        '&nbsp;&nbsp;&nbsp;' +
        '<a class="remove" href="javascript:void(0)" title="删除" data-target="#deleteRoleModal" data-to="modal"  ><i class="fa fa-trash-o"></i></a>' +
        '&nbsp;&nbsp;&nbsp;'
    ].join('');
}


window.operateEvents = {
    'click .edit': function (e, value, row, index) {
        $("#editinfo").toggleClass('slide-open');
        $("#upRoleId").val(row.id);
        $("#upRole").val(row.name);
    },
    'click .remove': function (e, value, row, index) {
        $("#deleteRoleId").val(row.id);
    }
};




/**
 * 修改角色信息
 */
$("#editinfo-btn").on('click',  function() {
    // token
    var token =   comment.getToken();

    var upRole  = $("#upRole").val();
    if(upRole == "" || upRole == null){
        hint("角色不能为空");
        return false;
    }

    var upRoleId  = $("#upRoleId").val();
    var url = security + saveOrUpdateRole+"?token="+token;
    var  params = {
        id:upRoleId ,
        name:upRole
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                hint("修改成功！")
                $("#editinfo").toggleClass('slide-open');
                $("#table").bootstrapTable('refresh');
            }
        }
    });
})





/**
 * 添加角色
 *
 **/
$("#addRole").on('click',  function() {
    // 清空数据
    $("#roleName").val("");

    $("#addRole-div").toggleClass('slide-open');

});


/**
 * 添加角色
 *
 **/
$("#submit-role").on('click',  function() {

    // 密码
    var roleName =  $.trim($("#roleName").val());
    if(roleName == "" || roleName.length == 0){
        hint("角色不可为空！");
        return;
    }

    // token
    var token =   comment.getToken();
    var url = security + saveOrUpdateRole+"?token="+token;
    var  params = {
        name:roleName
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                $("#addRole-div").toggleClass('slide-open');
                hint("添加成功！")
                $("#table").bootstrapTable('refresh');
            }
        }
    });
})



/**
 * 删除角色
 *
 **/
$("#deleteRole").on('click',  function() {

    var roleId =  $("#deleteRoleId").val();
    // token
    var token =   comment.getToken();
    var url = security + deleteRoleById+"?token="+token;
    var  params = {
        id:roleId ,
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        alert(JSON.stringify(res));
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                modal.modal('hide')
                hint("删除成功！")
                $("#table").bootstrapTable('refresh');
            }
        }
    });
});
