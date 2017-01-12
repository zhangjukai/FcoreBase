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
import com.fcore.base.entity.SysChildDict;
import com.fcore.base.entity.SysDict;
import com.fcore.base.entity.SysUser;
import com.fcore.base.service.RedisService;
import com.fcore.base.service.SysChildDictService;
import com.fcore.base.service.SysDictService;
import com.fcore.base.utils.CommUtil;
import com.fcore.base.utils.DateTimeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Controller
@RequestMapping(value=CommonConstants.ROOT_VIEWS+"/sysDict")
public class SysDictController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(SysDictController.class);

	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
	private RedisService redisService; 
	
	@Autowired
	private SysChildDictService childDictService; 
	
	@RequestMapping(value="/list")
	public String list(Model model,SysDict sysDict) {
		Pager pager = sysDictService.findForPager(sysDict);
		model.addAttribute("pager", pager);
		model.addAttribute("sysDict", sysDict);
		return "/views/sysDict/list";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Model model,SysDict sysDict) {
		if(sysDict.getId()!=null && sysDict.getId()>0){
			sysDict = sysDictService.getById(sysDict.getId());
			
		}
		model.addAttribute("sysDict", sysDict);
		return "/views/sysDict/edit";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(HttpServletResponse response,SysDict sysDict){
		SysUser user = this.getSessionUser();
		JSONObject object = new JSONObject();
		if(sysDict.getId() != null && sysDict.getId() >0){
			sysDict.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysDict.setUpdateUserId(user.getId());
			sysDictService.update(sysDict);
			object.put("state",1);
		}else{
			sysDict.setCreateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysDict.setCreateUserId(user.getId());
			sysDict.setIsDelete(0);
			long id = sysDictService.add(sysDict);
			object.put("state",1);
		}
		
		//添加到缓存中
		if(sysDict.getIsMoreLevel()!=1){
			JSONObject value = new JSONObject();
			value.put("value", sysDict.getValue());
			redisService.set(sysDict.getKey(), value);
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
			SysDict sysDict = sysDictService.getById(Long.parseLong(id));
			sysDict.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysDict.setUpdateUserId(user.getId());
			sysDict.setIsDelete(1);
			sysDictService.update(sysDict);
			
			//删除缓存中的数据
			redisService.remove(sysDict.getKey());
		}
		CommUtil.writeJson(response, object.toString());
	}
	
	
	@RequestMapping("/getById")
	@ResponseBody
	public SysDict getById(HttpServletRequest request){
		SysDict sysDict = null;
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			sysDict = sysDictService.getById(Long.parseLong(id));
		}
		return sysDict;
	}
	
	@RequestMapping("/getByKey")
	@ResponseBody
	public Map<String, Object> getByKey(String key){
		Map<String, Object> result = new HashMap<String,Object>();
		Object data = redisService.get(key);
		result.put("data", data);
		return result;
	}
	
	@RequestMapping(value="/editChild")
	public String editChild(Model model,SysChildDict childDict) {
		if(childDict.getId()!=null && childDict.getId()>0){
			childDict = childDictService.getById(childDict.getId());
		}
		model.addAttribute("childDict", childDict);
		return "/views/sysDict/editChild";
	}
	
	@RequestMapping("/saveChild")
	@ResponseBody
	public void saveChild(HttpServletResponse response,SysChildDict childDict){
		SysUser user = this.getSessionUser();
		JSONObject object = new JSONObject();
		if(childDict.getId() != null && childDict.getId() >0){
			childDict.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			childDict.setUpdateUserId(user.getId());
			childDictService.update(childDict);
			object.put("state",1);
		}else{
			childDict.setCreateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			childDict.setCreateUserId(user.getId());
			childDict.setIsDelete(0);
			long id = childDictService.add(childDict);
			object.put("state",1);
		}
		
		//更新redis
		SysDict dict = sysDictService.getById(childDict.getSysDictId());
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("sysDictId", childDict.getSysDictId());
		JSONObject value = new JSONObject();
		List<SysChildDict> list = childDictService.getByParams(param);
		value.put("isMoreLevel", dict.getIsMoreLevel());
		value.put("value", JSONArray.fromObject(list));
		redisService.set(dict.getKey(), value);
		
 		CommUtil.writeJson(response, object.toString());
	}

}
