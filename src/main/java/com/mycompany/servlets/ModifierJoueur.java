package com.mycompany.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mycompany.beans.BeansException;
import com.mycompany.beans.Joueur;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDao;
import com.mycompany.dao.JoueurDaoImpl;

@WebServlet("/modifierJoueur")
public class ModifierJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private JoueurDao joueurDao;

    public ModifierJoueur() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	joueurDao = new JoueurDaoImpl(daoFactory);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		long idL = Long.parseLong(id);
		Joueur joueur = joueurDao.lecture(idL);
		request.setAttribute("joueur", joueur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierJoueur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Joueur joueur = new Joueur();
		try {
			joueur.setNom(request.getParameter("txtNom"));
			joueur.setPrenom(request.getParameter("txtPrenom"));
			joueur.setSexe(request.getParameter("opSexe"));
			int id = Integer.parseInt(request.getParameter("idJoueur"));
			joueur.setId(id);
			joueurDao.modifier(joueur);		
			response.sendRedirect("/App_joueurs/listjoueur");
		}catch(BeansException e) {
			request.setAttribute("erreur", e.getMessage());	
			this.getServletContext().getRequestDispatcher("/WEB-INF/modifierJoueur.jsp").forward(request, response);
		}
	}

}
