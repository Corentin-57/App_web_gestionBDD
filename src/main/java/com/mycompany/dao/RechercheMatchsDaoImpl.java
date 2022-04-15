package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.Joueur;

public class RechercheMatchsDaoImpl implements RechercheMatchsDao {
	
	private DaoFactory daofactory;
	
	public RechercheMatchsDaoImpl(DaoFactory daofactory) {
		this.daofactory = daofactory;
	}
	
	public List<Joueur> listeVainqueursFinalistes(String statut) {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		List<Joueur> joueurs = new ArrayList<>();
	
	try {
		connexion = daofactory.getConnection();
		if(statut.equals("V")) {
			statement = connexion.prepareStatement("SELECT * FROM joueur INNER JOIN match_tennis WHERE joueur.ID = match_tennis.ID_VAINQUEUR");
		}else{
			statement = connexion.prepareStatement("SELECT * FROM joueur INNER JOIN match_tennis WHERE joueur.ID = match_tennis.ID_FINALISTE");
		}
		
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			Joueur joueur =  new Joueur(rs.getString("NOM"),rs.getString("PRENOM"), rs.getString("SEXE"));
			joueurs.add(joueur);
		}
	}
	catch(Exception exception){
		throw new RuntimeException(exception);	
	}	
	return joueurs;
}
	
	@Override
	public List<Joueur> rechercheAvanceeMatchs(String statut, String chaine) {
		Connection connexion = null;
		PreparedStatement statement = null;
		List<Joueur> joueurs = new ArrayList<>();
	
	try {
		connexion = daofactory.getConnection();
		
		if (statut.equals("V")){
			statement = connexion.prepareStatement("SELECT NOM, PRENOM, SEXE FROM joueur INNER JOIN match_tennis ON joueur.ID = match_tennis.ID_VAINQUEUR WHERE NOM LIKE ? OR PRENOM LIKE ?");
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");
			
		}else {
			statement = connexion.prepareStatement("SELECT NOM, PRENOM, SEXE FROM joueur INNER JOIN match_tennis ON joueur.ID = match_tennis.ID_FINALISTE WHERE NOM LIKE ? OR PRENOM LIKE ?");
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");

		}
		
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			
			Joueur joueur = new Joueur();
			joueur.setNom(rs.getString("NOM"));
			joueur.setPrenom(rs.getString("PRENOM"));
			joueur.setSexe(rs.getString("SEXE"));
			
			joueurs.add(joueur);
		}
		
	}
	catch(Exception exception){
		throw new RuntimeException(exception);	
	}	
		return joueurs;
	}
}
