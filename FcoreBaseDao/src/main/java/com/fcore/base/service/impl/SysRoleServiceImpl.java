package com.fcore.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.base.bean.Pager;
import com.fcore.base.dao.SysRoleDao;
import com.fcore.base.entity.SysRole;
import com.fcore.base.service.SysRoleService;



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
	
}