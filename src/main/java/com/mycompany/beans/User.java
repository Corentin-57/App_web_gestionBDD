package com.mycompany.beans;

public class User {
	private int id;
	private String login, password;
	private int profil;
	
	public User(int id, String login, String password, int profil) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.profil = profil;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getProfil() {
		return profil;
	}
}
