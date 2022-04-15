package com.mycompany.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.beans.Joueur;
import com.mycompany.beans.User;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.HashClass;
import com.mycompany.dao.JoueurDaoImpl;
import com.mycompany.dao.UserDaoImpl;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDaoImpl userDaoImpl;
	 
	    
    public Login() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	userDaoImpl = new UserDaoImpl(daoFactory);
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("txtLogin");
		String password = request.getParameter("txtPassword");
		User connectedUser = userDaoImpl.isValidLogin(login, password);
	
		if(connectedUser != null) {
			
			HttpSession session = request.getSession(true); 
			session.setAttribute("connectedUser", connectedUser); //Création d'une session et enregistrement du login
			
			//request.setAttribute("joueurs", joueurDao.lister());
			
//			request.setAttribute("joueurs", joueurDao.lister());
			
			
			//this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);
			//this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);
			response.sendRedirect("/App_joueurs/listjoueur");//Va vers le serveur et execute servlet avant de renvoyer le resultat
		}else {
			int identifiant = 0;
			request.setAttribute("identifiants", identifiant);
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		
//		HttpSession session = request.getSession(true); //Créer une session
//		session.setAttribute("login", login);
//		session.setAttribute("password", password);
		
		
		
//		if (login.equals("co@co") && password.equals("co")) { //Vérifier les identifiants pour la connexion
//			this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);
//			session.setAttribute("isConnected", true);
//			
//		}else {
//			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
//			session.setAttribute("isConnected", false);
//			
//		}
		//doGet(request, response);
	}

}
