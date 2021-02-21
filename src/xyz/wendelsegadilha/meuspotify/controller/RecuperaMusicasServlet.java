package xyz.wendelsegadilha.meuspotify.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.wendelsegadilha.meuspotify.dao.ConnectionFactory;
import xyz.wendelsegadilha.meuspotify.dao.MusicaDao;
import xyz.wendelsegadilha.meuspotify.model.Usuario;

@WebServlet("/recuperamusicas")
public class RecuperaMusicasServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pagina = "/erro.jsp";
		Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");

		if (usuario != null) {
			
			try {
				
				ConnectionFactory connectionFactory = new ConnectionFactory();
				MusicaDao musicaDao = new MusicaDao(connectionFactory);
				List<Object> lista = musicaDao.listar(null);
				if (lista == null) {
					request.setAttribute("erroSTR", "Não há músicas na base de dados!");
				}else {
					String idPlaylist = request.getParameter("idplaylist");
					request.setAttribute("idPlaylist", idPlaylist);
					request.setAttribute("ListaMusicas", lista);
					pagina = "/acervomusicas.jsp";
				}
				connectionFactory.getConnection().close();
				
			}catch (Exception e) {
				e.printStackTrace();
				pagina = "/acervomusicas.jsp";
				request.setAttribute("erroSTR", "Erro ao recuperar músicas!");
			}
			
		} else {
			request.setAttribute("erroSTR", "Usuário não conectado!");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);

	}

}
