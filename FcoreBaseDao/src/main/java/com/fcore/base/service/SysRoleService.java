package com.fcore.base.service;

import java.util.List;

import com.fcore.base.entity.SysRole;

public interface SysRoleService extends BaseService<SysRole,Long>{

	public long addRole(SysRole sysRole);

	public Boolean checkRoleName(SysRole role);

	public void updateRole(SysRole sysRole);

	public List<SysRole> getRolesForUser(Long userId);

}