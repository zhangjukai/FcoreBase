package com.fcore.base.admin.filter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitParam {
	protected static Logger logger=LoggerFactory.getLogger(InitParam.class);  
	@PostConstruct
    public  void init() {
    	//========初始化开始============
    	logger.info("=================系统初始化===================");
    }
    
    @PreDestroy
	public void  dostory(){
    	logger.info("=================系统关闭注销===================");
	}
}
