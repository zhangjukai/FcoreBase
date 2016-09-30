package com.fcore.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.base.bean.Pager;
import com.fcore.base.dao.SysDictDao;
import com.fcore.base.entity.SysDict;
import com.fcore.base.service.SysDictService;

 /**   
* @Title: SysDictServiceImpl.java 
* @Package com.fcore.base.service
* @Description: 
* @author zhangjukai
* @date 2016-09-29 09:39:44
* @version V1.0   
* create by codeFactory
*/
@Service("SysDictServiceImpl")
public class SysDictServiceImpl  extends BaseServiceImpl<SysDict,Long> implements SysDictService{
	@Autowired
	private SysDictDao sysDictDao;
	@Autowired
	public void setBaseDao() {
		super.setBaseDao(sysDictDao);
	}
	
	@Override
	public Pager findForPager(SysDict sysDict) {
		Pager pager = new Pager();
		if (sysDict.getPageSize() == 0) {
			sysDict.setPageSize(pager.getPageSize());
		}
		if (sysDict.getPageNumber() == 0) {
			sysDict.setPageNumber(1);
		}
		List<SysDict> list = sysDictDao.getList(sysDict);
		int count = sysDictDao.getCount(sysDict);
		pager.setList(list);
		pager.setTotalCount(count);
		pager.setPageNumber(sysDict.getPageNumber());
		return pager;
	}
	
}