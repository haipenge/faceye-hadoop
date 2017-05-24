package com.faceye.component.mail.service;

import com.faceye.component.mail.service.wrap.Mail;

public interface MailService {
	
	/**
     * 发送邮件
     * @todo
     * @param mail
     * @return
     * @author:@haipenge
     * haipenge@gmail.com
     * 2013年12月29日
     */
	public void sendMail(Mail mail);

}
