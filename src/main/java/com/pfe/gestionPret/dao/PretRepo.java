package com.pfe.gestionPret.dao;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Etat;
import com.pfe.gestionPret.entities.Pret;
import com.pfe.gestionPret.entities.TypePret;
import com.pfe.gestionPret.projections.CustomPret;
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.PATCH,RequestMethod.DELETE,
		RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS,RequestMethod.HEAD})
@RepositoryRestResource(excerptProjection =CustomPret.class)
public interface PretRepo extends JpaRepository<Pret, Long> {
	public Pret findByEtat(Etat etat );
	public List<Pret> findByEtatLike(@Param("etat") Etat etat); 
	public Optional<Pret> findByUser(AppUser user);
}
