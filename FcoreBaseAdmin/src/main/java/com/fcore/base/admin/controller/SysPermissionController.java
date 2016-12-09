package com.fcore.base.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fcore.base.entity.SysPermission;
import com.fcore.base.entity.SysUser;
import com.fcore.base.service.SysPermissionService;
import com.fcore.base.utils.CommUtil;
import com.fcore.base.utils.DateTimeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value=CommonConstants.ROOT_VIEWS+"/sysPermission")
public class SysPermissionController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(SysPermissionController.class);

	@Autowired
	private SysPermissionService sysPermissionService;
	
	@RequestMapping(value="/list")
	public String list(Model model,SysPermission sysPermission) {
		Pager pager = sysPermissionService.findForPager(sysPermission);
		model.addAttribute("pager", pager);
		model.addAttribute("sysPermission", sysPermission);
		return "/views/sysPermission/list";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Model model,SysPermission sysPermission) {
		if(sysPermission.getId()!=null && sysPermission.getId()>0){
			sysPermission = sysPermissionService.getById(sysPermission.getId());
		}
		model.addAttribute("sysPermission", sysPermission);
		return "/views/sysPermission/edit";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(HttpServletResponse response,SysPermission sysPermission){
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser user = this.getSessionUser();
		if(sysPermission.getId() != null && sysPermission.getId() >0){
			sysPermission.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysPermission.setUpdateUserId(user.getId());
			sysPermissionService.update(sysPermission);
			map.put("editType", 2);
		}else{
			sysPermission.setCreateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysPermission.setCreateUserId(user.getId());
			sysPermission.setIsDelete(0);
			sysPermission.setLevelCode(1);
			SysPermission permission = sysPermissionService.getById(sysPermission.getParentId());
			if(permission!=null){
				sysPermission.setLevelCode(permission.getLevelCode()+1);
				if(StringUtils.isNotEmpty(permission.getParentIds())){
					sysPermission.setParentIds(permission.getParentIds()+"/"+permission.getId());
				}else {
					sysPermission.setParentIds("/"+permission.getId());					
				}
			}
			sysPermissionService.add(sysPermission);
			map.put("editType", 1);
		}
		map.put("sysPermission", sysPermission);
		return map;
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public Map<String, Object> deleteById(long id){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Long> ids = sysPermissionService.deletePermission(id);
		map.put("ids", ids);
		return map;
	}
	
	
	@RequestMapping("/getById")
	@ResponseBody
	public SysPermission getById(HttpServletRequest request){
		SysPermission sysPermission = null;
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			sysPermission = sysPermissionService.getById(Long.parseLong(id));
		}
		return sysPermission;
	}
	
	/**
	 * 获取树形结构
	 * @param response
	 */
	@RequestMapping("getPreForTree")
	@ResponseBody
	public void getPreForTree(HttpServletResponse response){
		SysPermission sysPermission = new SysPermission();
		List<Map<String, Object>> maps = sysPermissionService.getPreForTree(sysPermission);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 0);
		map.put("name", "权限管理");
		map.put("Pid", -1);
		map.put("open", true);
		maps.add(map);
		CommUtil.writeJson(response, JSONArray.fromObject(maps).toString());
	}
	
	/**
	 * 唯一性验证
	 * @param value
	 * @param response
	 */
	@RequestMapping("checkPerValue")
	public void checkPerValue(SysPermission permission,HttpServletResponse response) {
		List<SysPermission> sysPermissions = sysPermissionService.getByValue(permission);
		boolean flag = true;
		if(sysPermissions!=null && sysPermissions.size()>0){
			flag = false;
		}
		JSONObject object = new JSONObject();
		object.put("flag", flag);
		CommUtil.writeJson(response, object.toString());
	}
}
