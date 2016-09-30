(function($){
    $.fn.FileUpload = function(opts){
        opts = $.extend({},$.fn.FileUpload.defaults,opts) ;
        initHtml(opts);
    } ;
    $.fn.FileUpload.defaults = {
        mainID : "#fileId",
        list:[]
    };
    
    function initHtml (opts){
    	var html = "";
    	html+="<div class=\"panel panel-default\">";
    	html+="<div class=\"panel-body\">";
    	html+="<div style=\"margin-bottom: 10px;cursor: pointer;width: 90px;float: left;\">";
    	html+="<button type=\"button\" class=\"btn btn-primary\" style=\"position: absolute;\" >选择文件</button>";
    	html+="<input type=\"file\" value=\"选择文件\"  multiple='multiple' class=\"btn btn-github btn-success fileIput\" id=\"uploadFiles\" style=\"cursor: pointer;\"/><br/>";
    	html+="</div><div style=\"float: left;\"> ";
    	html+="<button type=\"button\" class=\"btn btn-danger\" style=\"position: absolute;\">删除文件</button>";
    	html+="</div></div>";
    	html+="<table class=\"table table-hover\">";
    	html+="<thead id=\"fileThead\">";
    	html+="<tr>";
    	html+="<th style=\"width: 5%;\"><input type=\"checkbox\" onclick=\"checkAll(this,'ids')\" /></th>";
    	html+="<th style=\"width: 120px;\">文件预览</th>";
    	html+="<th>文件名称</th><th>文件大小</th></tr></thead>";
    	html+="<tbody id=\"fileBody\"></tbody></table>";
    	html+="<div style=\"padding: 10px;\" id=\"fileProgress\"></div>";
    	$(opts.mainID).append(html);
    }
    function initData (list){
    	
    }
})(jQuery) ;

/**
 * 复选框全选
 * @param ckBoxElement
 * @param ckBoxName
 * @return
 */
function checkAll(ckBoxElement, ckBoxName) {
	var checkStatus = $(ckBoxElement).prop('checked');
	if (checkStatus) {
		$(":checkbox[name='" + ckBoxName + "'][disabled!='disabled']").prop('checked', true);
	} else {
		$(":checkbox[name='" + ckBoxName + "'][disabled!='disabled']").prop('checked', false);
	}
}

/**
 * 全选框选中非选中
 */
function checkSelect(ckBoxElement, ckBoxName, len){
	var arrCheck = $("input[name='"+ckBoxName+"']:checked");
	if(arrCheck.length == len){
		$('#'+ckBoxElement).prop('checked', true);
	} else {
		$('#'+ckBoxElement).prop('checked', false);
	}
}
