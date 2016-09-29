package com.fcore.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.base.dao.SysChildDictDao;
import com.fcore.base.dao.SysDictDao;
import com.fcore.base.entity.SysChildDict;
import com.fcore.base.entity.SysDict;
import com.fcore.base.service.RedisService;
import com.fcore.base.service.SysParamInitService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("SysParamInitServiceImpl")
public class SysParamInitServiceImpl implements SysParamInitService {

	@Autowired
	private SysDictDao sysDictDao; 
	@Autowired
	private SysChildDictDao sysChildDictDao; 
	@Autowired
	private RedisService redisService; 
	
	@Override
	public void initSysDictToRedis() {
		//查询数据字典数据
		Map<String, Object> params = new HashMap<String, Object>();
		List<SysDict> sysDicts = sysDictDao.getByParams(params);
		List<SysChildDict> childDicts = sysChildDictDao.getByParams(params);
		List<SysChildDict> cds = null;
		JSONObject object = null;
		
		for (SysDict sysDict : sysDicts) {
			//遍历封装 
			object = new JSONObject();
			object.put("isMoreLevel", sysDict.getIsMoreLevel());
			if (sysDict.getIsMoreLevel() == 1) {
				cds = childDicts.stream().filter(childDict -> childDict.getSysDictId() == sysDict.getId())
						.collect(Collectors.toList());
				object.put("value", JSONArray.fromObject(cds));
			} else {
				object.put("value", sysDict.getValue());
			}
			//存放到redis中
			redisService.set(sysDict.getKey(), object);
		}
	}

}
