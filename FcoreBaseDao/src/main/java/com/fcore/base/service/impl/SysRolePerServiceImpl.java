package com.fcore.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.base.bean.Pager;
import com.fcore.base.dao.SysRolePerDao;
import com.fcore.base.entity.SysRolePer;
import com.fcore.base.service.SysRolePerService;


 /**   
* @Title: SysRolePerServiceImpl.java 
* @Package com.fcore.boot.service
* @Description: 
* @author zhangjukai
* @date 2016-09-26 16:21:19
* @version V1.0   
* create by codeFactory
*/
@Service("SysRolePerServiceImpl")
public class SysRolePerServiceImpl  extends BaseServiceImpl<SysRolePer,Long> implements SysRolePerService{
	@Autowired
	private SysRolePerDao sysRolePerDao;
	@Autowired
	public void setBaseDao() {
		super.setBaseDao(sysRolePerDao);
	}
	
	@Override
	public Pager findForPager(SysRolePer sysRolePer) {
		Pager pager = new Pager();
		if (sysRolePer.getPageSize() == 0) {
			sysRolePer.setPageSize(pager.getPageSize());
		}
		if (sysRolePer.getPageNumber() == 0) {
			sysRolePer.setPageNumber(1);
		}
		List<SysRolePer> list = sysRolePerDao.getList(sysRolePer);
		int count = sysRolePerDao.getCount(sysRolePer);
		pager.setList(list);
		pager.setTotalCount(count);
		pager.setPageNumber(sysRolePer.getPageNumber());
		return pager;
	}
	
}