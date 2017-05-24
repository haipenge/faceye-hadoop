package com.faceye.component.mail.service.wrap;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 待发邮件包装
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2013年12月29日
 */
public class Mail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2691882445483944098L;
	/**
	 * 邮件接收人，多个邮件接收人之间使用";"进行分隔
	 */
	private String receiver = "";
	// 发送邮件时使用的velocity模板
	private String template = "";
	//标题
	private String subject="";

	private Date createDate = new Date();
	// 邮件内容,可以是文本，Map等结构
	private Object body = null;
	
	private String [] filenames=null;

	public String getReceiver() {
		return receiver;
	}
	
	public String [] getReceivers(){
		String [] receivers=null;
		if(StringUtils.isNotEmpty(receiver)&&receiver.contains(",")){
			receivers= receiver.split(",");
		}else if(StringUtils.isNotEmpty(receiver)){
			receivers= new String []{receiver};
		}
		//数组去空@todo.
		return receivers;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String[] getFilenames() {
		return filenames;
	}

	public void setFilenames(String[] filenames) {
		this.filenames = filenames;
	}
	

}
