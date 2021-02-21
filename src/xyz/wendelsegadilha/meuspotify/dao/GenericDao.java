package xyz.wendelsegadilha.meuspotify.dao;

import java.util.List;

public interface GenericDao {
	
	public void salvar(Object o);
	public void editar(Object o);
	public void excluir(Object o);
	public List<Object> listar(Object o);

}
