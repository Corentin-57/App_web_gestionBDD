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
import com.mycompany.beans.Tournoi;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDaoImpl;
import com.mycompany.dao.TournoiDao;
import com.mycompany.dao.TournoiDaoImpl;

@WebServlet("/ajouterTournoi")
public class AjouterTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TournoiDao tournoiDao;

    public AjouterTournoi() {
        super();
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	tournoiDao = new TournoiDaoImpl(daoFactory);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("connectedUser") == null){
			response.sendRedirect("/App_joueurs/login");
			return;
		}
		if(session.getAttribute("connectedUser") != null){
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterTournoi.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("connectedUser") == null){
			response.sendRedirect("/App_joueurs/login");
			return;
		}
		
		Tournoi tournoi = new Tournoi();
		try {
				tournoi.setNom(request.getParameter("txtNom"));
				tournoi.setCode(request.getParameter("txtCode"));
				tournoiDao.ajouter(tournoi);
				request.setAttribute("tournois", tournoiDao.lister());
				response.sendRedirect("/App_joueurs/listeTournois");
			
		}catch(BeansException e) {
			request.setAttribute("erreur", e.getMessage());		
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterTournoi.jsp").forward(request, response);
		}
	}

}
