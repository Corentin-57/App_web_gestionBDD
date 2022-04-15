package com.mycompany.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mycompany.beans.Joueur;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDao;
import com.mycompany.dao.JoueurDaoImpl;

@WebServlet("/supprimerJoueur")
public class SupprimerJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDao joueurDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	joueurDao = new JoueurDaoImpl(daoFactory);
	}
       

    public SupprimerJoueur() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		long idL = Long.parseLong(id);
		joueurDao.supprimer(idL);
		//this.getServletContext().getRequestDispatcher("/listjoueur").forward(request, response);
		response.sendRedirect("/App_joueurs/listjoueur");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
