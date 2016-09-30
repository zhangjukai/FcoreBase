package com.fcore.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.base.bean.Pager;
import com.fcore.base.dao.SysChildDictDao;
import com.fcore.base.entity.SysChildDict;
import com.fcore.base.service.SysChildDictService;

 /**   
* @Title: SysChildDictServiceImpl.java 
* @Package com.fcore.base.service
* @Description: 
* @author zhangjukai
* @date 2016-09-29 09:53:19
* @version V1.0   
* create by codeFactory
*/
@Service("SysChildDictServiceImpl")
public class SysChildDictServiceImpl  extends BaseServiceImpl<SysChildDict,Long> implements SysChildDictService{
	@Autowired
	private SysChildDictDao sysChildDictDao;
	@Autowired
	public void setBaseDao() {
		super.setBaseDao(sysChildDictDao);
	}
	
	@Override
	public Pager findForPager(SysChildDict sysChildDict) {
		Pager pager = new Pager();
		if (sysChildDict.getPageSize() == 0) {
			sysChildDict.setPageSize(pager.getPageSize());
		}
		if (sysChildDict.getPageNumber() == 0) {
			sysChildDict.setPageNumber(1);
		}
		List<SysChildDict> list = sysChildDictDao.getList(sysChildDict);
		int count = sysChildDictDao.getCount(sysChildDict);
		pager.setList(list);
		pager.setTotalCount(count);
		pager.setPageNumber(sysChildDict.getPageNumber());
		return pager;
	}
	
}