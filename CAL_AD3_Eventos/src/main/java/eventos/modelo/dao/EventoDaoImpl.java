package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Evento;
import eventos.modelo.repository.EventoRepository;

@Repository
public class EventoDaoImpl implements EventoDao {
	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public Evento crearEvento(Evento evento) {
		try {
			return eventoRepository.save(evento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Evento modificarEvento(Evento evento) {
		try {
			return eventoRepository.save(evento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Evento eliminarEvento(Evento evento) {
		try {
			eventoRepository.delete(evento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		return null;
	}

	@Override
	public Evento buscarUnEvento(int idEvento) {
		return eventoRepository.findById(idEvento).orElse(null);
	}

	@Override
	public List<Evento> buscarEventosActivos() {
		return eventoRepository.findEventoPorEstado("Activo");
	}

	@Override
	public List<Evento> buscarEventosDestacados() {
		return eventoRepository.findEventoDestacado();
	}

	@Override
	public List<Evento> buscarEventosPorTipo(int idTipo) {
		return eventoRepository.findEventoPorTipo(idTipo);
	}

}
