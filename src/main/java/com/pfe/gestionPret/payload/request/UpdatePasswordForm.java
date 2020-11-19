package com.pfe.gestionPret.payload.request;

import lombok.Data;

@Data
public class UpdatePasswordForm {

	
	private String oldPass;
	private String newPass;
	
}
