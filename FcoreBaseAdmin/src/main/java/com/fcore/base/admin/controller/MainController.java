package com.fcore.base.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/views")
public class MainController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="/")
	public String main(Model model){
		logger.info("登录成功后，跳转到首页！");
		return "views/index";
	}
}
