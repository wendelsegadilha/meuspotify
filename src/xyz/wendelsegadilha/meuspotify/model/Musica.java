package xyz.wendelsegadilha.meuspotify.model;

public class Musica {
	
	private int id;
	private String titulo;
	private String artista;
	private String album;
	private int estilo;
	private String linkArquivo;
	
	public Musica() {
	
	}
	
	public Musica(int id, String titulo, String artista, String album, int estilo, String linkArquivo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.artista = artista;
		this.album = album;
		this.estilo = estilo;
		this.linkArquivo = linkArquivo;
	}

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

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getEstilo() {
		return estilo;
	}

	public void setEstilo(int estilo) {
		this.estilo = estilo;
	}

	public String getLinkArquivo() {
		return linkArquivo;
	}

	public void setLinkArquivo(String linkArquivo) {
		this.linkArquivo = linkArquivo;
	}

}
