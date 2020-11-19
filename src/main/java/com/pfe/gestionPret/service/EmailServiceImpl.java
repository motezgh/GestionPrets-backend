package com.pfe.gestionPret.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pfe.gestionPret.entities.Mail;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendSempleMessage( Mail mail) {
		
		SimpleMailMessage messege = new SimpleMailMessage();
		
		messege.setSubject(mail.getSubject());
		messege.setText(mail.getContent());
		messege.setTo(mail.getTo());
		messege.setFrom(mail.getFrom());
		
		emailSender.send(messege);
		
	}
	
	
}
