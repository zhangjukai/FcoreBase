package com.fcore.base.service;

import com.fcore.base.entity.SysUser;

public interface SysUserService extends BaseService<SysUser,Long>{

	/**
	 * 通过用户名查询
	 * @param loginName
	 * @return
	 */
	public SysUser getUserByLoginName(String loginName);

}
