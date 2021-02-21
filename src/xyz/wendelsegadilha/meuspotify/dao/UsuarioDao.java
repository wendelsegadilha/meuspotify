package xyz.wendelsegadilha.meuspotify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import xyz.wendelsegadilha.meuspotify.model.Usuario;

public class UsuarioDao implements GenericDao {

	private Connection connection;

	public UsuarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void salvar(Object o) {
		try {
			if (o instanceof Usuario) {
				Usuario usuario = (Usuario) o;
				String SQL = "INSERT INTO tblUsuario VALUES (null, ?, ?, ?)";
				PreparedStatement pst = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, usuario.getNome());
				pst.setString(2, usuario.getEmail());
				pst.setString(3, usuario.getSenha());
				int res = pst.executeUpdate();
				//retorna o id do novo registro
				if (res > 0) {
					ResultSet rs = pst.getGeneratedKeys();
					if (rs.next()) {
						usuario.setId(rs.getInt(1));
					}
					rs.close();
				}else {
					throw new RuntimeException("Não foi possível cadastrar usuário!");
				}
				pst.close();
			} else {
				throw new RuntimeException("Objeto inválido");
			}
		} catch (Exception e) {
			System.out.println("Erro ao salvar usuário: " + e.getMessage());
		}

	}

	@Override
	public void editar(Object o) {

	}

	@Override
	public void excluir(Object o) {

	}

	@Override
	public List<Object> listar(Object o) {

		try {
			if (o instanceof Usuario) {
				Usuario incompleto = (Usuario) o;
				String SQL = "SELECT * FROM tblUsuario WHERE email = ? AND senha = ?";
				PreparedStatement pst = connection.prepareStatement(SQL);
				pst.setString(1, incompleto.getEmail());
				pst.setString(2, incompleto.getSenha());
				ResultSet rs = pst.executeQuery();
				List<Object> result = new ArrayList<Object>();
				while (rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt("idUsuario"));
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("email"));
					usuario.setSenha(rs.getString("senha"));
					result.add(usuario);
				}
				pst.close();
				rs.close();
				return result;
			} else {
				throw new RuntimeException("Objeto inválido");
			}
		} catch (Exception e) {
			System.out.println("Erro ao recuperar usuário " + e.getMessage());
		}
		return null;
	}

}
