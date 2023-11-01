package net.unir.eventos.modelo.dao;

import java.util.List;

import net.unir.eventos.modelo.javabean.Tipo;

/**
 * Esta interfaz define los métodos que deben ser implementados por cualquier
 * clase que quiera proporcionar acceso a los tipos de eventos.
 */
public interface TipoDao {

	/**
	 * Devuelve una lista de tipos de eventos.
	 *
	 * @return Lista de objetos Tipo.
	 */
	List<Tipo> findAll();

	/**
	 * Busca y devuelve un objeto Tipo según su ID.
	 *
	 * @param idTipo ID del tipo de evento a buscar.
	 * @return Objeto Tipo o null si no se encuentra.
	 */
	Tipo findById(int idTipo);
}
