package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.Joueur;

import jakarta.el.StaticFieldELResolver;

public class JoueurDaoImpl implements JoueurDao {
	private DaoFactory daofactory;
	
	public JoueurDaoImpl(DaoFactory daofactory) {
		this.daofactory = daofactory;
	}

	//@Override
//	public List<Joueur> lister() {
//		List<Joueur> joueurs = new ArrayList<Joueur>();
//		Joueur joueur1 = new Joueur(4, "coco", "col", "m");
//		Joueur joueur2 = new Joueur(5, "lala", "lat", "h");
//		joueurs.add(joueur1);
//		joueurs.add(joueur2);
//		
//		return joueurs;
//	}
	@Override
	public List<Joueur> lister() {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		List<Joueur> joueurs = new ArrayList<>();
	
	try {
		connexion = daofactory.getConnection();
		statement = connexion.prepareStatement("SELECT * FROM joueur");
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			Joueur joueur =  new Joueur(rs.getInt("ID"), rs.getString("NOM"),rs.getString("PRENOM"), rs.getString("SEXE"));
			joueurs.add(joueur);
		}
	}
	catch(Exception exception){
		throw new RuntimeException(exception);	
	}	
	return joueurs;
}

	@Override
	public void ajouter(Joueur joueur) {
		Connection connexion = null;
		PreparedStatement statement = null;
	
	try {
		connexion = daofactory.getConnection();
		statement = connexion.prepareStatement("INSERT INTO joueur (NOM, PRENOM, SEXE) VALUES (?, ?, ?) ");
		
		statement.setString(1,	joueur.getNom());
		statement.setString(2,	joueur.getPrenom());
		statement.setString(3,	joueur.getSexe());
		
		statement.executeUpdate();
	}
	catch(Exception exception){
		throw new RuntimeException(exception);	
	}	
		
	}

	@Override
	public Joueur lecture(Long id) {
		Connection connexion = null;
		PreparedStatement statement = null;
	
	try {
		connexion = daofactory.getConnection();
		statement = connexion.prepareStatement("SELECT * FROM joueur WHERE id = ? ");
		
		statement.setLong(1, id);
		
		ResultSet rs = statement.executeQuery();
		
		if(rs.next()) {
		
		Joueur joueur = new Joueur();
		joueur.setId(rs.getInt("ID"));
		joueur.setNom(rs.getString("NOM"));
		joueur.setPrenom(rs.getString("PRENOM"));
		joueur.setSexe(rs.getString("SEXE"));
		
		return joueur;
		}else {
			return null;
		}
		
	}
	catch(Exception exception){
		throw new RuntimeException(exception);	
	}	
	}
	
	public void modifier(Joueur joueur) {
		Connection connexion = null;
		PreparedStatement statement = null;
	
	try {
		connexion = daofactory.getConnection();
		statement = connexion.prepareStatement("UPDATE joueur SET PRENOM=?, NOM=?, SEXE=? WHERE ID=?");
		
		statement.setString(1, joueur.getNom());
		statement.setString(2, joueur.getPrenom());
		statement.setString(3, joueur.getSexe());
		statement.setInt(4, joueur.getId());
		
		statement.executeUpdate();
		
		
	}
	catch(Exception exception){
		throw new RuntimeException(exception);	
	}	
	}
	
	public void supprimer(Long id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

	try {
		
        connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris",
				"root", "Mesemu_3"); 
		
		//Suppression d'abord de score_vainqueur (clef étrangère ID MATCH) puis match_tennis (clef étrangère ID Vainqueur et ID Finaliste) et enfin joueur 
        //Suppression de la ligne dans la table score ou le joueur a participé 
		preparedStatement = connexion.prepareStatement("DELETE score_vainqueur FROM score_vainqueur INNER JOIN match_tennis ON match_tennis.ID = score_vainqueur.ID_MATCH WHERE (match_tennis.ID_VAINQUEUR=? OR match_tennis.ID_FINALISTE=?);");
        connexion.setAutoCommit(false);
        preparedStatement.setLong(1, id);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
        
        //Suppression du match tennis où le joueur a participé
        preparedStatement = connexion.prepareStatement("DELETE match_tennis FROM match_tennis WHERE (match_tennis.ID_VAINQUEUR=? OR match_tennis.ID_FINALISTE=?);");
        preparedStatement.setLong(1, id);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
        
        preparedStatement = connexion.prepareStatement("DELETE FROM joueur WHERE ID=? ");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        connexion.commit();
		
	}
	catch(Exception exception){
		throw new RuntimeException(exception);	
	}	
	}

	@Override
	public List<Joueur> rechercher(String chaine) {
		Connection connexion = null;
		PreparedStatement statement = null;
		List<Joueur> liste = new ArrayList<>();
	
	try {
		connexion = daofactory.getConnection();
		statement = connexion.prepareStatement("SELECT * FROM joueur WHERE NOM LIKE? OR PRENOM LIKE?");
		
		statement.setString(1, "%" + chaine + "%");
		statement.setString(2, "%" + chaine + "%");

		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			
			Joueur joueur = new Joueur();
			joueur.setId(rs.getInt("ID"));
			joueur.setNom(rs.getString("NOM"));
			joueur.setPrenom(rs.getString("PRENOM"));
			joueur.setSexe(rs.getString("SEXE"));
			
			liste.add(joueur);
		}
		
	}
	catch(Exception exception){
		throw new RuntimeException(exception);	
	}	
		return liste;
	}
	
}
