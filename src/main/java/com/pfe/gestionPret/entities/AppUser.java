package com.pfe.gestionPret.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;



import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data @AllArgsConstructor@NoArgsConstructor

public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	private String prenom;
	
	@Column(unique = true)
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(unique = true)
	private String email;
	
	
	private String phone;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dateNaissance;
	
	private String photoUrl;
	
	private String adresse;
	
	private int salaire;
	
	private float capRemb;
	
	private String fonction;
	
	private String departement;

	@Enumerated(EnumType.STRING)
	private Econtrat contratType;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate debutContrat;
	
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate finContrat;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private Collection<Pret> prets=new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER )
	private Collection<AppRole> roles=new ArrayList<>();
	
	
}
