package com.fcore.base.admin;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ServletComponentScan
@EnableScheduling
@MapperScan(value={"com.fcore.base.dao"})
@ComponentScan(value={"com.fcore.base"})
public class Application {
	
	protected static Logger logger=LoggerFactory.getLogger(Application.class);  
    
    @PostConstruct
    public  void init() {
    	//========初始化开始============
    	logger.info("=================系统初始化===================");
    }
    
    @PreDestroy
	public void  dostory(){
    	logger.info("=================系统关闭注销===================");
	}
    
	public static void main(String[] args) {
		//启动程序
		SpringApplication.run(Application.class, args);
	}
}
