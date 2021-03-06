package xyz.wendelsegadilha.meuspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.wendelsegadilha.meuspotify.dao.ConnectionFactory;
import xyz.wendelsegadilha.meuspotify.dao.PlaylistDao;
import xyz.wendelsegadilha.meuspotify.model.Playlist;
import xyz.wendelsegadilha.meuspotify.model.Usuario;

@WebServlet("/detalheplaylist")
public class DetalhePlaylistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pagina = "/erro.jsp";
		Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
		
		if (usuario != null) {
			
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				ConnectionFactory connectionFactory = new ConnectionFactory();
				PlaylistDao playlistDao = new PlaylistDao(connectionFactory);
				Playlist playlist = playlistDao.listaPlaylistporId(id);
				if(playlist != null) {
					request.getSession().setAttribute("Playlist", playlist);
					pagina = "/detalheplaylist.jsp";
				}else {
					pagina = "/detalheplaylist.jsp";
					request.setAttribute("erroSTR", "Erro ao recuperar playlist!");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				pagina = "/detalheplaylist.jsp";
				request.setAttribute("erroSTR", "Erro Inesperado!");
			}

		} else {
			request.setAttribute("erroSTR", "Usuário não conectado!");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);

	}

}
