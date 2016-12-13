$(function(){
	var id = $("input[name='id']").val();
	var key = $("input[name='key']").val();
	var isMoreLevel = $("input[name='isMoreLevel']:checked").val();
	if(id!='' && isMoreLevel==1){
		$("#chiledDiv").show();
		var param = {};
		param.key = key;
		doAjax("getByKey",param,function(data){
			var list = data.data.value;
			var html = "<tr>";
			for(var i=0;i<list.length;i++){
				html += "<tr>";
				html += "<td>"+i+"</td>";
				html += "<td>"+list[i].name+"</td>";
				html += "<td>"+list[i].key+"</td>";
				html += "<td>"+list[i].value+"</td>";
				html += "<td>"+list[i].createTime+"</td>";
				html += "<td>";
				html += "<a class=\"btn btn-primary btn-xs\" onclick=\"addChild("+list[i].id+")\" role=\"button\" title=\"编辑\">编辑</a>";
				html += "<a class=\"btn btn-primary btn-xs\" onclick=\"delChild("+list[i].id+")\" role=\"button\" title=\"删除\">删除</a>";
				html += "</td>";
				html += "</tr>";
			}
			
			$("#chiledTbody").append(html);
		});
	}
})
function addChild(id) {
	var dictId = $("input[name='id']").val();
	var url = "editChild?sysDictId="+dictId;
	if (id != null) {
		url = url + "&id=" + id;
	}
	parent.layer.open({
		content : url, // iframe的url
		type : 2,
		title : '添加/修改',
		shade : 0.8,
		area : [ '100%', '100%' ],
		btn : [ '确定', '取消' ],
		yes : function(index, layero) {
			var iframeWin = window[layero.find('iframe')[0]['name']];
			iframeWin.save(index);
		},
		cancel : function(index) {
		}
	});
}