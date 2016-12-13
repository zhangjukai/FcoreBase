package com.fcore.base.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.base.bean.Pager;
import com.fcore.base.dao.SysRoleDao;
import com.fcore.base.dao.SysRolePerDao;
import com.fcore.base.entity.SysRole;
import com.fcore.base.entity.SysRolePer;
import com.fcore.base.service.SysRoleService;
import com.fcore.base.utils.DateTimeUtil;

import net.sf.json.JSONArray;



 /**   
* @Title: SysRoleServiceImpl.java 
* @Package com.fcore.boot.service
* @Description: 
* @author zhangjukai
* @date 2016-09-26 16:38:45
* @version V1.0   
* create by codeFactory
*/
@Service("SysRoleServiceImpl")
public class SysRoleServiceImpl  extends BaseServiceImpl<SysRole,Long> implements SysRoleService{
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRolePerDao rolePerDao; 
	@Autowired
	public void setBaseDao() {
		super.setBaseDao(sysRoleDao);
	}
	
	@Override
	public Pager findForPager(SysRole sysRole) {
		Pager pager = new Pager();
		if (sysRole.getPageSize() == 0) {
			sysRole.setPageSize(pager.getPageSize());
		}
		if (sysRole.getPageNumber() == 0) {
			sysRole.setPageNumber(1);
		}
		List<SysRole> list = sysRoleDao.getList(sysRole);
		int count = sysRoleDao.getCount(sysRole);
		pager.setList(list);
		pager.setTotalCount(count);
		pager.setPageNumber(sysRole.getPageNumber());
		return pager;
	}

	@Override
	public long addRole(SysRole sysRole) {
		Long id = sysRoleDao.add(sysRole);
		if(StringUtils.isNotEmpty(sysRole.getPerIds())){
			String[] arr = sysRole.getPerIds().split(",");
			for(int i=0;i<arr.length;i++){
				Long perId = Long.parseLong(arr[i]);
				SysRolePer rolePer = new SysRolePer();
				rolePer.setCreateTime(sysRole.getCreateTime());
				rolePer.setCreateUserId(sysRole.getCreateUserId());
				rolePer.setIsDelete(0);
				rolePer.setSysPerId(perId);
				rolePer.setSysRoleId(id);
				rolePerDao.add(rolePer);
			}
		}
		return id;
	}

	@Override
	public Boolean checkRoleName(SysRole role) {
		List<SysRole> list = sysRoleDao.checkRoleName(role);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public void updateRole(SysRole sysRole) {
		sysRoleDao.update(sysRole);
		//先删除现有权限
		rolePerDao.deleteByRoleId(sysRole.getId());
		if(StringUtils.isNotEmpty(sysRole.getPerIds())){
			String[] arr = sysRole.getPerIds().split(",");
			for(int i=0;i<arr.length;i++){
				Long perId = Long.parseLong(arr[i]);
				SysRolePer rolePer = new SysRolePer();
				rolePer.setCreateTime(sysRole.getCreateTime());
				rolePer.setCreateUserId(sysRole.getCreateUserId());
				rolePer.setIsDelete(0);
				rolePer.setSysPerId(perId);
				rolePer.setSysRoleId(sysRole.getId());
				rolePerDao.add(rolePer);
			}
		}
	}

	@Override
	public List<SysRole> getRolesForUser(Long userId) {
		return sysRoleDao.getRolesForUser(userId);
	}
	
}