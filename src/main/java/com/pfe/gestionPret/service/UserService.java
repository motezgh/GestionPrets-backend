package com.pfe.gestionPret.service;

import java.util.Date;
import java.util.List;

import com.pfe.gestionPret.entities.AppUser;

public interface UserService {
	
	void deleteUser(AppUser user);
	List<AppUser> searchUser(String keyword);
	AppUser modifierUser(AppUser user);
	

}
