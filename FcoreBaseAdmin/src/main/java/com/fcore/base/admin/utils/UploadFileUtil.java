package com.fcore.base.admin.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class UploadFileUtil {

	/**
	 * 上传文件 
	 * @param suffix
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String uploudFile(String filePath, String suffix, HttpServletRequest request)throws Exception {
		// 上传文件保存路径
		String savePath = filePath;
		File file = new File(savePath);
		// 获取文件对象
		if (!file.exists()) {
			// 创建文件夹
			file.mkdirs();
		}
		String fileName = UUID.randomUUID().toString() + suffix;
		savePath = savePath + File.separator + fileName;
		file = new File(savePath);
		BufferedInputStream fileIn;
		try {
			fileIn = new BufferedInputStream(request.getInputStream());
			BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(file));
			byte[] buf = new byte[1024];
			Long size = 0l;
			while (true) {
				// 读取数据
				int bytesIn = fileIn.read(buf, 0, 1024);
				if (bytesIn == -1) {
					break;
				} else {
					size += bytesIn;
					fileOut.write(buf, 0, bytesIn);
				}
			}
			fileOut.flush();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  savePath;
	}
	
	/**
	 * 文件单位大小换算
	 * @param fileLength
	 * @return 包含单位的字符串
	 */
	public String conversion(Long fileLength){
		String str = "";
		if(fileLength>1024){
			str = fileLength%1024 +"B";
			fileLength = fileLength/1024;
			if(fileLength>1024){
				str = fileLength%1024+"KB" + str;
				fileLength = fileLength/1024;
				if(fileLength>1024){
					return fileLength/1024 + fileLength%1024 +"MB" +str;
				}else {
					return fileLength+"MB" +str;
				}
			}else {
				return fileLength+"KB" + str;
			}
		}else {
			return fileLength + "B";
		}
	}

	public static String getFileType(String fileType) {
		String str = "notype";
		if(StringUtils.isNotEmpty(fileType)){
			if(fileType.contains("word")){
				str = "word";
			}
			if(fileType.contains("video")){
				str = "video";
			}
			if(fileType.contains("image")){
				str = "image";
			}
			if(fileType.contains("excel") || fileType.contains("sheet")){
				str = "excel";
			}
			if(fileType.contains("pdf")){
				str = "pdf";
			}
			if(fileType.contains("ppt")){
				str = "ppt";
			}
		}
		return str;
	}
	
	
}
