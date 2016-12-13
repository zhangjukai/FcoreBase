package com.fcore.base.service;

import java.util.Map;

import com.fcore.base.entity.SysUser;

public interface SysUserService extends BaseService<SysUser,Long>{

	/**
	 * 通过用户名查询
	 * @param loginName
	 * @return
	 */
	public SysUser getUserByLoginName(String loginName);

	/**
	 * 验证登录名是否重复
	 * @param map
	 * @return
	 */
	public int checkLoginName(Map<String, Object> map);

	public long addUser(SysUser sysUser);

	public void updateUser(SysUser sysUser);

}
