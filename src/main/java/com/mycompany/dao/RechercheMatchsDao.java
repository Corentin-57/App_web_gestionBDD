package com.mycompany.dao;

import java.util.List;

import com.mycompany.beans.Joueur;

public interface RechercheMatchsDao {
	List<Joueur> listeVainqueursFinalistes(String statut);
	
	List<Joueur> rechercheAvanceeMatchs(String statut, String chaine);
}
