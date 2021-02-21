package xyz.wendelsegadilha.meuspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.wendelsegadilha.meuspotify.dao.UsuarioDao;
import xyz.wendelsegadilha.meuspotify.model.Usuario;

@WebServlet("/efetivacadastro")
public class EfetivaCadastroServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		UsuarioDao usuarioDao = new UsuarioDao();
		usuarioDao.salvar(usuario);
		System.out.println(usuario);
		
		if(usuario.getId() != 0) {
			request.getSession().setAttribute("Usuario", usuario);	
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/minhaconta.jsp");
		dispatcher.forward(request, response);

	}

}
