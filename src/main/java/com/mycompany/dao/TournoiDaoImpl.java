package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.Joueur;
import com.mycompany.beans.Tournoi;

public class TournoiDaoImpl implements TournoiDao {
	private DaoFactory daoFactory;
	
	public TournoiDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Tournoi> lister() {
		Connection connexion = null;
		PreparedStatement statement = null;
			
		List<Tournoi> tournois = new ArrayList<>();
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("SELECT * FROM tournoi");
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Tournoi tournoi =  new Tournoi(rs.getInt("ID"), rs.getString("NOM"),rs.getString("CODE"));
				tournois.add(tournoi);
			}
		}
		catch(Exception exception){
			throw new RuntimeException(exception);	
		}	
		return tournois;
	}
	
	@Override
	public void ajouter(Tournoi tournoi) {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("INSERT INTO tournoi (NOM, CODE) VALUES (?, ?) ");
			
			statement.setString(1,	tournoi.getNom());
			statement.setString(2,	tournoi.getCode());
			
			statement.executeUpdate();
		}
		catch(Exception exception){
			throw new RuntimeException(exception);	
		}	
	}
	
	@Override
	public Tournoi lecture(Long id) {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("SELECT * FROM tournoi WHERE id = ? ");
			
			statement.setLong(1, id);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
			
			Tournoi tournoi = new Tournoi();
			tournoi.setId(rs.getInt("ID"));
			tournoi.setNom(rs.getString("NOM"));
			tournoi.setCode(rs.getString("CODE"));
			
			return tournoi;
			}else {
				return null;
			}
			
		}
		catch(Exception exception){
			throw new RuntimeException(exception);	
		}	
	}
	
	@Override
	public void modifier(Tournoi tournoi) {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("UPDATE tournoi SET NOM=?, CODE=? WHERE ID=?");
			
			statement.setString(1, tournoi.getNom());
			statement.setString(2, tournoi.getCode());
			statement.setInt(3, tournoi.getId());
			
			statement.executeUpdate();
			
			
		}
		catch(Exception exception){
			throw new RuntimeException(exception);	
		}		
	}
	
	@Override
	public void supprimer(Long id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
	        connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris",
					"root", "Mesemu_3"); 
			
            preparedStatement = connexion.prepareStatement("DELETE FROM epreuve WHERE ID_TOURNOI=?");
            connexion.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            
            preparedStatement = connexion.prepareStatement("DELETE FROM tournoi WHERE ID=? ");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
			
			
			
			
//			connexion = daoFactory.getConnection();
//			statement = connexion.prepareStatement("DELETE FROM tournoi WHERE ID=?");
//			
//			statement.setLong(1, id);
//			
//			statement.executeUpdate();
			
		}
		catch(Exception exception){
			throw new RuntimeException(exception);	
		}	
	}
	
	@Override
	public List<Tournoi> rechercher(String chaine) {
		Connection connexion = null;
		PreparedStatement statement = null;
		List<Tournoi> liste = new ArrayList<>();
	
	try {
		connexion = daoFactory.getConnection();
		statement = connexion.prepareStatement("SELECT * FROM tournoi WHERE NOM LIKE? OR CODE LIKE?");
		
		statement.setString(1, "%" + chaine + "%");
		statement.setString(2, "%" + chaine + "%");

		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			
			Tournoi tournoi = new Tournoi();
			tournoi.setId(rs.getInt("ID"));
			tournoi.setNom(rs.getString("NOM"));
			tournoi.setCode(rs.getString("CODE"));
			
			liste.add(tournoi);
		}
		
	}
	catch(Exception exception){
		throw new RuntimeException(exception);	
	}	
		return liste;
	}
	
}
