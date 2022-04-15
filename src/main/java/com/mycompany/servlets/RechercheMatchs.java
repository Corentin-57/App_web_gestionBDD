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
import com.mycompany.dao.RechercheMatchsDao;
import com.mycompany.dao.RechercheMatchsDaoImpl;

@WebServlet("/rechercheMatchs")
public class RechercheMatchs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RechercheMatchsDao rechercheMatchsDao;
	//JoueurDao joueurDao;
       
    public RechercheMatchs() {
        super();
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	rechercheMatchsDao = new RechercheMatchsDaoImpl(daoFactory);
//    	DaoFactory daoFactory2 = DaoFactory.getInstance();
//    	joueurDao = new JoueurDaoImpl(daoFactory2);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("connectedUser") == null){
			response.sendRedirect("/App_joueurs/login");
			return;
		}
		if(session.getAttribute("connectedUser") != null){

			if(session.getAttribute("statut") != null) {
				String statut = (String)session.getAttribute("statut");
				String champRecherche = (String)session.getAttribute("champRecherche");
				
				if(champRecherche != null) {
					request.setAttribute("joueurs", rechercheMatchsDao.rechercheAvanceeMatchs(statut, champRecherche));
				}else {
					request.setAttribute("joueurs", rechercheMatchsDao.listeVainqueursFinalistes(statut));
				}	
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/rechercheMatchs.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("connectedUser") == null){
			response.sendRedirect("/App_joueurs/login");
			return;
		}

		try {
			String statut = request.getParameter("statut");
			String champRecherche = request.getParameter("txtsearch");
			session.setAttribute("statut", statut); //Utilisation de la session pour stocker attribut et récupérer dans le doGet
			session.setAttribute("champRecherche", champRecherche);
			response.sendRedirect("/App_joueurs/rechercheMatchs");
			
		}catch(Exception e) {
			request.setAttribute("erreur", e.getMessage());		
			System.out.println(e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/rechercheMatchs.jsp").forward(request, response);
		}
	}

}
