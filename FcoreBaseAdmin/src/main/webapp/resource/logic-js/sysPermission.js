var treeObj = null;
$(document).ready(function() {
	$("#formID").validationEngine() 
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : onClick
		}
	};
	var zNodes = null;
	var url = "getPreForTree";
	doAjax(url, null, function(data) {
		zNodes = data;
	});
	treeObj = $.fn.zTree.init($("#treeOrg"), setting, zNodes);
});

function onClick(e, treeId, node) {
	if (node.id != 0) {
		setFormValue(node.id);
	}
}

function getSelectNode() {
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length > 0) {
		return nodes[0];
	} else {
		parent.layer.msg("请选中一个节点！");
		return false;
	}
}

function setFormValue(id){
	doAjax("getById?id="+id,null,function(data){
		$("input[name='id']").val(data.id);
		$("input[name='name']").val(data.name);
		$("input[name='value']").val(data.value);
		$("input[name='href']").val(data.href);
		$("input[name='parentId']").val(data.parentId);
		$("input[name=isLast]:eq("+data.isLast+")").attr("checked",'checked'); 
		$("input[name='orderCode']").val(data.orderCode);
	})
}

function addPermission() {
	var node = getSelectNode();
	if(node.isLast==1){
		parent.layer.msg("不允许在最后一级权限下面继续添加权限！");
		return false;
	}
	$("input[name='parentId']").val(node.id);
	$("input[name='id']").val("");
	$("input[name='name']").val("");
	$("input[name='value']").val("");
	$("input[name='href']").val("");
	$("input[name=isLast]:eq(0)").attr("checked",'checked'); 
	$("input[name='orderCode']").val("");
}

function savePermission() {
	var index = parent.layer.load(3, {shade: [0.5,'#B3B3B3']});
	if(!jQuery('#formID').validationEngine('validate')){
		parent.layer.close(index);
		return;	
	}
	var params = $("#formID").serialize();
	doAjax("save",params,function(data){
		if(data.editType==1){
			addNode(data.sysPermission)
		}else{
			updateNode(data.sysPermission)
		}
		parent.layer.close(index);
		parent.layer.msg("操作成功！");
	})
}

function delPermission() {
	var node = getSelectNode();
	parent.layer.open({
	    content: '删除该权限会连同子权限一起删除，确定删除？',
	    btn: ['确认', '取消'],
	    shadeClose: false,
	    yes: function(){
	    	var index = parent.layer.load(3, {shade: [0.5,'#B3B3B3']});
	    	parent.layer.open({content: '你点了确认', time: 1});
	        var url = 'deleteById';
	        doAjax(url,{id:node.id},function(data){
	        	removeNode(data.ids);
				parent.layer.close(index);
				parent.layer.msg("操作成功！");
				$("input[name='parentId']").val(0);
				$("input[name='id']").val("");
				$("input[name='name']").val("");
				$("input[name='value']").val("");
				$("input[name='href']").val("");
				$("input[name=isLast]:eq(0)").attr("checked",'checked'); 
				$("input[name='orderCode']").val("");
	        })
	    }
	});
}

function checkPerValue(obj){
	var value = $(obj).val();
	if(value!=null  && value!=""){
		var data = {};
		data.id = $("input[name='id']").val();
		data.value = value;
		doAjax("checkPerValue",data,function(data){
			var data = eval(data);
			if(!data.flag){
				layer.alert("权限值："+value+"已经存在！！");
				$(obj).val("");
			}
		})
	}
}

// 定义ztree的节点类
function ZtreeNode(id, pId, name) {
	this.id = id;
	this.pId = pId;
	this.name = name;
}

// 动态添加节点
function addNode(data) {
	if (treeObj != null) {
		// 构造子节点
		var childZNode = new ZtreeNode(data.id, data.parentId, data.name);
		childZNode.isLast = data.isLast;
		// 获取父节点
		var parentZNode = treeObj.getNodeByParam("id", data.parentId, null);
		// 添加节点
		treeObj.addNodes(parentZNode, childZNode, true);
	}
}

// 更新节点
function updateNode(data) {
	if (treeObj != null) {
		// 获取节点
		var node = treeObj.getNodeByParam("id", data.id, null);
		node.name = data.name;
		treeObj.updateNode(node);
	}
}

// 删除节点
function removeNode(ids) {
	var len = ids.length;
	for (var i = 0; i < len; i++) {
		// 获取节点
		var node = treeObj.getNodeByParam("id", ids[i], null);
		treeObj.removeNode(node);
	}
}