package com.fcore.base.service;

import java.util.List;
import java.util.Map;

import com.fcore.base.entity.SysPermission;

public interface SysPermissionService extends BaseService<SysPermission,Long>{
	/**
	 * 查询树形结构
	 * @param sysPermission
	 * @return
	 */
	public List<Map<String, Object>> getPreForTree(SysPermission sysPermission);

	/**
	 * 根据value查询
	 * @param value
	 * @return
	 */
	public List<SysPermission> getByValue(SysPermission permission);

	/**
	 * 查询所有子项
	 * @param id
	 * @return
	 */
	public List<SysPermission> getListByParents(long id);

	/**
	 * 查询用户的权限
	 * @param id
	 * @return
	 */
	public List<SysPermission> getListByUserId(Long id);

	/**
	 * 获取菜单
	 * @param id
	 * @return
	 */
	public List<SysPermission> getMenuByUserId(Long id);

	/**
	 * 封装json数据
	 * @param sysPermissions
	 * @return
	 */
	public String getAllMenuByList(List<SysPermission> sysPermissions);

	public List<Long> deletePermission(long id);
}