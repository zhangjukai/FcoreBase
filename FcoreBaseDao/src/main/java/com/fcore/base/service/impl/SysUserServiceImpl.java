package com.fcore.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.base.bean.Pager;
import com.fcore.base.dao.SysUserDao;
import com.fcore.base.dao.SysUserRoleDao;
import com.fcore.base.entity.SysUser;
import com.fcore.base.entity.SysUserRole;
import com.fcore.base.service.SysUserService;



 /**   
* @Title: SysUserServiceImpl.java 
* @Package com.fcore.boot.service
* @Description: 
* @author zhangjukai
* @date 2016-09-26 16:16:52
* @version V1.0   
* create by codeFactory
*/
@Service("SysUserServiceImpl")
public class SysUserServiceImpl  extends BaseServiceImpl<SysUser,Long> implements SysUserService{

	@Autowired
	private SysUserDao userDao;
	@Autowired
	private SysUserRoleDao userRoleDao;

	@Autowired
	public void setBaseDao() {
		super.setBaseDao(userDao);
	}

	@Override
	public SysUser getUserByLoginName(String loginName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginName", loginName);
		List<SysUser> users = userDao.getByParams(params);
		SysUser user = null;
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public Pager findForPager(SysUser user) {
		Pager pager = new Pager();
		if (user.getPageSize() == 0) {
			user.setPageSize(pager.getPageSize());
		}
		if (user.getPageNumber() == 0) {
			user.setPageNumber(1);
		}
		List<SysUser> list = userDao.getList(user);
		int count = userDao.getCount(user);
		pager.setList(list);
		pager.setTotalCount(count);
		pager.setPageNumber(user.getPageNumber());
		return pager;
	}

	@Override
	public int checkLoginName(Map<String, Object> map) {
		return userDao.checkLoginName(map);
	}

	@Override
	public long addUser(SysUser sysUser) {
		Long id = userDao.add(sysUser);
		if(StringUtils.isNotEmpty(sysUser.getRoleIds())){
			String[] roleIds = sysUser.getRoleIds().split(",");
			for(int i=0; i<roleIds.length;i++) {
				SysUserRole userRole = new SysUserRole();
				userRole.setCreateTime(sysUser.getCreateTime());
				userRole.setCreateUserId(sysUser.getCreateUserId());
				userRole.setIsDelete(0);
				userRole.setSysRoleId(Long.parseLong(roleIds[i]));
				userRole.setSysUserId(id);
				userRoleDao.add(userRole);
			}
		}
		return id;
	}

	@Override
	public void updateUser(SysUser sysUser) {
		userDao.update(sysUser);
		userRoleDao.deleteUserRoleByUserId(sysUser.getId());
		if(StringUtils.isNotEmpty(sysUser.getRoleIds())){
			String[] roleIds = sysUser.getRoleIds().split(",");
			for(int i=0; i<roleIds.length;i++) {
				SysUserRole userRole = new SysUserRole();
				userRole.setCreateTime(sysUser.getCreateTime());
				userRole.setCreateUserId(sysUser.getCreateUserId());
				userRole.setIsDelete(0);
				userRole.setSysRoleId(Long.parseLong(roleIds[i]));
				userRole.setSysUserId(sysUser.getId());
				userRoleDao.add(userRole);
			}
		}
	}
	
}