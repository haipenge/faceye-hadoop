package com.faceye.feature.upload;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faceye.feature.service.PropertyService;
import com.faceye.feature.service.impl.BeanContextUtil;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.RandUtil;

/**
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年2月20日
 */
public class Path {
	private static Logger logger = LoggerFactory.getLogger(Path.class);

	/**
	 * 默认存储路径。
	 */
//	private static final String DEFAULT_PARENT = "/work/server/document/pic/";

	/**
	 * 今天的默认路径（yyyyMMdd)
	 */
	private static String TODAY_DIR = "";

	/**
	 * 根据当前日期取得目录
	 * 
	 * @todo
	 * @author:@haipenge haipenge@gmail.com 2014年2月20日
	 */
	public static String getPath() {
		PropertyService propertyService=BeanContextUtil.getBean(PropertyService.class);
		return getPath(propertyService.get("upload.dir"));
	}

	public static String getPath(String parent) {
		PropertyService propertyService=BeanContextUtil.getBean(PropertyService.class);
		String path = "";
		if (StringUtils.isEmpty(parent)) {
			parent = propertyService.get("upload.dir");
		}
		String date = getDate();
		path = parent + date + "/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}
	
	/**
	 * 根据文件名获取文件全路径 
	 * @todo
	 * @param filename
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年2月20日
	 */
	public static String getPathByFilename(String filename){
		String path="";
		PropertyService propertyService=BeanContextUtil.getBean(PropertyService.class);
		if(StringUtils.isNotEmpty(filename)&&filename.length()>=8){
			String dir=filename.substring(0, 8);
			path=propertyService.get("upload.dir")+dir+"/";
		}
		path+=filename;
		return path;
	}

	/**
	 * 生成新文件名
	 * 
	 * @todo
	 * @param filename
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年2月20日
	 */
	public static String generateFileName(String filename) {
		String res = "";
//		res = StringUtil.randId("");
		res=RandUtil.randId("");
		if (StringUtils.isNotEmpty(filename)) {
			if (filename.indexOf(".") != -1) {
				res += filename.substring(filename.lastIndexOf("."));
			}
		} else {
			logger.debug(">>FaceYe debug:file name is empty.");
		}
		return res;
	}
	
	/**
	 * 取得文件后缀名之前的字符
	 * @todo
	 * @param filename
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年2月22日
	 */
	public static String getFileNameWithoutSuffix(String filename){
		String res="";
		if(StringUtils.isNotEmpty(filename)){
			if(filename.indexOf(".")!=-1){
				res=filename.substring(0,filename.indexOf("."));
			}
		}
		return res;
	}

	private static String getDate() {
		Date date = new Date();
		String res = DateUtil.formatDate(date, "yyyyMMdd");
		return res;
	}

}
