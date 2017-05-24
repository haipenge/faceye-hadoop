package com.faceye.component.mail.service.impl;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class MailEngine {
	protected  final Log log = LogFactory.getLog(MailEngine.class);
	@Autowired
	@Qualifier("mailSender")
	private MailSender mailSender = null;
	@Autowired
	@Qualifier("velocityEngine")
	private VelocityEngine velocityEngine = null;

	/**
	 * Send a simple message based on a Velocity template.
	 * 
	 * @param msg
	 * @param templateName
	 * @param model
	 */
	public void sendMessage(SimpleMailMessage msg, String templateName, Map model) {
		String result = null;
		try {
			result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, model);
		} catch (VelocityException e) {
			log.error(">>FaceYe -->Exception e:"+e.getMessage());
		}
		msg.setText(result);
		send(msg);
	}

	public void sendMimeMessage(SimpleMailMessage msg, String templateName, Map model) {
		String result = null;
		MimeMessage mimeMessage = ((JavaMailSender) mailSender).createMimeMessage();
		try {
			result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, model);
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setTo(msg.getTo());
			helper.setFrom(msg.getFrom());
			helper.setSubject(msg.getSubject());
			helper.setText(result, true);
			((JavaMailSender) mailSender).send(mimeMessage);
		} catch (MessagingException e) {
			log.error(">>FaceYe:" + e.toString());
		} catch (VelocityException e) {
			log.error(">>FaceYe:" + e.toString());
		} catch (Exception e) {
			log.error(">>FaceYe throws Exception e:" + e.toString());
		}
	}

	/**
	 * Send a simple message with pre-populated values.
	 * 
	 * @param msg
	 */
	public void send(SimpleMailMessage msg) {
		try {
			mailSender.send(msg);
		} catch (MailException ex) {
			// log it and go on
			log.error(ex.getMessage());
		} catch (Exception e) {
			log.error(">>FaceYe throws Exception:" + e.getMessage());
		}
	}

	/**
	 * Convenience method for sending messages with attachments.
	 * 
	 * @param emailAddresses
	 * @param resource
	 * @param bodyText
	 * @param subject
	 * @param attachmentName
	 * @throws MessagingException
	 * @author Ben Gill
	 */
	public void sendMessage(String[] emailAddresses, ClassPathResource resource, String bodyText, String subject, String attachmentName)
			throws MessagingException {
		MimeMessage message = ((JavaMailSenderImpl) mailSender).createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(emailAddresses);
		helper.setText(bodyText);
		helper.setSubject(subject);
		helper.addAttachment(attachmentName, resource);
		((JavaMailSenderImpl) mailSender).send(message);
	}
}
