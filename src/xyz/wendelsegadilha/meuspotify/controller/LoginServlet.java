package xyz.wendelsegadilha.meuspotify.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.wendelsegadilha.meuspotify.dao.UsuarioDao;
import xyz.wendelsegadilha.meuspotify.model.Usuario;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Usuario incompleto =  new Usuario();
		incompleto.setEmail(email);
		incompleto.setSenha(senha);
		String pagina = "/index.jsp";
		
		UsuarioDao usuarioDao = new UsuarioDao();
		List<Object> resultado = usuarioDao.listar(incompleto);
		if(resultado != null && resultado.size() > 0) {
			Usuario usuario = (Usuario)resultado.get(0);
			pagina = "/minhaconta.jsp";
			request.getSession().setAttribute("Usuario", usuario);	
		}else {
			request.setAttribute("erroSTR", "E-mail/Senha inválidos!");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);

	}

}
