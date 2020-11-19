package com.pfe.gestionPret.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pfe.gestionPret.entities.AppRole;
import com.pfe.gestionPret.entities.Erole;
@CrossOrigin("*")
@RepositoryRestResource
public interface AppRoleRepo extends JpaRepository<AppRole, Long> {
	public AppRole findByRoleName(Erole roleName);
}
