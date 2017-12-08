

var uploadFileUrl = powerUrl+"/web/export/excel";

var deleteUrl = powerUrl+'/web/project/delExcel';

var saveOrUpdateUrl = 'http://127.0.0.1:8899/web/project/updateProject';

var loadYear = function(){

    var thisYear = new Date().getFullYear();
    for(var i = 2000;i<=2100;i++){
        var year = i;
        if(thisYear==year){
            $("#year").append('<option selected="selected" value="'+year+'">'+year+'</option>');
        }else{
            $("#year").append('<option value="'+year+'">'+year+'</option>');
        }
    }
}

loadYear();


function queryParams(params) {
    params["year"] = $("select[name=year]").val();
    params["search"] = $("input[name=search]").val();
    console.log(params);
    return params;
}



$("#year").on("change",function () {
    $("#table").bootstrapTable('refresh');
})


$("#export-excel").on("click",function(){

    $("input[name=excel]").val("");

    $("#export-div").toggleClass('slide-open');

});




$("#search").on("click",function(){

    $("#table").bootstrapTable('refresh');

});

$("#refresh").on("click",function(){

    $("input[name=search]").val("");

    $("#table").bootstrapTable('refresh');

});


$("#upload-file").on("click",function(){
   var file = $("input[name=excel]").val();
   if(file==''){
       hint("请先选择excel文件");
       return ;
   }

   var form = $("#export-form");
    $.blockUI(
        {
            message: '上传文件中...',
            baseZ: 2000,
            css: {
                border: 'none',
                padding: '15px',
                backgroundColor: 'white',
                width: "300px",
                opacity: .5,
                color: 'black'
            }
        }
    );
    form.ajaxSubmit({
        url: uploadFileUrl,
        success: function (data) {
            $.unblockUI();
            console.log(data);
            alert('数据插入成功');
            $("#export-div").toggleClass('slide-open');
            $("#table").bootstrapTable("refresh");
        },
        error: function (data) {
            $.unblockUI();
            if(data.responseJSON!=null){
                var res = data.responseJSON;
                if(res.status==500){
                    alert(res.message);
                }
            }
            console.log(data);
        }
    });

   return false;
});

slider();


operateFormatter = function (value, row, index) {
    return [
        '<a class="edit" href="javascript:void(0)" title="编辑"><i class="glyphicon glyphicon-pencil" ></i></a>' +
        '&nbsp;&nbsp;&nbsp;' +
        '<a class="remove" href="javascript:void(0)" title="删除" data-target="#deleteModel" data-to="modal"  ><i class="fa fa-trash-o"></i></a>' +
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
        $("#deleteModelId").val(row.id);
    }
};

/**
 * 修改用户按钮
 */
$("#editinfo-btn").on("click",function(){
    update();
});


var update = function () {
    var inputs = $("#editinfo").find("input[name]");
    var map = {};
    for(var i=0;i<inputs.length;i++){
        var input = $(inputs[i]);
        var name = input.attr("name");
        map[name] = input.val();
    }

    console.log(map);

    comment.post2( saveOrUpdateUrl  , JSON.stringify(map) , '执行中....', function (res) {
        $("#editinfo").toggleClass('slide-open');
        $("#table").bootstrapTable('refresh');
    });

}


/**
 *  删除用户按钮
 */

$("#deleteData").on("click",function(){
    var userId = $("#deleteModelId").val();
    var year = $("select[name=year]").val();
    comment.post2( deleteUrl  , JSON.stringify({id:userId,year:year}) , '执行中....', function (res) {
        modal.modal('hide');
        $("#table").bootstrapTable('refresh');
    });
});
