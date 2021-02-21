package xyz.wendelsegadilha.meuspotify.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.wendelsegadilha.meuspotify.dao.ConnectionFactory;
import xyz.wendelsegadilha.meuspotify.dao.MusicaDao;
import xyz.wendelsegadilha.meuspotify.model.Musica;
import xyz.wendelsegadilha.meuspotify.model.Usuario;

@WebServlet("/uploadmusica")
@MultipartConfig(maxFileSize = 20848820, maxRequestSize = 418018841) // recebe vários tipos de dados
public class UploadMusicaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pagina = "/erro.jsp";
		Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
		if (usuario != null) {
			try {
				String titulo = request.getParameter("txtNomeMusica");
				String artista = request.getParameter("txtArtista");
				String album = request.getParameter("txtAlbum");
				int estilo = Integer.parseInt(request.getParameter("txtEstilo"));
				
				/*
				 * Tratamento do aquivo para upload
				 */
				//pegando o arquivo de entrada
				InputStream arquivoOriginal = request.getPart("arquivoMP3").getInputStream();
				//pegando o nome do arquivo submetido pelo usuário
				String nomeArquivoOriginal = request.getPart("arquivoMP3").getSubmittedFileName();
				//gerando o path para salvar o arquivo no servidor (caminho completo do arquivo)
				String nomeArquivo = getServletContext().getRealPath(File.separator)+File.separator+"musicas"+File.separator+request.getPart("arquivoMP3").getSubmittedFileName();
				//cria o novo arquivo no servidor
				FileOutputStream arquivoMP3 = new FileOutputStream(nomeArquivo);
				byte[] b = new byte[1024]; //tamanho do buffer
				while(arquivoOriginal.available() > 0) {
					arquivoOriginal.read(b); //ler o arquivo original
					arquivoMP3.write(b); //gravo no novo arquivo
				}
				//fecha os streams
				arquivoOriginal.close();
				arquivoMP3.close();
				/*
				 * Fim do processo de upload
				 */
				
				Musica musica = new Musica();
				musica.setTitulo(titulo);
				musica.setArtista(artista);
				musica.setAlbum(album);
				musica.setEstilo(estilo);
				musica.setLinkArquivo("musicas"+File.separator+nomeArquivoOriginal);
				
				ConnectionFactory connectionFactory = new ConnectionFactory();
				MusicaDao musicaDao = new MusicaDao(connectionFactory);
				musicaDao.salvar(musica);
				pagina = "/novamusica.jsp";
				request.setAttribute("okSTR", "Upload realizado com sucesso!");
			}catch (IllegalStateException e) {
				pagina = "/novamusica.jsp";
				request.setAttribute("erroSTR", "Falha no upload. Tamanho do arquivo excedido!");
			}catch (Exception e) {
				pagina = "/novamusica.jsp";
				request.setAttribute("erroSTR", "Falha no upload!");
				e.printStackTrace();
			}
		}else {
			request.setAttribute("erroSTR", "Usuário não conectado!");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);

	}

}
