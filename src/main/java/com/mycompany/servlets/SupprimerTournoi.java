package com.mycompany.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDaoImpl;
import com.mycompany.dao.TournoiDao;
import com.mycompany.dao.TournoiDaoImpl;

@WebServlet("/supprimerTournoi")
public class SupprimerTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TournoiDao tournoiDao;
    
    public SupprimerTournoi() {
        super();
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	tournoiDao = new TournoiDaoImpl(daoFactory);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		long idL = Long.parseLong(id);
		tournoiDao.supprimer(idL);
		response.sendRedirect("/App_joueurs/listeTournois");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
