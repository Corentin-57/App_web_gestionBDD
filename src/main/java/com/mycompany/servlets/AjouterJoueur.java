package com.mycompany.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.mycompany.beans.BeansException;
import com.mycompany.beans.Joueur;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDao;
import com.mycompany.dao.JoueurDaoImpl;

@WebServlet("/ajouterJoueur")
public class AjouterJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDao joueurDao;

    public AjouterJoueur() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	joueurDao = new JoueurDaoImpl(daoFactory);
    	super.init();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("connectedUser") == null){
			response.sendRedirect("/App_joueurs/login");
			return;
		}
		if(session.getAttribute("connectedUser") != null){
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterjoueur.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("connectedUser") == null){
			response.sendRedirect("/App_joueurs/login");
			return;
		}
		
		Joueur joueur = new Joueur();
		try {
				joueur.setNom(request.getParameter("txtNom"));
				joueur.setPrenom(request.getParameter("txtPrenom"));
				joueur.setSexe(request.getParameter("opSexe"));
				joueurDao.ajouter(joueur);
				request.setAttribute("joueurs", joueurDao.lister());
				response.sendRedirect("/App_joueurs/listjoueur");
			
		}catch(BeansException e) {
			request.setAttribute("erreur", e.getMessage());		
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterjoueur.jsp").forward(request, response);
		}
	
	}

}
