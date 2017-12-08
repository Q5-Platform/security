/**
 * Created by houcunlu on 2017/11/25.
 */

URL = 'http://127.0.0.1:8080/xx/';

var defaultAttrs = [
        {name:'api-data-method',value:"post"},
        {name:'api-data-toggle',value:"table"},
        {name:'api-data-height',value:"600"},
        {name:'api-data-side-pagination',value:"server"},
        {name:'api-data-pagination',value:"true"},
        {name:'api-data-page-list',value:"[5, 10, 20, 50, 100, 200]"},
        {name:'api-data-search',value:"true"},
        {name:'api-data-show-refresh',value:"true"},
        {name:'api-data-show-toggle',value:"true"},
        {name:'api-data-show-columns',value:"true"}
    ];


var constant = function (attrName,attrs) {
    for(var i in attrs) {
        var attr = attrs[i];
        var name = attr.name;
         if(attrName == name){
             return true;
         }
    }
    return false;
}

var reloadNamedNodeMap = function(attrs){

    var array = new Array();

    for(var i in attrs) {
        var attr = attrs[i];
        var attrName = attr.name;
        var attrValue = attr.value;
        array.push({name:attrName,value:attrValue});
    }

    return array;
}

var init = function(){

    var tabs =$("div[api-type=table]");
    $.each(tabs,function(){

        var div = $(this);

        var attrs = reloadNamedNodeMap(div[0].attributes);

        var table = $('<table></table>');

        for(var i in defaultAttrs){
            var attr = defaultAttrs[i];
            console.log(attr);
            var attrName = attr.name;

            if(!constant(attrName,attrs)){
                attrs.push(attr);
            }
        }

        for(var i in attrs){
            var attr = attrs[i];
            var attrName = attr.name;
            var attrValue = attr.value;

            if(attrName!=null&&attrName.startWith("api-data")){
                var newAttrName = attrName.substring(4,attr.length);
                table.attr(newAttrName,attrValue);
            }
        }

        var thead = $('<thead></thead>');
        var tr = $('<tr></tr>');

        tr.append('<th>标识</th>');
        tr.append('<th>标识</th>');
        tr.append('<th>标识</th>');

        thead.append(tr);
        table.append(thead);

       $(div).append(table);
    });
}


var getTableStyle = function(){





}

init();