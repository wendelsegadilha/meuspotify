package xyz.wendelsegadilha.meuspotify.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import xyz.wendelsegadilha.meuspotify.model.Musica;

public class MusicaDao implements GenericDao {

	private ConnectionFactory connectionFactory;

	public MusicaDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void salvar(Object o) {
		try {
			if (o instanceof Musica) {
				Musica musica = (Musica) o;
				String SQL = "INSERT INTO tblMusica VALUES (null, ?, ?, ?, ?, ?)";
				PreparedStatement pst = connectionFactory.getConnection().prepareStatement(SQL);
				pst.setString(1, musica.getTitulo());
				pst.setString(2, musica.getArtista());
				pst.setString(3, musica.getAlbum());
				pst.setInt(4, musica.getEstilo());
				pst.setString(5, musica.getLinkArquivo());
				pst.executeUpdate();
				pst.close();
			} else {
				throw new RuntimeException("Objeto inválido!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void editar(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Object> listar(Object o) {

		try {
			String SQL = "SELECT * FROM tblMusica order by titulo";
			PreparedStatement pst = connectionFactory.getConnection().prepareStatement(SQL);
			ResultSet rs = pst.executeQuery();
			List<Object> lista = new ArrayList<Object>();
			while (rs.next()) {
				Musica musica = new Musica();
				musica.setId(rs.getInt("idMusica"));
				musica.setTitulo(rs.getString("titulo"));
				musica.setArtista(rs.getString("artista"));
				musica.setAlbum(rs.getString("album"));
				musica.setEstilo(rs.getInt("estilo"));
				musica.setLinkArquivo(rs.getString("linkArquivo"));
				lista.add(musica);
			}
			return lista;
		} catch (Exception e) {
			System.out.println("Erro ao recuperar acervo de músicas " + e.getMessage()); 
		}
		return null;
	}

}
