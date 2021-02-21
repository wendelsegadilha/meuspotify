package xyz.wendelsegadilha.meuspotify.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import xyz.wendelsegadilha.meuspotify.model.Musica;
import xyz.wendelsegadilha.meuspotify.model.Playlist;

public class PlaylistDao implements GenericDao {

	private ConnectionFactory connectionFactory;

	public PlaylistDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void salvar(Object o) {
		try {
			if (o instanceof Playlist) {
				Playlist playlist = (Playlist) o;
				String SQL = "INSERT INTO tblPlaylist VALUES (null, ?, ?)";
				PreparedStatement pst = connectionFactory.getConnection().prepareStatement(SQL,
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, playlist.getTitulo());
				pst.setInt(2, playlist.getUsuario().getId());
				int res = pst.executeUpdate();
				if (res > 0) {
					ResultSet rs = pst.getGeneratedKeys();
					if (rs.next()) {
						playlist.setId(rs.getInt(1));
					}
					rs.close();
				} else {
					throw new RuntimeException("Não foi possível cadastrar playlist!");
				}
				pst.close();
			} else {
				throw new RuntimeException("Objeto inválido!");
			}

		} catch (Exception e) {
			System.out.println("Erro ao salvar playlist: " + e.getMessage());
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
			Integer idUser = (Integer) o;
			String SQL = "SELECT * FROM tblPlaylist WHERE idUsuario = ?";
			PreparedStatement pst = connectionFactory.getConnection().prepareStatement(SQL);
			pst.setInt(1, idUser);
			ResultSet rs = pst.executeQuery();
			ArrayList<Object> list = new ArrayList<Object>();
			while (rs.next()) {
				Playlist playlist = new Playlist();
				playlist.setId(rs.getInt("idPlaylist"));
				playlist.setTitulo(rs.getString("titulo"));
				list.add(playlist);
			}
			rs.close();
			pst.close();
			return list;
		} catch (Exception e) {
			System.out.println("Erro ao recuperar playlist " + e.getMessage());
		}

		return null;
	}

	public Playlist listaPlaylistporId(int id) {
		Playlist playlist = null;
		try {
			String SQL = "select  tblPlaylist.idPlaylist as idPlaylist, "
					+ "		tblPlaylist.idUsuario  as idUsuario, " + "        tblPlaylist.titulo	  as pl_titulo, "
					+ "        tblMusica.idMusica     as idMusica, " + "        tblMusica.titulo       as mu_titulo, "
					+ "        tblMusica.artista      as artista, " + "        tblMusica.album        as album, "
					+ "        tblMusica.estilo       as estilo, " + "        tblMusica.linkArquivo  as linkArquivo "
					+ "from tblPlaylist left outer join tblMusicaPlaylist using (idPlaylist) left outer join tblMusica using (idMusica) where idPlaylist = ?";
			PreparedStatement pst = connectionFactory.getConnection().prepareStatement(SQL);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			do {
				if (playlist == null) {
					playlist = new Playlist();
					playlist.setId(rs.getInt("idPlaylist"));
					playlist.setTitulo(rs.getString("pl_titulo"));
					playlist.setMusicas(new ArrayList<Musica>());
				}
				if (rs.getString("mu_titulo") != null) {
					Musica musica = new Musica();
					musica.setId(rs.getInt("idMusica"));
					musica.setTitulo(rs.getString("mu_titulo"));
					musica.setArtista(rs.getString("artista"));
					musica.setAlbum(rs.getString("album"));
					musica.setEstilo(rs.getInt("estilo"));
					musica.setLinkArquivo(rs.getString("linkArquivo"));
					playlist.getMusicas().add(musica);
				}
			} while (rs.next());
			return playlist;
		} catch (Exception e) {
			System.out.println("Erro ao recuperar playlist " + e.getMessage());
		}
		return null;
	}

	public boolean salvarMusicaPlaylist(int idPlaylist, int idMusica) {

		try {
			String SQL = "INSERT INTO tblMusicaPlaylist VALUES (? ,?)";
			PreparedStatement pst = connectionFactory.getConnection().prepareStatement(SQL);
			pst.setInt(1, idPlaylist);
			pst.setInt(2, idMusica);
			int resultado = pst.executeUpdate();
			if (resultado == 1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Erro ao adicionar música na playlist" + e.getMessage());
		}

		return false;
	}

}
