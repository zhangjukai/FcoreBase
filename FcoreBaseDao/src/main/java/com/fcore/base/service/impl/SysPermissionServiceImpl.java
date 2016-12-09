package com.fcore.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.base.bean.Pager;
import com.fcore.base.dao.SysPermissionDao;
import com.fcore.base.entity.SysPermission;
import com.fcore.base.service.SysPermissionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


 /**   
* @Title: SysPermissionServiceImpl.java 
* @Package com.fcore.boot.service
* @Description: 
* @author zhangjukai
* @date 2016-09-26 16:17:23
* @version V1.0   
* create by codeFactory
*/
@Service("SysPermissionServiceImpl")
public class SysPermissionServiceImpl  extends BaseServiceImpl<SysPermission,Long> implements SysPermissionService{
	@Autowired
	private SysPermissionDao sysPermissionDao;
	@Autowired
	public void setBaseDao() {
		super.setBaseDao(sysPermissionDao);
	}
	
	@Override
	public Pager findForPager(SysPermission sysPermission) {
		Pager pager = new Pager();
		if (sysPermission.getPageSize() == 0) {
			sysPermission.setPageSize(pager.getPageSize());
		}
		if (sysPermission.getPageNumber() == 0) {
			sysPermission.setPageNumber(1);
		}
		List<SysPermission> list = sysPermissionDao.getList(sysPermission);
		int count = sysPermissionDao.getCount(sysPermission);
		pager.setList(list);
		pager.setTotalCount(count);
		pager.setPageNumber(sysPermission.getPageNumber());
		return pager;
	}

	@Override
	public List<Map<String, Object>> getPreForTree(SysPermission sysPermission) {
		List<Map<String, Object>> lists = sysPermissionDao.getPreForTree(sysPermission);
		return lists;
	}
	@Override
	public List<SysPermission> getByValue(SysPermission permission) {
		return sysPermissionDao.getByValue(permission);
	}
	@Override
	public List<SysPermission> getListByParents(long id) {
		return sysPermissionDao.getListByParents(id);
	}
	@Override
	public List<SysPermission> getListByUserId(Long id) {
		return sysPermissionDao.getListByUserId(id);
	}
	@Override
	public List<SysPermission> getMenuByUserId(Long id) {
		return sysPermissionDao.getMenuByUserId(id);
	}
	@Override
	public String getAllMenuByList(List<SysPermission> sysPermissions) {
		List<SysPermission> pers1 = new ArrayList<SysPermission>();
		List<SysPermission> pers2 = new ArrayList<SysPermission>();
		
		for(SysPermission sysPermission:sysPermissions){
			if(sysPermission.getParentId()==0){
				pers1.add(sysPermission);
			}else {
				pers2.add(sysPermission);
			}
		}
		JSONArray allMenu = new JSONArray();
		JSONObject oneMenu = null;
		JSONObject thOneMenu = null;
		JSONArray array = null;
		for(SysPermission permission:pers1){
			oneMenu = new JSONObject();
			oneMenu.put("text", permission.getName());
			array = new JSONArray();
			for(SysPermission permission2:pers2){
				if(permission2.getParentId()==permission.getId()){
					thOneMenu = new JSONObject();
					thOneMenu.put("id", permission2.getValue());
					thOneMenu.put("text", permission2.getName());
					thOneMenu.put("href", permission2.getHref());
					array.add(thOneMenu);
				}
			}
			oneMenu.put("items", array);
			allMenu.add(oneMenu);
		}
		
		return allMenu.toString();
	}

	@Override
	public List<Long> deletePermission(long id) {
		SysPermission permission = this.getById(id);
		List<Long> list = new ArrayList<Long>();
		if(permission!=null){
			list.add(permission.getId());
			//删除所有的子菜单
			List<SysPermission> permissions = this.getListByParents(id);
			for(SysPermission sysPermission:permissions){
				sysPermission.setIsDelete(1);
				this.update(sysPermission);
				list.add(sysPermission.getId());
			}
			permission.setIsDelete(1);
			this.update(permission);
		}
		return list;
	}
	
}