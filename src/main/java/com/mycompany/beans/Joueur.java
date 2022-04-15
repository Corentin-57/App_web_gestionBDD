package com.mycompany.beans;

public class Joueur {
	int id;
	String nom, prenom, sexe, statut;
	
	public Joueur() {
	}
	
	public Joueur(int id, String nom, String prenom, String sexe) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
	}
	
	public Joueur(String nom, String prenom, String sexe) {
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
	}
	
	public Joueur(String statut) {
		this.statut = statut;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) throws BeansException {
		if(nom.length() > 20) {
			throw new BeansException("Le nom du joueur est trop grand ! (20 caractères maximum)");
		}else this.nom = nom;

	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) throws BeansException {
		if(prenom.length() > 20) {
			throw new BeansException("Le prenom du joueur est trop grand ! (20 caractères maximum)");
		}else this.prenom = prenom;
	}
	
	public String getSexe() {
		return sexe;
	}
	
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
}
