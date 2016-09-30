package com.fcore.base.dao;

import java.util.Map;

import com.fcore.base.entity.SysUser;

public interface SysUserDao extends BaseDao<SysUser, Long> {

	/**
	 * 验证登录名是否重复
	 * @param map
	 * @return
	 */
	public int checkLoginName(Map<String, Object> map);

}
