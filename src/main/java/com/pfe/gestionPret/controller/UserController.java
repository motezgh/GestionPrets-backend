package com.pfe.gestionPret.controller;


import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayway.jsonpath.Option;
import com.pfe.gestionPret.dao.AppUserRepo;
import com.pfe.gestionPret.dao.PretRepo;
import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Pret;
import com.pfe.gestionPret.payload.request.UpdatePasswordForm;
import com.pfe.gestionPret.payload.request.UpdateUsernameForm;
import com.pfe.gestionPret.payload.request.UserForm;
import com.pfe.gestionPret.service.AccountService;
import com.pfe.gestionPret.service.UserService;
@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppUserRepo userRepo ;
	
	@Autowired
	private PretRepo pretRepo;
	
	
	@PostMapping("/register")
	public AppUser createUser(@RequestBody UserForm userForm) {
		
		return accountService.saveUser(userForm);
	}
	
	@PutMapping("/editUser/{id}")
	public ResponseEntity<AppUser> editUser(@PathVariable(name = "id") Long id,@RequestBody AppUser user)throws ResourceNotFoundException {
		AppUser currentUser=userRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id : " +id));
		currentUser.setAdresse(user.getAdresse());
		currentUser.setDateNaissance(user.getDateNaissance());
		currentUser.setNom(user.getNom());
		currentUser.setPhone(user.getPhone());
		currentUser.setPrenom(user.getPrenom());
		userService.modifierUser(currentUser);
		return new ResponseEntity<AppUser>(currentUser,HttpStatus.OK);
	}
	
	@PostMapping("/editUserContrat/{id}")
	public ResponseEntity<AppUser> editUserContrat(@PathVariable(name = "id") Long id,@RequestBody AppUser user)throws ResourceNotFoundException {
		AppUser currentUser=userRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id : " +id));
		currentUser.setContratType(user.getContratType());
		currentUser.setDebutContrat(user.getDebutContrat());
		float prevCapRembWithEcheance=currentUser.getCapRemb();
		int prevSalaire=currentUser.getSalaire();
		float prevCapRemb=prevSalaire*4/10;
		float differenceCapRemb=prevCapRemb-prevCapRembWithEcheance;
		currentUser.setFinContrat(user.getFinContrat());
		currentUser.setSalaire(user.getSalaire());
		currentUser.setCapRemb((user.getSalaire()*4/10)-differenceCapRemb);
		currentUser.setDepartement(user.getDepartement());
		currentUser.setFonction(user.getFonction());
		userService.modifierUser(currentUser);
		return new ResponseEntity<AppUser>(currentUser,HttpStatus.OK);
	}
	
	@PostMapping("/updateUsername/{id}")
	public ResponseEntity<Boolean> updateUsername(@PathVariable(name = "id") Long id,@RequestBody UpdateUsernameForm form) {
		Optional<AppUser> user=userRepo.findById(id);	
		if(user.isPresent()) {
			boolean test=accountService.updateUsername(form.getUsername(),user.get());
			if(test  )
				return new ResponseEntity<Boolean>(test,HttpStatus.OK);
			else
				return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
		
		}
	
		return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/updatePassword/{id}")
	public ResponseEntity<Boolean> updatePassword(@PathVariable(name = "id") Long id,@RequestBody UpdatePasswordForm form){
		Optional<AppUser> user=userRepo.findById(id);
		if(user.isPresent()) {
			boolean test=accountService.updatePassword(user.get(),form.getOldPass(),form.getNewPass());
			if(test)
				return new ResponseEntity<Boolean>(test,HttpStatus.OK);
			else
				return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<AppUser> deleteUser(@PathVariable("id") Long id){
		Optional<AppUser> user=userRepo.findById(id);
		
		if(user.isPresent()) {
			Optional<Pret> pret=pretRepo.findByUser(user.get());
			userService.deleteUser(user.get());
			if(pret.isPresent()) {
				pretRepo.delete(pret.get());
			}
			
			return new ResponseEntity<AppUser>(HttpStatus.ACCEPTED);
		} else 
			return new ResponseEntity<AppUser>(HttpStatus.NOT_ACCEPTABLE);
	} 
	
	
	
	@GetMapping("/searchUser")
	public List<AppUser> searchUser(@Param("keyword") String keyword){
		List<AppUser> users=userService.searchUser(keyword);
		return users;
	}
	
	
	@PostMapping("/addRoleResponsable/{id}")
	public void addRoleResponsable(@PathVariable("id") Long id) {
		Optional<AppUser> user=userRepo.findById(id);
		accountService.makeUserResponsable(user.get());
		
	}
	
}





