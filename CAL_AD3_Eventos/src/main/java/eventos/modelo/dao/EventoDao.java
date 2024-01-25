package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Evento;

public interface EventoDao {
	Evento crearEvento (Evento evento);
	Evento modificarEvento (Evento evento);
	Evento eliminarEvento (Evento evento);
	Evento buscarUnEvento (int idEvento);
	List<Evento>buscarEventosActivos();
	List<Evento>buscarEventosDestacados();
	List<Evento>buscarEventosPorTipo(int idTipo);
	
}
