package com.faceye.feature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.faceye.feature.upload.Upload;

/**
 * 提供统一的MultiPart处理
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2015年2月2日
 */
public class MultiPartController {

	/**
	 * 上传
	 * @todo
	 * @param upfile
	 * @param request
	 * @param response
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月2日
	 */
	public void upload(@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request, HttpServletResponse response){
		Upload.upoad(request);
	}
}
