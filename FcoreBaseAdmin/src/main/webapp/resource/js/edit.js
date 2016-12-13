$(document).ready(function() {
	$("#saveObjForm").validationEngine() 
})
function save(parentIndex){
	var index = parent.layer.load(3, {shade: [0.5,'#B3B3B3']});
	if(!jQuery('#saveObjForm').validationEngine('validate')){
		parent.layer.close(index);
		return false;	
	}
	var url = "save";
	var params = $("#saveObjForm").serialize();
	$.post(url,params,function(data){
		parent.layer.close(index);
		parent.layer.msg("操作成功!");
		setTimeout(function() {
			$("#searchForm", window.parent.document).submit();
			parent.layer.close(parentIndex);
		}, 1000);
	 },'json');
}