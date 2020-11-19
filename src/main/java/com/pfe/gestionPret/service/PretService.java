package com.pfe.gestionPret.service;

import com.pfe.gestionPret.entities.EtypeName;
import com.pfe.gestionPret.entities.Pret;
import com.pfe.gestionPret.payload.request.PretForm;

public interface PretService {
	public Pret createPret(PretForm pretForm );
	public Pret editPret(PretForm pretForm);
	public boolean deletePret(Pret pret);
	public boolean accepterPret(Pret pret );
	public boolean refuserPret(Pret pret);
}
