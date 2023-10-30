package net.unir.eventos.modelo.dao;

import java.util.List;

import net.unir.eventos.modelo.javabean.Evento;

public interface EventoDao {
	// Método que devuelve un evento a partir de su ID
	Evento findById(int idEvento);
	// Método que devuelve una lista con todos los eventos activos
	List<Evento> findActives();
	// Método que devuelve una lista con todos los eventos cancelados
	List<Evento> findNotActives();
	// Método para crear un nuevo evento
	int insert(Evento evento);
	//Método para borrar un evento según su ID
	int delete(int idEvento);
	//Dado un objeto evento, se modifican sus atributos
	int updateOne(Evento evento);
	//Cancelar evento
	int cancelEvent(int idEvento);
}
