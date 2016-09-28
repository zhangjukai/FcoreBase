package com.fcore.base.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.fcore.base.entity.SysUser;

public class BaseController {
	public  SysUser getSessionUser(){
		Subject subject = SecurityUtils.getSubject();
		return (SysUser) subject.getSession().getAttribute("sessionUser");
	}
}
