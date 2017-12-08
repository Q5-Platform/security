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
    var url = security + findResourcePage+"?token="+token;
    table.bootstrapTable("refresh", {url: url});

    // 关闭侧滑
    slider();

})

/**
 *  资源名称
 */
nameFormatter = function (value, row, index) {
    return [
        '<span class="child line" style="color: #3D8EBC;cursor:pointer;" >'+value+'</span>'
    ].join('');

}




var queryResource =function(id){
    // token
    var token =   comment.getToken();

    var table = $("#table");
    var url = security + findResourcePage+"?token="+token+"&superId="+id;
    table.bootstrapTable("refresh",{url:url});

}


var pathClick = function(id,superId,name){
    queryResource(id);
    $("#sid").val(id);
    $('#path').append('<span style="color: #3D8EBC;cursor:pointer;" data-id="'+id+'" onclick="clearPathClick('+id+','+superId+')">'+name+'>&nbsp;&nbsp;</span>');
}


var clearPathClick = function(id,superId){
    queryResource(id);
    if(superId==null){
        $("#sid").val('');
        $('#right-title').text('全局功能权限');
        $("#rightId").val(0);
        findResourceRight(0);
    }
    $("#sid").val(id);
    var spans = $('#path').children("span");
    var isStart = false;
    $.each(spans,function(){
        var s = $(this);
        if(isStart){
            s.remove();
        }else{
            var mid = s.attr('data-id');
            if(mid==id){
                isStart = true;
            }
        }
    });
}




var findResourceRight = function(row){
    // token
    var token =   comment.getToken();

    var type = $("#resourceType").val();
    var id = row.id;
    var table = $("#rightTable");
    var url = security + findResourceRightPage+"?token="+token+"&superId="+id+"&type="+type;
    table.bootstrapTable("refresh",{url:url});
}



/**
 * 排序
 */
orderNum = function(value,row,index){
    return "<a href='javascript:void(0)' onclick='showOrderNum("+row.ordernum+","+row.id+")'><span>"+row.ordernum+"</span></a>";
}


/**
 * 显示修改排名页面
 * @param orderNum
 */
var showOrderNum = function(orderNum,id){
    $("#upId").val(id);
    $("#upNumber").val(orderNum);

    $("#upResourceDiv").toggleClass('slide-open');
}


/**
 * 显示修改
 */
$("#upResourceDiv-btn").on('click',  function() {
    // token
    var token =   comment.getToken();

   var id =  $("#upId").val();
   var number =  $("#upNumber").val();
    if(!isNumber(number)){
        hint('请输入纯数字！');
        return;
    }

    var url = security + updateOrderNumById+"?token="+token;
    var  params = {
        id:id ,
        orderNum:number
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                hint("修改成功！")
                $("#upResourceDiv").toggleClass('slide-open');
                $("#table").bootstrapTable('refresh');
            }
        }
    });


})


operateFormatter = function (value, row, index) {
    return [
        '<a class="edit" href="javascript:void(0)" title="编辑"  ><i class="glyphicon glyphicon-pencil"></i></a>' +
        '&nbsp;&nbsp;' +
        '<a class="remove" href="javascript:void(0)" title="删除" data-target="#deleteResourceModal" data-to="modal" ><i class="fa fa-trash-o"></i></a>'+
        '&nbsp;&nbsp;' +
        '<a class="show" href="javascript:void(0)" title="功能"><i class="glyphicon glyphicon-arrow-right"></i></a>'
    ].join('');
}


rightOperateFormatter = function (value, row, index) {
    return [
        '<a class="rightEdit" href="javascript:void(0)" title="编辑"  ><i class="glyphicon glyphicon-pencil"></i></a>' +
        '&nbsp;&nbsp;' +
        '<a class="rightRemove" href="javascript:void(0)" title="删除" data-target="#deleteResourceModal" data-to="modal" ><i class="fa fa-trash-o"></i></a>'
    ].join('');
}



/**
 *  点击
 */
operateEvents = {
    'click .child': function (e, value, row, index) {
        pathClick(row.id,row.superId,row.name);
    }, 'click .edit': function (e, value, row, index) {
        $("#upResourceId").val(row.id);
        $("#modify_address").val(row.url);
        $("#modify_code").val(row.code);
        $("#upRole").val(row.name);
        $("#upSuperId").val(row.superId);
        $("#modify_icon").val(row.icon);
        var icon = $("i[data-event=icon]");
        icon.attr("class",row.icon);

        $("#editinfo").toggleClass('slide-open');
    }, 'click .remove': function (e, value, row, index) {
        $("#deleteResourceId").val(row.id);
    }, 'click .show': function (e, value, row, index) {
        $("#rightId").val(row.id);
        findResourceRight(row);
    }, 'click .rightEdit': function (e, value, row, index) {
        $("#upRightResourceId").val(row.id);
        $("#upRightTypeResourceId").val(row.type);

        $("#modify_Right_address").val(row.url);
        $("#modify_Right_code").val(row.code);
        $("#upRightRole").val(row.name);

        $("#editRightInfo").toggleClass('slide-open');
    }, 'click .rightRemove': function (e, value, row, index) {
        $("#deleteResourceId").val(row.id);
    }

}


/**
 * 显示资源图标
 * @param value
 * @param row
 * @param inde
 */
iconFormatter = function(value,row,inde){
    if(row.icon!=''&&row.icon!=null){
        return[
            '<i class="'+row.icon+'" ></i>'
        ].join('');
    }else{
        return[
            '<span>无</span>'
        ].join('');
    }
}





/**
 * 修改资源信息
 */
