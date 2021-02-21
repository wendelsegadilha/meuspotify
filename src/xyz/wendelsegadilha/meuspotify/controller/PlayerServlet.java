package xyz.wendelsegadilha.meuspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.wendelsegadilha.meuspotify.model.Playlist;
import xyz.wendelsegadilha.meuspotify.model.Usuario;

@WebServlet("/player")
public class PlayerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = "/erro.jsp";
		Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");

		if (usuario != null) {
			Playlist playlist = (Playlist) request.getSession().getAttribute("Playlist");
			if(playlist != null) {
				pagina ="/player.jsp";
			}else {
				pagina ="/player.jsp";
				request.setAttribute("erroSTR", "Playlist não encontrada!");
			}
		} else {
			request.setAttribute("erroSTR", "Usuário não conectado!");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);

	}
}
