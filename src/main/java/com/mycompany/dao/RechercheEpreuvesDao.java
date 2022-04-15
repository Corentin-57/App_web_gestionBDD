package com.mycompany.dao;

import java.util.List;

import com.mycompany.beans.Joueur;

public interface RechercheEpreuvesDao {

	List<Joueur> rechercheAvanceeEpreuves(String type, int annee);
}
