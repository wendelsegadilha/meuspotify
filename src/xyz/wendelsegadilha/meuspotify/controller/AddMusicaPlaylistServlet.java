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
import xyz.wendelsegadilha.meuspotify.model.Usuario;

@WebServlet("/addmusicaplaylist")
public class AddMusicaPlaylistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pagina = "/erro.jsp";
		Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");

		if (usuario != null) {
			
			try {
				int idMusica = Integer.parseInt(request.getParameter("idmusica"));
				int idPlaylist = Integer.parseInt(request.getParameter("idplaylist"));
				ConnectionFactory connectionFactory = new ConnectionFactory();
				PlaylistDao playlistDao = new PlaylistDao(connectionFactory);
				boolean resultado =  playlistDao.salvarMusicaPlaylist(idPlaylist, idMusica);
				if(resultado) {
					request.setAttribute("statusSTR", "Adicionada!");
					pagina = "/resultado.jsp";
				}else {
					pagina = "/resultado.jsp";
					request.setAttribute("statusSTR", "Música já na Playlist!");
				}
			}catch (Exception e) {
				e.printStackTrace();
				pagina = "/resultado.jsp";
				request.setAttribute("statusSTR", "Erro ao inserir música na playlist!");
			}

		} else {
			request.setAttribute("erroSTR", "Usuário não conectado!");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);

	}

}
