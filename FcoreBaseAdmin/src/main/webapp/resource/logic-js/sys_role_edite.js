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
function save(){
	alert("aaaaa")
}
function saveUserRole(index){
	var nodes = treeObj.getCheckedNodes(true);
	var perIds = [];
	 for (var i = 0; i < nodes.length; i++) {
		 perIds.push(nodes[i].id);
	 }
	var url = "saveRoolPer";
	var data = {};
	data.roleId = $("input[name='id']").val();
	data.perIds = perIds.toString();
 	doAjax(url,data,function(data){
 		parent.layer.msg("权限设置成功!");
 		parent.layer.close(index);
 	});
}