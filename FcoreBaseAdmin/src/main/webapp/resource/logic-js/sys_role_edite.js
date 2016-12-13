var treeObj = null;
var setting = {
	check: {
		enable: true,
		chkboxType:{ "Y" : "p", "N" : "s" }
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};

$(document).ready(function(){
	 var zNodes = null;
	 var rolePers = null;
	 var url = "getPreTreeForRool";
	 var data = {};
	 var roleId = $("input[name='id']").val();
	 if(roleId!=''){
		 data.roleId = roleId;
	 }
	 doAjax(url,data,function(data){
		 data = eval(data);
		 zNodes = data.pers;
		 rolePers = data.rolePers;
	 });
	 treeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
	 var node = null;
	 for(var i=0;i<rolePers.length;i++){
		 node = treeObj.getNodeByParam("id", rolePers[i].sysPerId, null);
		 treeObj.checkNode(node, true, true);
	 }
});
function save(parentIndex){
	var index = parent.layer.load(3, {shade: [0.5,'#B3B3B3']});
	if(!jQuery('#saveObjForm').validationEngine('validate')){
		parent.layer.close(index);
		return false;	
	}
	var url = "save";
	var params = {}
	var id = $("input[name='id']").val();
	var roleName = $("input[name='roleName']").val();
	var description = $("textarea[name='description']").val();
	var nodes = treeObj.getCheckedNodes(true);
	var perIds = [];
	for (var i = 0; i < nodes.length; i++) {
		 perIds.push(nodes[i].id);
	}
	if(id!=''){
		params.id = id;
	}
	alert(description)
	params.description = description;
	params.roleName = roleName;
	params.perIds = perIds.toString();
	
	$.post(url,params,function(data){
		parent.layer.close(index);
		parent.layer.msg("保存成功！");
		parent.layer.close(parentIndex);
	 },'json');
}
function checkValue(obj){
	var value = $(obj).val();
	if(value!=null  && value!=""){
		var data = {};
		data.id = $("input[name='id']").val();
		data.roleName = value;
		doAjax("checkRoleName",data,function(data){
			var data = eval(data);
			if(data.flag){
				parent.layer.alert("角色名称："+value+"已经存在！！");
				$(obj).val("");
			}
		})
	}
}