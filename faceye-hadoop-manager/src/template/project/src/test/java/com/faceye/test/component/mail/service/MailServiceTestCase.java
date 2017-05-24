package com.faceye.test.component.mail.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.faceye.component.mail.service.MailService;
import com.faceye.component.mail.service.wrap.Mail;
import com.faceye.test.feature.service.BaseServiceTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceTestCase extends BaseServiceTestCase {
  private Log log=LogFactory.getLog(getClass());
  
  @Autowired
  private MailService mailService=null;
  @Test
  public void testSendTextMail() throws Exception{
	  Mail mail=new Mail();
	  mail.setReceiver("haipenge@gmail.com");
	  mail.setSubject("test mail");
	  this.mailService.sendMail(mail);
  }
  @Test
  public void testSendHtmlMail() throws Exception{
	  Mail mail=new Mail();
	  mail.setReceiver("haipenge@gmail.com");
	  mail.setSubject("test mail");
	  Map body =new HashMap();
	  body.put("key-1", "test-key-1");
	  body.put("key-2", "test-key-2");
	  mail.setBody(body);
	  this.mailService.sendMail(mail);
  }
  
  
  
}