$("#editinfo-btn").on('click',  function() {
    // token
    var token =   comment.getToken();

    var id =  $("#upResourceId").val();

    var upRole =  $("#upRole").val();
    if( upRole == "" || upRole.length == 0){
        hint('资源名称不可为空！');
        return;
    }

    var modify_address =  $("#modify_address").val();
    if( modify_address == "" || modify_address.length == 0){
        hint('资源地址不可为空！');
        return;
    }

    var modify_code =  $("#modify_code").val();
    if( modify_code == "" || modify_code.length == 0){
        hint('资源CODE不可为空！');
        return;
    }

    var modify_icon =  $("#modify_icon").val();

    var url = security + saveOrUpdateResource+"?token="+token;
    var  params = {
        id:id ,
        name:upRole,
        url:modify_address,
        code:modify_code ,
        icon:modify_icon
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
 * 选择图标
 */
$("#saveIcon-btn").on('click',  function() {
    var txt = sessionStorage.getItem("iconValue");
    var iconDIV = $("i[data-event=icon]");
    iconDIV.attr("class",txt);
    $("#modify_icon").val(txt);
    $("#add_icon").val(txt);
    modal.modal('hide')

})






/**
 * 删除资源信息
 */
$("#deleteResource").on('click',  function() {
    // token
    var token =   comment.getToken();

    var id =  $("#deleteResourceId").val();

    var url = security + deleteResourceById+"?token="+token;
    var  params = {
        id:id
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                hint("删除成功！")
                modal.modal('hide')
                $("#table").bootstrapTable('refresh');
                $("#rightTable").bootstrapTable('refresh');
            }
        }
    });
})


/**
 * 添加资源信息
 */
$("#addResource").on('click',  function() {
    $("#addRole").val("");
    $("#add_address").val("");
    $("#add_code").val("");
    $("#add_icon").val("fa fa-circle-o");

    var iconDIV = $("i[data-event=icon]");
    iconDIV.attr("class","fa fa-circle-o");

    $("#addinfo").toggleClass('slide-open');
})


/**
 * 添加资源信息
 */
$("#addinfo-btn").on('click',  function() {

    // token
    var token =   comment.getToken();

    var name =  $("#addRole").val();
    if(name=="" || name.length == 0){
        hint("资源名称不可为空！");
        return;
    }

    var add_address =  $("#add_address").val();
    if(add_address=="" || add_address.length == 0){
        hint("资源地址不可为空！");
        return;
    }

    var add_code =  $("#add_code").val();
    if(add_code=="" || add_code.length == 0){
        hint("资源code不可为空！");
        return;
    }

    var add_icon =  $("#add_icon").val();

     var sid = 0;
     var value =  $("#sid").val();

    if(value != "" ||value.length != 0  ){
        sid = value;
    }

    var url = security + saveOrUpdateResource+"?token="+token;
    var  params = {
        superId:sid ,
        name:name,
        code:add_code ,
        icon:add_icon,
        url:add_address
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                hint("添加成功！")
                $("#addinfo").toggleClass('slide-open');
                $("#table").bootstrapTable('refresh');
            }
        }
    });
})


/**
 * 添加资源 功能
 */
$("#addRight").on('click',  function() {

    $("#addRightName").val("");
    $("#add_Right_address").val("");
    $("#add_Right_code").val("");

    $("#addRightDiv").toggleClass('slide-open');

})


/**
 * 添加资源 功能
 */
$("#addRightInfo-btn").on('click',  function() {
    // token
    var token =   comment.getToken();

    var name =   $("#addRightName").val();
    if(name=="" || name.length == 0){
        hint("名称不可为空！");
        return;
    }
    var address =    $("#add_Right_address").val();
    var code =  $("#add_Right_code").val();
    var type = $("#AddResourceType").val();

    var superId = $("#rightId").val();
    if(superId == 0){
        hint("请选择资源！");
        return;
    }

    var url = security + saveOrUpdateResource+"?token="+token;
    var  params = {
        superId:superId ,
        name:name,
        code:code ,
        url:address,
        type:type
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                hint("添加成功！")
                $("#addRightDiv").toggleClass('slide-open');
                $("#rightTable").bootstrapTable('refresh');
            }
        }
    });

})




/**
 * 修改资源 功能
 */
$("#edit-RightInfo-btn").on('click',  function() {
    // token
    var token =   comment.getToken();

    var name =   $("#upRightRole").val();
    if(name=="" || name.length == 0){
        hint("名称不可为空！");
        return;
    }
    var modify_Right_address =    $("#modify_Right_address").val();
    var modify_Right_code =  $("#modify_Right_code").val();
    var upRightResourceId =  $("#upRightResourceId").val();
    var upRightTypeResource =  $("#upRightTypeResourceId").val();

    var url = security + saveOrUpdateResource+"?token="+token;
    var  params = {
        id:upRightResourceId,
        name:name,
        code:modify_Right_code ,
        url:modify_Right_address,
        type:upRightTypeResource
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (res) {
        if (res.state && res.res.code == 40000) {
            if(res.res.data == true){
                hint("修改成功！")
                $("#editRightInfo").toggleClass('slide-open');
                $("#rightTable").bootstrapTable('refresh');
            }
        }
    });

})






/**
 *  查询功能
 */
$("#resourceType").on('click',  function() {

    // token
    var token =   comment.getToken();

    var id =   $("#rightId").val();
    var type =   $("#resourceType").val();

    var table = $("#rightTable");
    var url = security + findResourceRightPage+"?token="+token+"&superId="+id+"&type="+type;
    table.bootstrapTable("refresh",{url:url});

})

