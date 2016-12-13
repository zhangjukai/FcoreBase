package com.fcore.base.dao;

import java.util.List;

import com.fcore.base.entity.SysRole;

/**   
* @Title: ISysRoleMapper.java 
* @Package com.fcore.boot.dao
* @Description: 
* @author zhangjukai
* @date 2016-09-26 16:38:45
* @version V1.0   
* create by codeFactory
*/
public interface SysRoleDao extends BaseDao<SysRole,Long>{

	List<SysRole> checkRoleName(SysRole role);

	List<SysRole> getRolesForUser(Long userId);
	
}
