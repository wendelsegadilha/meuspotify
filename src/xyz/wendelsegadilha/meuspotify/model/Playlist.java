package xyz.wendelsegadilha.meuspotify.model;

import java.util.List;

public class Playlist {
	
	private int id;
	private String titulo;
	private List<Musica> musicas;
	private Usuario usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public List<Musica> getMusicas() {
		return musicas;
	}
	
	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
