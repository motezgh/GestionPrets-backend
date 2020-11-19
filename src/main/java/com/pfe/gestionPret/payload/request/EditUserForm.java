package com.pfe.gestionPret.payload.request;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EditUserForm {

	private String nom;
	private String prenom;
	private String phone;
	private String adresse;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dateNaissance;
	;
	
}
