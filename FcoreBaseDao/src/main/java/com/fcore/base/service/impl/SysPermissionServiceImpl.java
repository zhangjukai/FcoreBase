package com.fcore.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.base.bean.Pager;
import com.fcore.base.dao.SysPermissionDao;
import com.fcore.base.entity.SysPermission;
import com.fcore.base.service.SysPermissionService;


 /**   
* @Title: SysPermissionServiceImpl.java 
* @Package com.fcore.boot.service
* @Description: 
* @author zhangjukai
* @date 2016-09-26 16:17:23
* @version V1.0   
* create by codeFactory
*/
@Service("SysPermissionServiceImpl")
public class SysPermissionServiceImpl  extends BaseServiceImpl<SysPermission,Long> implements SysPermissionService{
	@Autowired
	private SysPermissionDao sysPermissionDao;
	@Autowired
	public void setBaseDao() {
		super.setBaseDao(sysPermissionDao);
	}
	
	@Override
	public Pager findForPager(SysPermission sysPermission) {
		Pager pager = new Pager();
		if (sysPermission.getPageSize() == 0) {
			sysPermission.setPageSize(pager.getPageSize());
		}
		if (sysPermission.getPageNumber() == 0) {
			sysPermission.setPageNumber(1);
		}
		List<SysPermission> list = sysPermissionDao.getList(sysPermission);
		int count = sysPermissionDao.getCount(sysPermission);
		pager.setList(list);
		pager.setTotalCount(count);
		pager.setPageNumber(sysPermission.getPageNumber());
		return pager;
	}
	
}