package com.mycompany.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.Joueur;
import com.mycompany.beans.User;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDao;
import com.mycompany.dao.JoueurDaoImpl;
import com.mycompany.dao.UserDaoImpl;

@WebServlet("/listjoueur")
public class Listjoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDao joueurDao;
       
    public Listjoueur() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	joueurDao = new JoueurDaoImpl(daoFactory);
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("connectedUser") == null){
			response.sendRedirect("/App_joueurs/login");
			return;
		}
		request.setAttribute("joueurs", joueurDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mot = request.getParameter("txtsearch");
		System.out.println(request);
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("connectedUser") == null) {
			response.sendRedirect("/App_joueurs/login");
			return;
		}
		
		if(request.getParameter("action1").equals("Rechercher")) {
			if (joueurDao.rechercher(mot).size() == 0){
				request.setAttribute("nboccurence", 0 );
			}
				request.setAttribute("joueurs", joueurDao.rechercher(mot));
				//request.setAttribute("joueurs", joueurDao.rechercher(mot));
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);
			
			
		}else if(request.getParameter("action1").equals("Deconnexion")) {
			
			session.setAttribute("connectedUser", null);
			response.sendRedirect("/App_joueurs/login");
			return;
		}
		
	}

}
