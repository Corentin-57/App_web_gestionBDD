package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.Joueur;

public class RechercheEpreuvesDaoImpl implements RechercheEpreuvesDao {
	
	private DaoFactory daofactory;
	
	public RechercheEpreuvesDaoImpl(DaoFactory daofactory) {
		this.daofactory = daofactory;
	}

	@Override
	public List<Joueur> rechercheAvanceeEpreuves(String type, int annee) {
		Connection connexion = null;
		PreparedStatement statement = null;
		List<Joueur> joueurs = new ArrayList<>();

	try {
		connexion = daofactory.getConnection();
		
			statement = connexion.prepareStatement("SELECT NOM, PRENOM FROM joueur, match_tennis, epreuve WHERE epreuve.ID = match_tennis.ID_EPREUVE AND epreuve.ANNEE = ? AND epreuve.TYPE_EPREUVE = ? AND (match_tennis.ID_VAINQUEUR = joueur.ID OR match_tennis.ID_FINALISTE = joueur.ID)");
			statement.setInt(1, annee);
			statement.setString(2, type);
		
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			
			Joueur joueur = new Joueur();
			joueur.setNom(rs.getString("NOM"));
			joueur.setPrenom(rs.getString("PRENOM"));
			
			joueurs.add(joueur);
		}
		
	}
	catch(Exception exception){
		throw new RuntimeException(exception);	
	}	
		return joueurs;
	}
}
