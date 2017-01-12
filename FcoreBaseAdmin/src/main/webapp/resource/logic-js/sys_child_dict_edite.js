$(document).ready(function() {
	$("#saveObjForm").validationEngine() 
})
function save(parentIndex,fn){
	var index = parent.layer.load(3, {shade: [0.5,'#B3B3B3']});
	if(!jQuery('#saveObjForm').validationEngine('validate')){
		parent.layer.close(index);
		return false;	
	}
	var url = "saveChild";
	var params = $("#saveObjForm").serialize();
	$.post(url,params,function(data){
		parent.layer.close(index);
		parent.layer.msg("操作成功!");
		fn();
	 },'json');
}