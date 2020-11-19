package com.pfe.gestionPret.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Pret;
import com.pfe.gestionPret.projections.CustomUser;
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.PATCH,RequestMethod.DELETE,
		RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS,RequestMethod.HEAD})
@RepositoryRestResource
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
	
	public AppUser findByUsername(String username);
	public Optional<AppUser> findByEmail(String email);
	//public List<AppUser> findByNomContainingOrPrenomeContaining(@Param("nom") String nom,@Param("prenom") String prenom);
	@Query("SELECT u FROM AppUser u WHERE u.nom LIKE %?1%"
			+ " OR u.prenom LIKE %?1%"
			+ " OR u.username LIKE %?1%")
	public List<AppUser> searchUser(String keyword);
	public AppUser findByPrets(Pret pret);
}
