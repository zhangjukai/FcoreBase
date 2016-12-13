$(function(){
	var param = {};
	var id = $("input[name='id']").val();
	if(id!=''){
		param.userId = id;
	}
	doAjax("getRoles",param,function(data){
		var roles = data.roles;
		var html = "";
		for(var i = 0; i < roles.length;i++){
			if(roles[i].isChecked==1){
				html+="<option value='"+roles[i].id+"' selected='selected'>"+roles[i].roleName+"</option>";
			}else{
				html+="<option value='"+roles[i].id+"' >"+roles[i].roleName+"</option>";
			}
		}
		$("#roleIds").append(html);
	});
})

function checkLoginName(obj) {
	var value = $(obj).val();
	if(value!=null && value!=''){
		var data = {};
		data.id = $("input[name='id']").val();
		data.loginName = value;
		$.post("checkLoginName", data, function(data) {
			if(data.state>0){
				parent.layer.msg(data.msg);
				$(obj).focus();
				return false;
			}
		},'json');
	}
}


