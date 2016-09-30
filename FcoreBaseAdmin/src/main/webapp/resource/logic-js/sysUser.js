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