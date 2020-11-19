package com.pfe.gestionPret.service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pfe.gestionPret.dao.AppUserRepo;
import com.pfe.gestionPret.dao.PretRepo;
import com.pfe.gestionPret.dao.TypePretRepo;
import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Econtrat;
import com.pfe.gestionPret.entities.Etat;
import com.pfe.gestionPret.entities.EtypeName;
import com.pfe.gestionPret.entities.Pret;
import com.pfe.gestionPret.entities.TypePret;
import com.pfe.gestionPret.payload.request.PretForm;
import com.pfe.gestionPret.payload.request.UserForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PretServiceImpl implements PretService {
	@Autowired
	private PretRepo pretRepo;
	@Autowired
	private TypePretRepo typePretRepo;
	@Autowired
	private AppUserRepo userRepo;
	
	public Pret createPret (PretForm pretForm) {
		TypePret typePret=typePretRepo.findByNameType(pretForm.getTypePret());
		Optional<AppUser> user =userRepo.findById(pretForm.getId());
		float capR=user.get().getCapRemb();
		Pret pret=new Pret();
		pret.setMontant(pretForm.getMontant());
		pret.setDureeRemboursement(pretForm.getDuree());
		pret.setEcheance(pretForm.getMontant()/pretForm.getDuree());
		
		float echeance=pret.getEcheance();
		pret.setTypePret(typePret);
		pret.setEtat(Etat.ENCOURS);
		
		pret.setUser(user.get());
		Clock cl = Clock.systemUTC();
		
		LocalDate nowDate =LocalDate.now(cl);
		pret.setCreatedAt(nowDate);
		if (capR>=echeance && typePret.getDureeRemb_plafond()>=pret.getDureeRemboursement() 
				&& typePret.getMnt_plafond()>=pret.getMontant() && user.get().getContratType()==Econtrat.CDI) {
			
			
			//user.get().setCapRemb(capR-echeance);
			//userRepo.save(user.get());
			pretRepo.save(pret);
			return pret;
		}else if (capR>=echeance && typePret.getDureeRemb_plafond()>=pret.getDureeRemboursement() 
				&& typePret.getMnt_plafond()>=pret.getMontant() && user.get().getContratType()!=Econtrat.CDI
				&& pret.getCreatedAt().plusMonths(2+pret.getDureeRemboursement()).isBefore(user.get().getFinContrat())) {
			
			pretRepo.save(pret);
			return pret;
		}
			
		else
			return null;
	}
	
	public Pret editPret(PretForm pretForm) {
		Optional<Pret> pret=pretRepo.findById(pretForm.getId());
		TypePret typePret=typePretRepo.findByNameType(pretForm.getTypePret());
		if(pret.get().getEtat()==Etat.ENCOURS) {
			pret.get().setDureeRemboursement(pretForm.getDuree());
			pret.get().setMontant(pretForm.getMontant());
			pret.get().setTypePret(typePret);
			return pretRepo.save(pret.get());
		}else
			return null;
	}

	@Override
	public boolean accepterPret(Pret pret) {
		if (pret!=null && pret.getEtat()==Etat.ENCOURS) {
			AppUser user=pret.getUser();
			float cr=user.getCapRemb();
			float echeance=pret.getEcheance();
			if (cr>=echeance) {
				user.setCapRemb(cr-echeance);
				pret.setEtat(Etat.ACCEPTER);
				
				pret.setDebutRemb(pret.getCreatedAt().plusMonths(2));
				pret.setFinRemb(pret.getDebutRemb().plusMonths(pret.getDureeRemboursement()));
				pretRepo.save(pret);
				
				if(pret.getFinRemb().isEqual(LocalDate.now())) {
					user.setCapRemb(cr+echeance);
					userRepo.save(user);
				}
				return true;
			}else
				return false;
		}
		
		return false;
	}

	@Override
	public boolean refuserPret(Pret pret) {
		if (pret!=null && pret.getEtat()==Etat.ENCOURS) {
			pret.setEtat(Etat.REFUSER);
			//float capRemb =pret.getUser().getCapRemb();
			//float echeance=pret.getEcheance();
			//pret.getUser().setCapRemb(capRemb+echeance);
			pretRepo.save(pret);
			return true;
		}
		
		return false;
		
	}

	@Override
	public boolean deletePret(Pret pret) {
		if (pret.getEtat()!=Etat.ACCEPTER) {
			pretRepo.delete(pret);
			return true;
		}
		return false;
		
	}
	

	
}


