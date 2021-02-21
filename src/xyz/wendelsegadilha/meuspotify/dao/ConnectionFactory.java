package xyz.wendelsegadilha.meuspotify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private String servidor;
	private String usuario;
	private String senha;
	private String banco;
	private Connection connection;

	public ConnectionFactory() {
		this.servidor = "localhost";
		this.usuario = "root";
		this.senha = "root";
		this.banco = "meuspotify";
		String URL = "jdbc:mysql://" + servidor + ":3306/" + banco + "?useTimezone=true&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(URL, usuario, senha);
			System.out.println("Conectou!!!");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro de conex�o com o banco de dados: " + e.getMessage());
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

}
