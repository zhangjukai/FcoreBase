package com.fcore.base.entity;

import java.io.Serializable;

/**
* @TableName: sys_file 
* @Package: com.fcore.base.entity
* @Title:SysFile.java 
* @Description:  
* @author: zhangjukai
* @date: 2016-09-30 17:39:44
* @version V1.0    
* create by codeFactory
*/
public class SysFile extends BaseEntity implements Serializable{
	/**
	*@Fields fileName :文件名
	*/
	private String fileName;
	/**
	*@Fields fileSize :文件大小
	*/
	private Long fileSize;
	/**
	*@Fields fileType :文件类型
	*/
	private String fileType;
	/**
	*@Fields suffix :后缀名
	*/
	private String suffix;
	/**
	*@Fields filePath :文件路径
	*/
	private String filePath;
		public void setFileName(String fileName){
		this.fileName=fileName;
	}
	
	public String getFileName(){
		return fileName;
	}
		public void setFileSize(Long fileSize){
		this.fileSize=fileSize;
	}
	
	public Long getFileSize(){
		return fileSize;
	}
		public void setFileType(String fileType){
		this.fileType=fileType;
	}
	
	public String getFileType(){
		return fileType;
	}
		public void setSuffix(String suffix){
		this.suffix=suffix;
	}
	
	public String getSuffix(){
		return suffix;
	}
		public void setFilePath(String filePath){
		this.filePath=filePath;
	}
	
	public String getFilePath(){
		return filePath;
	}

	public SysFile(String fileName, Long fileSize, String fileType, String suffix, String filePath) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.suffix = suffix;
		this.filePath = filePath;
	}
	public SysFile() { }
}

