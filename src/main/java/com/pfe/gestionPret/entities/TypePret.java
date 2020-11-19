package com.pfe.gestionPret.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data@AllArgsConstructor@NoArgsConstructor
public class TypePret {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private EtypeName nameType;
	
	private String libelle;
	
	private int mnt_plafond;
	
	private int dureeRemb_plafond;
	
	@OneToMany(mappedBy = "typePret")
	@JsonIgnore
	private Collection<Pret> pret;
}
