package com.pfe.gestionPret.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.gestionPret.dao.AppUserRepo;
import com.pfe.gestionPret.dao.PretRepo;
import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Mail;
import com.pfe.gestionPret.entities.Pret;
import com.pfe.gestionPret.service.EmailService;

@CrossOrigin("*")
@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AppUserRepo userRepo;
	
	@Autowired
	private PretRepo pretRepo;
	
	 
	
	@GetMapping("/userCreatedMessage/{id}")
	public String sendUserCreatedMessage(@PathVariable("id") Long id) {
		Optional<AppUser> user =userRepo.findById(id);
		Mail mail = new Mail();
		mail.setFrom("responsable.siga@gmail.com");
		mail.setTo(user.get().getEmail());
		mail.setSubject("Compte SIGA ");
		mail.setContent("Dear "+ "Employe" +"," +"\r\n" +
		"\r\n"+
		"Votre compte a été cree avc succee." +"\r\n" +
		"\r\n" + 
		"Votre Username : " + user.get().getUsername() + "\r\n" +
		"Votre Mot de pass : " +"1234" + "\r\n" +
		"\r\n" +
		"Cordialement,"); 
		emailService.sendSempleMessage(mail);
		return "message sent successffully" ;
		
	}
	
	@GetMapping("/pretResponseMessage/{id}")
	public String sendPretResponseMessage(@PathVariable("id") Long id) {
		Optional<Pret> pret=pretRepo.findById(id);
		Mail mail = new Mail();
		mail.setFrom("responsable.siga@gmail.com");
		mail.setTo(pret.get().getUser().getEmail());
		mail.setSubject("Demande de pret -SIGA- ");
		mail.setContent("Cher "+ pret.get().getUser().getPrenom() + " "+pret.get().getUser().getNom() +"," +"\r\n" +
		"\r\n"+
		"Votre Demande de pret a été executer." +"\r\n" +
		"\r\n" +
		"Details du pret : " + "Montant : "+pret.get().getMontant()+ " DT" +", Duree : "+pret.get().getDureeRemboursement() + " mois"
		+ ", echeance : "+ pret.get().getEcheance()+" DT par mois," +" type du pret : "+pret.get().getTypePret().getNameType()+" ." 
		+"\r\n" +
		"Nous vous informant que nous avons " + pret.get().getEtat() + " votre demande de pret"+ "\r\n" +
		"\r\n" +
		"Cordialement,"); 
		emailService.sendSempleMessage(mail);
		return "message sent successffully" ;
		
	}
	
}
