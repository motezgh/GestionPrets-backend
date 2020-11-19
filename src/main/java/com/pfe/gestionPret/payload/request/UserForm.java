package com.pfe.gestionPret.payload.request;

import java.time.LocalDate;




import com.fasterxml.jackson.annotation.JsonFormat;
import com.pfe.gestionPret.entities.Econtrat;

import lombok.Data;


@Data
public class UserForm {

	private String username;
	private String email;
	private String password;
	private int salaire;
	private String fonction;
	private String departement ;
	
	private Econtrat contratType; 
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate debutContrat;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate finContrat;
	
}
