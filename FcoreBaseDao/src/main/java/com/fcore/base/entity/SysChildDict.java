package com.fcore.base.entity;

import java.io.Serializable;

/**
* @TableName: sys_child_dict 
* @Package: com.fcore.base.entity
* @Title:SysChildDict.java 
* @Description:  
* @author: zhangjukai
* @date: 2016-09-29 09:53:19
* @version V1.0    
* create by codeFactory
*/
public class SysChildDict extends BaseEntity implements Serializable{
	/**
	*@Fields sysDictId :上级ID
	*/
	private Long sysDictId;
	/**
	*@Fields name :名称
	*/
	private String name;
	/**
	*@Fields key :key值
	*/
	private String key;
	/**
	*@Fields value :value值
	*/
	private String value;
	public void setSysDictId(Long sysDictId){
		this.sysDictId=sysDictId;
	}
	
	public Long getSysDictId(){
		return sysDictId;
	}
		public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
		public void setKey(String key){
		this.key=key;
	}
	
	public String getKey(){
		return key;
	}
		public void setValue(String value){
		this.value=value;
	}
	
	public String getValue(){
		return value;
	}
}

