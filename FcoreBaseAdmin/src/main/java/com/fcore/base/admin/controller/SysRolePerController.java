package com.fcore.base.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcore.base.admin.bean.CommonConstants;
import com.fcore.base.bean.Pager;
import com.fcore.base.entity.SysRolePer;
import com.fcore.base.entity.SysUser;
import com.fcore.base.service.SysRolePerService;
import com.fcore.base.utils.CommUtil;
import com.fcore.base.utils.DateTimeUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value=CommonConstants.ROOT_VIEWS+"/sysRolePer")
public class SysRolePerController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(SysRolePerController.class);

	@Autowired
	private SysRolePerService sysRolePerService;
	
	@RequestMapping(value="/list")
	public String list(Model model,SysRolePer sysRolePer) {
		Pager pager = sysRolePerService.findForPager(sysRolePer);
		model.addAttribute("pager", pager);
		model.addAttribute("sysRolePer", sysRolePer);
		return "/views/sysRolePer/list";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Model model,SysRolePer sysRolePer) {
		if(sysRolePer.getId()!=null && sysRolePer.getId()>0){
			sysRolePer = sysRolePerService.getById(sysRolePer.getId());
		}
		model.addAttribute("sysRolePer", sysRolePer);
		return "/views/sysRolePer/edit";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(HttpServletResponse response,SysRolePer sysRolePer){
		SysUser user = this.getSessionUser();
		JSONObject object = new JSONObject();
		if(sysRolePer.getSysRoleId() != null && sysRolePer.getSysRoleId() >0){
			sysRolePer.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysRolePer.setUpdateUserId(user.getId());
			sysRolePerService.update(sysRolePer);
			object.put("state",1);
		}else{
			sysRolePer.setCreateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysRolePer.setCreateUserId(user.getId());
			sysRolePer.setIsDelete(1);
			long id = sysRolePerService.add(sysRolePer);
			object.put("state",1);
		}
		CommUtil.writeJson(response, object.toString());
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public void deleteById(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		SysUser user = this.getSessionUser();
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			SysRolePer sysRolePer = sysRolePerService.getById(Long.parseLong(id));
			sysRolePer.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysRolePer.setUpdateUserId(user.getId());
			sysRolePer.setIsDelete(2);
			sysRolePerService.update(sysRolePer);
		}
		CommUtil.writeJson(response, object.toString());
	}
	
	
	@RequestMapping("/getById")
	@ResponseBody
	public SysRolePer getById(HttpServletRequest request){
		SysRolePer sysRolePer = null;
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			sysRolePer = sysRolePerService.getById(Long.parseLong(id));
		}
		return sysRolePer;
	}
}
