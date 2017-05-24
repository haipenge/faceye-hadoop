package com.faceye.component.mail.service.impl;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.faceye.component.mail.service.MailService;
import com.faceye.component.mail.service.wrap.Mail;
import com.faceye.feature.util.ServiceException;


@Service
public class MailServiceImpl implements MailService{
	private Log log=LogFactory.getLog(getClass());
	@Autowired
	private MailEngine mailEngine=null;
	@Autowired
	@Qualifier("mailMessage")
	private SimpleMailMessage mailMessage=null;
	//默认邮件模版
	@Value("#{property['mail.default.template']}")
	private String defaultMailSenderTemplate="";
	public void sendMail(Mail mail) {
		mailMessage=this.buildMailMessage(mail);
		String templateName=StringUtils.isEmpty(mail.getTemplate())?this.defaultMailSenderTemplate:mail.getTemplate();
		Object body=mail.getBody();
		if(body!=null){
			if(body instanceof String){
				mailMessage.setText(body.toString());
				this.mailEngine.send(mailMessage);
			}else if(body instanceof Map){
				this.mailEngine.sendMimeMessage(mailMessage, templateName, (Map)body);
			}
		}else{
			log.debug(">>FaceYe --> mail body is empty now.");
		}
	}

	/**
	 * 组装Simple mail message.
	 * @todo
	 * @param mail
	 * @return
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2013年12月29日
	 */
	private SimpleMailMessage buildMailMessage(Mail mail){
        String [] receivers=mail.getReceivers();
        mailMessage.setTo(receivers);
        mailMessage.setSubject(mail.getSubject());
		return (SimpleMailMessage)mailMessage;
	}
	
}
