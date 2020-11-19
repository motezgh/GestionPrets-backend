package com.pfe.gestionPret.projections;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Etat;
import com.pfe.gestionPret.entities.Pret;
import com.pfe.gestionPret.entities.TypePret;

@Projection(name = "customPret", types = Pret.class)
public interface CustomPret {

	Long getId();
	String getDescription();
	int getDureeRemboursement();
	int getMontant();
	float getEcheance();
	
	LocalDate getDebutRemb();
	
	LocalDate getFinRemb();
	
	LocalDate getCreatedAt();
	Etat getEtat();
	TypePret getTypePret();
	AppUser getUser();
	
}
