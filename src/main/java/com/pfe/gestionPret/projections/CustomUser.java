package com.pfe.gestionPret.projections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.pfe.gestionPret.entities.AppRole;
import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Econtrat;
import com.pfe.gestionPret.entities.Pret;

@Projection(name = "customUser", types = AppUser.class)
public interface CustomUser {

	Long getId();
	
	String getNom();
	
	String getPrenom();
	
	@Column(unique = true)
	String getUsername();
	
	@JsonProperty(access = Access.WRITE_ONLY)
	String getPassword();
	
	@Column(unique = true)
	String getEmail();
	
	
	String getPhone();
	
	@JsonFormat(pattern="dd/MM/yyyy")
	LocalDate getDateNaissance();
	
	String getPhotoUrl();
	
	String getAdresse();
	
	int getSalaire();
	
	float getCapRemb();
	
	String getFonction();
	
	String getDepartement();

	@Enumerated(EnumType.STRING)
	Econtrat getContratType();
	
	@JsonFormat(pattern="dd/MM/yyyy")
	LocalDate getDebutContrat();
	
	
	@JsonFormat(pattern="dd/MM/yyyy")
	LocalDate getFinContrat();
	
	
	Collection<Pret> getPrets();
	
	
	Collection<AppRole> getRoles();
	
}
