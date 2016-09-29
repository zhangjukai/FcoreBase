package com.fcore.base.service;

/**
 * 参数初始化接口
 * @author zjk
 *
 */
public interface SysParamInitService {
	/**
	 * 将数据字典初始化到redis缓存中
	 */
	public void initSysDictToRedis();
}
