package com.mycompany.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.RechercheEpreuvesDao;
import com.mycompany.dao.RechercheEpreuvesDaoImpl;
import com.mycompany.dao.RechercheMatchsDaoImpl;

@WebServlet("/rechercheEpreuves")
public class RechercheEpreuves extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RechercheEpreuvesDao rechercheEpreuvesDao;
    public RechercheEpreuves() {
        super();
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	rechercheEpreuvesDao = new RechercheEpreuvesDaoImpl(daoFactory);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("connectedUser") == null){
			response.sendRedirect("/App_joueurs/login");
			return;
		}
	
		if(session.getAttribute("connectedUser") != null){
			if(session.getAttribute("type") != null) {
				String type = (String)session.getAttribute("type");
				int annee = (int)session.getAttribute("année");
				request.setAttribute("joueurs", rechercheEpreuvesDao.rechercheAvanceeEpreuves(type, annee));
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/rechercheEpreuves.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("connectedUser") == null){
			response.sendRedirect("/App_joueurs/login");
			return;
		}

		try {
			String type = request.getParameter("type");
			int annee = Integer.parseInt(request.getParameter("année"));
			
			session.setAttribute("type", type); //Utilisation de la session pour stocker attribut et récupérer dans le doGet
			session.setAttribute("année", annee);
			response.sendRedirect("/App_joueurs/rechercheEpreuves");
			
		}catch(Exception e) {
			request.setAttribute("erreur", e.getMessage());		
			System.out.println(e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/rechercheEpreuves.jsp").forward(request, response);
		}
	}

}
