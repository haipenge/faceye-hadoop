package com.faceye.feature.upload;

import java.io.Serializable;

public class UploadResult implements Serializable {
	// 存储路径
	private String path = "";
	// 原文件名
	private String name = "";
	// 生成新文件名
	private String generateFileName = "";

	// 文件大小
	private String size = "";

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenerateFileName() {
		return generateFileName;
	}

	public void setGenerateFileName(String generateFileName) {
		this.generateFileName = generateFileName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
