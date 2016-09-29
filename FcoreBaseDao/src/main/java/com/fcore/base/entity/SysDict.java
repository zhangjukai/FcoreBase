package com.fcore.base.entity;

import java.io.Serializable;

/**
* @TableName: sys_dict 
* @Package: com.fcore.base.entity
* @Title:SysDict.java 
* @Description:  
* @author: zhangjukai
* @date: 2016-09-29 09:39:43
* @version V1.0    
* create by codeFactory
*/
public class SysDict extends BaseEntity implements Serializable{
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
	/**
	*@Fields isMoreLevel :是否多级 1是 2否
	*/
	private Integer isMoreLevel;
	/**
	*@Fields remark :说明
	*/
	private String remark;
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
		public void setIsMoreLevel(Integer isMoreLevel){
		this.isMoreLevel=isMoreLevel;
	}
	
	public Integer getIsMoreLevel(){
		return isMoreLevel;
	}
		public void setRemark(String remark){
		this.remark=remark;
	}
	
	public String getRemark(){
		return remark;
	}
}

