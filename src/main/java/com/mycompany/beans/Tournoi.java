package com.mycompany.beans;

public class Tournoi {
	int id;
	String nom, code;
	
	public Tournoi() {
	}
	
	public Tournoi(int id, String nom, String code) {
		this.id = id;
		this.nom = nom;
		this.code = code;
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
			throw new BeansException("Le nom du tournoi est trop grand ! (20 caractères maximum)");
		}else this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) throws BeansException {
		if(code.length() != 2) {
			throw new BeansException("Le code du tournoi doit être de 2 caractères");
		}else this.code = code;
	}
	
	
}
