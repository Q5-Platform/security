

alert('hahaha');

var saveOrUpdateUrl = powerUrl+'/web/user/saveOrUpdate';
var deleteUrl = powerUrl+'/web/user/delUser';



operateFormatter = function (value, row, index) {
    return [
        '<a class="edit" href="javascript:void(0)" title="编辑"><i class="glyphicon glyphicon-pencil" ></i></a>' +
        '&nbsp;&nbsp;&nbsp;' +
        '<a class="remove" href="javascript:void(0)" title="删除" data-target="#deleteUserModel" data-to="modal"  ><i class="fa fa-trash-o"></i></a>' +
        '&nbsp;&nbsp;&nbsp;'
    ].join('');
}


window.operateEvents = {
    'click .edit': function (e, value, row, index) {
        $("#editinfo").toggleClass('slide-open');

        var inputs = $("#editinfo").find("input[name]");

        for(var i=0;i<inputs.length;i++){
            var input = $(inputs[i]);
            var name = input.attr("name");
            input.val(row[name]);
        }

    },
    'click .remove': function (e, value, row, index) {
        $("#deleteUserId").val(row.id);
    }
};

var saveOrUpdateUser = function (tag) {
    var inputs = $("#"+tag).find("input[name]");
    var map = {};
    for(var i=0;i<inputs.length;i++){
        var input = $(inputs[i]);
        var name = input.attr("name");
        map[name] = input.val();
    }

    console.log(map);

    comment.post2( saveOrUpdateUrl  , JSON.stringify(map) , '执行中....', function (res) {
        $("#"+tag).toggleClass('slide-open');
        $("#table-user").bootstrapTable('refresh');
    });

}

/**
 * 修改用户按钮
 */
$("#editinfo-btn").on("click",function(){
    saveOrUpdateUser("editinfo");
});


/**
 *  删除用户按钮
 */

$("#deleteUser").on("click",function(){
    var userId = $("#deleteUserId").val();
    comment.post2( deleteUrl  , JSON.stringify({userId:userId}) , '执行中....', function (res) {
        modal.modal('hide');
        $("#table-user").bootstrapTable('refresh');
    });
});

/**
 * 添加用户
 */

$("#submit-user").on("click",function(){
    saveOrUpdateUser("addUser-div");
});


/**
 * 添加用户
 *
 **/
$("#addUser").on('click',  function() {

    // 清空数据
    var inputs = $("#addUser-div").find("input[name]");
    $.each(inputs,function(){
        var name = $(this).attr("name");
        $(this).val("");
    });

    $("#addUser-div").toggleClass('slide-open');

});

slider();
