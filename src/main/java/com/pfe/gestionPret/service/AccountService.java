package com.pfe.gestionPret.service;

import java.util.Date;


import org.springframework.web.bind.annotation.CrossOrigin;

import com.pfe.gestionPret.entities.AppRole;
import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Econtrat;
import com.pfe.gestionPret.entities.Erole;
import com.pfe.gestionPret.payload.request.UserForm;

public interface AccountService{

     public AppUser saveUser(UserForm userForm);
	 public AppRole save(AppRole role);
	 public AppUser loadUserByUsername(String username);
	 public void addRoleToUser(String username,Erole rolename);
	 public boolean updateUsername(String username,AppUser user);
	 public boolean updatePassword(AppUser user,String oldPassword ,String newPassword);
	 
	 public void makeUserResponsable(AppUser user);
	 
	
}
