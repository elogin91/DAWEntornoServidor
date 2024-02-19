package net.unir.eventos.modelo.dao;

import java.util.List;

import net.unir.eventos.modelo.javabean.Evento;

/**
 * Esta interfaz define los métodos que deben ser implementados por cualquier
 * clase que quiera proporcionar acceso a los eventos.
 */
public interface EventoDao {

	/**
	 * Busca y devuelve un objeto Evento según su ID.
	 *
	 * @param idEvento ID del evento a buscar.
	 * @return Objeto Evento o null si no se encuentra.
	 */
	Evento findById(int idEvento);

	/**
	 * Devuelve una lista de eventos activos.
	 *
	 * @return Lista de objetos Evento activos.
	 */
	List<Evento> findActives();

	/**
	 * Devuelve una lista de eventos cancelados.
	 *
	 * @return Lista de objetos Evento cancelados.
	 */
	List<Evento> findNotActives();

	/**
	 * Crea un nuevo evento.
	 *
	 * @param evento Objeto Evento a crear.
	 * @return El ID del evento creado.
	 */
	int insert(Evento evento);

	/**
	 * Borra un evento según su ID.
	 *
	 * @param idEvento ID del evento a borrar.
	 * @return El número de filas afectadas (debería ser 1 si se borró
	 *         correctamente).
	 */
	int delete(int idEvento);

	/**
	 * Modifica un evento con los atributos del objeto Evento proporcionado.
	 *
	 * @param evento Objeto Evento con los nuevos valores.
	 * @return El número de filas afectadas (debería ser 1 si se modificó
	 *         correctamente).
	 */
	int updateOne(Evento evento);

	/**
	 * Cancela un evento según su ID.
	 *
	 * @param idEvento ID del evento a cancelar.
	 * @return El número de filas afectadas (debería ser 1 si se canceló
	 *         correctamente).
	 */
	int cancelEvent(int idEvento);
}
