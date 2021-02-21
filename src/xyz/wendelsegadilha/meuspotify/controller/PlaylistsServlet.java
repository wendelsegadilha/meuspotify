package xyz.wendelsegadilha.meuspotify.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/playlists")
public class PlaylistsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pagina = "erro.jsp";

		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
			if (usuario != null) {
				//if(usuario.getPlaylists() == null) {
					ConnectionFactory connectionFactory = new ConnectionFactory();
					PlaylistDao playlistDao = new PlaylistDao(connectionFactory);
					List<Object> lista = playlistDao.listar(usuario.getId());
					connectionFactory.getConnection().close();
					if(lista != null) {
						ArrayList<Playlist> minhasPlaylists = new ArrayList<Playlist>();
						for (Object o : lista) {
							Playlist novaPl = (Playlist) o;
							novaPl.setUsuario(usuario);
							minhasPlaylists.add(novaPl);
						}
						usuario.setPlaylists(minhasPlaylists);
						request.getSession().setAttribute("Usuario", usuario);
						pagina = "/minhasplaylits.jsp";
					}
				//}
			}else {
				request.setAttribute("erroSTR", "Usuário não conectado!");
			}

		} catch (Exception e) {
			request.setAttribute("erroSTR", "Erro ao recuperar playlists");
			System.out.println("Erro ao salvar playlist: " + e.getMessage());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);

	}

}
