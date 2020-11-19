package com.pfe.gestionPret.service;


import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.gestionPret.dao.AppRoleRepo;
import com.pfe.gestionPret.dao.AppUserRepo;
import com.pfe.gestionPret.entities.AppRole;
import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Econtrat;
import com.pfe.gestionPret.entities.Erole;
import com.pfe.gestionPret.payload.request.UserForm;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
    private AppUserRepo userRepo;
    @Autowired
	private AppRoleRepo roleRepo;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    
    @Override
    public AppUser saveUser(UserForm userForm) {
        AppUser  userTest=userRepo.findByUsername(userForm.getUsername());
        Optional<AppUser> emailTest=userRepo.findByEmail(userForm.getEmail());
        if(userTest!=null) throw new RuntimeException("Username existe déjà");
        if(emailTest.isPresent())throw new RuntimeException("Email existe déjà");
        
        AppUser appUser=new AppUser();
        appUser.setUsername(userForm.getUsername());
        appUser.setEmail(userForm.getEmail());
        appUser.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        appUser.setSalaire(userForm.getSalaire());
        appUser.setCapRemb(40*appUser.getSalaire()/100);
        appUser.setContratType(userForm.getContratType());
        appUser.setDebutContrat(userForm.getDebutContrat());
        appUser.setFinContrat(userForm.getFinContrat());
        appUser.setDepartement(userForm.getDepartement());
        appUser.setFonction(userForm.getFonction());
        
        userRepo.save(appUser);
        addRoleToUser(userForm.getUsername(),Erole.EMPLOYE);
        return appUser;
    }
    
    @Override
    public AppRole save(AppRole role) {
        return roleRepo.save(role);
    }
    
    @Override
    public AppUser loadUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    
    @Override
    public void addRoleToUser(String username, Erole rolename) {
        AppUser appUser=userRepo.findByUsername(username);
        AppRole appRole=roleRepo.findByRoleName(rolename);
        appUser.getRoles().add(appRole);
    }

	@Override
	public boolean updateUsername(String username,AppUser user) {
		AppUser currentUser=userRepo.findByUsername(username);
		
		if(currentUser==null) {
			
			user.setUsername(username);
			userRepo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePassword(AppUser user,String oldPass, String newPass) {
		
		System.out.println(user.getPassword());
			if(bCryptPasswordEncoder.matches(oldPass, user.getPassword())) {
				
				user.setPassword(bCryptPasswordEncoder.encode(newPass));
				userRepo.save(user);
				return true;
			}
		
		return false;
	}

	@Override
	public void makeUserResponsable(AppUser user) {
		addRoleToUser(user.getUsername(), Erole.RH);
		userRepo.save(user);
	}
    
    
}
