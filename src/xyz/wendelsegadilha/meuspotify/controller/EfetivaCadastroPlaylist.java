package xyz.wendelsegadilha.meuspotify.controller;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/efetivaplaylist")
public class EfetivaCadastroPlaylist extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
		String pagina = "erro.jsp";

		try {
			if (usuario != null) {
				String titulo = request.getParameter("txtNomePlaylist");
				Playlist playlist = new Playlist();
				playlist.setTitulo(titulo);
				playlist.setUsuario(usuario);

				// Grava no banco
				ConnectionFactory connectionFactory = new ConnectionFactory();
				PlaylistDao playlistDao = new PlaylistDao(connectionFactory);
				playlistDao.salvar(playlist);
				connectionFactory.getConnection().close();

				// Atribui a playlist ao usuario logado
				if (usuario.getPlaylists() == null) {
					usuario.setPlaylists(new ArrayList<Playlist>());
				}
				usuario.getPlaylists().add(playlist);

				// atualiza o usuario na sessão
				request.getSession().setAttribute("Usuario", usuario);
				request.setAttribute("okSTR", "Playlist criada com sucesso!");
				pagina = "novaplaylist.jsp";
			} else {
				request.setAttribute("erroSTR", "Usuário não conectado!");
			}
		} catch (Exception e) {
			pagina = "erro.jsp";
			request.setAttribute("erroSTR", "Erro ao cadastrar playlist");
			System.out.println("Erro ao salvar playlist: " + e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);

	}

}
