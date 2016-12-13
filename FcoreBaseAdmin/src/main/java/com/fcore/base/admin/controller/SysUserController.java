package com.fcore.base.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.fcore.base.admin.config.UserRealm;
import com.fcore.base.bean.Pager;
import com.fcore.base.entity.SysRole;
import com.fcore.base.entity.SysUser;
import com.fcore.base.service.SysRoleService;
import com.fcore.base.service.SysUserService;
import com.fcore.base.utils.CommUtil;
import com.fcore.base.utils.DateTimeUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value=CommonConstants.ROOT_VIEWS+"/sysUser")
public class SysUserController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private UserRealm userRealm; 
	
	@RequestMapping(value="/list")
	public String list(Model model,SysUser sysUser) {
		Pager pager = sysUserService.findForPager(sysUser);
		model.addAttribute("pager", pager);
		model.addAttribute("sysUser", sysUser);
		return "/views/sysUser/list";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Model model,SysUser sysUser) {
		if(sysUser.getId()!=null && sysUser.getId()>0){
			sysUser = sysUserService.getById(sysUser.getId());
		}
		model.addAttribute("sysUser", sysUser);
		return "/views/sysUser/edit";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(HttpServletResponse response,SysUser sysUser){
		SysUser user = this.getSessionUser();
		JSONObject object = new JSONObject();
		if(sysUser.getId() != null && sysUser.getId() >0){
			sysUser.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysUser.setUpdateUserId(user.getId());
			sysUserService.updateUser(sysUser);
			object.put("state",1);
		}else{
			sysUser.setCreateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysUser.setCreateUserId(user.getId());
			sysUser.setIsDelete(0);
			sysUser.setSalt(UUID.randomUUID().toString());
			sysUser.setPassword(userRealm.shiroMd5(sysUser.getPassword(), sysUser.getSalt(), UserRealm.hashIterations));
			long id = sysUserService.addUser(sysUser);
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
			SysUser sysUser = sysUserService.getById(Long.parseLong(id));
			sysUser.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysUser.setUpdateUserId(user.getId());
			sysUser.setIsDelete(1);
			sysUserService.update(sysUser);
			object.put("state", 1);
			object.put("msg", CommonConstants.DELETE_SUC_INFO);
		}else{
			object.put("state", -1);
			object.put("msg", CommonConstants.DELETE_ERR_INFO);
		}
		CommUtil.writeJson(response, object.toString());
	}
	
	
	@RequestMapping("/getById")
	@ResponseBody
	public SysUser getById(HttpServletRequest request){
		SysUser sysUser = null;
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			sysUser = sysUserService.getById(Long.parseLong(id));
		}
		return sysUser;
	}
	
	@RequestMapping("/checkLoginName")
	@ResponseBody
	public void checkLoginName(HttpServletResponse response,SysUser sysUser){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", sysUser.getId());
		map.put("loginName", sysUser.getLoginName());
		int count = sysUserService.checkLoginName(map);
		JSONObject object = new JSONObject();
		object.put("state", count);
		object.put("msg", CommonConstants.CHECK_LOGINNAME_MSG);
		CommUtil.writeJson(response, object.toString());
	}
	
	@RequestMapping("/getRoles")
	@ResponseBody
	public Map<String, Object> getRoles(Long userId){
		Map<String, Object> result = new HashMap<String, Object>();
		List<SysRole> roles = null;
		if(userId==null){
			roles = sysRoleService.getByParams(new HashMap<String, Object>());
		}else{
			roles = sysRoleService.getRolesForUser(userId);
		}
		result.put("roles", roles);
		return result;
	}
}
