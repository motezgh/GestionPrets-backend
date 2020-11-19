package com.pfe.gestionPret.dao;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pfe.gestionPret.entities.EtypeName;
import com.pfe.gestionPret.entities.TypePret;
@CrossOrigin("*")
@RepositoryRestResource
public interface TypePretRepo extends JpaRepository<TypePret, Long> {
	public TypePret findByNameType(EtypeName nameType);
}
