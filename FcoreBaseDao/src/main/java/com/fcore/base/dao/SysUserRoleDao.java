package com.fcore.base.dao;

import com.fcore.base.entity.SysUserRole;

/**   
* @Title: ISysUserRoleMapper.java 
* @Package com.fcore.boot.dao
* @Description: 
* @author zhangjukai
* @date 2016-09-26 16:21:33
* @version V1.0   
* create by codeFactory
*/
public interface SysUserRoleDao extends BaseDao<SysUserRole,Long>{

	void deleteUserRoleByUserId(Long userId);
	
}
