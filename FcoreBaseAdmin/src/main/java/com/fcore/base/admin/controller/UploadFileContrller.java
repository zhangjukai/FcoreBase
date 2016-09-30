package com.fcore.base.admin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcore.base.admin.bean.CommonConstants;
import com.fcore.base.admin.utils.UploadFileUtil;
import com.fcore.base.service.RedisService;
import com.fcore.base.utils.CommUtil;

@Controller
public class UploadFileContrller {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping(value = "uploadFilePage")
	public String uploadFilePage() {
		return "/views/uploadFile";
	}

	@RequestMapping(value = "uploadFile")
	@ResponseBody
	public void uploadFile(HttpServletRequest request, HttpServletResponse response, String fileName) {
		JSONObject object = new JSONObject();
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String uploadFileType = (String) redisService.get("UPLOAD_FILE_TYPE");
		if (StringUtils.isNotEmpty(uploadFileType) && uploadFileType.contains(suffix)) {
			try {
				Calendar ca = Calendar.getInstance();
				String filePath = (String)redisService.get("UPLOAD_FILE_ROOT_PATH") + ca.get(Calendar.YEAR) + "/" + ca.get(Calendar.MONTH) + "/" + ca.get(Calendar.DAY_OF_MONTH);
				filePath = UploadFileUtil.uploudFile(filePath, suffix, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			object.put("state", 1);
		} else {
			object.put("state", CommonConstants.UPLOAD_FILE_ERROR_TYPE_CODE);
			object.put("msg", CommonConstants.UPLOAD_FILE_ERROR_TYPE_MSG);
		}
		CommUtil.writeJson(response, object.toString());
	}

	@RequestMapping(value = "downloadFile")
	@ResponseBody
	public void download(String path, HttpServletResponse response, HttpServletRequest request) {
		try {
			// request.getSession().getServletContext().getRealPath("/")+path;
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
