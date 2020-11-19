package com.pfe.gestionPret.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.gestionPret.dao.AppUserRepo;
import com.pfe.gestionPret.dao.PretRepo;
import com.pfe.gestionPret.dao.TypePretRepo;
import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Etat;
import com.pfe.gestionPret.entities.Pret;
import com.pfe.gestionPret.entities.TypePret;
import com.pfe.gestionPret.payload.request.PretForm;
import com.pfe.gestionPret.service.PretService;
@CrossOrigin(origins = "*")
@RestController
public class PretController {

	@Autowired
	PretService pretService;
	
	@Autowired
	PretRepo pretRepo;
	
	@Autowired
	TypePretRepo typePretRepo;
	
	@Autowired
	AppUserRepo userRepo;
	
	@PostMapping("/createPret")
	public ResponseEntity<Pret> createPret(@RequestBody PretForm pretForm){
		
		Pret pret=pretService.createPret(pretForm);
		if(pret!=null) {
			return new ResponseEntity<Pret>(pret,HttpStatus.CREATED);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/updatePret/{id}")
	public ResponseEntity<Pret> updatePret(@PathVariable(name = "id") Long id,@RequestBody PretForm pretForm){
		Optional<Pret> pret=pretRepo.findById(id);
		Optional<AppUser> user=userRepo.findById(pretForm.getId());
		TypePret typePret=typePretRepo.findByNameType(pretForm.getTypePret());
		if(pret.isPresent()) {
			if(pret.get().getEtat()==Etat.ENCOURS) {
				//float prevEcheance=pret.get().getEcheance();
				//float prevCapRem=user.get().getCapRemb();
				//user.get().setCapRemb(prevCapRem+prevEcheance);
				pret.get().setDureeRemboursement(pretForm.getDuree());
				pret.get().setMontant(pretForm.getMontant());
				pret.get().setTypePret(typePret);
				
				
				pret.get().setEcheance(pretForm.getMontant()/pretForm.getDuree());
				//float echeance=pret.get().getEcheance();
				//float capRemb=user.get().getCapRemb();
				//if(capRemb>=echeance) {
					pretRepo.save(pret.get());
					//user.get().setCapRemb(user.get().getCapRemb()-pret.get().getEcheance());
					//userRepo.save(user.get());
					return new ResponseEntity<Pret>(pret.get(),HttpStatus.ACCEPTED);
				//}else
					//return new ResponseEntity<Pret>(HttpStatus.NOT_ACCEPTABLE);
				
			}
		}
			return new ResponseEntity<Pret>(HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@GetMapping("/accepterPret/{id}")
	public ResponseEntity<Boolean> accepterPret(@PathVariable Long id){
		Optional<Pret> pret=pretRepo.findById(id);
		boolean test=pretService.accepterPret(pret.get());
		if (test)
		return new ResponseEntity<Boolean>(test,HttpStatus.OK);
		else 
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping("/refuserPret/{id}")
	public ResponseEntity<Boolean> refuserPret(@PathVariable Long id){
		Optional<Pret> pret=pretRepo.findById(id);
		boolean test=pretService.refuserPret(pret.get());
		if (test)
		return new ResponseEntity<Boolean>(test,HttpStatus.OK);
		else 
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@DeleteMapping("/deletePret/{id}")
	public ResponseEntity<Boolean> deletePret(@PathVariable(name = "id")Long id){
		Optional<Pret> pret=pretRepo.findById(id);
		if(pret.isPresent()) {
			boolean test=pretService.deletePret(pret.get());
			return new ResponseEntity<Boolean>(test,HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
	}
	
}
