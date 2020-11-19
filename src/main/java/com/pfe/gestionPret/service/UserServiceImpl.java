package com.pfe.gestionPret.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.gestionPret.dao.AppRoleRepo;
import com.pfe.gestionPret.dao.AppUserRepo;
import com.pfe.gestionPret.dao.PretRepo;
import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Pret;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AppUserRepo userRepo;
	

	@Override
	public void deleteUser(AppUser user) {
		if (user!=null)
			
			userRepo.delete(user);

	}


	@Override
	public List<AppUser> searchUser(String keyword) {
		if(keyword != null) {
			return userRepo.searchUser(keyword);
		}
		return userRepo.findAll();
	}


	@Override
	public AppUser modifierUser(AppUser user) {
		return userRepo.save(user);
		
	}




	
	

	

	

}
