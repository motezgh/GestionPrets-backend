package com.pfe.gestionPret.payload.request;

import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.EtypeName;

import lombok.Data;
@Data
public class PretForm {

	private Long id;
	private int montant;
	private int duree;
	private EtypeName typePret;
	
}
