package com.pfe.gestionPret.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class Pret {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	private int dureeRemboursement;
	
	private int montant;
	
	private float echeance;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate debutRemb;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate finRemb;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate createdAt;
	
	@Enumerated(EnumType.STRING)
	private Etat etat;
	
	@ManyToOne
	private TypePret typePret;
	
	@ManyToOne
	//@JsonIgnore
	private AppUser user;
}
