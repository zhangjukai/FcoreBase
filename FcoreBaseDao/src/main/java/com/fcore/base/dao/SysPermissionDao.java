package com.fcore.base.dao;

import java.util.List;
import java.util.Map;

import com.fcore.base.entity.SysPermission;

/**   
* @Title: ISysPermissionMapper.java 
* @Package com.fcore.boot.dao
* @Description: 
* @author zhangjukai
* @date 2016-09-26 16:17:23
* @version V1.0   
* create by codeFactory
*/
public interface SysPermissionDao extends BaseDao<SysPermission,Long>{
	
	public List<Map<String, Object>> getPreForTree(SysPermission sysPermission);

	public List<SysPermission> getByValue(SysPermission sysPermission);

	public List<SysPermission> getListByParents(long id);

	public List<SysPermission> getListByUserId(Long userId);

	public List<SysPermission> getMenuByUserId(Long userId);
}
