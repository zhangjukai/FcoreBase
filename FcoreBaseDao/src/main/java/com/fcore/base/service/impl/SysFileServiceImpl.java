package com.fcore.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.base.bean.Pager;
import com.fcore.base.dao.SysFileDao;
import com.fcore.base.entity.SysFile;
import com.fcore.base.service.SysFileService;

 /**   
* @Title: SysFileServiceImpl.java 
* @Package com.fcore.base.service
* @Description: 
* @author zhangjukai
* @date 2016-09-30 17:39:45
* @version V1.0   
* create by codeFactory
*/
@Service("SysFileServiceImpl")
public class SysFileServiceImpl  extends BaseServiceImpl<SysFile,Long> implements SysFileService{
	@Autowired
	private SysFileDao sysFileDao;
	@Autowired
	public void setBaseDao() {
		super.setBaseDao(sysFileDao);
	}
	
	@Override
	public Pager findForPager(SysFile sysFile) {
		Pager pager = new Pager();
		if (sysFile.getPageSize() == 0) {
			sysFile.setPageSize(pager.getPageSize());
		}
		if (sysFile.getPageNumber() == 0) {
			sysFile.setPageNumber(1);
		}
		List<SysFile> list = sysFileDao.getList(sysFile);
		int count = sysFileDao.getCount(sysFile);
		pager.setList(list);
		pager.setTotalCount(count);
		pager.setPageNumber(sysFile.getPageNumber());
		return pager;
	}
	
}