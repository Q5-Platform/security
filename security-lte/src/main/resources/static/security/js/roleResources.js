/**
 * Created by houcunlu on 2017/8/30.
 */
/**
 * 页面加载
 */
$(function(){

    $.blockUI(
        {
            message: '加载菜单数据中....',
            baseZ: 2000,
            css: {
                border: 'none',
                padding: '15px',
                backgroundColor: 'white',
                width: "300px",
                opacity: .5,
                color: 'black'
            }
        });

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

    loadTree();

})




/**
 * 角色列表操作按钮
 */
operateFormatter = function (value, row, index) {
    return [
        '<a class="look" href="javascript:void(0)" title="查看角色资源"><i class="glyphicon glyphicon-arrow-right"></i></a>' +
        '&nbsp;&nbsp;&nbsp;&nbsp;'
    ].join('');
}



/**
 * 操作按钮点击事件
 * @type {{click .look: Function, click .lookRight: Function, click .remove: Function}}
 */
window.operateEvents = {
    'click .look': function (e, value, row, index) {
        // token
        var token =   comment.getToken();

        $("#roId").val(row.id);


        var url = security + getResourceByRoleId+"?token="+token;
        var  params = {
            rId:row.id
        }
        comment.post( url  , JSON.stringify(params) , '执行中....', function (data) {
            if (data.res.code == 40000) {
                var treeObj = $.fn.zTree.getZTreeObj("jstree_demo_div");
                treeObj.checkAllNodes(false);
                treeObj.expandAll(true);
                var nodes = treeObj.getNodes();
                var checkSelected = function (nodes) {
                    if (nodes != null)
                        for (var index = 0; index < nodes.length; index++) {
                            var n = nodes[index];
                            for (var i = 0; i < data.res.data.length; i++) {
                                if (data.res.data[i] == n.id) {
                                    treeObj.checkNode(n, true, true);
                                    checkSelected(n.children);
                                }
                            }
                        }
                }
                checkSelected(nodes);
            }
        });
    }
}





var loadTree = function (ids) {
    // token
    var token =   comment.getToken();

    //加载tree
    var url = security + loadResourceTree+"?token="+token;

    var nodes ;
    var setting = {
        callback: {
            beforeClick: function beforeClick(treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                zTree.checkNode(treeNode, !treeNode.checked, null, true);
                return true;
            },
            beforeCheck: zTreeBeforeCheck
        },
        check: {
            enable: true,
            chkboxType: {"Y": "p", "N": "ps"},
            chkStyle: "checkbox",
            radioType: "all"
        },
        async: {
            enable: true,
            url: function () {
                return url;
            },
            dataFilter: function (treeId, parentNode, childNodes) {
                $.unblockUI();
                //js中节点数据：
                return childNodes.res.data;
            }
        }
    };

    nodes = [
        //父节点展开 折叠时分别使用不同的图标
        { name:"", iconOpen:"/static/images/favicon.ico", iconClose:"/static/images/favicon.ico"},
        //叶子节点个性化图标
        { name:"", icon:"/static/images/favicon.ico"}
    ]

    var zTree = $.fn.zTree.init($("ul[data-event=jstree_demo_div]"), setting);
    function zTreeBeforeCheck(treeId, treeNode) {
        //$("input[name=typeId]").val(treeNode.id);
    }
}


$("#open").on('click',  function() {
    var treeObj = $.fn.zTree.getZTreeObj("jstree_demo_div");
    treeObj.expandAll(true);
})


$("#close").on('click',  function() {
    var treeObj = $.fn.zTree.getZTreeObj("jstree_demo_div");
    treeObj.expandAll(false);
})



$("#saveRoleResource").on('click',  function() {
    // token
    var token =   comment.getToken();

    var treeObj = $.fn.zTree.getZTreeObj("jstree_demo_div");
    var nodes = treeObj.getCheckedNodes(true);

    var ids = "";
    if (nodes != null && nodes.length > 0) {
        for (var i = 0; i < nodes.length; i++) {
            var n = nodes[i];
            ids += n.id + ",";
        }
    }

    var roId = $("#roId").val();

    var url = security + saveRoleResource+"?token="+token;
    var  params = {
        roId:roId ,
        ids:ids
    }
    comment.post( url  , JSON.stringify(params) , '执行中....', function (data) {
        if (data.res.code == 40000) {
            hint("添加成功!");
        }
    });



})


